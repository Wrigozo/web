package hu.neuron.database.entity;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
 
@Getter
@Setter
@ToString
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;
	
	@JoinColumn
	@ManyToOne
	private Category category;

	@JoinColumn
	@ManyToOne
	private Unit unit;
	
	private int quantity;

	private int purchasePrice;

	private int salePrice;

	private String description;

	@SuppressWarnings("unused")
	private Product() {
	}

	public Product(long id, String name, Category category, Unit unit, int quantity, int purchasePrice, int salePrice, String description) {
		this.id=id;
		this.name = name;
		this.category = category;
		this.unit = unit;
		this.quantity=quantity;
		this.purchasePrice = purchasePrice;
		this.salePrice = salePrice;
		this.description = description;
	}
	
}
