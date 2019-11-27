package hu.neuron.login;

import javax.servlet.http.*;
import javax.servlet.*;
import java.io.*;
import javax.servlet.http.HttpSession;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	
	@Override
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		//res.setContentType("text/html");// setting the content type
		
		String n=req.getParameter("username");  
	    String p=req.getParameter("password"); 
	  
	  if(n!=null&&p!=null)  {
		    if(n.equals("admin")&&p.equals("password")) {
		    	HttpSession session = req.getSession(true);
		    	session.setAttribute("authenticated", true);
		    	res.sendRedirect("/warehouse/secured/profil.html");
		    }
		    else {
		    	res.sendRedirect("login.html");
		    }
	    }
	  
	}
}