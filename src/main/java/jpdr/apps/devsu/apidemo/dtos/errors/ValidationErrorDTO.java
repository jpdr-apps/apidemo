package jpdr.apps.devsu.apidemo.dtos.errors;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ValidationErrorDTO extends ErrorDTO {

	@JsonProperty("Errores detectados")
	private ArrayList<ValidationErrorItem> errores;

	public ValidationErrorDTO(String descripcion) {
		super(descripcion);
		this.errores = new ArrayList<ValidationErrorItem>();
	}

	public List<ValidationErrorItem> getErrores() {
		return errores;
	}
			
	public void addError(String mensaje, String valorRechazado) {		
		errores.add( new ValidationErrorItem(mensaje, valorRechazado));		
	}

	@Override
	public String toString() {
		return "ValidationErrorDTO [errores=" + errores + "]";
	}
	
	
	
}
