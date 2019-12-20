package hu.neuron.database.entity;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
 
@Getter
@Setter
@ToString
@Entity
@Table(name = "product")
public class Product {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	private String name;

	@ManyToOne
	private String category;

	@ManyToOne
	private String unit;
	
	private int quantity;

	private int purchasePrice;

	private int salePrice;

	private String description;

	private Product() {
	}

	public Product(long id, String name, String category, String unit, int quantity, int purchasePrice, int salePrice, String description) {
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
