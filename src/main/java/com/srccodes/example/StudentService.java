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

public class StudentService {
	
	public String onGet(HttpServletRequest request) {
		List<Student> students = new ArrayList<>();
		ToJson toJson = new ToJson();
//		String studentId = Integer.toString(mapper.readValue(inputStreamToString(request.getInputStream()), Student.class).getId());
		Student student = new Student();
		try {
			ObjectMapper mapper = new ObjectMapper();
			String studentId  = Integer.toString(mapper.readValue(inputStreamToString(request.getInputStream()), Student.class).getId());
			ResultSet postgres = new StudentDb().StudentRequests("SELECT * FROM student WHERE id = "+ studentId +"");
			while (postgres.next()) {
			student.setId(Integer.parseInt(postgres.getString(1)));
			student.setName(postgres.getString(2));
			student.setSurname(postgres.getString(3));
			student.setLast_name(postgres.getString(4));
			students.add(student);
}
			postgres.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		 return toJson.objectToJson(students);
	}

	public void  onPost(HttpServletRequest request) {
		StudentDb postgres = new StudentDb();
		ObjectMapper mapper = new ObjectMapper();
		try {
			Student student = mapper.readValue(inputStreamToString(request.getInputStream()), Student.class);	
			postgres.StudentRequests(
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
		StudentDb postgres = new StudentDb();
		ObjectMapper mapper = new ObjectMapper();
		try {
			Student student = mapper.readValue(inputStreamToString(request.getInputStream()), Student.class);
					postgres.StudentRequests( "UPDATE student SET name = '"
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

	public void onDelete(HttpServletRequest request) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String studentId = Integer.toString(mapper.readValue(inputStreamToString(request.getInputStream()), Student.class).getId());
			StudentDb postgres = new StudentDb();
			postgres.StudentRequests( "DELETE FROM student WHERE id = "+  studentId +";");			
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String inputStreamToString(InputStream inputStream) {
		Scanner scanner = new Scanner(inputStream, "UTF-8");
		return scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
	}
}
