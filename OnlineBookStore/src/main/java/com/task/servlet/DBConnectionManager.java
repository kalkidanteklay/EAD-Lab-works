package com.task.servlet;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class DBConnectionManager {
    private Connection connection;
    private String url = "jdbc:mysql://localhost:3306/BookstoreDB"; 
    private String username = "root";                         
    private String password = "root";                   

   
    public void openConnection() throws SQLException, ClassNotFoundException {
        
        Class.forName("com.mysql.cj.jdbc.Driver");

     
        connection = DriverManager.getConnection(url, username, password);
        System.out.println("Database connection established successfully.");
    }

   
    public void closeConnection() throws SQLException {
        if (connection != null && !connection.isClosed()) {
            connection.close();
            System.out.println("Database connection closed.");
        }
    }
}
