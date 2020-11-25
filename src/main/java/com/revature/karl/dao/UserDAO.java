package com.revature.karl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import com.revature.karl.model.User;
import com.revature.karl.util.JDBCUtility;

public class UserDAO {

	public String logInOutUser(String inOut, User user) {
		if (inOut.equals("login")) {
			try (Connection connection = JDBCUtility.getConnection()) {
				connection.setAutoCommit(false);
				String sqlQuery = "UPDATE users SET isLoggedIn = true WHERE username = ? AND password = ?";
				PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, user.getUsername());
				statement.setString(2, user.getPassword());

				if (statement.executeUpdate() != 1) {
					throw new SQLException("Log in failed, no rows were affected.");
				}

				connection.commit();

				return "User has been logged in!";
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else if (inOut.equals("logout")) {
			try (Connection connection = JDBCUtility.getConnection()) {
				connection.setAutoCommit(false);
				String sqlQuery = "UPDATE users SET isLoggedIn = false WHERE username = ? AND password = ?";
				PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, user.getUsername());
				statement.setString(2, user.getPassword());

				if (statement.executeUpdate() != 1) {
					throw new SQLException("Log out failed, no rows were affected.");
				}

				connection.commit();

				return "User has been logged out!";
			} catch (SQLException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Check issue with logging in and out.");
		}
		return null;
	}
}
