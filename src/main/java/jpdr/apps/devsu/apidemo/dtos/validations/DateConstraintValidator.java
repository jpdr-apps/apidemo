package jpdr.apps.devsu.apidemo.dtos.validations;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;
import java.util.Date;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class DateConstraintValidator implements ConstraintValidator<ValidateAsDate, String> {


	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		
		if(value==null) {
			return false;
		}
		try {			
			SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
			dateFormat.setLenient(false);
			dateFormat.parse(value);		
		}catch (ParseException e) {
			return false;
		}
		
		return true;
	}

}