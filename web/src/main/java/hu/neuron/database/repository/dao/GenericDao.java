package hu.neuron.database.repository.dao;

import java.util.List;
import java.util.Optional;

public interface GenericDao<T,V> {

	Optional<T> save(T entity);

	void update(T entity);

	Optional<T> findById(long id);

	Optional<T> findByName(String name);

	List<T> findAll();

	void delete(T entity);

	void deleteAll();
	
	T convertToEntity(V value);
	
	V convertToValue(T entity); 
	
	List<T> convertToEntityList(List<V> valueList); 
	
	List<V> convertToValueList(List<T> entityList); 
}
