package hu.neuron.warehouse.client.api;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductVO {
	
	private int id;

	private String name;

	private CategoryVO category;

	private UnitVO unit;

	private int purchasePrice;

	private int salePrice;

	private String description;

	public ProductVO() {
	}

	public ProductVO(int id, String name, CategoryVO category, UnitVO unit, int purchasePrice, int salePrice, String description) {
		this.id=id;
		this.name = name;
		this.category = category;
		this.unit = unit;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.description = description;
	}
	
	public ProductVO(int id, String name, String category, String unit, int purchasePrice, int salePrice, String description) {
		this.id=id;
		this.name = name;
		this.category = new CategoryVO(category);
		this.unit = new UnitVO(unit);
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.description = description;
	}
	
	public String getCategory() {
		return this.category.getCategory();
	}
	
	public String getUnit() {
		return this.unit.getUnit();
	}


}
