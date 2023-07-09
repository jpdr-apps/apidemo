package jpdr.apps.devsu.apidemo;

import static org.assertj.core.api.Assertions.assertThat;

import java.net.URI;
import java.net.URISyntaxException;

import org.junit.jupiter.api.MethodOrderer.OrderAnnotation;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;

import jpdr.apps.devsu.apidemo.dtos.ClienteDTO;
import jpdr.apps.devsu.apidemo.dtos.CuentaDTO;
import jpdr.apps.devsu.apidemo.dtos.MovimientoDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestMethodOrder(OrderAnnotation.class)
@Order(4)
public class IntegrationTests {
	
	@Autowired
	private TestRestTemplate testRestTemplate;	
	
	@LocalServerPort
	private int localServerPort;
	
	public static int clienteId;
	public static int cuentaId;
	public static int movimientoId;
		
	@Test
	@Order(1)
	public void MetodoPOST_Cliente_RetornaStatus201() throws URISyntaxException {
		
		final String url = "http://localhost:"+localServerPort+"/apidemo/clientes";
		
		URI uri = new URI(url);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Content-Type", "application/json");		
		
		ClienteDTO clienteDTO = new ClienteDTO();
		clienteDTO.setNombre("Juan Integral");
		clienteDTO.setDireccion("Av. Integral 123");
		clienteDTO.setEdad(52);
		String identificacion = "773377998833";
		clienteDTO.setIdentificacion(identificacion);
		clienteDTO.setGenero("Masculino");
		clienteDTO.setTelefono("31313541121");
		clienteDTO.setEstado("true");
		clienteDTO.setContraseña("1234");
		
		HttpEntity<ClienteDTO> httpEntity = new HttpEntity<ClienteDTO>(clienteDTO, httpHeaders);
		
		ResponseEntity<ClienteDTO> result = testRestTemplate.postForEntity(uri, httpEntity, ClienteDTO.class);				
		
		assertThat(result.getStatusCode().value()).isEqualTo(HttpStatus.CREATED.value());
		
		clienteId = result.getBody().getClienteId();		
				
	}
	
	@Test
	@Order(2)
	public void MetodoGET_Cliente_RetornaStatus200() throws URISyntaxException {
		
		final String url = "http://localhost:"+localServerPort+"/apidemo/clientes/"+clienteId;
		
		URI uri = new URI(url);
		
		RequestEntity<String> requestEntity = new RequestEntity<String>(HttpMethod.GET, uri);
		ResponseEntity<String> result = testRestTemplate.exchange(requestEntity, String.class);	
		
		assertThat(result.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
				
	}

	
	@Test
	@Order(3)
	public void MetodoPOST_Cuenta_RetornaStatus201() throws URISyntaxException {
		
		final String url = "http://localhost:"+localServerPort+"/apidemo/cuentas";
		
		URI uri = new URI(url);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Content-Type", "application/json");		
		
		CuentaDTO cuentaDTO = new CuentaDTO();
		int numeroCuenta = 123111;
		cuentaDTO.setNumeroCuenta(numeroCuenta);
		cuentaDTO.setEstado("true");
		double saldoInicial = 1000.00;
		cuentaDTO.setSaldoInicial(saldoInicial);
		String tipoCuenta = "Ahorro";
		cuentaDTO.setTipoCuenta(tipoCuenta);
		cuentaDTO.setClienteId(clienteId);
		
		HttpEntity<CuentaDTO> httpEntity = new HttpEntity<CuentaDTO>(cuentaDTO, httpHeaders);
		
		ResponseEntity<CuentaDTO> result = testRestTemplate.postForEntity(uri, httpEntity, CuentaDTO.class);				
		
		assertThat(result.getStatusCode().value()).isEqualTo(HttpStatus.CREATED.value());
		
		cuentaId = result.getBody().getCuentaId();		
	
	}
	
	
	@Test
	@Order(4)
	public void MetodoGET_Cuenta_RetornaStatus200() throws URISyntaxException {
		
		final String url = "http://localhost:"+localServerPort+"/apidemo/cuentas/"+cuentaId;
		
		URI uri = new URI(url);
		
		RequestEntity<String> requestEntity = new RequestEntity<String>(HttpMethod.GET, uri);
		ResponseEntity<String> result = testRestTemplate.exchange(requestEntity, String.class);	
		
		assertThat(result.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
				
	}
	
	
	@Test
	@Order(5)
	public void MetodoPOST_Movimiento_RetornaStatus201() throws URISyntaxException {
		
		final String url = "http://localhost:"+localServerPort+"/apidemo/movimientos";
		
		URI uri = new URI(url);
		
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Content-Type", "application/json");		
		
		MovimientoDTO movimientoDTO = new MovimientoDTO();
		String fecha = "31/12/1902";
		movimientoDTO.setFecha(fecha);
		String tipoMovimiento = "Depósito";
		movimientoDTO.setTipoMovimiento(tipoMovimiento);		
		double valor = 5.00;
		movimientoDTO.setValor(valor);
		movimientoDTO.setCuentaId(cuentaId);
		
		HttpEntity<MovimientoDTO> httpEntity = new HttpEntity<MovimientoDTO>(movimientoDTO, httpHeaders);
		
		ResponseEntity<MovimientoDTO> result = testRestTemplate.postForEntity(uri, httpEntity, MovimientoDTO.class);				
		
		assertThat(result.getStatusCode().value()).isEqualTo(HttpStatus.CREATED.value());
		
		movimientoId = result.getBody().getMovimientoId();		
	
	}
	
	
	@Test
	@Order(6)
	public void MetodoGET_Movimiento_RetornaStatus200() throws URISyntaxException {
		
		final String url = "http://localhost:"+localServerPort+"/apidemo/movimientos/"+movimientoId;
		
		URI uri = new URI(url);
		
		RequestEntity<String> requestEntity = new RequestEntity<String>(HttpMethod.GET, uri);
		ResponseEntity<String> result = testRestTemplate.exchange(requestEntity, String.class);	
		
		assertThat(result.getStatusCode().value()).isEqualTo(HttpStatus.OK.value());
				
	}
	
	
	@Test
	@Order(7)
	public void MetodoDELETE_Movimiento_RetornaStatus204() throws URISyntaxException {
		
		final String url = "http://localhost:"+localServerPort+"/apidemo/movimientos/"+movimientoId;
		
		URI uri = new URI(url);		
		
		RequestEntity<String> requestEntity = new RequestEntity<String>(HttpMethod.DELETE, uri);
		ResponseEntity<String> result = testRestTemplate.exchange(requestEntity, String.class);	
		
		assertThat(result.getStatusCode().value()).isEqualTo(HttpStatus.NO_CONTENT.value());
		
	}
	
	@Test
	@Order(8)
	public void MetodoDELETE_Cuenta_RetornaStatus204() throws URISyntaxException {
		
		final String url = "http://localhost:"+localServerPort+"/apidemo/cuentas/"+cuentaId;
		
		URI uri = new URI(url);		
		
		RequestEntity<String> requestEntity = new RequestEntity<String>(HttpMethod.DELETE, uri);
		ResponseEntity<String> result = testRestTemplate.exchange(requestEntity, String.class);	
		
		assertThat(result.getStatusCode().value()).isEqualTo(HttpStatus.NO_CONTENT.value());
		
	}
	
	@Test
	@Order(9)
	public void MetodoDELETE_Cliente_RetornaStatus204() throws URISyntaxException {
		
		final String url = "http://localhost:"+localServerPort+"/apidemo/clientes/"+clienteId;
		
		URI uri = new URI(url);		
		
		RequestEntity<String> requestEntity = new RequestEntity<String>(HttpMethod.DELETE, uri);
		ResponseEntity<String> result = testRestTemplate.exchange(requestEntity, String.class);	
		
		assertThat(result.getStatusCode().value()).isEqualTo(HttpStatus.NO_CONTENT.value());
		
	}
	
	

}
