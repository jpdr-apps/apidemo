package jpdr.apps.devsu.apidemo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jpdr.apps.devsu.apidemo.entities.Parametro;
import jpdr.apps.devsu.apidemo.exceptions.ParametroNoExisteException;
import jpdr.apps.devsu.apidemo.exceptions.ValorDeParametroInvalidoException;
import jpdr.apps.devsu.apidemo.repositories.ParametrosRepository;

@Service
public class ParametrosService {
	
	@Autowired
	private ParametrosRepository parametrosRepository;
	
	
	public double getDoubleValue(String clave) {
		
		Parametro parametro = parametrosRepository.findById(clave).orElseThrow(
				() -> new ParametroNoExisteException());
		
		try {			
			return Double.parseDouble(parametro.getValor());			
		}catch (NumberFormatException e ) {
			throw new ValorDeParametroInvalidoException(clave);
		}
		 
	}

}
