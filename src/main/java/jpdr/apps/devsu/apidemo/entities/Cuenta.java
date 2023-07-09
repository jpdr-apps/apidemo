package jpdr.apps.devsu.apidemo.entities;


import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "cuentas")
public class Cuenta {	
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "cuenta_id", nullable = false)
	private int cuentaId;
	
	@Column(name = "numero_cuenta", nullable = false)
	private int numeroCuenta;
	
	@Column(name="tipo_cuenta", nullable = false)
	private String tipoCuenta;
	
	@Column(name = "saldo_inicial", nullable = false, columnDefinition = "real(16,2)")
	private double saldoInicial;
	
	@Column(name ="estado", nullable = false)
	private boolean estado;
	
	@ManyToOne
	@JoinColumn(name = "cliente_id", nullable = false)
	private Cliente cliente;
	
	@OneToMany(mappedBy = "cuenta")
	private Set<Movimiento> movimientosCuenta;
	
	public Cuenta() {}

	public Cuenta(int numeroCuenta, String tipoCuenta, double saldoInicial, boolean estado, Cliente cliente) {	 
		this.numeroCuenta = numeroCuenta;
		this.tipoCuenta = tipoCuenta;
		this.saldoInicial = saldoInicial;
		this.estado = estado;
		this.cliente = cliente;
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

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Set<Movimiento> getMovimientosCuenta() {
		return movimientosCuenta;
	}
	
	
	
}
