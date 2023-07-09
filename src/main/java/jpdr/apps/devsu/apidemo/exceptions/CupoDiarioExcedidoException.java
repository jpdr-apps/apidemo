package jpdr.apps.devsu.apidemo.exceptions;

import java.text.DecimalFormat;

public class CupoDiarioExcedidoException extends BasicException{

	private static final long serialVersionUID = -452044882044803003L;

	public CupoDiarioExcedidoException(double sumaRetiros) {
		super("Cupo diario excedido. Sumatoria de Retiros Actual: " + (new DecimalFormat("#.00")).format(sumaRetiros) );
	}
}
