package com.srccodes.example;

import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

public class ToJson {
	
	public String objectToJson (List<Student> student) {
		try {
			ObjectMapper mapper = new ObjectMapper();
			String json = mapper.writeValueAsString(student);
			return json;
		} catch (Exception e) {
			e.printStackTrace();
			return e.toString();
		}
	}
}
