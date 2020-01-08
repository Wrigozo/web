package hu.neuron.database.repository.dao;


import hu.neuron.database.entity.Unit;
import hu.neuron.database.repository.GenericDao;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnitDao extends GenericDao<Unit>{

	public UnitDao() {
		super(Unit.class);
	}
	
}
