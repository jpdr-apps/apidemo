package jpdr.apps.devsu.apidemo.log;

import java.util.function.Consumer;
import java.util.function.Function;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import jpdr.apps.devsu.apidemo.dtos.helpers.FieldDictionaryDTO;
import jpdr.apps.devsu.apidemo.exceptions.ErrorDeParametroException;
import jpdr.apps.devsu.apidemo.exceptions.ErrorInternoException;


@Component
public class ControllerResponseLogger {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ControllerResponseLogger.class);
	
	private static ControllerResponseLogger instance ;
	
	private ControllerResponseLogger() {}
	
	public ControllerResponseLogger getInstance() {
	
		if (instance==null) {
			synchronized (ControllerResponseLogger.class) {
				if(instance==null) {
					instance = new ControllerResponseLogger();
				}
			}
		}
		return instance;
	}

	public void log(Class<?> claseOrigen, String metodo, ResponseEntity<?> responseEntity) {
		
		HttpStatusCode statusCode = responseEntity.getStatusCode();
		
		String statusCodeString = "";		
		if(statusCode!=null)
			statusCodeString = statusCode.toString();
		
		String headersString = "";
		if(responseEntity.getHeaders()!=null)
			headersString = responseEntity.getHeaders().toString();
		
		String bodyString = "";
		if(responseEntity.getBody()!=null)
			bodyString = responseEntity.getBody().toString();		
		
		Consumer<String> logLevelConsumer = LOGGER::debug; 
		if ( claseOrigen.equals(ErrorDeParametroException.class) || claseOrigen.equals(ErrorInternoException.class) || claseOrigen.equals(Exception.class))
			logLevelConsumer = LOGGER::error; 		
		 				
		logLevelConsumer.accept("CLASE ORIGEN [ "+claseOrigen.getName()+" ] METODO [ "+metodo+" ] HTTP STATUS CODE [ " + statusCodeString + " ] HEADERS [ "+headersString + " ]");
		logLevelConsumer.accept("DATOS [ "+bodyString+" ]");
	}
	
}
