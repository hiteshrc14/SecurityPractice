package com.abc.user;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.apache.commons.lang.StringEscapeUtils;

public class UserManager {
	/** The connection. */
	private Connection connection = null;

	/** The ps. */
	private PreparedStatement preparedStatement = null;

	/**
	 * Instantiates a new user manager.
	 */
	public UserManager() {
		connection = DBConnection.getInstance();
	}

	/**
	 * Login.
	 *
	 * @param username
	 *            the username
	 * @param password
	 *            the password
	 * @param sessionCaptcha
	 *            the session captcha
	 * @param userCaptcha
	 *            the user captcha
	 * @param userType
	 *            the user type
	 * @return the string
	 */
	public String login(String username, String password, String sessionCaptcha, String userCaptcha) {
		if (sessionCaptcha.equals(userCaptcha)) {
			try {
				// check username exists or not
				preparedStatement = null;
				preparedStatement = connection
						.prepareStatement("select user_id,password from users where username=?");
				preparedStatement.setString(1, username);
				ResultSet rs = preparedStatement.executeQuery();
				if (rs.next()) {
					// username exists, now get password and hash it to compare
					if (PasswordHash.validatePassword(password, rs.getString("password"))) {
						return "Success:" + rs.getString("user_id");
					} else {
						// to increase timing validate with wrong password
						PasswordHash.validatePassword(password, Utils.randomString());
						return "Username " + StringEscapeUtils.escapeHtml(username) + " or password is wrong";
					}
				} else {
				    PasswordHash.validatePassword(password, Utils.randomString());
					return "Username " + StringEscapeUtils.escapeHtml(username) + " or password is wrong";
				}
			} catch (Exception e) {
				return "Username " + StringEscapeUtils.escapeHtml(username) + " or password is wrong";
			}
		}
		return "Wrong Captcha";
	}
	
	/**
	 * Gets the full names.
	 *
	 * @param username
	 *            the username
	 * @return the full names
	 */
	public String getFullName(String username) {
		String name = "";
		try {
			preparedStatement = null;
			// get full name based on username
			preparedStatement = connection.prepareStatement("select first_name,last_name from users where username=?");
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				name = rs.getString("first_name") + " " + rs.getString("last_name");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return name;
	}
}