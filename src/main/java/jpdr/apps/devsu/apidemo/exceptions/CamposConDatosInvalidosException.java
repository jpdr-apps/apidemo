package jpdr.apps.devsu.apidemo.exceptions;

import java.util.ArrayList;
import java.util.List;

import jpdr.apps.devsu.apidemo.dtos.errors.ValidationErrorItem;

public class CamposConDatosInvalidosException extends BasicException{

	private static final long serialVersionUID = -2975205535774117213L;

	private List<ValidationErrorItem> listaErrores;
	
	public CamposConDatosInvalidosException() {
		super("Se han encontrado valores incorrectos para los campos informados en la solicitud");
		this.listaErrores = new ArrayList<ValidationErrorItem>();
	}

	public List<ValidationErrorItem> getListaErrores() {
		return listaErrores;
	}

	public void addError(String mensaje, String valorRechazado) {
		this.listaErrores.add(new ValidationErrorItem(mensaje, valorRechazado));
	}
	
	
}
