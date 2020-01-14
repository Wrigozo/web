package hu.neuron.service;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import org.modelmapper.ModelMapper;

import com.google.gson.reflect.TypeToken;

import hu.neuron.config.DatabaseUtil;
import hu.neuron.database.entity.Category;
import hu.neuron.database.entity.Product;
import hu.neuron.database.entity.Unit;
import hu.neuron.database.repository.dao.CategoryDao;
import hu.neuron.database.repository.dao.ProductDao;
import hu.neuron.database.repository.dao.UnitDao;
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

		Connection conn = DatabaseUtil.getConnection();
		
		ProductDao productDao = new ProductDao();
		CategoryDao categoryDao=new CategoryDao();
		UnitDao unitDao=new UnitDao();
		ModelMapper modelMapper = new ModelMapper();
		
		Category category=categoryDao.findCategoryByName(request.getParameter("category"));
		Unit unit=unitDao.findUnitByName(request.getParameter("unit"));
		String search=request.getParameter("search");
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));

		System.out.println("\ncurrentpage: " + currentPage);
		System.out.println("recordsPerPage: " + recordsPerPage);
		System.out.println("search: " + search);
		System.out.println("category: " + request.getParameter("category"));
		System.out.println("unit: " + request.getParameter("unit"));

		List<ProductVO> products = new ArrayList<ProductVO>();
		List<Product> entityProducts = productDao.getByMultipleParameter(category, unit, recordsPerPage, currentPage, search);

		int rows = entityProducts.size();

		int nOfPages = rows / recordsPerPage;

		// a két if sorrendje fontos
		if (nOfPages % recordsPerPage > 0) {
			nOfPages++;
		}

		if (currentPage > nOfPages) {
			currentPage=1;
		}
		

		for (Product product : entityProducts) {
			products.add(modelMapper.map(product, ProductVO.class));
		}

		System.out.print("rows: "+rows+"noOfPages " + nOfPages + " currentPage " + currentPage + " recordsPerPage " + recordsPerPage);

		return products;
	}

	@SuppressWarnings("unchecked")
	@GET
	@Path("/getNumberOfPages")
	@Produces(MediaType.APPLICATION_JSON)
	public int getNumberOfPages(@Context HttpServletRequest request)
			throws SQLException, ClassNotFoundException, UnsupportedEncodingException {

		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));

		ProductDao productDao = new ProductDao();

		int rows = productDao.getNumberOfRows();

		int nOfPages = rows / recordsPerPage;

		// a két if sorrendje fontos
		if (nOfPages % recordsPerPage > 0) {
			nOfPages++;
		}

		System.out.print("noOfPages " + nOfPages + " currentPage " + currentPage + " recordsPerPage " + recordsPerPage);

		return nOfPages;

	}

	@SuppressWarnings("unchecked")
	@GET
	@Path("/getCategories")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CategoryVO> getCategory(@Context HttpServletRequest request)
			throws SQLException, ClassNotFoundException, UnsupportedEncodingException {

		List<CategoryVO> categories = new ArrayList<CategoryVO>();

		CategoryDao categoryDao = new CategoryDao();

		ModelMapper modelMapper = new ModelMapper();

		List<Category> entityCategories = categoryDao.findAll();

		categories = modelMapper.map(entityCategories, new TypeToken<List<CategoryVO>>() {
		}.getType());

		System.out.print(categories);

		return categories;

	}

	@SuppressWarnings("unchecked")
	@PUT
	@Path("/addProducts")
	@Produces(MediaType.APPLICATION_JSON)
	public Product putProduct(ProductVO productVO)
			throws SQLException, ClassNotFoundException, UnsupportedEncodingException {

		ModelMapper modelMapper = new ModelMapper();

		Product product = modelMapper.map(productVO, Product.class);
		System.out.print(productVO);
		ProductDao productDao = new ProductDao();
		productDao.save(product);
		
		return product;

	}
}

