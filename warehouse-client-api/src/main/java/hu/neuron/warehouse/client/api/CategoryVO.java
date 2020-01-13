package hu.neuron.warehouse.client.api;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;


@SuppressWarnings("serial")
@Getter
@Setter
@ToString
public class CategoryVO implements Serializable{

	/**
	 * Represents a catogory value object.
	 */
	
	private String name;
	
	public CategoryVO() {
	}
	
	public CategoryVO(String name) {
		this.name=name;
	}


}
