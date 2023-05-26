package com.srccodes.example;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.sql.*;
import java.util.Scanner;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.srccodes.example.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;  

@WebServlet("/HelloWorld")
public class HelloWorld extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Postgres postgres = new Postgres();     

    public HelloWorld() {
        super();

    }


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter printWriter = response.getWriter();
		
		printWriter.print(
				postgres.getConnection(
						RequestMethod.GET,
						"SELECT * FROM student"
						));
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String body = inputStreamToString(request.getInputStream());
		ObjectMapper mapper  = new ObjectMapper();
		Student stud = mapper.readValue(body, Student.class);
		
		postgres.getConnection(
				RequestMethod.POST,
				"INSERT INTO student (name, surname, last_name) VALUES ('"+stud.getName()+"', '"+stud.getSurname()+"', '"+stud.getLast_name()+"');");
	}


	protected void doPut(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String body = inputStreamToString(request.getInputStream());
		ObjectMapper mapper  = new ObjectMapper();
		Student stud = mapper.readValue(body, Student.class);
		
		postgres.getConnection(
				RequestMethod.POST,
				"UPDATE student SET name = '"+stud.getName()+"', surname = '"+stud.getSurname()+"', last_name = '"+stud.getLast_name()+"' WHERE id = "+stud.getId()+"");
	}


	protected void doDelete(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String body = inputStreamToString(request.getInputStream());
		ObjectMapper mapper  = new ObjectMapper();
		Student stud = mapper.readValue(body, Student.class);
		
		postgres.getConnection(
				RequestMethod.POST,
				"DELETE FROM student WHERE id = " +stud.getId()+";");
	
	}
	private static String inputStreamToString(InputStream inputStream) {
		Scanner scanner = new Scanner(inputStream, "UTF-8");
		return scanner.hasNext()?scanner.useDelimiter("\\A").next() : "";
	}

}
