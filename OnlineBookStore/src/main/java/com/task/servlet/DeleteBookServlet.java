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


@WebServlet("/deleteBook")
public class DeleteBookServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    
    private DBConnectionManager dbManager = new DBConnectionManager();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
       
        String bookId = request.getParameter("book_id");

        
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
           
            dbManager.openConnection();
            Connection conn = dbManager.getConnection();

            
            String query = "DELETE FROM Books WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);

          
            stmt.setInt(1, Integer.parseInt(bookId));

           
            int rowsAffected = stmt.executeUpdate();

            
            if (rowsAffected > 0) {
                out.println("<h2>Book deleted successfully!</h2>");
            } else {
                out.println("<h2>No book found with the given ID!</h2>");
            }

           
            stmt.close();
        } catch (Exception e) {
            
            e.printStackTrace();
            out.println("<h2>An error occurred while deleting the book.</h2>");
        } finally {
            try {
               
                dbManager.closeConnection();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}