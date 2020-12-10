package com.revature.karl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.log4j.Logger;

import com.revature.karl.model.User;
import com.revature.karl.util.JDBCUtility;

public class UserDAO {
	
	Logger logger = Logger.getLogger(UserDAO.class);

	public User logInOutUser(String inOut, User user) {
	
		logger.debug("Request for user authentication action");
		
		if (inOut.equals("login")) {
			logger.debug("Request for user log in");
			try (Connection connection = JDBCUtility.getConnection()) {
				connection.setAutoCommit(false);
				
				//Update user
				String sqlQuery = "UPDATE users SET isLoggedIn = true WHERE username = ? AND password = ?";
				PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
				statement.setString(1, user.getUsername());
				statement.setString(2, user.getPassword());

				if (statement.executeUpdate() != 1) {
					throw new SQLException("Log in failed, no rows were affected.");
				}
				
				int autoID = 0;
				ResultSet generatedKeys = statement.getGeneratedKeys();
				if (generatedKeys.next()) {
					autoID = generatedKeys.getInt(1);
				} else {
					throw new SQLException("Inserting garrison failed, no ID generated.");
				}
				
				//Get the newly logged in user
				String getUserQuery = "SELECT * FROM users WHERE id = ?";
				PreparedStatement getUserstatement = connection.prepareStatement(getUserQuery, Statement.RETURN_GENERATED_KEYS);
				getUserstatement.setInt(1, autoID);
				ResultSet resultSet = getUserstatement.executeQuery();
				resultSet.next();
				
				String username = resultSet.getString(2);
				String password = resultSet.getString(3);
				boolean isLoggedIn = resultSet.getBoolean(4);
				boolean isAdmin = resultSet.getBoolean(5);

				connection.commit();
				logger.debug("Successfully logged user in");
				return new User(autoID, username, password, isLoggedIn, isAdmin);
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
				
				int autoID = 0;
				ResultSet generatedKeys = statement.getGeneratedKeys();
				if (generatedKeys.next()) {
					autoID = generatedKeys.getInt(1);
				} else {
					throw new SQLException("Inserting garrison failed, no ID generated.");
				}
				
				//Get the newly logged out user
				String getUserQuery = "SELECT * FROM users WHERE id = ?";
				PreparedStatement getUserstatement = connection.prepareStatement(getUserQuery, Statement.RETURN_GENERATED_KEYS);
				getUserstatement.setInt(1, autoID);
				ResultSet resultSet = getUserstatement.executeQuery();
				resultSet.next();
				
				String username = resultSet.getString(2);
				String password = resultSet.getString(3);
				boolean isLoggedIn = resultSet.getBoolean(4);
				boolean isAdmin = resultSet.getBoolean(5);

				connection.commit();
				logger.debug("Successfully logged user out");
				return new User(autoID, username, password, isLoggedIn, isAdmin);
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
