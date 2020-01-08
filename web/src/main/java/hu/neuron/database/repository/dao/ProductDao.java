package hu.neuron.database.repository.dao;


import java.util.Optional;

import hu.neuron.database.entity.Product;
import hu.neuron.database.repository.GenericDao;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class ProductDao extends GenericDao<Product> {
	
	public ProductDao() {
		super(Product.class);
	}
	
	
	public Optional<Product> findByCategory(String name) {
		Product entity = entityManager
				.createQuery("SELECT p FROM Product p inner join Category c WHERE c.name = :name", Product.class)
				.setParameter("name", name).getSingleResult();
		return entity != null ? Optional.of(entity) : Optional.empty();
	}

}