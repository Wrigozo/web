package hu.neuron.database.repository;


import hu.neuron.database.entity.Unit;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UnitDao extends GenericDao<Unit>{

	public UnitDao() {
		super(Unit.class);
	}
	
}
