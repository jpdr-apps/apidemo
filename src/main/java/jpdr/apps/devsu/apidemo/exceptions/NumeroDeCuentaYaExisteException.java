package jpdr.apps.devsu.apidemo.exceptions;

public class NumeroDeCuentaYaExisteException extends BasicException{

	public NumeroDeCuentaYaExisteException() {
		super("El número de cuenta informado ya existe");
	}
	
}
