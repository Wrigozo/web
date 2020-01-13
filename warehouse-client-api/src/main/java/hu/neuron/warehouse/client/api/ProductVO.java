package hu.neuron.warehouse.client.api;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@SuppressWarnings("serial")
@Getter
@Setter
@ToString
@AllArgsConstructor
public class ProductVO implements Serializable {

	/**
	 * Represents a product value object.
	 */

	private String name;

	private CategoryVO category;

	private UnitVO unit;

	private int quantity;

	private int purchasePrice;

	private int salePrice;

	private String description;

	public ProductVO() {
	}

	public ProductVO(String name, String category, String unit, int quantity, int purchasePrice, int salePrice,
			String description) {
		
		this.name = name;
		this.category = new CategoryVO(category);
		this.unit = new UnitVO(unit);
		this.quantity = quantity;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.description = description;
	}

}
