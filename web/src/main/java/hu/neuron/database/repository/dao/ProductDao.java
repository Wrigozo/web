package hu.neuron.database.repository.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.TypedQuery;

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

	public List<Product> findAll(int currentPage, int recordsPerPage) {

		int start = currentPage * recordsPerPage - recordsPerPage;
		String sql = "FROM Product";

		TypedQuery<Product> typedQuery = entityManager.createQuery(sql, Product.class);
		typedQuery.setFirstResult(start);
		typedQuery.setMaxResults(recordsPerPage);
		
		System.out.print(typedQuery.getResultList());
		
		return typedQuery.getResultList();

	}

	public Optional<Product> findByCategory(String name) {
		Product entity = entityManager
				.createQuery("SELECT p FROM Product p inner join Category c WHERE c.name = :name", Product.class)
				.setParameter("name", name).getSingleResult();
		return entity != null ? Optional.of(entity) : Optional.empty();
	}

	public int getNumberOfRows() {

		long numOfRows = 4;

		
			String sql = "SELECT COUNT(Id) FROM Product";

			numOfRows = entityManager.createQuery(sql, Long.class).getSingleResult();

			System.out.print(entityManager.createQuery(sql, Long.class).getSingleResult().getClass());
			
		return (int)numOfRows;
	}

}