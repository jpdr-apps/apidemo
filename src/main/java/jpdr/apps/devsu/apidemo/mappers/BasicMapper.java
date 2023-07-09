package jpdr.apps.devsu.apidemo.mappers;

public interface BasicMapper<E,D> {
	
	public E fromDTO(D dto);
	public D fromEntity(E entity);
	
}
