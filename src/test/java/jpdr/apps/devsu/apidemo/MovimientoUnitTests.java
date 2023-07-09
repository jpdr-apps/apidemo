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
import jpdr.apps.devsu.apidemo.entities.Movimiento;
import jpdr.apps.devsu.apidemo.repositories.ClientesRepository;
import jpdr.apps.devsu.apidemo.repositories.CuentasRepository;
import jpdr.apps.devsu.apidemo.repositories.MovimientosRepository;

@DataJpaTest
@ContextConfiguration(classes = ApidemoApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Order(3)
public class MovimientoUnitTests {
	
	@Autowired
	private TestEntityManager testEntityManager;
	
	@Autowired
	private ClientesRepository clientesRepository;
	
	@Autowired
	private CuentasRepository cuentasRepository;
	
	@Autowired
	private MovimientosRepository movimientosRepository;
	
	
	@Test
	public void Insertar_Movimiento_OK() {
		
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
		
		Movimiento movimiento = new Movimiento();
		String fecha = "1901/12/31";
		movimiento.setFecha(fecha);
		String tipoMovimiento = "Depósito";
		movimiento.setTipoMovimiento(tipoMovimiento);		
		movimiento.setSaldoInicial(saldoInicial);
		double valor = 111.11;
		movimiento.setValor(valor);
		double saldoDisponible = 12456.78;
		movimiento.setSaldoDisponible(saldoDisponible);
		movimiento.setCuenta(cuentaPersistida);
				
		testEntityManager.persist(movimiento);
		testEntityManager.flush();
		
		Movimiento movimientoPersistido = movimientosRepository.findByFecha(fecha).orElseThrow();
		
		assertThat(movimientoPersistido.getCuenta().getCuentaId()).isEqualTo(cuenta.getCuentaId());
		assertThat(movimientoPersistido.getFecha()).isEqualTo(fecha);
		assertThat(movimientoPersistido.getSaldoDisponible()).isEqualTo(saldoDisponible);
		assertThat(movimientoPersistido.getTipoMovimiento()).isEqualTo(tipoMovimiento);		
		
	}

}
