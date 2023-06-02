package com.srccodes.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentRepository {
	ConnectionToBase conn = new ConnectionToBase();

	public Student getStudentById(String studentId) {
		try {
			Student student = new Student();
			ResultSet statement = conn.getConnection().createStatement()
					.executeQuery("SELECT * FROM student WHERE id = " + studentId + ";");

			while (statement.next()) {
				student.setId(Integer.parseInt(statement.getString(1)));
				student.setName(statement.getString(2));
				student.setSurname(statement.getString(3));
				student.setLast_name(statement.getString(4));
			}
			return student;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public void createNewStudent(Student newStudent) {
		try {
			ResultSet statement = conn.getConnection().createStatement()
					.executeQuery("INSERT INTO student (name, surname, last_name) VALUES ('" + newStudent.getName()
							+ "', '" + newStudent.getSurname() + "', '" + newStudent.getLast_name() + "');");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateStudent(Student updatedStudent) {
		try {
			ResultSet statement = conn.getConnection().createStatement()
					.executeQuery("UPDATE student SET name = '" + updatedStudent.getName() + "', surname = '"
							+ updatedStudent.getSurname() + "', last_name = '" + updatedStudent.getLast_name()
							+ "' WHERE id = " + updatedStudent.getId() + "");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteStudent(String studentId) {
		try {
			ResultSet statement = conn.getConnection().createStatement()
					.executeQuery("DELETE FROM student WHERE id = " + studentId + ";");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
