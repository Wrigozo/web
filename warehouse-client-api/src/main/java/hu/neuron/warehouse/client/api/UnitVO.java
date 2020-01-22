package hu.neuron.warehouse.client.api;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a unit value object.
 */

@SuppressWarnings("serial")
@Getter
@Setter
@ToString
public class UnitVO implements Serializable{

	private String name;
	
	public UnitVO() {
		
	}
	
	public UnitVO(String name) {
		this.name=name;
	}
}
