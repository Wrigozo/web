package hu.neuron.database.repository;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import hu.neuron.database.repository.GenericDaoInterface;
import lombok.Getter;

public abstract class GenericDao<T> implements GenericDaoInterface<T> {

	protected Class<T> entityClass;

	@Getter
	protected EntityManager entityManager = Database.getEntityManager();

	public GenericDao(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	public GenericDao(Class<T> entityClass) {
		this.entityClass = entityClass;
	}

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

}
