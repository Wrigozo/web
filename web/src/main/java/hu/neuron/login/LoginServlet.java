package hu.neuron.login;

import java.io.*;
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
		// res.setContentType("text/html");// setting the content type

		String n = req.getParameter("username");
		String p = req.getParameter("password");

		//Product product = new Product("Sissi", "tej", 1, 100, 200, "nagyon finom");

		HttpSession session = req.getSession(true);
		if (n != null && p != null) {
			if (n.equals("admin") && p.equals("password")) {

				session.setAttribute("authenticated", true);
				//session.setAttribute("product", product);
				
				res.sendRedirect("/warehouse/secured/profil.html");
			} else {
				session.setAttribute("authenticated", false);
				res.sendRedirect("login.jsp");
			}
		}

	}
}