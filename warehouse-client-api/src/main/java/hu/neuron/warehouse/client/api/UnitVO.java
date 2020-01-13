package hu.neuron.warehouse.client.api;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@ToString
public class UnitVO implements Serializable{

	/**
	 * Represents a unit value object.
	 */
	
	private String name;
	
	public UnitVO() {
		
	}
	
	public UnitVO(String name) {
		this.name=name;
	}

	

}
