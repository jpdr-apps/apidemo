package jpdr.apps.devsu.apidemo.exceptions;

public class ValorDeParametroInvalidoException extends ErrorDeParametroException {

	public ValorDeParametroInvalidoException(String claveParametro) {
		super("Ha ocurrido un error al procesar el siguiente parámetro: " + claveParametro);
	}
}
