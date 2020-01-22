package hu.neuron.database.repository.dao;

import hu.neuron.database.entity.Unit;
import hu.neuron.warehouse.client.api.UnitVO;

public interface UnitDao extends GenericDao<Unit, UnitVO>{

	Unit findUnitByName(String name);
}
