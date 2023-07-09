package jpdr.apps.devsu.apidemo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "parametros")
public class Parametro {
	
	@Id
	@Column(name = "clave", nullable = false)	
	private String clave;
	
	@Column(name = "valor", nullable = false)	
	private String valor;
	
	public Parametro() {}

	public Parametro(String clave, String valor) {		
		this.clave = clave;
		this.valor = valor;
	}

	public String getClave() {
		return clave;
	}

	public void setClave(String clave) {
		this.clave = clave;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}
	
	
	
	

}
