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
		ToJson toJson = new ToJson();
		try {
			ObjectMapper mapper = new ObjectMapper();
			String studentId = Integer
					.toString(mapper.readValue(inputStreamToString(request.getInputStream()), Student.class).getId());
			StudentRepository get = new StudentRepository();
			return toJson.objectToJson(get.getStudentById(studentId));
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void onPost(HttpServletRequest request) {
		ObjectMapper mapper = new ObjectMapper();
		try {
			Student student = mapper.readValue(inputStreamToString(request.getInputStream()), Student.class);
			StudentRepository create = new StudentRepository();
			create.createNewStudent(student);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void onPut(HttpServletRequest request) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			Student student = mapper.readValue(inputStreamToString(request.getInputStream()), Student.class);
			StudentRepository put = new StudentRepository();
			put.updateStudent(student);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void onDelete(HttpServletRequest request) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String studentId = Integer
					.toString(mapper.readValue(inputStreamToString(request.getInputStream()), Student.class).getId());
			StudentRepository delete = new StudentRepository();
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
