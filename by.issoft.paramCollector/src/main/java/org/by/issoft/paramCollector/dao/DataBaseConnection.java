package org.by.issoft.paramCollector.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.by.issoft.paramCollector.MyPropertyManager;

public class DataBaseConnection {

	private final static String URL = MyPropertyManager.getJDBCProperty("jdbc.url");
	private final static String UESRNAME = MyPropertyManager.getJDBCProperty("jdbc.username");
	private final static String PASSWORD = MyPropertyManager.getJDBCProperty("jdbc.password");
	private static Connection connection = null;

	public static Connection getConnection() {
		try {
			connection = DriverManager.getConnection(URL, UESRNAME, PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return connection;
	}

	public static void closeConnection() {
		try {
			if (connection != null) {
				connection.close();
			}
		} catch (Exception e) {

		}
	}
}
