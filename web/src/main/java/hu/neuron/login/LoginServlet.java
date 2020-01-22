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
import hu.neuron.database.repository.daoimpl.ProductDaoImpl;
import hu.neuron.database.repository.daoimpl.UnitDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
//@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	private static final int recordsPerPage = 5;
	private static final int currentPage = 1;

	private ProductDao productDao = new ProductDaoImpl();

	private UnitDao unitDao = new UnitDaoImpl();

	private ModelMapper modelMapper = new ModelMapper();

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String name = req.getParameter("username");

		String password = req.getParameter("password");

		List<ProductVO> products = new ArrayList<>();
		List<UnitVO> units = new ArrayList<>();

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

		if (name != null && password != null) {
			if (name.equals("admin") && password.equals("password")) {

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