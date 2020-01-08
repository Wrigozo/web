package hu.neuron.warehouse.client.api;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class CategoryVO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private long id;
	
	private String name;
	
	public CategoryVO() {
	}
	
	public CategoryVO(String name) {
		this.name=name;
	}


}
