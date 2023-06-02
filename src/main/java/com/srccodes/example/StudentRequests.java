package com.srccodes.example;

public class StudentRequests {
	public Student getStudentById(String studentId) {
		try {
			Student student = new Student();
			System.out.println( "helo");
			StudentRepository postgres = new StudentRepository();

			while (postgres.studentRequests("SELECT * FROM student WHERE id = " + studentId +";").next()) {
				student.setId(Integer.parseInt(postgres.studentRequests("SELECT * FROM student WHERE id = "+studentId+";").getString(0)));
				student.setName(postgres.studentRequests("SELECT * FROM student WHERE id = 	"+studentId+";").getString(1)); 
				student.setSurname(postgres.studentRequests("SELECT * FROM student WHERE id = "+studentId+";").getString(2)); 
				student.setLast_name(postgres.studentRequests("SELECT * FROM student WHERE id = "+studentId+";").getString(3)); 
			}
			return student;
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}
	}
	public void createNewStudent(Student newStudent) {
		StudentRepository postgres = new StudentRepository();
		postgres.studentRequests(
				"INSERT INTO student (name, surname, last_name) VALUES ('" 
						+ newStudent.getName() +
						"', '" +
						newStudent.getSurname() +
						"', '"  
						+ newStudent.getLast_name()
						+ "');"
					);
	}
	public void updateStudent(Student updatedStudent) {
		StudentRepository postgres = new StudentRepository();
		postgres.studentRequests( "UPDATE student SET name = '"
				+ updatedStudent.getName() +
				"', surname = '"
				+ updatedStudent.getSurname() +
				"', last_name = '" 
				+ updatedStudent.getLast_name() 
				+ "' WHERE id = " +
				updatedStudent.getId() +
				"");
	}
	public void deleteStudent(String studentId) {
			StudentRepository postgres = new StudentRepository();
			postgres.studentRequests( "DELETE FROM student WHERE id = "+  studentId +";");			
	}
}
