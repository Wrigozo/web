package hu.neuron.login;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;

import hu.neuron.database.service.ProductService;
import hu.neuron.warehouse.client.api.ProductVO;
import hu.neuron.database.entity.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private ProductService productService = new ProductService();
	
	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		List<ProductVO> products = new ArrayList<>();

		String n = req.getParameter("username");

		String p = req.getParameter("password");

		List<Product> entityProducts = productService.findAll();

		for (Product product : entityProducts) {
			products.add(modelMapper.map(product, ProductVO.class));
		}

//        for(ProductVO q:products) {
//        	System.out.println(q.getName());
//        }

		HttpSession session = req.getSession(true);

		if (n != null && p != null) {
			if (n.equals("admin") && p.equals("password")) {

				session.setAttribute("authenticated", true);
				session.setAttribute("products", products);

				res.sendRedirect("/web/secured/profil.html");

			} else {
				session.setAttribute("authenticated", false);
				res.sendRedirect("login.jsp");
			}
		}

	}
}