package jpdr.apps.devsu.apidemo.exceptions;

public class MovimientoNoExisteException extends NoExisteException{

	private static final long serialVersionUID = 542297643689887694L;

	public MovimientoNoExisteException() {
		super("El movimiento solicitado no existe");
	}
	
}
