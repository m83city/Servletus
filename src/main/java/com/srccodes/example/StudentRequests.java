package com.srccodes.example;

public class StudentRequests {
	public Student getStudentById(String studentId) {
		try {
			Student student = new Student();
			System.out.println( "helo");
			StudentDb postgres = new StudentDb();

			while (postgres.StudentRequests("SELECT * FROM student WHERE id = " + studentId +";").next()) {
				student.setId(Integer.parseInt(postgres.StudentRequests("SELECT * FROM student WHERE id = "+studentId+";").getString(0)));
				student.setName(postgres.StudentRequests("SELECT * FROM student WHERE id = 	"+studentId+";").getString(1)); 
				student.setSurname(postgres.StudentRequests("SELECT * FROM student WHERE id = "+studentId+";").getString(2)); 
				student.setLast_name(postgres.StudentRequests("SELECT * FROM student WHERE id = "+studentId+";").getString(3)); 
			}
		
			return student;
		} catch (Exception e) {
			e.printStackTrace();
			return null ;
		}
		
		
//		try {
//			List<Student> students = new ArrayList<>();
//			Student student = new Student();
//			ResultSet postgres = new StudentDb().StudentRequests("SELECT * FROM student WHERE id = "+ studentId +"");	
//			student.setId();
//			student.setName(postgres.getString(2));
//			student.setSurname(postgres.getString(3));
//			student.setLast_name(postgres.getString(4));
//			students.add(student);
//			return students;
//		} catch (NumberFormatException | SQLException e) {
//			e.printStackTrace();
//			return null;
//		}
	}
	public void createNewStudent(Student newStudent) {
		StudentDb postgres = new StudentDb();
		postgres.StudentRequests(
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
		StudentDb postgres = new StudentDb();
		postgres.StudentRequests( "UPDATE student SET name = '"
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
			StudentDb postgres = new StudentDb();
			postgres.StudentRequests( "DELETE FROM student WHERE id = "+  studentId +";");			
	}
}
