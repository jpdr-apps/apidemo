package jpdr.apps.devsu.apidemo.controllers;

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

import jpdr.apps.devsu.apidemo.dtos.CuentaDTO;
import jpdr.apps.devsu.apidemo.dtos.helpers.FieldDictionaryDTO;
import jpdr.apps.devsu.apidemo.exceptions.CamposConDatosInvalidosException;
import jpdr.apps.devsu.apidemo.exceptions.NombreDeCampoInvalidoException;
import jpdr.apps.devsu.apidemo.exceptions.SolicitudVaciaException;
import jpdr.apps.devsu.apidemo.log.ControllerResponseLogger;
import jpdr.apps.devsu.apidemo.services.CuentasService;

@RestController
@RequestMapping("/apidemo/cuentas")
public class CuentasController implements BasicController<CuentaDTO>{

	@Autowired
	private CuentasService cuentasService;
	
	@Autowired
	private FieldDictionaryDTO fieldDictionaryDTO;
	
	@Autowired
	private ControllerResponseLogger controllerResponseLogger;
	
	
	@Override
	@PostMapping	
	public ResponseEntity<CuentaDTO> post(@Valid @RequestBody CuentaDTO cuentaDTO){		
		
		cuentaDTO = cuentasService.save(0,cuentaDTO);		
		
		ResponseEntity<CuentaDTO> response = ResponseEntity.status(HttpStatus.CREATED).body(cuentaDTO);		
		controllerResponseLogger.log(this.getClass(), "post", response);
		return response;
	}
	
	
	@Override
	@GetMapping("/{cuentaId}")	
	public ResponseEntity<CuentaDTO> get(@PathVariable int cuentaId){
		
		CuentaDTO cuentaDTO = cuentasService.get(cuentaId);				
		  				
		ResponseEntity<CuentaDTO> response = ResponseEntity.ok(cuentaDTO);
		controllerResponseLogger.log(this.getClass(), "get", response);
		return response;
		
	}
	
	
	@Override
	@PutMapping("/{cuentaId}")	
	public ResponseEntity<CuentaDTO> put(@PathVariable int cuentaId, @Valid @RequestBody CuentaDTO cuentaDTO){
				
		HttpStatusCode httpStatusCode = HttpStatus.OK;
				
		if (!cuentasService.exists(cuentaId))
			httpStatusCode = HttpStatus.CREATED;
				
		cuentaDTO = cuentasService.save(cuentaId, cuentaDTO);
				
		ResponseEntity<CuentaDTO> response = ResponseEntity.status(httpStatusCode).body(cuentaDTO);
		controllerResponseLogger.log(this.getClass(), "put", response);
		return response;
		
	}

	
	@Override
	@PatchMapping("/{cuentaId}")
	public ResponseEntity<CuentaDTO> patch(@PathVariable int cuentaId, @RequestBody HashMap<String, String> entries) {
				
		if(entries.isEmpty())
			throw new SolicitudVaciaException();
		
		for (Entry<String, String> entry : entries.entrySet()) {
			
			if(!fieldDictionaryDTO.validateJSONProperty(CuentaDTO.class, entry.getKey()))
				throw new NombreDeCampoInvalidoException(entry.getKey());			

			try {					

				if(entry.getKey().equals("Numero Cuenta"))
					Integer.parseInt(entry.getValue());
				
				if(entry.getKey().equals("Saldo Inicial"))
					Double.parseDouble(entry.getValue());
				
				if(entry.getKey().equals("ID Cliente"))
					Integer.parseInt(entry.getValue());
					
				}catch (NumberFormatException e) {
					CamposConDatosInvalidosException exception = new CamposConDatosInvalidosException();
					exception.addError("El campo '"+ entry.getKey()+"' tiene un valor inválido", entry.getValue() );
					throw exception;
				}
				
		}
		
		CuentaDTO cuentaDTO = cuentasService.patch(cuentaId, entries);
		
		ResponseEntity<CuentaDTO> response = ResponseEntity.status(HttpStatus.OK).body(cuentaDTO);
		controllerResponseLogger.log(this.getClass(), "patch", response);
		return response;
		
	}

	
	@Override
	@DeleteMapping("/{cuentaId}")
	public ResponseEntity<CuentaDTO> delete(@PathVariable int cuentaId) {
		
		cuentasService.delete(cuentaId);
		
		ResponseEntity<CuentaDTO> response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		controllerResponseLogger.log(this.getClass(), "delete", response);
		return response;

	}
	
	
	
}
