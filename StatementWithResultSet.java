package com.example.JDBC;
import java.sql.*;

public class StatementWithResultSet {

	public static void main(String[] args) throws SQLException {
		String jdbcURL = "jdbc:mysql://localhost:3306/teachersdb";
		String username = "root";
		String pwd = "K@lkidan1995";
		Connection connection = DriverManager.getConnection(jdbcURL,
				username, pwd);
				Statement statement = connection.createStatement();
				String query = "Select * from teachers";
				String createTableSQL = "CREATE TABLE teacher (" +
										"id int auto_increment primary key," +
										"first_name varchar(255)," +
										"last_name varchar(255)," +
										"school varchar(255)," +
										"hire_date date," +
										"salary decimal(10, 2))";
				statement.executeUpdate(createTableSQL);
				System.out.println("table 'teachers' created successfully. ");
	
		

				 String[] insertStatements = {
			                "INSERT INTO teacher(first_name, last_name, school, hire_date, salary) VALUES('Aster', 'Nega', 'Yekatit 12', '2021-01-01', 8000)",
			                "INSERT INTO teacher(first_name, last_name, school, hire_date, salary) VALUES('Jemal', 'Edris', 'Bole', '2021-09-11', 20000)",
			                "INSERT INTO teacher(first_name, last_name, school, hire_date, salary) VALUES('Haile', 'Anaol', 'Saris', '2022-01-22', 15000)",
			                "INSERT INTO teacher(first_name, last_name, school, hire_date, salary) VALUES('Teddy', 'Anbesaw', 'Bole', '2021-01-01', 8000)"
			            };

			            for (String stmt : insertStatements) {
			                statement.executeUpdate(stmt);
			            }
			            System.out.println("Data inserted successfully.");

			          
			            statement.close();
			            connection.close();

			        }
			    }
			