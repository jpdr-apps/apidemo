package jpdr.apps.devsu.apidemo.exceptions;

public class NombreDeCampoInvalidoException extends BasicException{

	private static final long serialVersionUID = 4697725430163814167L;

	public NombreDeCampoInvalidoException(String campoInvalido) {
		super("El campo '"+ campoInvalido +"' no es un nombre de campo válido para el recurso solicitado");
	}
	
}
