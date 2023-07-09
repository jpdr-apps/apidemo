package jpdr.apps.devsu.apidemo.exceptions;

public class CuentaNoExisteException extends NoExisteException{

	private static final long serialVersionUID = -8780147345320642723L;

	public CuentaNoExisteException() {
		super("La cuenta solicitada no existe");
	}
	
}
