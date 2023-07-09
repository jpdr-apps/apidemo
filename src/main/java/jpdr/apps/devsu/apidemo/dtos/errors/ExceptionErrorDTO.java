package jpdr.apps.devsu.apidemo.dtos.errors;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ExceptionErrorDTO extends ErrorDTO {
	
	@JsonProperty("Clase de la Excepción")
	private String claseExcepcion;
	
	@JsonProperty("Mensaje de la Excepción")
	private String mensajeExcepcion;
	
	public ExceptionErrorDTO() {}

	public ExceptionErrorDTO(String descripcion, String claseExcepcion, String mensajeExcepcion) {
		super(descripcion);
		this.claseExcepcion = claseExcepcion;
		this.mensajeExcepcion = mensajeExcepcion;
	}

	public String getClaseExcepcion() {
		return claseExcepcion;
	}

	public void setClaseExcepcion(String claseExcepcion) {
		this.claseExcepcion = claseExcepcion;
	}

	public String getMensajeExcepcion() {
		return mensajeExcepcion;
	}

	public void setMensajeExcepcion(String mensajeExcepcion) {
		this.mensajeExcepcion = mensajeExcepcion;
	}

	@Override
	public String toString() {
		return "ExceptionErrorDTO [claseExcepcion=" + claseExcepcion + ", mensajeExcepcion=" + mensajeExcepcion + "]";
	}

	 
	
}
