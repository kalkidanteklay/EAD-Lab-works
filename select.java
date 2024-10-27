package com.example.JDBC;
import java.sql.*;

public class select {

	public static void main(String[] args) throws SQLException {
		String jdbcURL = "jdbc:mysql://localhost:3306/teachersdb";
		String username = "root";
		String pwd = "K@lkidan1995";
		Connection connection = DriverManager.getConnection(jdbcURL,
		username, pwd);
		Statement statement = connection.createStatement();
		String query = "Select * from teacher";
		ResultSet res = statement.executeQuery(query);
		
		while(res.next()) {
			int id = res.getInt("id");
			String fname = res.getString("first_name");
			
			Date hire_date = res.getDate("hire_date");
			double salary = res.getDouble("salary");
			System.out.println("Teacher ID: " + id);
			System.out.println("Teacher Name: " + fname);
			System.out.println("Teacher Hire Date: " + hire_date);
			System.out.println("Teacher Salary: " + salary);
			System.out.println("...............");
			}
			res.close();
			statement.close(); 
			connection.close();
			}
			
		

	}


