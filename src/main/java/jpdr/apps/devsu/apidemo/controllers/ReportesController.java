package jpdr.apps.devsu.apidemo.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
 
import jpdr.apps.devsu.apidemo.dtos.ReporteDTO;
import jpdr.apps.devsu.apidemo.exceptions.CamposConDatosInvalidosException;
import jpdr.apps.devsu.apidemo.log.ControllerResponseLogger;
import jpdr.apps.devsu.apidemo.services.ReportesService;


@RestController
@RequestMapping("/apidemo/reportes")
public class ReportesController {
	
	
	@Autowired
	private ReportesService reportesService;
	
	@Autowired
	private ControllerResponseLogger controllerResponseLogger;
	
	
	@GetMapping
	public ResponseEntity<ReporteDTO> get(	
			@Valid @RequestParam(name = "fecha") 
			@NotBlank 
			@Pattern(regexp = "(0[1-9]|[12][0-9]|[3][01])/(0[1-9]|1[012])/\\d{4}-(0[1-9]|[12][0-9]|[3][01])/(0[1-9]|1[012])/\\d{4}", 
				message = "El rango de fechas defindo en el parámetro 'fecha' es inválido. El formato esperado es 'dd/mm/aaaa-dd/mm/aaaa'")
			String rangoFechas, 
			@Valid 
			@RequestParam(name = "clienteId") 
			@Positive 
			int clienteId ) {
		
		
		String[] fechasSeparadas = rangoFechas.split("-");
		
		boolean fechasValidas = true;
		
		if (fechasSeparadas.length == 2) {			
			try {				
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
				dateFormat.setLenient(false);
				Date dateDesde = dateFormat.parse(fechasSeparadas[0]);
				Date dateHasta = dateFormat.parse(fechasSeparadas[1]);
				if (dateDesde.compareTo(dateHasta) > 0) {
					CamposConDatosInvalidosException exception = new CamposConDatosInvalidosException();						
					exception.addError(
							"El rango de fechas definido en el parámetro 'fecha' es inválido. La primera fecha no puede ser mayor a la segunda.", 									
							rangoFechas);															
					throw ( exception );				
				}
			}catch (ParseException e) {
				fechasValidas = false;
			}						
		}else {
			fechasValidas = false;
		}
		
		if(!fechasValidas) {
			CamposConDatosInvalidosException exception = new CamposConDatosInvalidosException();						
			exception.addError(
					"El rango de fechas definido en el parámetro 'fecha' es inválido. El formato esperado es 'dd/mm/aaaa-dd/mm/aaaa'", 									
					rangoFechas);															
			throw ( exception );						
		}		
						
		ReporteDTO reporteDTO = reportesService.get(fechasSeparadas[0], fechasSeparadas[1], clienteId);
						
		ResponseEntity<ReporteDTO> response = ResponseEntity.status(HttpStatus.OK).body(reporteDTO);
		controllerResponseLogger.log(this.getClass(), "get", response);
		return response;
				
	}
	

}
