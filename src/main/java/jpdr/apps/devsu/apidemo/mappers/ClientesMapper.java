package jpdr.apps.devsu.apidemo.mappers;

import jpdr.apps.devsu.apidemo.dtos.ClienteDTO;
import jpdr.apps.devsu.apidemo.entities.Cliente;

public class ClientesMapper implements BasicMapper<Cliente, ClienteDTO> {
	
	private static volatile ClientesMapper instance;
	
	private ClientesMapper() {}
	
	public static ClientesMapper getInstance() {		
		
		if (instance==null) { 
			synchronized (ClientesMapper.class) {
				if (instance==null) 
					instance = new ClientesMapper();					
			}
		}
		return instance;				
	}
	

	@Override
	public Cliente fromDTO(ClienteDTO clienteDTO) {		
		Cliente cliente = new Cliente();
		cliente.setId(clienteDTO.getClienteId());
		cliente.setNombre(clienteDTO.getNombre());
		cliente.setGenero(clienteDTO.getGenero());
		cliente.setEdad(clienteDTO.getEdad());
		cliente.setIdentificacion(clienteDTO.getIdentificacion());
		cliente.setDireccion(clienteDTO.getDireccion());
		cliente.setTelefono(clienteDTO.getTelefono());
		cliente.setContraseña(clienteDTO.getContraseña());
		cliente.setEstado(Boolean.parseBoolean(clienteDTO.getEstado()));			
		return cliente;
	}

	@Override
	public ClienteDTO fromEntity(Cliente cliente) {
		
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setClienteId(cliente.getId());
		clienteDTO.setNombre(cliente.getNombre());
		clienteDTO.setGenero(cliente.getGenero());
		clienteDTO.setEdad(cliente.getEdad());
		clienteDTO.setIdentificacion(cliente.getIdentificacion());
		clienteDTO.setDireccion(cliente.getDireccion());
		clienteDTO.setTelefono(cliente.getTelefono());
		clienteDTO.setContraseña(cliente.getContraseña());
		clienteDTO.setEstado(Boolean.toString(cliente.getEstado()));
		
		return clienteDTO;	
				
	}
	
	

}
