package jpdr.apps.devsu.apidemo.controllers;

import java.util.HashMap;

import org.springframework.http.ResponseEntity;

public interface BasicController<D> {
	
	public ResponseEntity<D> post(D dto);
	public ResponseEntity<D> get(int entityId);
	public ResponseEntity<D> put(int entityId, D dto);
	public ResponseEntity<D> patch(int entityId, HashMap<String, String> entries);
	public ResponseEntity<D> delete(int entityId);

}
