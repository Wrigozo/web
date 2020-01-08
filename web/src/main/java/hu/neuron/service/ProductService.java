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

	@SuppressWarnings("unchecked")
	@GET
	@Path("/getProducts")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductVO> getProduct(@Context HttpServletRequest request)
			throws SQLException, ClassNotFoundException, UnsupportedEncodingException {

		List<ProductVO> products = new ArrayList<ProductVO>();

		Connection conn = DatabaseUtil.getConnection();

		ProductDao productDao = new ProductDao();

		ModelMapper modelMapper = new ModelMapper();

		List<Product> entityProducts = productDao.findAll();

		for (Product product : entityProducts) {
			products.add(modelMapper.map(product, ProductVO.class));
		}
		System.out.println("Entity:");
		System.out.println(entityProducts);
		System.out.println("\n VO:");
		System.out.println(products);

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
