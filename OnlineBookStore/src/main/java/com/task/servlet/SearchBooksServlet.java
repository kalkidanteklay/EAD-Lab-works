package com.task.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet("/searchBooks")
public class SearchBooksServlet extends HttpServlet {

	 private static final long serialVersionUID = 1L;

	    
	    private DBConnectionManager dbManager = new DBConnectionManager();

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response)
	            throws ServletException, IOException {
	       
	        String searchQuery = request.getParameter("title");

	        
	        response.setContentType("text/html");
	        PrintWriter out = response.getWriter();

	        try {
	           
	            dbManager.openConnection();
	            Connection conn = dbManager.getConnection();

	            
	            String query = "SELECT * FROM Books WHERE description LIKE ?";
	            PreparedStatement stmt = conn.prepareStatement(query);

	          
	            stmt.setString(1, "%" + searchQuery + "%");

	            
	            ResultSet rs = stmt.executeQuery();

	          
	            out.println("<html>");
	            out.println("<head><title>Search Results</title></head>");
	            out.println("<body>");
	            out.println("<h2>Search Results for: \"" + searchQuery + "\"</h2>");

	            if (!rs.isBeforeFirst()) {
	                out.println("<p>No books found.</p>");
	            } else {
	               
	                out.println("<table border='1' cellspacing='0' cellpadding='5'>");
	                out.println("<tr><th>ID</th><th>Title</th><th>Author</th><th>Price</th></tr>");

	                while (rs.next()) {
	                    int id = rs.getInt("id");
	                    String title = rs.getString("title");
	                    String author = rs.getString("author");
	                    String price = rs.getString("price");

	                    out.println("<tr>");
	                    out.println("<td>" + id + "</td>");
	                    out.println("<td>" + title + "</td>");
	                    out.println("<td>" + author + "</td>");
	                    out.println("<td>" + price + "</td>");
	                    out.println("</tr>");
	                }

	                out.println("</table>");
	            }

	            out.println("</body>");
	            out.println("</html>");

	           
	            rs.close();
	            stmt.close();
	        } catch (Exception e) {
	            
	            e.printStackTrace();
	            out.println("<h2>An error occurred while searching for books.</h2>");
	        } finally {
	            try {
	              
	                dbManager.closeConnection();
	            } catch (Exception ex) {
	                ex.printStackTrace();
	            }
	        }
	    }
	}