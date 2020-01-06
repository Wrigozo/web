package hu.neuron.database.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import hu.neuron.database.daoimpl.ProductDaoInterface;
import hu.neuron.database.entity.Product;
import hu.neuron.warehouse.client.api.ProductVO;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductDao implements ProductDaoInterface<Product> {

	private Session currentSession;

	private Transaction currentTransaction;

	public ProductDao() {
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
	public void persist(Product product) {

		getCurrentSession().save(product);
	}

	@Override
	public void update(Product product) {

		getCurrentSession().update(product);
	}

	@Override
	public Product findById(long id) {

		Product product = (Product) getCurrentSession().get(Product.class, id);
		return product;
	}

	@Override
	public void delete(Product product) {
		getCurrentSession().delete(product);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> findAll() {

		List<Product> products = (List<Product>) getCurrentSession().createQuery("select p from Product p").list();
		
		System.out.println("All products in DAO:");
		for (Product product : products) {
			System.out.println(product);
		}
		return products;
	}

	@Override
	public void deleteAll() {

		List<Product> productList = findAll();
		for (Product product : productList) {
			delete(product);
		}
	}

	

}