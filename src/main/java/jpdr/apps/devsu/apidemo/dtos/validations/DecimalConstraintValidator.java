package jpdr.apps.devsu.apidemo.dtos.validations;

import java.math.BigDecimal;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DecimalConstraintValidator implements ConstraintValidator<ValidateAsDecimal, Double> {
		
	private int integerDigits;
	private int fractionalDigits;
	
	public void initialize( ValidateAsDecimal validateAsDecimal ) {
		
		this.integerDigits = validateAsDecimal.integerDigits();
		this.fractionalDigits = validateAsDecimal.fractionalDigits();
	}
	
	@Override
	public boolean isValid(Double value, ConstraintValidatorContext context) {
		
		if(value==null) {
			return false;
		}

		try {
			BigDecimal bigDecimal = new BigDecimal(Double.toString(value));
			int integerPart = bigDecimal.intValue();			
			if(String.valueOf(integerPart).length() > integerDigits ) {
				return false;
			}
			if(bigDecimal.subtract(new BigDecimal(integerPart)).toPlainString().length() > fractionalDigits + 2) {
				return false;
			}
		} catch (NumberFormatException nfe) {
			return false;
		}

		return true;
	}
	

}
