package jpdr.apps.devsu.apidemo.dtos.errors;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidationErrorItem {

	@JsonProperty("Mensaje de Error")
	private String mensaje;
	
	@JsonProperty("Valor Rechazado")
	private String valorRechazado;
	
	public ValidationErrorItem(String mensaje, String valorRechazado) {
		this.mensaje = mensaje;
		this.valorRechazado = valorRechazado;
	}
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}	
	public String getValorRechazado() {
		return valorRechazado;
	}
	public void setValorRechazado(String valorRechazado) {
		this.valorRechazado = valorRechazado;
	}
	@Override
	public String toString() {
		return "ValidationErrorItem [mensaje=" + mensaje + ", valorRechazado=" + valorRechazado + "]";
	}
	
	

}
