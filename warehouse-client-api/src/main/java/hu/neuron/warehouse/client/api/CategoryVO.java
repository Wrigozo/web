package hu.neuron.warehouse.client.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CategoryVO {

	private String category;
	
	public CategoryVO() {
	}
	
	public CategoryVO(String category) {
		this.category=category;
	}
	
	public String getCategory() {
		return category;
	}
}
