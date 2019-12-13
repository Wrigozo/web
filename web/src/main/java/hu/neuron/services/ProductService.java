package hu.neuron.services;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import hu.neuron.config.DatabaseUtil;
import hu.neuron.config.StartingListener;
import hu.neuron.warehouse.client.api.Product;

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
	public List<Product> getProduct(@Context HttpServletRequest request) throws SQLException, ClassNotFoundException {

		List<Product> products = new ArrayList<>();

		Statement stmt = DatabaseUtil.getConnection().createStatement();

		ResultSet rs = stmt.executeQuery("SELECT * FROM product;");

		while (rs.next()) {
			String name = rs.getString("name");
			String category = rs.getString("category");

			String unit = rs.getString("unit");

			int purchasePrice = rs.getInt("purchasePrice");

			int salePrice = rs.getInt("salePrice");

			String description = rs.getString("description");

			products.add(new Product(name, category, unit, purchasePrice, salePrice, description));

		}

		return products;
	}

}
