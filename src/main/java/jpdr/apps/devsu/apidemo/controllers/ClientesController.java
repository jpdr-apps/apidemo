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
import jpdr.apps.devsu.apidemo.dtos.ClienteDTO;
import jpdr.apps.devsu.apidemo.dtos.helpers.FieldDictionaryDTO;
import jpdr.apps.devsu.apidemo.exceptions.CamposConDatosInvalidosException;
import jpdr.apps.devsu.apidemo.exceptions.NombreDeCampoInvalidoException;
import jpdr.apps.devsu.apidemo.exceptions.SolicitudVaciaException;
import jpdr.apps.devsu.apidemo.log.ControllerResponseLogger;
import jpdr.apps.devsu.apidemo.services.ClientesService;


@RestController
@RequestMapping("/apidemo/clientes")
public class ClientesController implements BasicController<ClienteDTO>{
	

	@Autowired
	private ClientesService clientesService;
	
	@Autowired
	private FieldDictionaryDTO fieldDictionaryDTO;
	
	@Autowired
	private ControllerResponseLogger controllerResponseLogger;
		
	
	@Override
	@PostMapping
	public ResponseEntity<ClienteDTO> post(@Valid @RequestBody ClienteDTO clienteDTO){		
				
		clienteDTO = clientesService.save(0,clienteDTO);
		ResponseEntity<ClienteDTO> response = ResponseEntity.status(HttpStatus.CREATED).body(clienteDTO);
		controllerResponseLogger.log(this.getClass(), "post", response);
		return response;
		
	}
	
	
	@Override
	@GetMapping(value = "/{clienteId}")
	public ResponseEntity<ClienteDTO> get(@PathVariable int clienteId){		
		
		ClienteDTO clienteDTO = clientesService.get(clienteId);		
		
		ResponseEntity<ClienteDTO> response = ResponseEntity.ok(clienteDTO);
		controllerResponseLogger.log(this.getClass(), "get", response);
		return response;
			
	}
	
	
	@Override
	@PutMapping(value = "/{clienteId}")
	public ResponseEntity<ClienteDTO> put(@PathVariable int clienteId, @Valid @RequestBody ClienteDTO clienteDTO){
		
		HttpStatusCode httpStatusCode = HttpStatus.OK;
		
		if (!clientesService.exists(clienteId))
			httpStatusCode = HttpStatus.CREATED;
			
		clienteDTO = clientesService.save(clienteId, clienteDTO);
		
		ResponseEntity<ClienteDTO> response = ResponseEntity.status(httpStatusCode).body(clienteDTO);
		controllerResponseLogger.log(this.getClass(), "put", response);
		return response;
		
	}
	
	
	@Override
	@PatchMapping(value="/{clienteId}")
	public ResponseEntity<ClienteDTO> patch(@PathVariable int clienteId, @RequestBody HashMap<String, String> entries){
		
		if(entries.isEmpty())
			throw new SolicitudVaciaException();
		
		for (Entry<String, String> entry : entries.entrySet()) {
			
			if(!fieldDictionaryDTO.validateJSONProperty(ClienteDTO.class, entry.getKey()))
				throw new NombreDeCampoInvalidoException(entry.getKey());			

			try {					

				if(entry.getKey().equals("Edad"))
						Integer.parseInt(entry.getValue());
					
				}catch (NumberFormatException e) {
					CamposConDatosInvalidosException exception = new CamposConDatosInvalidosException();
					exception.addError("El campo '"+ entry.getKey()+"' tiene un valor inválido", entry.getValue() );
					throw exception;
				}
				
		}
					
		ClienteDTO clienteDTO = clientesService.patch(clienteId, entries);				
		
		ResponseEntity<ClienteDTO> response = ResponseEntity.status(HttpStatus.OK).body(clienteDTO);
		controllerResponseLogger.log(this.getClass(), "path", response);
		return response;
	}
	
	
	@Override
	@DeleteMapping(value = "/{clienteId}")
	public ResponseEntity<ClienteDTO> delete(@PathVariable int clienteId){
				
		clientesService.delete(clienteId);				
		
		ResponseEntity<ClienteDTO> response = ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		controllerResponseLogger.log(this.getClass(), "delete", response);
		return response;
				
	}

}
