package jpdr.apps.devsu.apidemo.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;

import jpdr.apps.devsu.apidemo.dtos.CuentaDTO;
import jpdr.apps.devsu.apidemo.entities.Cliente;
import jpdr.apps.devsu.apidemo.entities.Cuenta;
import jpdr.apps.devsu.apidemo.exceptions.CamposConDatosInvalidosException;
import jpdr.apps.devsu.apidemo.exceptions.CuentaNoExisteException;
import jpdr.apps.devsu.apidemo.exceptions.NumeroDeCuentaYaExisteException;
import jpdr.apps.devsu.apidemo.exceptions.SaldoNoDisponibleException;
import jpdr.apps.devsu.apidemo.mappers.CuentasMapper;
import jpdr.apps.devsu.apidemo.repositories.CuentasRepository;

@Service
public class CuentasService implements BasicService<Cuenta, CuentaDTO> {
	
	@Autowired
	private CuentasRepository cuentasRepository;
	
	@Autowired
	private ClientesService clientesService;
	
	@Autowired 
	private Validator validator;
	
	
	@Override
	public boolean exists(int entityId) {
		return cuentasRepository.existsById(entityId);
	}
	
	
	@Override
	public CuentaDTO get(int entityId) {
		
		Cuenta cuenta = cuentasRepository.findById(entityId).orElseThrow(
				() -> new CuentaNoExisteException());
				
		CuentaDTO cuentaDTO = CuentasMapper.getInstance().fromEntity(cuenta);		
		cuentaDTO.setClienteId(cuenta.getCliente().getId());
		
		return cuentaDTO;
		
	}	
	
	@Override
	public CuentaDTO save(int cuentaId, CuentaDTO dto) {
		
		dto.setCuentaId(cuentaId);
		
		Cliente cliente = clientesService.getEntity(dto.getClienteId());
		
		if(cuentasRepository.existsByNumeroCuentaAndCuentaIdNot(dto.getNumeroCuenta(), cuentaId))
			throw new NumeroDeCuentaYaExisteException();
		
		Cuenta cuenta = CuentasMapper.getInstance().fromDTO(dto);
		cuenta.setCliente(cliente);
		
		cuentasRepository.save(cuenta);
		
		dto.setCuentaId(cuenta.getCuentaId());	
		
		return dto;
	}

	
	@Override
	public CuentaDTO patch(int cuentaId, HashMap<String, String> entries) {
		
		Cuenta cuenta = cuentasRepository.findById(cuentaId).orElseThrow(
				() -> new CuentaNoExisteException());
		
		Cliente cliente = cuenta.getCliente();
		
		CuentaDTO cuentaDTO = CuentasMapper.getInstance().fromEntity(cuenta);
		cuentaDTO.setClienteId(cliente.getId());
		
		for ( Entry<String, String> entry : entries.entrySet()) {
						
			switch (entry.getKey()) {
				
				case "Numero Cuenta": {
					int numeroCuentaNuevo = Integer.parseInt(entry.getValue());					
					if(cuentasRepository.existsByNumeroCuentaAndCuentaIdNot(numeroCuentaNuevo, cuentaId))
						throw new NumeroDeCuentaYaExisteException();
					
					cuentaDTO.setNumeroCuenta( numeroCuentaNuevo );
					break;
				}
				case "Tipo": {
					cuentaDTO.setTipoCuenta(entry.getValue());
					break;
				}
				case "Saldo Inicial":{
					cuentaDTO.setSaldoInicial( Double.parseDouble(entry.getValue()));					
					break;
				}				
				case "Estado":{
					cuentaDTO.setEstado(entry.getValue());
					break;
				}
				case "ID Cliente":{																					
					cliente = clientesService.getEntity( Integer.parseInt(entry.getValue()));					
					cuentaDTO.setClienteId(cliente.getId());						
					break;					
				}
				
			}
			
		}
		
		Set<ConstraintViolation<CuentaDTO>> violations = validator.validate(cuentaDTO);
		
		if (violations.size()>0) {			
			CamposConDatosInvalidosException exception = new CamposConDatosInvalidosException();		
			for ( ConstraintViolation<CuentaDTO> constraintViolation : violations ) {
			
				exception.addError(
						constraintViolation.getMessage(), 						
						String.valueOf(constraintViolation.getInvalidValue()));					
			}			
			throw exception;
		}	
		
		cuenta = CuentasMapper.getInstance().fromDTO(cuentaDTO);
		cuenta.setCliente(cliente);
							
		cuentasRepository.save(cuenta);		
		
		return cuentaDTO;
	}


	@Override
	public void delete(int cuentaId) {
		
		if(!cuentasRepository.existsById(cuentaId))
			throw new CuentaNoExisteException();	
		
		cuentasRepository.deleteById(cuentaId);		
		
	}	
	
	
	@Override
	public Cuenta getEntity (int cuentaId) {
		
		return cuentasRepository.findById(cuentaId).orElseThrow(
				() -> new CuentaNoExisteException() );
		
	}
	
		
	public void debitar(int cuentaId, double importe) {
				
		Cuenta cuenta = cuentasRepository.findById(cuentaId).orElseThrow(
				() -> new CuentaNoExisteException() );
		
		
		long disponibleTemp =	(long) (cuenta.getSaldoInicial() * 100) ;
		disponibleTemp -= (long) (importe * 100);

		double nuevoDisponible = (double) disponibleTemp / 100;
		
		if (nuevoDisponible < 0)
			throw new SaldoNoDisponibleException(cuenta.getSaldoInicial());
		
		cuenta.setSaldoInicial(nuevoDisponible);
		
		cuentasRepository.save(cuenta);
		
	}
	
	public void acreditar(int cuentaId, double importe) {
		
		Cuenta cuenta = cuentasRepository.findById(cuentaId).orElseThrow(
				() -> new CuentaNoExisteException() );
		 			
		long disponibleTemp =	(long) (cuenta.getSaldoInicial() * 100) ;
		disponibleTemp += (long) (importe * 100);
		
		double nuevoDisponible = (double) disponibleTemp / 100;
		
		cuenta.setSaldoInicial(  nuevoDisponible );
		
		cuentasRepository.save(cuenta);
	}

 
}

