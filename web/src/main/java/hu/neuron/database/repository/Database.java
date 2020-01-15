package hu.neuron.database.repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Database {

	public static final EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence
			.createEntityManagerFactory("ProductManagement");

	public static EntityManager getEntityManager() {
		return ENTITY_MANAGER_FACTORY.createEntityManager();
	}

	public static void close() {
		ENTITY_MANAGER_FACTORY.close();
	}
}
