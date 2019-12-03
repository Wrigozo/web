package hu.neuron.login;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

//@WebFilter(
//		urlPatterns = "/secured/*",
//		servletNames ="LoginServlet")
public class LoginFilter implements Filter {

	@Override
	public void doFilter(ServletRequest req, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();

		boolean a = (boolean) session.getAttribute("authenticated");
		System.out.print(a);
		if (a) {
			chain.doFilter(request, response);

		} else {
			System.out.print("nincs session");
		}

	}

}
