package jpdr.apps.devsu.apidemo.controllers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
 
import java.util.HashMap;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
 
import jpdr.apps.devsu.apidemo.dtos.MovimientoDTO;
import jpdr.apps.devsu.apidemo.dtos.helpers.FieldDictionaryDTO;
import jpdr.apps.devsu.apidemo.exceptions.CamposConDatosInvalidosException;
import jpdr.apps.devsu.apidemo.exceptions.FechaInvalidaException;
import jpdr.apps.devsu.apidemo.exceptions.NombreDeCampoInvalidoException;
import jpdr.apps.devsu.apidemo.exceptions.SolicitudVaciaException;
import jpdr.apps.devsu.apidemo.log.ControllerResponseLogger;
import jpdr.apps.devsu.apidemo.services.MovimientosService;


@RestController
@RequestMapping("/apidemo/movimientos")
public class MovimientosController implements BasicController<MovimientoDTO> {
	
	
	@Autowired
	private MovimientosService movimientosService;
	
	@Autowired
	private FieldDictionaryDTO fieldDictionaryDTO;

	@Autowired
	private ControllerResponseLogger controllerResponseLogger;
	
	@Override
	@PostMapping
	public ResponseEntity<MovimientoDTO> post(@Valid @RequestBody MovimientoDTO movimientoDTO) {		
		
		movimientoDTO = movimientosService.save(0,movimientoDTO);		
		
		ResponseEntity<MovimientoDTO> response = ResponseEntity.status(HttpStatus.CREATED).body(movimientoDTO);
		controllerResponseLogger.log(this.getClass(), "post", response);
		return response;
		
	}

	
	@Override
	@GetMapping("/{movimientoId}")
	public ResponseEntity<MovimientoDTO> get(@PathVariable int movimientoId) {
		
		MovimientoDTO movimientoDTO = movimientosService.get(movimientoId);		
 
		
		ResponseEntity<MovimientoDTO> response = ResponseEntity.ok(movimientoDTO);
		controllerResponseLogger.log(this.getClass(), "get", response);
		return response;
 
	}

	
	@Override
	@PutMapping("/{movimientoId}")
	public ResponseEntity<MovimientoDTO> put(@PathVariable int movimientoId, @Valid @RequestBody MovimientoDTO movimientoDTO) {
		
		HttpStatusCode httpStatusCode = HttpStatus.OK;
		
		if (!movimientosService.exists(movimientoId))
			httpStatusCode = HttpStatus.CREATED;
				
		movimientoDTO = movimientosService.save(movimientoId, movimientoDTO);
 		
		ResponseEntity<MovimientoDTO> response = ResponseEntity.status(httpStatusCode).body(movimientoDTO);
		controllerResponseLogger.log(this.getClass(), "put", response);
		return response;
		
	}

	
	@Override
	@PatchMapping("/{movimientoId}")
	public ResponseEntity<MovimientoDTO> patch(@PathVariable int movimientoId, @RequestBody HashMap<String, String> entries) {
		
		if(entries.isEmpty())
			throw new SolicitudVaciaException();
		
		for (Entry<String, String> entry : entries.entrySet()) {
			
			if(!fieldDictionaryDTO.validateJSONProperty(MovimientoDTO.class, entry.getKey()))
				throw new NombreDeCampoInvalidoException(entry.getKey());			

			try {					

				if(entry.getKey().equals("Fecha")) {					
					SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
					dateFormat.setLenient(false);
					dateFormat.parse(entry.getValue());										
				}
									
				if(entry.getKey().equals("Saldo Inicial"))
					Double.parseDouble(entry.getValue());

				if(entry.getKey().equals("Valor"))
					Double.parseDouble(entry.getValue());
				
				if(entry.getKey().equals("Saldo Disponible"))
					Double.parseDouble(entry.getValue());
				
				if(entry.getKey().equals("ID Cliente"))
					Integer.parseInt(entry.getValue());
					
				}catch (NumberFormatException e) {
					CamposConDatosInvalidosException exception = new CamposConDatosInvalidosException();
					exception.addError("El campo '"+ entry.getKey()+"' tiene un valor inválido", entry.getValue() );
					throw exception;
				}catch (ParseException e) {
					throw new FechaInvalidaException(entry.getValue());
				}
				
		}
		
		MovimientoDTO movimientoDTO = movimientosService.patch(movimientoId, entries);
		
		ResponseEntity<MovimientoDTO> response = ResponseEntity.status(HttpStatus.OK).body(movimientoDTO);
		controllerResponseLogger.log(this.getClass(), "patch", response);
		return response;
		
	}

	
	@Override
	@DeleteMapping("/{movimientoId}")
	public ResponseEntity<MovimientoDTO> delete(@PathVariable int movimientoId) {
				
		movimientosService.delete(movimientoId);		
		
		ResponseEntity<MovimientoDTO> response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		controllerResponseLogger.log(this.getClass(), "delete", response);
		return response;

	}

}
