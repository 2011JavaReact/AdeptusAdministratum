package com.revature.karl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.revature.karl.model.User;
import com.revature.karl.util.JDBCUtility;

public class UserDAO {
	
	Logger logger = Logger.getLogger(UserDAO.class);

	public String logInOutUser(String inOut, User user) {
	
		logger.debug("Request for user authentication action");
		
		if (inOut.equals("login")) {
			logger.debug("Request for user log in");
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
				logger.debug("Successfully logged user in");
				return "User has been logged in!";
			} catch (SQLException e) {
				logger.debug("Failed to log user in");
				e.printStackTrace();
			}
		} else if (inOut.equals("logout")) {
			logger.debug("Request for user log out");
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
				logger.debug("Successfully logged user out");
				return "User has been logged out!";
			} catch (SQLException e) {
				logger.debug("Failed to log user out");
				e.printStackTrace();
			}
		} else {
			logger.debug("Check issue with logging in and out");
			System.out.println("Check issue with logging in and out.");
		}
		return null;
	}
}
