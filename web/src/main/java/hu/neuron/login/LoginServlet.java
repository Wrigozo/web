package hu.neuron.login;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.modelmapper.ModelMapper;

import com.google.gson.reflect.TypeToken;

import hu.neuron.warehouse.client.api.ProductVO;
import hu.neuron.warehouse.client.api.UnitVO;
import hu.neuron.database.entity.Product;
import hu.neuron.database.entity.Unit;
import hu.neuron.database.repository.dao.ProductDao;
import hu.neuron.database.repository.dao.UnitDao;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private ProductDao productDao = new ProductDao();

	private UnitDao unitDao = new UnitDao();

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String n = req.getParameter("username");

		String p = req.getParameter("password");

		List<ProductVO> products = new ArrayList<>();
		List<UnitVO> units = new ArrayList<>();

		int recordsPerPage = 5;
		int currentPage = 1;

		List<Product> entityProducts = productDao.findAll(currentPage, recordsPerPage);
		List<Unit> entityUnits = unitDao.findAll();
		
		for (Product product : entityProducts) {
			products.add(modelMapper.map(product, ProductVO.class));
		}

		units = modelMapper.map(entityUnits, new TypeToken<List<UnitVO>>() {
		}.getType());

		int rows = productDao.getNumberOfRows();
		int nOfPages = rows / recordsPerPage;

		if (nOfPages % recordsPerPage > 0) {
			nOfPages++;
		}

		HttpSession session = req.getSession(true);

		if (n != null && p != null) {
			if (n.equals("admin") && p.equals("password")) {

				session.setAttribute("authenticated", true);
				session.setAttribute("products", products);
				session.setAttribute("units", units);
				session.setAttribute("nOfPages", nOfPages);
				session.setAttribute("recordsPerPage", recordsPerPage);
				session.setAttribute("currentPage", currentPage);
				res.sendRedirect("/web/secured/profil.html");

			} else {
				session.setAttribute("authenticated", false);
				res.sendRedirect("login.jsp");
			}
		}

	}
}