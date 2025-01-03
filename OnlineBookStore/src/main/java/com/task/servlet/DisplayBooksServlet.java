package com.task.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


@WebServlet("/display-books")
public class DisplayBooksServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

   
    private DBConnectionManager dbManager = new DBConnectionManager();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();

        try {
           
            dbManager.openConnection();
            Connection conn = dbManager.getConnection();

            
            String query = "SELECT * FROM Books";
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(query);

          
            out.println("<html><head><title>Books</title></head><body>");
            out.println("<h1>Task List</h1>");
            out.println("<table border='1'>");
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
            out.println("</body></html>");

            rs.close();
            stmt.close();
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h2>An error occurred while retrieving tasks.</h2>");
        } finally {
            try {
                
                dbManager.closeConnection();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }
}