package jpdr.apps.devsu.apidemo.dtos.validations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = DateConstraintValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidateAsDate {
	
	public static final String MENSAJE = "El campo 'Fecha' es inválido. Se espera una fecha válida, en formato 'dd/mm/aaaa'";	
	
	public String message() default MENSAJE;
	
	public Class<?>[] groups() default {};  
	
	public Class<? extends Payload>[] payload() default {};  	

}
