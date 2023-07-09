package jpdr.apps.devsu.apidemo.entities;

import java.util.Set;

import jakarta.persistence.AttributeOverride;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "clientes")
@AttributeOverride(name = "id", column = @Column(name="cliente_id"))
public class Cliente extends Persona {
	
	@Column(name="contraseña", nullable = false)
	private String contraseña;
	
	@Column(name = "estado", nullable = false)
	private boolean estado;
	
	@OneToMany(mappedBy = "cliente")
	private Set<Cuenta> cuentasCliente;
	
	public Cliente() {
		super();
	}
	
	public Cliente(String nombre, String genero, int edad, String identificacion, String direccion, String telefono, String contraseña, boolean estado) {
		super(nombre, genero, edad, identificacion, direccion, telefono);
		this.contraseña = contraseña;
		this.estado = estado;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public boolean getEstado() {
		return estado;
	}

	public void setEstado(boolean estado) {
		this.estado = estado;
	}

	public Set<Cuenta> getCuentasCliente() {
		return cuentasCliente;
	}
			
}
