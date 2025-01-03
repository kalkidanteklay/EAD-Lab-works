package com.task.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/register")
public class BookRegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

   
    private DBConnectionManager dbManager = new DBConnectionManager();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String price = request.getParameter("price");

       
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
            
            dbManager.openConnection();
            Connection conn = dbManager.getConnection();

          
            String query = "INSERT INTO Books (title, author, price) VALUES (?, ?, ?)";
            PreparedStatement stmt = conn.prepareStatement(query);

            
            stmt.setString(1, title);
            stmt.setString(2, author);
            stmt.setString(3, price);

            
            int rowsAffected = stmt.executeUpdate();

            
            if (rowsAffected > 0) {
                out.println("<h2>Book registered successfully!</h2>");
            } else {
                out.println("<h2>Book registration failed!</h2>");
            }

           
            stmt.close();
        } catch (Exception e) {
            
            e.printStackTrace();
            out.println("<h2>An error occurred while registering the task.</h2>");
        } finally {
            try {
                
                dbManager.closeConnection();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}
