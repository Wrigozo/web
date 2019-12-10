package hu.neuron.services;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import hu.neuron.warehouse.client.api.Product;

@Path("/ProductService")
public class ProductService {
	
	@SuppressWarnings("unchecked")
	@GET
	@Path("/getProducts")
	@Produces(MediaType.APPLICATION_JSON)
	public List<Product> getProduct(@Context HttpServletRequest request) {
		
		request.getSession();
		List<Product> products=(List<Product>) request.getSession().getAttribute("products");
		
		return products;
	}

}
