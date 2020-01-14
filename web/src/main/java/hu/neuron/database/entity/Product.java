package hu.neuron.database.entity;

import java.io.Serializable;

import javax.persistence.*;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
 

@SuppressWarnings("serial")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
public class Product implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@Column(unique = true)
	private String name;
	
	@JoinColumn
	@ManyToOne(cascade = CascadeType.ALL)
	private Category category;

	@JoinColumn
	@ManyToOne(cascade = CascadeType.ALL)
	private Unit unit;
	
	private int quantity;

	private int purchasePrice;

	private int salePrice;

	private String description;
	
}
