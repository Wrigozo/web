package hu.neuron.database.repository;

import java.util.List;
import java.util.Optional;

public interface GenericDaoInterface<T> {
	
	public Optional<T> save(T entity);

	public void update(T entity);

	public Optional<T> findById(long id);

	public Optional<T> findByName(String name);
	
	public List<T> findAll();
	
	public void delete(T entity);

	public void deleteAll();
}
