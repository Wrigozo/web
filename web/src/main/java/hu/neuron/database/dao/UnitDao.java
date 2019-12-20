package hu.neuron.database.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import hu.neuron.database.daoimpl.UnitDaoInterface;
import hu.neuron.database.entity.Unit;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnitDao implements UnitDaoInterface<Unit>{

	private Session currentSession;

	private Transaction currentTransaction;

	public UnitDao() {
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
	public void persist(Unit unit) {

		getCurrentSession().save(unit);
	}

	@Override
	public void update(Unit unit) {

		getCurrentSession().update(unit);
	}

	@Override
	public Unit findById(long id) {

		Unit product = (Unit) getCurrentSession().get(Unit.class, id);
		return product;
	}

	@Override
	public void delete(Unit unit) {
		getCurrentSession().delete(unit);

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Unit> findAll() {

		List<Unit> unites = (List<Unit>) getCurrentSession().createQuery("select u from Unit u").list();
		return unites;
	}

	@Override
	public void deleteAll() {

		List<Unit> unitList = findAll();
		for (Unit unit : unitList) {
			delete(unit);
		}
	}

	
}
