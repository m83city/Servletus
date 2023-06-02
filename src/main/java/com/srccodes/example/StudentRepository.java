package com.srccodes.example;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentRepository {
	public ResultSet studentRequests (String query) {
		ConnectionToBase conn = new ConnectionToBase();
     	Statement statement;
		try {
			statement = conn.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			return resultSet;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
