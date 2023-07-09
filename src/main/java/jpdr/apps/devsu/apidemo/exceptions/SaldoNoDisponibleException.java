package jpdr.apps.devsu.apidemo.exceptions;

import java.text.DecimalFormat;

public class SaldoNoDisponibleException extends BasicException{
	
	private static final long serialVersionUID = -1861634837779287547L;

	public SaldoNoDisponibleException(double saldoActual) {
		super("Saldo no disponible. Saldo Actual: " + (new DecimalFormat("#.00")).format(saldoActual) );
	}

}
