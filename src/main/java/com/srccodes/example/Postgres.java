package com.srccodes.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Postgres {
	String url = "jdbc:postgresql://localhost/postgres?user=postgres&password=Welcome4$";

	public ResultSet getConnection(String query) {
		try {
			Connection conn = DriverManager.getConnection(url);
			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery(query);
			resultSet.close();
			statement.close();
			return resultSet;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}