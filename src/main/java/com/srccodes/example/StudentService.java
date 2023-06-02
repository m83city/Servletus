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
		StudentRequests getStudent = new StudentRequests();
		ToJson toJson = new ToJson();
		try {
			ObjectMapper mapper = new ObjectMapper();
			String studentId  = Integer.toString(mapper.readValue(inputStreamToString(request.getInputStream()), Student.class).getId());
			return toJson.objectToJson(getStudent.getStudentById(studentId));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void  onPost(HttpServletRequest request) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Student student = mapper.readValue(inputStreamToString(request.getInputStream()), Student.class);	
			StudentRequests create = new StudentRequests();
			create.createNewStudent(student);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onPut(HttpServletRequest request) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Student student = mapper.readValue(inputStreamToString(request.getInputStream()), Student.class);
			StudentRequests update = new StudentRequests();
			update.updateStudent(student);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onDelete(HttpServletRequest request) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String studentId = Integer.toString(mapper.readValue(inputStreamToString(request.getInputStream()), Student.class).getId());
			StudentRequests delete = new StudentRequests();
			delete.deleteStudent(studentId);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private static String inputStreamToString(InputStream inputStream) {
		Scanner scanner = new Scanner(inputStream, "UTF-8");
		return scanner.hasNext() ? scanner.useDelimiter("\\A").next() : "";
	}
}
