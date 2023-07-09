package jpdr.apps.devsu.apidemo.dtos;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;

@JsonPropertyOrder(value = {"ID Cliente"})
public class ClienteDTO {

	@JsonProperty("ID Cliente")
	private int clienteId;
	
	@JsonProperty(value="Nombres", required = true)
	@NotBlank(message = "El campo 'Nombres' no puede estar vacío")
	private String nombre;

	@JsonProperty(value="Género", required = true)
	@NotBlank(message = "El campo 'Género' debe recibir alguno de los siguientes valores: ['true', 'false']")
	@Pattern(regexp = "Masculino|Femenino|Otro", message = "El campo 'Género' debe recibir alguno de los siguientes valores: ['Masculino', 'Femenino', 'Otro']")	
	private String genero;
	
	@JsonProperty(value="Edad", required = true)
	@Positive(message = "El campo 'Edad' debe ser mayor a cero")
	private int edad;
	
	@JsonProperty(value="Identificación", required = true)
	@NotBlank(message = "El campo 'Identificación' no puede estar vacío")
	private String identificacion;
	
	@JsonProperty(value="Dirección", required = true)
	@NotBlank(message = "El campo 'Dirección' no puede estar vacío")
	private String direccion;
	
	@JsonProperty(value="Teléfono", required = true)
	@NotBlank(message = "El campo 'Teléfono' no puede estar vacío")
	private String telefono;
	
	@JsonProperty(value="Contraseña", required = true)
	@NotBlank(message = "El campo 'Contraseña' no puede estar vacío")
	private String contraseña;
	
	@JsonProperty(value="Estado", required=true)
	@NotBlank(message = "El campo 'Estado' debe recibir alguno de los siguientes valores: ['true', 'false']")
	@Pattern(regexp = "true|false", message = "El campo 'estado' debe recibir alguno de los siguientes valores: ['true', 'false']")
	private String estado;
		
	public ClienteDTO() {
	}	
	
	@JsonCreator
	public ClienteDTO(@NotBlank(message = "El campo 'Nombres' no puede estar vacío") String nombre,
			@NotBlank(message = "El campo 'Género' debe recibir alguno de los siguientes valores: ['true', 'false']") @Pattern(regexp = "Masculino|Femenino|Otro", message = "El campo 'Género' debe recibir alguno de los siguientes valores: ['Masculino', 'Femenino', 'Otro']") String genero,
			@Positive(message = "El campo 'Edad' debe ser mayor a cero") int edad,
			@NotBlank(message = "El campo 'Identificación' no puede estar vacío") String identificacion,
			@NotBlank(message = "El campo 'Dirección' no puede estar vacío") String direccion,
			@NotBlank(message = "El campo 'Teléfono' no puede estar vacío") String telefono,
			@NotBlank(message = "El campo 'Contraseña' no puede estar vacío") String contraseña,
			@NotBlank(message = "El campo 'Estado' debe recibir alguno de los siguientes valores: ['true', 'false']") @Pattern(regexp = "true|false", message = "El campo 'estado' debe recibir alguno de los siguientes valores: ['true', 'false']") String estado) {
		
		this.nombre = nombre;
		this.genero = genero;
		this.edad = edad;
		this.identificacion = identificacion;
		this.direccion = direccion;
		this.telefono = telefono;
		this.contraseña = contraseña;
		this.estado = estado;
	}










	public int getClienteId() {
		return clienteId;
	}

	public void setClienteId(int clienteId) {
		this.clienteId = clienteId;
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

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	@Override
	public String toString() {
		return "ClienteDTO [clienteId=" + clienteId + ", nombre=" + nombre + ", genero=" + genero + ", edad=" + edad
				+ ", identificacion=" + identificacion + ", direccion=" + direccion + ", telefono=" + telefono
				+ ", contraseña=" + contraseña + ", estado=" + estado + "]";
	}

	
		

}
