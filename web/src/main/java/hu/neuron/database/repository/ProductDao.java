package hu.neuron.database.repository;


import hu.neuron.database.entity.Product;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductDao extends GenericDao<Product> {
	
	public ProductDao() {
		super(Product.class);
	}

}