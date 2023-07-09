package jpdr.apps.devsu.apidemo.exceptions;

public class ClienteNoExisteException extends NoExisteException{
	
	private static final long serialVersionUID = -8726572300604758606L;

	public ClienteNoExisteException() {
		super("El cliente solicitado no existe");
	}

}
