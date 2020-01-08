package hu.neuron.warehouse.client.api;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UnitVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private String name;
	
	public UnitVO() {
		
	}
	
	public UnitVO(String name) {
		this.name=name;
	}

	

}
