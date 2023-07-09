package jpdr.apps.devsu.apidemo.entities;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.persistence.Table;

@MappedSuperclass
@Table(name = "personas")
public abstract class Persona {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)		
	@Column(name = "id", nullable = false)
	private int id;
	
	@Column(name = "nombre", nullable = false)
	private String nombre;
	
	@Column(name = "genero", nullable = false)
	private String genero;
	
	@Column(name = "edad", nullable = false)
	private int edad;
	
	@Column(name = "identificacion", nullable = false)
	private String identificacion;
	
	@Column(name = "direccion", nullable = false)
	private String direccion;
	
	@Column(name = "telefono")
	private String telefono;
	
	public Persona() {}
		
	public Persona(String nombre, String genero, int edad, String identificacion, String direccion, String telefono) {	
		this.nombre = nombre;
		this.genero = genero;
		this.edad = edad;
		this.identificacion = identificacion;
		this.direccion = direccion;
		this.telefono = telefono;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getGenero() {
		return genero;
	}

	public void setGenero(String genero) {
		this.genero = genero;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getIdentificacion() {
		return identificacion;
	}

	public void setIdentificacion(String identificacion) {
		this.identificacion = identificacion;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	
	

}
