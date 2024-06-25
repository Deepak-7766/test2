package org.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.DbConnection.DBConnectionClass;

//@WebServlet("/regester")
@WebServlet("/regester")
public class RegesterPage extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		String name=request.getParameter("name");
		String email=request.getParameter("email");
		String contact =request.getParameter("contact");
		String password=request.getParameter("password");
		out.println(name);
		out.println(email);
		out.println(contact);
		out.println(password);
		
		try {
			Connection con= DBConnectionClass.getConnection();
			PreparedStatement stmt=con.prepareStatement("insert into login values(?,?,?,?)");
			stmt.setString(1, name);
			stmt.setString(2,email);
			stmt.setString(3, contact);
			stmt.setString(4, password);
			
			int res=stmt.executeUpdate();
			if(res>0) {
				out.println("<h4 style="+"color:green"+">Regester Successfully..</h4>");
				RequestDispatcher rd= request.getRequestDispatcher("/login.html"); 
				rd.include(request, response);
				
			}
			else {
				
			}
			
		}catch(Exception e) {
			e.printStackTrace();
		}
				
	}

}
