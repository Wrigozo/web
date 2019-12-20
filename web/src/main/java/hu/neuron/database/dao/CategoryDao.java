package hu.neuron.database.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import hu.neuron.database.daoimpl.CategoryDaoInterface;
import hu.neuron.database.entity.Category;
import hu.neuron.database.entity.Product;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CategoryDao implements CategoryDaoInterface<Category>{

	private Session currentSession;

	private Transaction currentTransaction;

	public CategoryDao() {
	}

	public Session openCurrentSession() {
		currentSession = getSessionFactory().openSession();
		return currentSession;
	}

	public Session openCurrentSessionwithTransaction() {
		currentSession = getSessionFactory().openSession();
		currentTransaction = currentSession.beginTransaction();
		return currentSession;
	}

	public void closeCurrentSession() {
		currentSession.close();
	}

	public void closeCurrentSessionwithTransaction() {
		currentTransaction.commit();
		currentSession.close();
	}

	private static SessionFactory getSessionFactory() {
		Configuration configuration = new Configuration().configure();
		StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
				.applySettings(configuration.getProperties());
		SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
		return sessionFactory;
	}
	
	@Override
	public void persist(Category category) {
		
		getCurrentSession().save(category);
	}

	@Override
	public void update(Category category) {
		
		getCurrentSession().update(category);
	}

	@Override
	public Category findById(long id) {
		
		Category category = (Category) getCurrentSession().get(Category.class, id);
		return category;
	}

	@Override
	public void delete(Category category) {
		
		getCurrentSession().delete(category);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Category> findAll() {
		
		List<Category> categories = (List<Category>) getCurrentSession().createQuery("select c from Category c").list();
		return categories;
	}

	@Override
	public void deleteAll() {
		
		List<Category> categoryList = findAll();
		for (Category category : categoryList) {
			delete(category);
		}
	}




}
