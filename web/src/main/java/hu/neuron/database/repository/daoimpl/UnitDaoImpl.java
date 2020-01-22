package hu.neuron.database.repository.daoimpl;

import hu.neuron.database.entity.Unit;
import hu.neuron.database.repository.dao.GenericDao;
import hu.neuron.database.repository.dao.UnitDao;
import hu.neuron.warehouse.client.api.UnitVO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnitDaoImpl extends GenericDaoImpl<Unit, UnitVO> implements UnitDao{

	public UnitDaoImpl() {
		super(Unit.class, UnitVO.class);
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
