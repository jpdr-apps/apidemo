package jpdr.apps.devsu.apidemo.services;

import java.util.HashMap;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import jpdr.apps.devsu.apidemo.dtos.ClienteDTO;
import jpdr.apps.devsu.apidemo.entities.Cliente;
import jpdr.apps.devsu.apidemo.exceptions.CamposConDatosInvalidosException;
import jpdr.apps.devsu.apidemo.exceptions.ClienteNoExisteException;
import jpdr.apps.devsu.apidemo.mappers.ClientesMapper;
import jpdr.apps.devsu.apidemo.repositories.ClientesRepository;

@Service
public class ClientesService implements BasicService<Cliente, ClienteDTO>{

	@Autowired
	private ClientesRepository clientesRepository;
	
	@Autowired 
	private Validator validator;
	

	@Override
	public boolean exists(int entityId) {
		return clientesRepository.existsById(entityId);
	}
	
	
	@Override
	public ClienteDTO get(int entityId) {
		
		Cliente cliente = clientesRepository.findById(entityId).orElseThrow(
				()-> new ClienteNoExisteException());
		
		ClienteDTO clienteDTO = ClientesMapper.getInstance().fromEntity(cliente);
		
		return clienteDTO;		
			
	}
	
	
	@Override
	public ClienteDTO save(int clienteId, ClienteDTO dto) {

		dto.setClienteId(clienteId);

		Cliente cliente = ClientesMapper.getInstance().fromDTO(dto);		

		cliente = clientesRepository.save(cliente);		
		
		dto.setClienteId(cliente.getId());
		
		return dto;
						
	}
	
	
	
	
	@Override
	public ClienteDTO patch(int entityId, HashMap<String, String> entries) {
		
		Cliente cliente = clientesRepository.findById(entityId).orElseThrow(
				()-> new ClienteNoExisteException());
		
		ClienteDTO clienteDTO = ClientesMapper.getInstance().fromEntity(cliente);	
		
		for ( Entry<String, String> entry : entries.entrySet()) {
		
			switch (entry.getKey()) {
				case "Nombres": {
					clienteDTO.setNombre(entry.getValue());
					break;
				}
				case "Género": {
					clienteDTO.setGenero(entry.getValue());
					break;
				}
				case "Edad":{
					clienteDTO.setEdad(Integer.parseInt(entry.getValue()));
					break;
				}
				case "Identificación":{
					clienteDTO.setIdentificacion(entry.getValue());
					break;
				}
				case "Dirección": {
					clienteDTO.setDireccion(entry.getValue());
					break;
				}
				case "Teléfono": {
					clienteDTO.setTelefono(entry.getValue());
					break;
				}
				case "Contraseña": {
					clienteDTO.setContraseña(entry.getValue());
					break;
				}
				case "Estado":{
					clienteDTO.setEstado(entry.getValue());
					break;
				}
				
			}
			
		}
		
		Set<ConstraintViolation<ClienteDTO>> violations = validator.validate(clienteDTO);
		
		if (violations.size()>0) {			
			CamposConDatosInvalidosException exception = new CamposConDatosInvalidosException();		
			for ( ConstraintViolation<ClienteDTO> constraintViolation : violations ) {			
				exception.addError(
						constraintViolation.getMessage(), 						
						String.valueOf(constraintViolation.getInvalidValue()));					
			}			
			throw ( exception );
		}		
		
		cliente = ClientesMapper.getInstance().fromDTO(clienteDTO);		
		clientesRepository.save(cliente);
		return clienteDTO;
		
	}
	
	
	@Override
	public void delete(int clinteId) {
		
		if(!clientesRepository.existsById(clinteId))
			throw new ClienteNoExisteException();
		
		clientesRepository.deleteById(clinteId);		
	}
	
	
	@Override
	public Cliente getEntity(int clienteId) {
		
		return clientesRepository.findById(clienteId).orElseThrow(
				()-> new ClienteNoExisteException());		
	}
	

	
	
}
