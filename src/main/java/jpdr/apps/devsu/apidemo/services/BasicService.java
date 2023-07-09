package jpdr.apps.devsu.apidemo.services;

import java.util.HashMap;

public interface BasicService<E,D> {
	
	public boolean exists(int entityId);
	public D get(int entityId);
	public D save(int entityId, D dto);
	public D patch(int entityId, HashMap<String, String> entries);
	public void delete(int entityId);
	public E getEntity(int entityId);

}
