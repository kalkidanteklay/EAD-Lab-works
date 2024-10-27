package com.example.JDBC;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class studentsdb {

	public static void main(String[] args) {
		String url = "jdbc:mysql://localhost:3306/teachersdb";
		String username = "root";
		String password = "K@lkidan1995";
		try {
			
			Connection connection = DriverManager.getConnection(url, username,
			password);
			Statement statement = connection.createStatement();
			statement.execute("CREATE TABLE IF NOT EXISTS students (id INT PRIMARY KEY, firstname VARCHAR(255), lastname VARCHAR(255), grade INT)");
			
			insertSampleData(connection);
			
			retrieveData(connection);
			
			updateStudentName(connection, 1, "UpdatedFirstName");
			
			deleteStudent(connection, 2);
			
			calculateAverageGrade(connection);
			
			statement.close();
			connection.close();
			} catch (Exception e) {
			e.printStackTrace();
			}
			}

			private static void insertSampleData(Connection connection) {
			try {
			
			PreparedStatement insertSingle = connection.prepareStatement("INSERT INTO students (id, firstname, lastname, grade) VALUES (?, ?, ?, ?)");
			insertSingle.setInt(1, 1);
			insertSingle.setString(2, "John");
			insertSingle.setString(3, "Doe");
			insertSingle.setInt(4, 90);
			insertSingle.executeUpdate();
			
			} catch (Exception e) {
			e.printStackTrace();
			}
			}
			private static void retrieveData(Connection connection) {
			try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT * FROM students LIMIT 5");
			while (resultSet.next()) {
			int id = resultSet.getInt("id");
			String firstname = resultSet.getString("firstname");
			String lastname = resultSet.getString("lastname");
			int grade = resultSet.getInt("grade");
			System.out.println("ID: " + id + ", Name: " + firstname + " " + lastname
			+ ", Grade: " + grade);
			}
			} catch (Exception e) {
			e.printStackTrace();
			}
			}
			private static void updateStudentName(Connection connection, int id, String
					newFirstName) {
					try {
					PreparedStatement updateStatement =
					connection.prepareStatement("UPDATE students SET firstname = ? WHERE id = ?");
					updateStatement.setString(1, newFirstName);
					updateStatement.setInt(2, id);
					updateStatement.executeUpdate();
					} catch (Exception e) {
					e.printStackTrace();
					}
					}
					private static void deleteStudent(Connection connection, int id) {
					try {
					PreparedStatement deleteStatement =
					connection.prepareStatement("DELETE FROM students WHERE id = ?");
					deleteStatement.setInt(1, id);
					deleteStatement.executeUpdate();
					} catch (Exception e) {
					e.printStackTrace();
					}
					}
					private static void calculateAverageGrade(Connection connection) {
					try {
					Statement statement = connection.createStatement();
					ResultSet resultSet = statement.executeQuery("SELECT AVG(grade) AS average_grade FROM students");
					while (resultSet.next()) {
					double averageGrade = resultSet.getDouble("average_grade");
					System.out.println("Average Grade: " + averageGrade);
					}
					} catch (Exception e) {
					e.printStackTrace();
					}
					

					
	}

}
