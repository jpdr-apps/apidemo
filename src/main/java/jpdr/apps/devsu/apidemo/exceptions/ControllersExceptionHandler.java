package jpdr.apps.devsu.apidemo.exceptions;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BeanPropertyBindingResult;

import org.springframework.validation.FieldError;

import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;

import jpdr.apps.devsu.apidemo.dtos.errors.ErrorDTO;
import jpdr.apps.devsu.apidemo.dtos.errors.ExceptionErrorDTO;
import jpdr.apps.devsu.apidemo.dtos.errors.ValidationErrorDTO;
import jpdr.apps.devsu.apidemo.dtos.errors.ValidationErrorItem;
import jpdr.apps.devsu.apidemo.log.ControllerResponseLogger;

@RestControllerAdvice
public class ControllersExceptionHandler{
	
	@Autowired
	private ControllerResponseLogger controllerResponseLogger;

	
	@ExceptionHandler(org.springframework.http.converter.HttpMessageNotReadableException.class)
	public ResponseEntity<ExceptionErrorDTO> handleException(org.springframework.http.converter.HttpMessageNotReadableException e) {
	
		ExceptionErrorDTO exceptionErrorDTO = new ExceptionErrorDTO(
				"Existe un error en el formato JSON de la solicitud", 
				e.getClass().toString(), 
				e.getMessage());

		ResponseEntity<ExceptionErrorDTO> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(exceptionErrorDTO);
		controllerResponseLogger.log(this.getClass(), "handleException", response);
		return response;
		
	}
	
	
	
	@ExceptionHandler(MissingServletRequestParameterException.class)
	public ResponseEntity<ErrorDTO> handleException(MissingServletRequestParameterException e) {
		
		ErrorDTO errorDTO = new ErrorDTO("Parámetro faltante en la URL recibida. El parámetro '" + 
				 e.getParameterName() + "' no ha sido informado."
		);
		

		ResponseEntity<ErrorDTO> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
		controllerResponseLogger.log(this.getClass(), "handleException", response);
		return response;
		
	}
	
	@ExceptionHandler(MethodArgumentTypeMismatchException.class)
	public ResponseEntity<ErrorDTO> handleException(MethodArgumentTypeMismatchException e) {
		
		ErrorDTO errorDTO = new ErrorDTO("Error en tipo de dato recibido como parámetro en la URL. El parámetro '" + 
				 e.getName() + "' debe ser del tipo " + e.getRequiredType().getName()
		);
		

		ResponseEntity<ErrorDTO> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
		controllerResponseLogger.log(this.getClass(), "handleException", response);
		return response;
		
	}
	
	@ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationErrorDTO> handleException(org.springframework.web.bind.MethodArgumentNotValidException e){

		ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO("Se han encontrado valores incorrectos para los campos informados en la solicitud");
		
		BeanPropertyBindingResult bindingResult = (BeanPropertyBindingResult) e.getBindingResult();		
		List<FieldError> listaErrores = bindingResult.getFieldErrors();
		
		
		for (FieldError fieldError : listaErrores) {
			
			validationErrorDTO.addError(
					fieldError.getDefaultMessage(), 	
					fieldError.getRejectedValue() != null ?	fieldError.getRejectedValue().toString() : "Nulo, vacío, o se omitió informar el campo" );		
		}						
					
		ResponseEntity<ValidationErrorDTO> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrorDTO);
		controllerResponseLogger.log(this.getClass(), "handleException", response);
		return response;
		
	}
	
	
	@ExceptionHandler(NoHandlerFoundException.class)
	public ResponseEntity<ErrorDTO> handleException(NoHandlerFoundException exception){
	
		ErrorDTO errorDTO = new ErrorDTO("La URL solicitada es inválida.");		
		ResponseEntity<ErrorDTO> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
		controllerResponseLogger.log(this.getClass(), "handleException", response);
		return response;	

	}
	
	
	@ExceptionHandler(CamposConDatosInvalidosException.class)
	public ResponseEntity<ErrorDTO> handleException(CamposConDatosInvalidosException exception){
								
		ValidationErrorDTO validationErrorDTO = new ValidationErrorDTO("Se han encontrado valores incorrectos para los campos informados en la solicitud");
			
		List<ValidationErrorItem> listaErrores = exception.getListaErrores();
			
			for (ValidationErrorItem fieldError : listaErrores) {
				validationErrorDTO.addError(
					fieldError.getMensaje(), 
					fieldError.getValorRechazado() != null ?	fieldError.getValorRechazado() : "Nulo, vacío, o se omitió informar el campo" );			
		}						
							
		ResponseEntity<ErrorDTO> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrorDTO);
		controllerResponseLogger.log(this.getClass(), "handleException", response);
		return response;
				
	}
	
	
	@ExceptionHandler(NoExisteException.class)
	public ResponseEntity<ErrorDTO> handleException(NoExisteException exception){

			ErrorDTO errorDTO = new ErrorDTO(exception.getMessage());		

			ResponseEntity<ErrorDTO> response = ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDTO);	
			controllerResponseLogger.log(this.getClass(), "handleException", response);
			return response;		
		
	}
	
	@ExceptionHandler(BasicException.class)
	public ResponseEntity<ErrorDTO> handleException(BasicException exception){

			ErrorDTO errorDTO = new ErrorDTO(exception.getMessage());		

			ResponseEntity<ErrorDTO> response = ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDTO);
			controllerResponseLogger.log(this.getClass(), "handleException", response);
			return response;	
		
	}
	
	
	@ExceptionHandler(ErrorDeParametroException.class)
	public ResponseEntity<ErrorDTO> handleException(ErrorDeParametroException exception){
		ErrorDTO errorDTO = new ErrorDTO(exception.getMessage());		

		ResponseEntity<ErrorDTO> response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
		controllerResponseLogger.log(ErrorDeParametroException.class, "handleException", response);
		return response;	


	}
	
	
	@ExceptionHandler(ErrorInternoException.class)
	public ResponseEntity<ErrorDTO> handleException(ErrorInternoException exception){
		ErrorDTO errorDTO = new ErrorDTO(exception.getMessage());		
		
		ResponseEntity<ErrorDTO> response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDTO);
		controllerResponseLogger.log(ErrorInternoException.class, "handleException", response);
		return response;

	}
	
	
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ExceptionErrorDTO> handleException(Exception e) {	
		
		ExceptionErrorDTO exceptionErrorDTO = new ExceptionErrorDTO(
				"Ha ocurrido una excepción al procesar la solicitud.", 
				e.getClass().toString(), 
				e.getMessage());
 		
		ResponseEntity<ExceptionErrorDTO> response = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(exceptionErrorDTO);
		controllerResponseLogger.log(Exception.class, "handleException", response);
		return response;
				
	}
	
}
