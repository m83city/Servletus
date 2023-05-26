package com.srccodes.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Postgres {
	String url = "jdbc:postgresql://localhost/postgres?user=postgres&password=Welcome4$";

	public String getConnection(RequestMethod method, String queryUrl) {
		ToJson toJson = new ToJson();
		List<Student> students = new ArrayList<>();

		String getAllQuery = "SELECT * FROM student";
		String deleteQuery = "";
		String putQuery = "";
		String getStudent = "SELECT * FROM student WHERE id = 22";
		String ns = "Super";

		String postQuery = "INSERT INTO student ("+ns+", surname, last_name) VALUES ('Josh Obrian', 'stsdesadave', 'asdasdhed');";
		try {
			Connection conn = DriverManager.getConnection(url);
			Statement statement = conn.createStatement();

			switch (method) {
			case GET: {

				ResultSet resultSet = statement.executeQuery(queryUrl);
				while (resultSet.next()) {
					Student student = new Student();
					student.setId(Integer.parseInt(resultSet.getString(1)));
					student.setName(resultSet.getString(2));
					student.setSurname(resultSet.getString(3));
					student.setLast_name(resultSet.getString(4));
					students.add(student);
				}	
			}
			
				break;
			case POST: {
				System.out.println("POST");
				ResultSet resultSet = statement.executeQuery(queryUrl);
				resultSet.close();
				statement.close();
			}
			case DELETE: {
				System.out.println("DELETE");
				ResultSet resultSet = statement.executeQuery(queryUrl);
				resultSet.close();
				statement.close();
			}
			case PUT: {
				System.out.println("PUT");
				ResultSet resultSet = statement.executeQuery(queryUrl);
				resultSet.close();
				statement.close();
			}
			

			default:
				break;
			}

//			ResultSet resultSet = statement.executeQuery("SELECT * FROM student");
//
//			while (resultSet.next()) {
//				System.out.print(resultSet.getString(1) + "");
//				System.out.print(resultSet.getString(2) + "");
//				System.out.print(resultSet.getString(3) + "");
//				System.out.print(resultSet.getString(4) + "");
//				System.out.println("");
//
//				student.setId(Integer.parseInt(resultSet.getString(1)));
//				student.setName(resultSet.getString(2));
//				student.setName(resultSet.getString(3));
//				student.setName(resultSet.getString(4));
//
//				students.add(student);
//
//			}



			return toJson.objectToJson(students);

		} catch (Exception e) {
			System.out.println(e);
			return e.toString();
		}

	}

	public void getAll() {

	}

	public void put() {

	}

	public void post() {

	}

	public void getStudent() {

	}

	public void delete() {

	}

}

/*
 * 
 * package com.srccodes.example; import java.sql.Connection; import
 * java.sql.DriverManager; import java.sql.ResultSet; import java.sql.Statement;
 * import java.util.ArrayList; import java.util.List; import
 * com.srccodes.example.RequestMethod;
 * 
 * import com.fasterxml.jackson.databind.ObjectMapper; import
 * com.fasterxml.jackson.databind.ObjectWriter;
 * 
 * 
 * 
 * public class Postgres { String url =
 * "jdbc:postgresql://localhost/postgres?user=postgres&password=Welcome4$";
 * 
 * public String getConnection (RequestMethod method) { String[] cars =
 * {"Volvo", "BMW", "Ford", "Mazda"}; Student student = new Student(); ToJson
 * toJson = new ToJson(); List<Student> students = new ArrayList<>(); try {
 * Connection conn = DriverManager.getConnection(url); Statement statement =
 * conn.createStatement();
 * 
 * switch (method) { case GET:{
 * 
 * 
 * } break; case POST:
 * 
 * break; case DELETE:
 * 
 * break; case PUT:
 * 
 * break;
 * 
 * default: break; }
 * 
 * 
 * ResultSet resultSet = statement.executeQuery("SELECT * FROM student");
 * //Class.forName("org.postgresql.Driver");
 * 
 * while (resultSet.next()) { System.out.println(resultSet.getString(2));
 * 
 * student.setId(Integer.parseInt(resultSet.getString(1)));
 * student.setName(resultSet.getString(2));
 * student.setName(resultSet.getString(3));
 * student.setName(resultSet.getString(4));
 * 
 * students.add(student);
 * 
 * }
 * 
 * resultSet.close(); statement.close();
 * 
 * return toJson.objectToJson(students);
 * 
 * 
 * } catch (Exception e) { System.out.println(e); return e.toString(); }
 * 
 * }
 * 
 * 
 * }
 * 
 * 
 * 
 */
