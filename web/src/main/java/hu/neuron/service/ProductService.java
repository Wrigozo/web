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

import hu.neuron.config.DatabaseUtil;
import hu.neuron.database.entity.Product;
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

		hu.neuron.database.service.ProductService productService = new hu.neuron.database.service.ProductService();
		ModelMapper modelMapper = new ModelMapper();

		List<Product> entityProducts = productService.findAll();

		for (Product product : entityProducts) {
			products.add(modelMapper.map(product, ProductVO.class));
		}
		
		return products;
	}

}
