package hu.neuron.warehouse.client.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Product {

	private String name;

	private String category;

	private String unit;

	private int purchasePrice;

	private int salePrice;

	private String description;

	public Product() {
	}

	public Product(String name, String category, String unit, int purchasePrice, int salePrice, String description) {
		this.name = name;
		this.category = category;
		this.unit = unit;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.description = description;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public int getPurchasePrice() {
		return purchasePrice;
	}

	public void setPurchasePrice(int purchasePrice) {
		this.purchasePrice = purchasePrice;
	}

	public int getSalePrice() {
		return salePrice;
	}

	public void setSalePrice(int salePrice) {
		this.salePrice = salePrice;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	

}
