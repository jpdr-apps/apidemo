package jpdr.apps.devsu.apidemo.dtos.errors;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ErrorDTO {
	
	@JsonProperty("Descripción del Error")
	private String descripcion;
	
	public ErrorDTO() {}
	
	public ErrorDTO(String descripcion) {		 
		this.descripcion = descripcion;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@Override
	public String toString() {
		return "ErrorDTO [descripcion=" + descripcion + "]";
	}

	
	

	
}
