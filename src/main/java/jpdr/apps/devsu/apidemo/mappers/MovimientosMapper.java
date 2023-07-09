package jpdr.apps.devsu.apidemo.mappers;

import jpdr.apps.devsu.apidemo.dtos.MovimientoDTO;
import jpdr.apps.devsu.apidemo.entities.Movimiento;


public class MovimientosMapper implements BasicMapper<Movimiento, MovimientoDTO>{
	
	private static volatile MovimientosMapper instance;
	
	private MovimientosMapper() {}
	
	public static MovimientosMapper getInstance() {		
		
		if (instance==null) { 
			synchronized (MovimientosMapper.class) {
				if (instance==null) 
					instance = new MovimientosMapper();					
			}
		}
		return instance;				
	}

	@Override
	public Movimiento fromDTO(MovimientoDTO dto) {

		Movimiento movimiento = new Movimiento();
		movimiento.setMovimientoId(dto.getMovimientoId());						
		String[] partesFecha = dto.getFecha().split("/");						
		movimiento.setFecha(String.join("/", partesFecha[2],partesFecha[1],partesFecha[0]));			
		movimiento.setTipoMovimiento(dto.getTipoMovimiento());
		movimiento.setValor(dto.getValor());
		movimiento.setSaldoDisponible(dto.getSaldoDisponible());	
		movimiento.setSaldoInicial(dto.getSaldoInicial());
			
		return movimiento;
			
	}

	@Override
	public MovimientoDTO fromEntity(Movimiento entity) {
		
		MovimientoDTO movimientoDTO = new MovimientoDTO();
		movimientoDTO.setMovimientoId(entity.getMovimientoId());
		String[] partesFecha = entity.getFecha().split("/");						
		movimientoDTO.setFecha(String.join("/", partesFecha[2],partesFecha[1],partesFecha[0]));
		movimientoDTO.setTipoMovimiento(entity.getTipoMovimiento());
		movimientoDTO.setValor(entity.getValor());
		movimientoDTO.setSaldoDisponible(entity.getSaldoDisponible());
		movimientoDTO.setSaldoInicial(entity.getSaldoInicial());
			
		return movimientoDTO;				
		
	}

}
