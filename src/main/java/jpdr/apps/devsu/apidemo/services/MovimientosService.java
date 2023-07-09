package jpdr.apps.devsu.apidemo.services;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import jpdr.apps.devsu.apidemo.dtos.MovimientoDTO;
import jpdr.apps.devsu.apidemo.dtos.validations.ValidateAsDecimal;
import jpdr.apps.devsu.apidemo.entities.Cuenta;
import jpdr.apps.devsu.apidemo.entities.Movimiento;
import jpdr.apps.devsu.apidemo.entities.Parametro;
import jpdr.apps.devsu.apidemo.exceptions.CamposConDatosInvalidosException;
import jpdr.apps.devsu.apidemo.exceptions.CupoDiarioExcedidoException;
import jpdr.apps.devsu.apidemo.exceptions.MovimientoNoExisteException;
import jpdr.apps.devsu.apidemo.mappers.MovimientosMapper;
import jpdr.apps.devsu.apidemo.repositories.MovimientosRepository;


@Service
public class MovimientosService implements BasicService<Movimiento, MovimientoDTO>{

	
	@Autowired
	private MovimientosRepository movimientosRepository;
	
	@Autowired
	private CuentasService cuentasService;
	
	@Autowired
	private ParametrosService parametrosService;
	
	@Autowired
	private Validator validator;

	
	@Override
	public boolean exists(int entityId) {		
		return movimientosRepository.existsById(entityId);
	}

	
	@Override
	public MovimientoDTO get(int entityId) {
		
		Movimiento movimiento = movimientosRepository.findById(entityId).orElseThrow(
				() -> new MovimientoNoExisteException() );
		
		MovimientoDTO movimientoDTO = MovimientosMapper.getInstance().fromEntity(movimiento);
		movimientoDTO.setCuentaId(movimiento.getCuenta().getCuentaId());
		
		return movimientoDTO;
		
	}
	

	@Override
	public MovimientoDTO save(int movimientoId, MovimientoDTO dto) {
		
		dto.setMovimientoId(movimientoId);
		
		String[] partesFecha = dto.getFecha().split("/");						
		String fechaEntity = String.join("/", partesFecha[2],partesFecha[1],partesFecha[0]);
		
		Cuenta cuenta = cuentasService.getEntity(dto.getCuentaId());

		Movimiento movimiento = new Movimiento();	
		
		if(movimientoId==0) {
		
			double saldoAnterior = cuentasService.getEntity(dto.getCuentaId()).getSaldoInicial();
			
			if (dto.getTipoMovimiento().equals("Retiro")) {			
				controlRetiros(fechaEntity, dto.getCuentaId() , dto.getValor());			
				cuentasService.debitar(dto.getCuentaId(), dto.getValor());
			}else {			
				cuentasService.acreditar(dto.getCuentaId(), dto.getValor());			
			}		
			movimiento.setMovimientoId(movimientoId);
			movimiento.setFecha(fechaEntity);
			movimiento.setTipoMovimiento(dto.getTipoMovimiento());
			movimiento.setCuenta(cuenta);				
			movimiento.setSaldoInicial(saldoAnterior);		
			movimiento.setSaldoDisponible(cuenta.getSaldoInicial());
			movimiento.setValor(dto.getValor());	
			
		}else {		
			
			movimiento = MovimientosMapper.getInstance().fromDTO(dto);
			movimiento.setCuenta(cuenta);
			
		}
		
		movimiento = movimientosRepository.save(movimiento);
		
		MovimientoDTO movimientoDTO = MovimientosMapper.getInstance().fromEntity(movimiento);
		movimientoDTO.setCuentaId(movimiento.getCuenta().getCuentaId());
		
		return movimientoDTO;
		
	}
	

	@Override
	public MovimientoDTO patch(int entityId, HashMap<String, String> entries) {

		Movimiento movimiento = movimientosRepository.findById(entityId).orElseThrow(
				() -> new MovimientoNoExisteException() );		
		
		
		Cuenta cuenta = movimiento.getCuenta();		
		MovimientoDTO movimientoDTO = MovimientosMapper.getInstance().fromEntity(movimiento);
		movimientoDTO.setCuentaId(cuenta.getCuentaId());
		
		for ( Entry<String, String> entry : entries.entrySet()) {
		
			switch (entry.getKey()) {
				
				case "Fecha": {
					movimientoDTO.setFecha(entry.getValue());
					break;
				}
				
				case "Tipo": {
					movimientoDTO.setTipoMovimiento(entry.getValue());
					break;
				}
				
				case "Saldo Inicial":{
					movimientoDTO.setSaldoInicial( Double.parseDouble(entry.getValue() ) );					
					break;
				}
				
				case "Valor":{					
					movimientoDTO.setValor( Double.parseDouble(entry.getValue() ) );					
					break;
				}
				
				case "Saldo Disponible":{
					movimientoDTO.setSaldoDisponible( Double.parseDouble(entry.getValue() ) );					
					break;
				}				

				case "ID Cuenta":{																					
					cuenta = cuentasService.getEntity(Integer.parseInt(entry.getValue()));					
					movimientoDTO.setCuentaId(cuenta.getCuentaId());						
					break;					
				}
				
			}
			
		}
		
		Set<ConstraintViolation<MovimientoDTO>> violations = validator.validate(movimientoDTO);
		
		if (violations.size()>0) {			
			CamposConDatosInvalidosException exception = new CamposConDatosInvalidosException();		
			for ( ConstraintViolation<MovimientoDTO> constraintViolation : violations ) {
			
				exception.addError(
						constraintViolation.getMessage(), 						
						String.valueOf(constraintViolation.getInvalidValue()));
				
			}			
			throw exception;
		}	
		
		movimiento = MovimientosMapper.getInstance().fromDTO(movimientoDTO);
		movimiento.setCuenta(cuenta);
							
		movimientosRepository.save(movimiento);		
		
		return movimientoDTO;
	}

	
	@Override
	public void delete(int movimientoId) {
		
		if(!movimientosRepository.existsById(movimientoId))
			throw new MovimientoNoExisteException();	
		
		movimientosRepository.deleteById(movimientoId);		
		
	}
	

	@Override
	public Movimiento getEntity(int movimientoId) {

		return movimientosRepository.findById(movimientoId).orElseThrow(
				() -> new MovimientoNoExisteException() );
		
	}
	
	
	private void controlRetiros(String fecha, int cuentaId, double valor) {
		 
		long totalRetirosTemp  = (long) (movimientosRepository.sumRetirosByFecha(fecha, cuentaId) * 100);
		long valorTemp = (long) valor * 100;
		long topeDiarioTemp = (long) (parametrosService.getDoubleValue("TOPE") * 100);
				
		if (totalRetirosTemp + valorTemp > topeDiarioTemp ) {
			double saldoRetiros = (double) totalRetirosTemp / 100 ;
			throw new CupoDiarioExcedidoException(saldoRetiros);
		}
			
		 
	 }
	
	
}
