package jpdr.apps.devsu.apidemo.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import jpdr.apps.devsu.apidemo.entities.Cliente;

public interface ClientesRepository extends CrudRepository<Cliente, Integer>{
	
	public Optional<Cliente> findByIdentificacion(String identificacion);

}
