package jpdr.apps.devsu.apidemo.dtos.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = DecimalConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateAsDecimal {
	
	public static final String MENSAJE = "El valor de este campo debe ser un número válido ( 16 dígitos enteros y 2 decimales como máximo)";	
			
	public String message() default MENSAJE;
	
	public Class<?>[] groups() default {};  
	
	public Class<? extends Payload>[] payload() default {};  
	
	int integerDigits();
	int fractionalDigits();

}
