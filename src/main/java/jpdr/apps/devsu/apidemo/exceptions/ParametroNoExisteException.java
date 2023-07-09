package jpdr.apps.devsu.apidemo.exceptions;

public class ParametroNoExisteException extends ErrorDeParametroException{

	private static final long serialVersionUID = -2162019335999421468L;

	public ParametroNoExisteException() {
		super("El parámetro solicitado no existe");
	}
	
}
