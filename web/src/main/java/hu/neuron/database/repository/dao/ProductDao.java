package hu.neuron.database.repository.dao;

import java.util.List;
import java.util.Optional;

import hu.neuron.database.entity.Category;
import hu.neuron.database.entity.Product;
import hu.neuron.database.entity.Unit;
import hu.neuron.warehouse.client.api.ProductVO;

public interface ProductDao extends GenericDao<Product, ProductVO>{

	List<Product> findAll(int currentPage, int recordsPerPage);

	Optional<Product> findByCategory(String name);

	int getNumberOfRows(Category category, Unit unit, String search);

	int getNumberOfRows() ;

	List<Product> getByMultipleParameter(Category category, Unit unit, int recordsPerPage, int currentPage,
			String search) ;
}
