package com.bridgeit.bean;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Registration extends HttpServlet {

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		response.setContentType("text/html");
		PrintWriter out = response.getWriter();

		try {

			Class.forName("com.mysql.jdbc.Driver");
			Connection c = DriverManager.getConnection("jdbc:mysql://localhost:3306/login", "root", "arpana"); // gets a

			PreparedStatement ps = c.prepareStatement("insert into login values(?,?)");

			String n = request.getParameter("userName");
			String p = request.getParameter("password");
			ps.setString(1, n);
			ps.setString(2, p);

			Pojo pojo = new Pojo();
			pojo.setUsername(n);
			pojo.setPassword(p);

			int i = ps.executeUpdate();
			if (i > 0)
				out.print("You are successfully registered...");

		} catch (Exception e2) {
			System.out.println(e2);
		}
		out.close();
	}

}
