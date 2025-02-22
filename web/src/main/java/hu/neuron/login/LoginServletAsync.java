package hu.neuron.login;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

import hu.neuron.login.modell.Result;


@SuppressWarnings("serial")
public class LoginServletAsync extends HttpServlet {

	Map<String, Boolean> map = new HashMap<String, Boolean>();

	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

		String name = req.getParameter("username");
		String password = req.getParameter("password");
		Gson gson = new Gson();
		HttpSession session;
		Result r = new Result(false);

		if (name != null && password != null) {

			if (name.equals("admin") && password.equals("password")) {

				session = req.getSession();
				session.setAttribute("authenticated", true);
				r.setResult(true);

				res.setContentType("application/json");
				res.setCharacterEncoding("UTF-8");
				res.getWriter().print(gson.toJson(r));
			}

			else {

				res.getWriter().print(gson.toJson(r));

			}
			res.getWriter().flush();

		}
	}
}