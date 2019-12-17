package hu.neuron.login;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import hu.neuron.warehouse.client.api.Product;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		 
		String n = req.getParameter("username");
		
		String p = req.getParameter("password");

		Product product1 = new Product(3,"Sissi1", "tej", "liter", 100, 200, "nagyon finom");
		Product product2 = new Product(4,"Sissi2", "tej", "gramm", 100, 200, "nagyon finom");
		Product product3 = new Product(5,"Sissi3", "tej", "gramm", 100, 200, "nagyon finom");
		
		List<Product> products=new ArrayList<>();
		
		products.add(product1);
		products.add(product2);
		products.add(product3);

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