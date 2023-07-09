package jpdr.apps.devsu.apidemo.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "movimientos")
public class Movimiento {
	
	@Id	
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="movimiento_id", nullable = false)
	private int movimientoId;
	
	@Column(name = "fecha", nullable = false)
	private String fecha;
	
	@Column(name = "tipo_movimiento", nullable = false)
	private String tipoMovimiento;
	
	@Column(name = "saldo_inicial", nullable = false, columnDefinition = "real(16,2)")
	private double saldoInicial;
	
	@Column(name = "valor", nullable = false, columnDefinition = "real(16,2)")
	private double valor;
	
	@Column(name = "saldo_disponible", nullable = false, columnDefinition = "real(16,2)")
	private double saldoDisponible;
	
	@ManyToOne
	@JoinColumn(name = "cuenta_id", nullable = false)
	private Cuenta cuenta;
	
	public Movimiento() {}

	public Movimiento(String fecha, String tipoMovimiento, double saldoInicial , double valor, double saldoDisponible) {
		 
		this.fecha = fecha;
		this.tipoMovimiento = tipoMovimiento;
		this.valor = valor;
		this.saldoDisponible = saldoDisponible;
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

	public Cuenta getCuenta() {
		return cuenta;
	}

	public void setCuenta(Cuenta cuenta) {
		this.cuenta = cuenta;
	}
	
	
	

}
