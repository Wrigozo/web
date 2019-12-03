package hu.neuron.warehouse.client.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

	private String name;

	private String category;

	private int unit;

	private int purchasePrice;

	private int salePrice;

	private String description;

	public Product() {
	}

	public Product(String name, String category, int unit, int purchasePrice, int salePrice, String description) {
		this.name = name;
		this.category = category;
		this.unit = unit;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.description = description;
	}

}
