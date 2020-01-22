package hu.neuron.warehouse.client.api;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * Represents a catogory value object.
 */

@SuppressWarnings("serial")
@Getter
@Setter
@ToString
public class CategoryVO implements Serializable{
	
	private String name;
	
	public CategoryVO() {
	}
	
	public CategoryVO(String name) {
		this.name=name;
	}
}
