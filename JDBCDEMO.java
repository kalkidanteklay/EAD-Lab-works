package com.example.JDBC; 
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCDEMO {
	 public static void main(String[] args) {
	        String url = "jdbc:mysql://localhost:3306/students";
	        String user = "root"; 
	        String password = "K@lkidan1995"; 
	        String databaseName = "new_database";

	        try {
	            Connection connection = DriverManager.getConnection(url, user, password);
	            Statement statement = connection.createStatement();	 
	            String createDatabaseSQL = "CREATE DATABASE " + databaseName;
	            statement.executeUpdate(createDatabaseSQL);
	            System.out.println("Database " + databaseName + "created successfully.");
	            statement.close();
	           connection.close(); 
	        } catch (SQLException e) {
	            System.err.println("Connection failed: " + e.getMessage());
	        }
	    }

}
