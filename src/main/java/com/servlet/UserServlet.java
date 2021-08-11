package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.dao.UserDAO;
import com.daoimpl.UserDAOImpl;
import com.google.gson.Gson;
import com.pojos.Author;
import com.pojos.User;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/UserServlet")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		boolean b=false;
		System.out.println("HELLO FROME SERVLET");
		
		String name=request.getParameter("name");
		
		String password=request.getParameter("password");
		
		UserDAO dao=new UserDAOImpl();
		
		List<User> list=dao.getAll();
		
		for(User user: list)
		{
			
			if(user.getUserName().equals(name)&& user.getPassword().equals(password))
			{
				response.sendRedirect("Home.html");
				b=true;
				break;
			}
			else
			{
				b=false;
			}
		}
		
		if(!b)
		{
			response.sendRedirect("Login.jsp");
		}
		
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
