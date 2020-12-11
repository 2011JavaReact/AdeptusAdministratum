package com.revature.karl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.revature.karl.util.JDBCUtility;
import com.revature.karl.model.User;

public class UserCRUDDAO {
	
	Logger logger = Logger.getLogger(UserCRUDDAO.class);

	public ArrayList<User> getAllUsers() {
		
		logger.debug("Request for all users");
		ArrayList<User> users = new ArrayList<>();

		try (Connection connection = JDBCUtility.getConnection()) {

			String sqlQuery = "SELECT * " + "FROM users";
			PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String username = resultSet.getString(2);
				String password = resultSet.getString(3);
				boolean isLoggedIn = resultSet.getBoolean(4);
				boolean isAdmin = resultSet.getBoolean(5);

				User user = new User(id, username, password, isLoggedIn, isAdmin);
				users.add(user);
			}
			logger.debug("Successfully got all users");
			return users;
		} catch (SQLException e) {
			logger.debug("Failed to get all users");
			e.printStackTrace();
		}
		return users;
	}

	public User getUser(int idParam) {
		
		logger.debug("Request for one user");
		String sqlQuery = "SELECT * " + "FROM users " + "WHERE id = ?";
		
		try (Connection connection = JDBCUtility.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, idParam);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			int id = resultSet.getInt(1);
			String username = resultSet.getString(2);
			String password = resultSet.getString(3);
			boolean isLoggedIn = resultSet.getBoolean(4);
			boolean isAdmin = resultSet.getBoolean(5);
			
			logger.debug("Successfully got user");
			return new User(id, username, password, isLoggedIn, isAdmin);
		} catch (SQLException e) {
			logger.debug("Failed to get user");
			e.printStackTrace();
		}
		return null;
	}

	public User insertUser(User user) {
		
		logger.debug("Request to insert user");
		
		try (Connection connection = JDBCUtility.getConnection()) {
			connection.setAutoCommit(false);

			String sqlQuery = "INSERT INTO users " 
					+ "(username, password, isLoggedIn, isAdmin) " 
					+ "VALUES " 
					+ "(?, ?, ?, ?)";

			PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setBoolean(3, user.isLoggedIn());
			statement.setBoolean(4, user.isAdmin());

			if (statement.executeUpdate() != 1) {
				throw new SQLException("Inserting user failed, no rows were affected.");
			}

			int autoID = 0;
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				autoID = generatedKeys.getInt(1);
			} else {
				throw new SQLException("Inserting user failed, no ID generated.");
			}

			connection.commit();
			logger.debug("Successfully inserted user");
			return new User(autoID, user.getUsername(), user.getPassword(), user.isLoggedIn(), user.isAdmin());
		} catch (SQLException e) {
			logger.debug("Failed to insert user");
			e.printStackTrace();
		}
		return null;
	}

	public User updateUser(int id, User user) {
		
		logger.debug("Request to update user");
		
		try (Connection connection = JDBCUtility.getConnection()) {
			connection.setAutoCommit(false);

			String sqlQuery = "UPDATE users "
					+ "set username = ?, password = ?, isLoggedIn = ?, isAdmin = ?"
					+ " where id = ?"; 

			PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, user.getUsername());
			statement.setString(2, user.getPassword());
			statement.setBoolean(3, user.isLoggedIn());
			statement.setBoolean(4, user.isAdmin());
			statement.setInt(5, id);

			if (statement.executeUpdate() != 1) {
				throw new SQLException("Updating user failed, no rows were affected.");
			}

			connection.commit();
			logger.debug("Successfully updated user");
			return new User(id, user.getUsername(), user.getPassword(), user.isLoggedIn(), user.isAdmin());
		} catch (SQLException e) {
			logger.debug("Failed to update user");
			e.printStackTrace();
		}
		return null;
	}

	public void deleteUser(int id) {
		
		logger.debug("Request to delete user");
		
		try (Connection connection = JDBCUtility.getConnection()) {
			connection.setAutoCommit(false);

			String sqlQuery = "DELETE FROM users "
					+ " where id = ?"; 

			PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, id);

			if (statement.executeUpdate() != 1) {
				throw new SQLException("Deleting user failed, no rows were affected.");
			}
			
			logger.debug("Successfully deleted user");
			connection.commit();
		} catch (SQLException e) {
			logger.debug("Failed to delete user");
			e.printStackTrace();
		}
	}
}
