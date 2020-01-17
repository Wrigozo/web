package hu.neuron.database.repository.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;
import javax.persistence.TypedQuery;

import hu.neuron.database.entity.Category;
import hu.neuron.database.entity.Product;
import hu.neuron.database.entity.Unit;
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
		String sql = "SELECT p FROM Product p";

		TypedQuery<Product> typedQuery = entityManager.createQuery(sql, Product.class);
		typedQuery.setFirstResult(start);
		typedQuery.setMaxResults(recordsPerPage);

		//System.out.print(typedQuery.getResultList());

		return typedQuery.getResultList();

	}

	public Optional<Product> findByCategory(String name) {
		Product entity = entityManager
				.createQuery("SELECT p FROM Product p inner join Category c WHERE c.name = :name", Product.class)
				.setParameter("name", name).getSingleResult();
		return entity != null ? Optional.of(entity) : Optional.empty();
	}

	public int getNumberOfRows(Category category, Unit unit, String search) {

		long row = entityManager.createQuery(
				"SELECT COUNT(Id) FROM Product p WHERE (:category is null or p.category = :category) AND (:unit is null or p.unit = :unit) AND (:search is null or p.name LIKE CONCAT('%', :search,'%'))",
				Long.class).setParameter("category", category).setParameter("unit", unit).setParameter("search", search)
				.getSingleResult();

		// System.out.print("numOfRows"+numOfRows);

		return (int) row;
	}

	public int getNumberOfRows() {

		long numOfRows = 4;

		String sql = "SELECT COUNT(Id)FROM Product ";

		numOfRows = entityManager.createQuery(sql, Long.class).getSingleResult();

		return (int) numOfRows;
	}

	public List<Product> getByMultipleParameter(Category category, Unit unit, int recordsPerPage, int currentPage,
			String search) {
		return entityManager.createQuery(
				"SELECT p FROM Product p WHERE (:category is null or p.category = :category) AND (:unit is null or p.unit = :unit) AND (:search is null or p.name LIKE CONCAT('%', :search,'%'))",
				Product.class).setParameter("category", category).setParameter("unit", unit)
				.setParameter("search", search).setMaxResults(recordsPerPage)
				.setFirstResult(recordsPerPage * (currentPage - 1)).getResultList();
	}
}