package jpdr.apps.devsu.apidemo.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jpdr.apps.devsu.apidemo.dtos.validations.ValidateAsDate;
import jpdr.apps.devsu.apidemo.dtos.validations.ValidateAsDecimal;

@JsonPropertyOrder(value = {"ID Movimiento"})
public class MovimientoDTO {
	
	@JsonProperty("ID Movimiento")
	private int movimientoId;
	
	@JsonProperty(value = "Fecha",required = true)
	@NotBlank(message = "El campo 'Fecha' es inválido. Se espera una fecha válida, en formato 'dd/mm/aaaa'")
	@Pattern(regexp = "(0[1-9]|[12][0-9]|[3][01])/(0[1-9]|1[012])/\\d{4}", message = "El campo 'Fecha' es inválido. Se espera una fecha válida, en formato 'dd/mm/aaaa'")
	@ValidateAsDate
	private String fecha;
		
	@JsonProperty(value = "Tipo",required = true)
	@NotBlank(message = "El campo 'Tipo' debe recibir alguno de los siguientes valores: ['Depósito', 'Retiro']")
	@Pattern(regexp = "Depósito|Retiro", message = "El campo 'Tipo' debe recibir alguno de los siguientes valores: ['Depósito', 'Retiro']")
	private String tipoMovimiento;
	
	@JsonProperty(value = "Saldo Inicial")
	@ValidateAsDecimal(integerDigits = 16, fractionalDigits = 2, message = ValidateAsDecimal.MENSAJE)	
	private double saldoInicial;
	
	@JsonProperty(value = "Valor", required = true)
	@Positive(message = "El campo 'Valor' debe recibir valores mayores a cero")
	@ValidateAsDecimal(integerDigits = 16, fractionalDigits = 2, message = ValidateAsDecimal.MENSAJE)	
	private double valor;
	
	@JsonProperty(value = "Saldo Disponible")
	@ValidateAsDecimal(integerDigits = 16, fractionalDigits = 2, message = ValidateAsDecimal.MENSAJE)	
	private double saldoDisponible;
	
	@JsonProperty(value = "ID Cuenta", required = true)	
	@Positive(message = "El campo 'ID Cuenta' debe recibir un ID de cuenta válido")
	private int cuentaId;	
	
	public MovimientoDTO() {}	 
	
	@JsonCreator
	public MovimientoDTO(
			@NotBlank(message = "El campo 'Fecha' es inválido. Se espera una fecha en formato 'dd/mm/aaaa'") @Pattern(regexp = "(0[1-9]|[12][0-9]|[3][01])/(0[1-9]|1[012])/\\d{4}", message = "El campo 'Fecha' es inválido. Se espera una fecha válida, en formato 'dd/mm/aaaa'") String fecha,
			@NotBlank(message = "El campo 'Tipo' debe recibir alguno de los siguientes valores: ['Depósito', 'Retiro']") @Pattern(regexp = "Depósito|Retiro", message = "El campo 'Tipo' debe recibir alguno de los siguientes valores: ['Depósito', 'Retiro']") String tipoMovimiento,
			double saldoInicial, double valor, double saldoDisponible,
			@Positive(message = "El campo 'cuentaId' debe recibir un ID de cuenta válido") int cuentaId) {		
		this.fecha = fecha;
		this.tipoMovimiento = tipoMovimiento;
		this.saldoInicial = saldoInicial;
		this.valor = valor;
		this.saldoDisponible = saldoDisponible;
		this.cuentaId = cuentaId;
	}




	public int getMovimientoId() {
		return movimientoId;
	}

	public void setMovimientoId(int movimientoId) {
		this.movimientoId = movimientoId;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}
	
	public double getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public double getValor() {
		return valor;
	}

	public void setValor(double valor) {
		this.valor = valor;
	}

	public double getSaldoDisponible() {
		return saldoDisponible;
	}

	public void setSaldoDisponible(double saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}

	public int getCuentaId() {
		return cuentaId;
	}

	public void setCuentaId(int cuentaId) {
		this.cuentaId = cuentaId;
	}

	@Override
	public String toString() {
		return "MovimientoDTO [movimientoId=" + movimientoId + ", fecha=" + fecha + ", tipoMovimiento=" + tipoMovimiento
				+ ", saldoInicial=" + saldoInicial + ", valor=" + valor + ", saldoDisponible=" + saldoDisponible
				+ ", cuentaId=" + cuentaId + "]";
	}
	
	
		
	
}
