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
import jpdr.apps.devsu.apidemo.entities.Cuenta;
import jpdr.apps.devsu.apidemo.repositories.ClientesRepository;
import jpdr.apps.devsu.apidemo.repositories.CuentasRepository;

@DataJpaTest
@ContextConfiguration(classes = ApidemoApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Order(2)
public class CuentaUnitTests {
	
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private ClientesRepository clientesRepository;
	
	@Autowired
	private CuentasRepository cuentasRepository;
	
	@Test
	public void Insertar_Cuenta_OK() {
		
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
		
		Cliente clientePersistido = clientesRepository.findByIdentificacion(identificacion).orElseThrow();
		
		Cuenta cuenta = new Cuenta();
		int numeroCuenta = 999111;
		cuenta.setNumeroCuenta(numeroCuenta);
		cuenta.setEstado(true);
		double saldoInicial = 12345.67;
		cuenta.setSaldoInicial(saldoInicial);
		String tipoCuenta = "Ahorro";
		cuenta.setTipoCuenta(tipoCuenta);
		cuenta.setCliente(clientePersistido);
		
		testEntityManager.persist(cuenta);
		testEntityManager.flush();
		
		Cuenta cuentaPersistida = cuentasRepository.findByNumeroCuenta(numeroCuenta).orElseThrow();
		
		assertThat(numeroCuenta).isEqualTo(cuentaPersistida.getNumeroCuenta());
		assertThat(saldoInicial).isEqualTo(cuentaPersistida.getSaldoInicial());
		assertThat(tipoCuenta).isEqualTo(cuentaPersistida.getTipoCuenta());
		assertThat(clientePersistido.getId()).isEqualTo(cuentaPersistida.getCliente().getId());		
		
		
	}
	

}
