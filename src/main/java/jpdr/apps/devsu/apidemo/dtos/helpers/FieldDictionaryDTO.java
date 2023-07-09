package jpdr.apps.devsu.apidemo.dtos.helpers;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.HashSet;

import com.fasterxml.jackson.annotation.JsonProperty;

import org.springframework.stereotype.Component;

import jpdr.apps.devsu.apidemo.dtos.ClienteDTO;
import jpdr.apps.devsu.apidemo.dtos.CuentaDTO;
import jpdr.apps.devsu.apidemo.dtos.MovimientoDTO;

@Component
public class FieldDictionaryDTO {
	
	private static FieldDictionaryDTO instance;
	private HashMap<Class<?>, HashSet<String>> entries;
	
	private FieldDictionaryDTO() {		
		entries = new HashMap<Class<?>, HashSet<String>>();
		entries.put(ClienteDTO.class, getJSONFields(ClienteDTO.class));
		entries.put(CuentaDTO.class, getJSONFields(CuentaDTO.class));
		entries.put(MovimientoDTO.class, getJSONFields(MovimientoDTO.class));		
	}
	
	public FieldDictionaryDTO getInstance() {
		
		if (instance==null) {
			synchronized (FieldDictionaryDTO.class) {
				if(instance==null) {
					instance = new FieldDictionaryDTO();
				}
			}
		}
		return instance;
	}
	
	private HashSet<String> getJSONFields(Class<?> clase) {
		
		HashSet<String> campos = new HashSet<String>();
		
		for (Field campo : clase.getDeclaredFields()) {				
			JsonProperty propiedad = campo.getAnnotation(JsonProperty.class);				
			if (propiedad !=null )
				campos.add(propiedad.value().toString());
		}			
		
		return campos;
		
	}
	
	public boolean validateJSONProperty(Class<?> clase, String propertyName ) {
		
		if (entries.containsKey(clase))
			if (entries.get(clase).contains(propertyName))
				return true;
		
		return false;
		
	}
	
}
