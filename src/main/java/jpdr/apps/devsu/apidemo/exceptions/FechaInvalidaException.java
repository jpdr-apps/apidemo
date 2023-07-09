package jpdr.apps.devsu.apidemo.exceptions;

public class FechaInvalidaException extends BasicException{

	private static final long serialVersionUID = 257067514922778280L;

	public FechaInvalidaException(String fechaInformada) {
		 super("La fecha informáda es inválida. Valor informado: "+fechaInformada);
	}

}
