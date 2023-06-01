package com.srccodes.example;

import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.databind.ObjectMapper;

public class Service {
	String getStudent = "SELECT * FROM student WHERE id = 110";
	
	public String onGet() {
		List<Student> students = new ArrayList<>();
		ToJson toJson = new ToJson();
		ResultSet postgres = new Postgres().getConnection(getStudent);
		try {
			while (postgres.next()) {
			Student student = new Student();
			student.setId(Integer.parseInt(postgres.getString(1)));
			student.setName(postgres.getString(2));
			student.setSurname(postgres.getString(3));
			student.setLast_name(postgres.getString(4));
			students.add(student);
}
		} catch (NumberFormatException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 
		 return toJson.objectToJson(students);
	}

	public void  onPost(HttpServletRequest request) {
		Postgres postgres = new Postgres();
		ObjectMapper mapper = new ObjectMapper();
		try {
			Student student = mapper.readValue(inputStreamToString(request.getInputStream()), Student.class);	
			postgres.getConnection(
					"INSERT INTO student (name, surname, last_name) VALUES ('" 
							+ student.getName() +
							"', '" +
							student.getSurname() +
							"', '"  
							+ student.getLast_name()
							+ "');"
						);	
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onPut(HttpServletRequest request) {
		Postgres postgres = new Postgres();
		ObjectMapper mapper = new ObjectMapper();
		try {
			Student student = mapper.readValue(inputStreamToString(request.getInputStream()), Student.class);
					postgres.getConnection( "UPDATE student SET name = '"
			+ student.getName() +
			"', surname = '"
			+ student.getSurname() +
			"', last_name = '" 
			+ student.getLast_name() 
			+ "' WHERE id = " +
			student.getId() +
			"");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onDelete() {
		Postgres postgres = new Postgres();	
		postgres.getConnection( "DELETE FROM student WHERE id = 14 ;");
	}
	
	private static String inputStreamToString(InputStream inputStream) {
		Scanner scanner = new Scanner(inputStream, "UTF-8");
		return scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
	}
}
