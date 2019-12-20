package hu.neuron.warehouse.client.api;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ProductVO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private long id;

	private String name;

	private CategoryVO category;

	private UnitVO unit;

	private int quantity;

	private int purchasePrice;

	private int salePrice;

	private String description;

	public ProductVO() {
	}

	public ProductVO(int id, String name, CategoryVO category, UnitVO unit, int quantity, int purchasePrice,
			int salePrice, String description) {
		this.id = id;
		this.name = name;
		this.category = category;
		this.unit = unit;
		this.quantity = quantity;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.description = description;
	}

	public ProductVO(int id, String name, String category, String unit, int quantity, int purchasePrice, int salePrice,
			String description) {
		this.id = id;
		this.name = name;
		this.category = new CategoryVO(category);
		this.unit = new UnitVO(unit);
		this.quantity = quantity;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.description = description;
	}

}
