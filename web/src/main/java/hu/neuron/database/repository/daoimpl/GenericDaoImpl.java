package hu.neuron.database.repository.daoimpl;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;

import hu.neuron.database.repository.Database;
import hu.neuron.database.repository.dao.GenericDao;

import lombok.Getter;

public abstract class GenericDaoImpl<T,V> implements GenericDao<T,V> {

	protected Class<T> entityClass;
	protected Class<V> valueClass;
	protected ModelMapper modelMapper = new ModelMapper();

	@Getter
	protected EntityManager entityManager = Database.getEntityManager();

	public GenericDaoImpl(Class<T> entityClass,Class<V> valueClass) {
		this.entityClass = entityClass;
		this.valueClass=valueClass;
	}

	@Override
	public Optional<T> save(T entity) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(entity);
			entityManager.getTransaction().commit();
			return Optional.of(entity);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return Optional.empty();
	}

	@Override
	public void update(T entity) {
		entityManager.merge(entity);
	}

	@Override
	public Optional<T> findById(long id) {
		T entity = entityManager.find(entityClass, id);
		return entity != null ? Optional.of(entity) : Optional.empty();
	}

	@Override
	public Optional<T> findByName(String name) {
		T entity = entityManager
				.createQuery("SELECT e FROM " + entityClass.getSimpleName() + "e WHERE e.name = :name", entityClass)
				.setParameter("name", name).getSingleResult();
		return entity != null ? Optional.of(entity) : Optional.empty();
	}

	@Override
	public List<T> findAll() {
		TypedQuery<T> typedQuery = entityManager.createQuery("FROM " + entityClass.getSimpleName(), entityClass);
		return typedQuery.getResultList();
	}

	@Override
	public void delete(T entity) {
		entityManager.remove(entity);
	}

	@Override
	public void deleteAll() {
		for (T entity : findAll()) {
			delete(entity);
		}
	}
	
	@Override
	public T convertToEntity(V value) {
		return modelMapper.map(value,entityClass);		
	}
	
	@Override
	public V convertToValue(T entity) {
		return modelMapper.map(entity,valueClass);		
	}
	
	@Override
	public List<T> convertToEntityList(List<V> valueList) {
		return modelMapper.map(valueList,new TypeToken<List<T>>() {}.getType());		
	}
	
	@Override
	public List<V> convertToValueList(List<T> entityList) {
		return modelMapper.map(entityList,new TypeToken<List<V>>() {}.getType());		
	}

}
