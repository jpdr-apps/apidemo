package jpdr.apps.devsu.apidemo.dtos;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ReporteDTOItem {
	
	@JsonProperty("Fecha")
	private String fecha;
	
	@JsonProperty("Cliente")
	private String nombreCliente;
	
	@JsonProperty("Número Cuenta")
	private int numeroCuenta;
	
	@JsonProperty("Tipo")
	private String tipoCuenta;
	
	@JsonProperty("Saldo Inicial")
	private Double saldoInicial;
	
	@JsonProperty("Estado")
	private String estado;
	
	@JsonProperty("Movimiento")
	private String tipoMovimiento;
	
	@JsonProperty("Valor")
	private Double valor;
	
	@JsonProperty("Saldo Disponible")
	private Double saldoDisponible;
	
	public ReporteDTOItem() {}

	public ReporteDTOItem(String fecha, String nombreCliente, int numeroCuenta, String tipoCuenta, Double saldoInicial,
			String estado, String tipoMovimiento, Double valor, Double saldoDisponible) {		
		this.fecha = fecha;
		this.nombreCliente = nombreCliente;
		this.numeroCuenta = numeroCuenta;
		this.tipoCuenta = tipoCuenta;
		this.saldoInicial = saldoInicial;
		this.estado = estado;
		this.tipoMovimiento = tipoMovimiento;
		this.valor = valor;
		this.saldoDisponible = saldoDisponible;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getNombreCliente() {
		return nombreCliente;
	}

	public void setNombreCliente(String nombreCliente) {
		this.nombreCliente = nombreCliente;
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

	public Double getSaldoInicial() {
		return saldoInicial;
	}

	public void setSaldoInicial(Double saldoInicial) {
		this.saldoInicial = saldoInicial;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getTipoMovimiento() {
		return tipoMovimiento;
	}

	public void setTipoMovimiento(String tipoMovimiento) {
		this.tipoMovimiento = tipoMovimiento;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getSaldoDisponible() {
		return saldoDisponible;
	}

	public void setSaldoDisponible(Double saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}

	@Override
	public String toString() {
		return "ReporteDTOItem [fecha=" + fecha + ", nombreCliente=" + nombreCliente + ", numeroCuenta=" + numeroCuenta
				+ ", tipoCuenta=" + tipoCuenta + ", saldoInicial=" + saldoInicial + ", estado=" + estado
				+ ", tipoMovimiento=" + tipoMovimiento + ", valor=" + valor + ", saldoDisponible=" + saldoDisponible
				+ "]";
	}
	
	
	

}
