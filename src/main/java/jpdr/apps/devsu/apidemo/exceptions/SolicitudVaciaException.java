package jpdr.apps.devsu.apidemo.exceptions;

public class SolicitudVaciaException extends BasicException{
 
	private static final long serialVersionUID = -2354077048188110165L;

	public SolicitudVaciaException() {
		
		super("Solicitud inválida. Se debe informar al menos 1 campo para poder procesar la solicitud");
	
	}
	
}
