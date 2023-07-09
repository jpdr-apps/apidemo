package jpdr.apps.devsu.apidemo.dtos;


import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import jpdr.apps.devsu.apidemo.dtos.validations.ValidateAsDecimal;

@JsonPropertyOrder(value = {"ID Cuenta"})
public class CuentaDTO {
	
	@JsonProperty("ID Cuenta")
	private int cuentaId;
	
	@JsonProperty(value = "Numero Cuenta", required = true)
	@Min(value = 100000, message = "El tamaño del campo 'numeroCuenta' no puede ser inferior a 6 dígitos")
	@Max(value = 999999, message = "El tamaño del campo 'numeroCuenta' no puede ser superior a 6 dígitos")
	private int numeroCuenta;
	
	@JsonProperty(value = "Tipo", required = true)
	@NotBlank(message = "El campo 'Tipo' debe recibir alguno de los siguientes valores: ['Ahorro', 'Corriente']")
	@Pattern(regexp = "Ahorro|Corriente", message = "El campo 'tipoCuenta' debe recibir alguno de los siguientes valores: ['Ahorro', 'Corriente']")
	private String tipoCuenta;
	
	@JsonProperty(value = "Saldo Inicial", required = true)
	@ValidateAsDecimal(integerDigits = 16, fractionalDigits = 2, message = ValidateAsDecimal.MENSAJE)
	@PositiveOrZero(message = "El campo 'saldoInicial' debe ser mayor o igual a cero")
	private double saldoInicial;
	
	@JsonProperty(value = "Estado", required = true)
	@NotBlank(message = "El campo 'estado' debe recibir alguno de los siguientes valores: ['true', 'false']")
	@Pattern(regexp = "true|false", message = "El campo 'estado' debe recibir alguno de los siguientes valores: ['true', 'false']")
	private String estado;	
	
	@JsonProperty(value = "ID Cliente", required = true)
	@PositiveOrZero(message = "El campo 'clienteId' debe recibir un ID de cliente válido")
	private int clienteId;	
	
	public CuentaDTO() {}
 
	@JsonCreator
	public CuentaDTO(
			@Min(value = 100000, message = "El tamaño del campo 'numeroCuenta' no puede ser inferior a 6 dígitos") @Max(value = 999999, message = "El tamaño del campo 'numeroCuenta' no puede ser superior a 6 dígitos") int numeroCuenta,
			@NotBlank(message = "El campo 'Tipo' debe recibir alguno de los siguientes valores: ['Ahorro', 'Corriente']") @Pattern(regexp = "Ahorro|Corriente", message = "El campo 'tipoCuenta' debe recibir alguno de los siguientes valores: ['Ahorro', 'Corriente']") String tipoCuenta,
			@PositiveOrZero(message = "El campo 'saldoInicial' debe ser mayor o igual a cero") double saldoInicial,
			@NotBlank(message = "El campo 'estado' debe recibir alguno de los siguientes valores: ['true', 'false']") @Pattern(regexp = "true|false", message = "El campo 'estado' debe recibir alguno de los siguientes valores: ['true', 'false']") String estado,
			@Positive(message = "El campo 'clienteId' debe recibir un ID de cliente válido") int clienteId) {
		super();
		this.numeroCuenta = numeroCuenta;
		this.tipoCuenta = tipoCuenta;
		this.saldoInicial = saldoInicial;
		this.estado = estado;
		this.clienteId = clienteId;
	}
	 
	
	
	
	
	
 
	public int getCuentaId() {
		return cuentaId;
	}

	public void setCuentaId(int cuentaId) {
		this.cuentaId = cuentaId;
	}

	public int getNumeroCuenta() {
		return numeroCuenta;
	}

	public void setNumeroCuenta(int numeroCuenta) {
		this.numeroCuenta = numeroCuenta;
	}

	public String getTipoCuenta() {
		return tipoCuenta;
	}

	public void setTipoCuenta(String tipoCuenta) {
		this.tipoCuenta = tipoCuenta;
	}

	public double getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public int getClienteId() {
		return clienteId;
	}

	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
	}

	@Override
	public String toString() {
		return "CuentaDTO [cuentaId=" + cuentaId + ", numeroCuenta=" + numeroCuenta + ", tipoCuenta=" + tipoCuenta
				+ ", saldoInicial=" + saldoInicial + ", estado=" + estado + ", clienteId=" + clienteId + "]";
	}


}
