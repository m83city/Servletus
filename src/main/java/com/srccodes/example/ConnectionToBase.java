package com.srccodes.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ConnectionToBase {
	String url = "jdbc:postgresql://localhost/postgres?user=postgres&password=Welcome4$";

	public Connection getConnection() {
		try {
			Connection conn = DriverManager.getConnection(url);
			return conn;

		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}
}