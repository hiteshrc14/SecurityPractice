package com.abc.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ResourceBundle;

// TODO: Auto-generated Javadoc
/**
 * The Class DBConnection.
 * 
 */
public class DBConnection {

	private static String DRIVER;
	private static String CONNECTION_STRING;
	private static String DB_NAME;
	private static String USERNAME;
	private static String PASSWORD;

	/**
	 * Instantiates a new DB connection.
	 */

	public DBConnection() {

	}

	/**
	 * Gets the single instance of DBConnection.
	 * 
	 * @return single instance of DBConnection
	 */
	public static Connection getInstance() {

		if (connection == null) {
			try {
				ResourceBundle bundle = ResourceBundle.getBundle("com.abc.user.db");
				DRIVER = bundle.getString("DRIVER");
				CONNECTION_STRING = bundle.getString("CONNECTION_STRING");
				DB_NAME = bundle.getString("DB_NAME");
				USERNAME = bundle.getString("USERNAME");
				PASSWORD = bundle.getString("PASSWORD");
				// connect to database
				Class.forName(DRIVER);
				// provide username and password
				connection = DriverManager.getConnection(CONNECTION_STRING + DB_NAME, USERNAME, PASSWORD);
			} catch (Exception e) {
				System.out.println(e);
			}
		}
		return connection;
	}

	/**
	 * Fire select.
	 *
	 * @param query
	 *            the query
	 * @return the result set
	 */
	public static ResultSet fireSelect(String query) {
		try {
			// fire select statement
			PreparedStatement ps = getInstance().prepareStatement(query);
			return ps.executeQuery();
		} catch (Exception e) {
		}
		return null;
	}

	/**
	 * Close db connection.
	 */
	public static void closeDBConnection() {
		try {
			if (connection != null) {
				connection.close();
				connection = null;
			}
		} catch (Exception e) {
		}
	}

	/** The connection. */
	private static Connection connection;
}