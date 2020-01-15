package hu.neuron.database.repository.dao;

import hu.neuron.database.entity.Unit;
import hu.neuron.database.repository.GenericDao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnitDao extends GenericDao<Unit> {

	public UnitDao() {
		super(Unit.class);
	}

	public Unit findUnitByName(String name) {
		if (name.equals("")) {
			return null;
		}
		Unit entity = entityManager
				.createQuery("SELECT e FROM " + entityClass.getSimpleName() + " e WHERE e.name = :name", entityClass)
				.setParameter("name", name).getSingleResult();
		return entity;
	}
}
