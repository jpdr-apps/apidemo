package jpdr.apps.devsu.apidemo.services;


import java.util.ArrayList;
import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jpdr.apps.devsu.apidemo.dtos.ReporteDTO;
import jpdr.apps.devsu.apidemo.dtos.ReporteDTOItem;
import jpdr.apps.devsu.apidemo.entities.Cliente;
import jpdr.apps.devsu.apidemo.entities.Cuenta;
import jpdr.apps.devsu.apidemo.entities.Movimiento;
import jpdr.apps.devsu.apidemo.exceptions.ClienteNoExisteException;
import jpdr.apps.devsu.apidemo.repositories.ClientesRepository;
import jpdr.apps.devsu.apidemo.repositories.MovimientosRepository;


@Service
public class ReportesService {
	
	
	@Autowired
	private ClientesRepository clientesRepository;
			
	@Autowired
	private MovimientosRepository movimientosRepository;
	
	
	public ReporteDTO get(String fechaDesde, String fechaHasta, int clienteId){
		
		Cliente cliente = clientesRepository.findById(clienteId).orElseThrow(
				() -> new ClienteNoExisteException());
		
		String[] partesFecha = fechaDesde.split("/");						
		String fechaDesdeEntity = String.join("/", partesFecha[2],partesFecha[1],partesFecha[0]);		
		
		partesFecha = fechaHasta.split("/");						
		String fechaHastaEntity = String.join("/", partesFecha[2],partesFecha[1],partesFecha[0]);
		
		List<ReporteDTOItem> detalleMovimientos = new ArrayList<ReporteDTOItem>();
		
		for( Cuenta cuenta : cliente.getCuentasCliente()) {
			
			List<Movimiento> movimientos = movimientosRepository.findByFechaBetweenAndCuenta_CuentaIdOrderByFechaAscMovimientoIdAsc(fechaDesdeEntity, fechaHastaEntity, cuenta.getCuentaId());
			
			for ( Movimiento movimiento : movimientos ) {
				
				ReporteDTOItem item = new ReporteDTOItem();					
					partesFecha = movimiento.getFecha().split("/");						
					item.setFecha(String.join("/", partesFecha[2],partesFecha[1],partesFecha[0]));
					item.setNombreCliente(cuenta.getCliente().getNombre());
					item.setNumeroCuenta(cuenta.getNumeroCuenta());
					item.setTipoCuenta(cuenta.getTipoCuenta());
					item.setSaldoInicial(movimiento.getSaldoInicial());
					item.setTipoMovimiento(movimiento.getTipoMovimiento());
					item.setValor(movimiento.getValor());
					item.setSaldoDisponible(movimiento.getSaldoDisponible());
					item.setEstado(Boolean.toString(cuenta.getEstado()));
				
				detalleMovimientos.add(item);
			}
			
		}
		
		ReporteDTO reporteDTO = new ReporteDTO();
		reporteDTO.setTituloReporte("Estado de Cuenta");
		reporteDTO.setClienteId(cliente.getId());
		reporteDTO.setNombreCliente(cliente.getNombre());
		reporteDTO.setFechaDesde(fechaDesde);
		reporteDTO.setFechaHasta(fechaHasta);
		reporteDTO.setCantidadMovimientos(detalleMovimientos.size());
		reporteDTO.setDetalleMovimientos(detalleMovimientos);
		
		return reporteDTO;
		
	}
	

}
