package hu.neuron.service;

import java.io.UnsupportedEncodingException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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

	private ProductDao productDao = new ProductDao();
	private CategoryDao categoryDao = new CategoryDao();
	private UnitDao unitDao = new UnitDao();
	private ModelMapper modelMapper = new ModelMapper();

	@GET
	@Path("/getProducts")
	@Produces(MediaType.APPLICATION_JSON)
	public List<ProductVO> getProduct(@Context HttpServletRequest request)
			throws SQLException, ClassNotFoundException, UnsupportedEncodingException {

		Connection conn = DatabaseUtil.getConnection();

		Category category = categoryDao.findCategoryByName(request.getParameter("category"));
		Unit unit = unitDao.findUnitByName(request.getParameter("unit"));
		String search = request.getParameter("search");
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));

		List<ProductVO> products = new ArrayList<ProductVO>();

		int rows = productDao.getNumberOfRows(category, unit, search);

		int nOfPages = rows / recordsPerPage;

		// a két if sorrendje fontos
		if (rows % recordsPerPage > 0) {
			nOfPages++;
		}

		if (currentPage > nOfPages) {
			currentPage = 1;
		}

		// fontos, hogy besettelje előtte a currentpage-t
		List<Product> entityProducts = productDao.getByMultipleParameter(category, unit, recordsPerPage, currentPage,
				search);

		for (Product product : entityProducts) {
			products.add(modelMapper.map(product, ProductVO.class));
		}

		return products;
	}

	@SuppressWarnings("unchecked")
	@GET
	@Path("/getNumberOfPages")
	@Produces(MediaType.APPLICATION_JSON)
	public int getNumberOfPages(@Context HttpServletRequest request)
			throws SQLException, ClassNotFoundException, UnsupportedEncodingException {

		Category category = categoryDao.findCategoryByName(request.getParameter("category"));
		Unit unit = unitDao.findUnitByName(request.getParameter("unit"));
		String search = request.getParameter("search");
		int currentPage = Integer.parseInt(request.getParameter("currentPage"));
		int recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));

		int rows = productDao.getNumberOfRows(category, unit, search);

		int nOfPages = rows / recordsPerPage;

		// a két if sorrendje fontos a currentpage szempontjából
		if (rows % recordsPerPage > 0) {
			nOfPages++;
		}

		if (currentPage > nOfPages) {
			currentPage = 1;
		}

		return nOfPages;

	}

	@SuppressWarnings("unchecked")
	@GET
	@Path("/getCategories")
	@Produces(MediaType.APPLICATION_JSON)
	public List<CategoryVO> getCategory(@Context HttpServletRequest request)
			throws SQLException, ClassNotFoundException, UnsupportedEncodingException {

		List<CategoryVO> categories = new ArrayList<CategoryVO>();

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
	public Response putProduct(@Context HttpServletResponse response, ProductVO productVO)
			throws SQLException, ClassNotFoundException, UnsupportedEncodingException {

		StringBuilder error = new StringBuilder();

		// leellenőrizni, hogy nullosak-e

		if (productVO.getQuantity() < 1 || productVO.getPurchasePrice() < 1 || productVO.getSalePrice() < 1) {
			if (productVO.getQuantity() < 1) {
				error.append("A mennyiség nem lehet 1-től kisebb.\n");
			}
			if (productVO.getPurchasePrice() < 1) {
				error.append("A vásárlási ár nem lehet 1-től kisebb.\n");
			}
			if (productVO.getSalePrice() < 1) {
				error.append("Az eladási ár nem lehet 1-től kisebb.");
			}
			return Response.status(400).entity(error).build();
		}

		Product product = modelMapper.map(productVO, Product.class);
		System.out.print(productVO);
		productDao.save(product);

		return Response.status(200).entity(product).build();

	}
}
