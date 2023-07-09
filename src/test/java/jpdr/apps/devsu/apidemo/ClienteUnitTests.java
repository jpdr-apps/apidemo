package jpdr.apps.devsu.apidemo;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ContextConfiguration;

import jpdr.apps.devsu.apidemo.entities.Cliente;
import jpdr.apps.devsu.apidemo.repositories.ClientesRepository;

@DataJpaTest
@ContextConfiguration(classes = ApidemoApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Order(1)
public class ClienteUnitTests {

	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private ClientesRepository clientesRepository;
	
	@Test
	public void Insertar_Cliente_OK() {
		
		Cliente cliente = new Cliente();	
		cliente.setNombre("Juan Smith");
		cliente.setDireccion("Av. San Martín 123");
		cliente.setEdad(52);
		String identificacion = "998833773377";
		cliente.setIdentificacion(identificacion);
		cliente.setGenero("Masculino");
		cliente.setTelefono("54112131313");
		cliente.setEstado(true);
		cliente.setContraseña("1234");
		
		testEntityManager.persist(cliente);
		testEntityManager.flush();
		
		Cliente clienteResultado = clientesRepository.findByIdentificacion(identificacion).orElseThrow();
		
		assertThat(identificacion).isEqualTo(clienteResultado.getIdentificacion());
				
	}
		
	
}
