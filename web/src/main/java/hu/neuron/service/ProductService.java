package hu.neuron.service;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.modelmapper.ModelMapper;

import com.google.gson.reflect.TypeToken;

import hu.neuron.config.DatabaseUtil;
import hu.neuron.database.entity.Category;
import hu.neuron.database.entity.Product;
import hu.neuron.database.repository.dao.CategoryDao;
import hu.neuron.database.repository.dao.ProductDao;
import hu.neuron.warehouse.client.api.CategoryVO;
import hu.neuron.warehouse.client.api.ProductVO;

/**
 * Singleton class.
 * 
 * @author szaboa
 *
 */
@Path("/ProductService")
public class ProductService {

	@GET
	@Path("/getProducts")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductVO> getProduct(@Context HttpServletRequest request)
			throws SQLException, ClassNotFoundException, UnsupportedEncodingException {

		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));

		System.out.println("\ncurrentpage" + currentPage);
		System.out.println("recordsPerPage" + recordsPerPage);

		List<ProductVO> products = new ArrayList<ProductVO>();

		Connection conn = DatabaseUtil.getConnection();

		ProductDao productDao = new ProductDao();

		ModelMapper modelMapper = new ModelMapper();

		List<Product> entityProducts = productDao.findAll(currentPage, recordsPerPage);

		for (Product product : entityProducts) {
			products.add(modelMapper.map(product, ProductVO.class));
		}

		System.out.println("Entity:");
		System.out.println(entityProducts);
		System.out.println("\n VO:");
		System.out.println(products);

		request.setAttribute("countries", products);

		int rows = productDao.getNumberOfRows();

		int nOfPages = rows / recordsPerPage;

		if (nOfPages % recordsPerPage > 0) {
			nOfPages++;
		}

		request.setAttribute("noOfPages", nOfPages);
		request.setAttribute("currentPage", currentPage);
		request.setAttribute("recordsPerPage", recordsPerPage);

		return products;
	}

	@SuppressWarnings("unchecked")
	@GET
	@Path("/getCategories")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CategoryVO> getCategory(@Context HttpServletRequest request)
			throws SQLException, ClassNotFoundException, UnsupportedEncodingException {

		List<CategoryVO> categories = new ArrayList<CategoryVO>();
		
		CategoryDao categoryDao=new CategoryDao();
		
		ModelMapper modelMapper = new ModelMapper();
		
		List<Category> entityCategories = categoryDao.findAll();
		
		categories=modelMapper.map(entityCategories, new TypeToken<List<CategoryVO>>() {}.getType());
		
		System.out.print(categories);
		
		return categories;

	}

}

//public List<Product> getByMultipleParameter(Category category, Unit unit, int itemCount, int page, String search) {
//	return em.createQuery(
//			"SELECT p FROM Product p WHERE (:cat is null or p.category = :cat) AND (:unit is null or p.unit = :unit) AND (:search is null or p.name LIKE CONCAT('%', :search,'%'))",
//			Product.class).setParameter("cat", category).setParameter("unit", unit).setParameter("search", search)
//			.setMaxResults(itemCount).setFirstResult(itemCount * (page - 1)).getResultList();
	
	
	
	
	
	
	