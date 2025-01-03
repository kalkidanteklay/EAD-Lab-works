package com.task.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


import jakarta.servlet.ServletConfig;
import com.task.servlet.DBConnectionManager;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;




@WebServlet("/registerBook")
public class ModifiedBookRegistrationServlet extends HttpServlet{
	private DBConnectionManager dbConnectionManager;
	
	public BookRegistrationServlet(DBConnectionManager dbConnectionManager) {
        this.dbConnectionManager = dbConnectionManager;
    }
	
	

	@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        String description = request.getParameter("description");
        String status = request.getParameter("status");
        String dueDate = request.getParameter("due_date");

        try (Connection connection = dbConnectionManager.getConnection()) {
            String query = "INSERT INTO Taks (description, status, due_date) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, description);
            statement.setString(2, status);
            statement.setString(3, dueDate);
            statement.executeUpdate();

            out.println("<p>Task registered successfully!</p>");
        } catch (Exception e) {
            out.println("<p>Error: " + e.getMessage() + "</p>");
        }
	    }
	@Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

       
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");

        
        this.dbConnectionManager = (DBConnectionManager) context.getBean("dbConnectionManager");
    }
	}


