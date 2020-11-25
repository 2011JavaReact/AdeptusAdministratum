package com.revature.karl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.revature.karl.util.JDBCUtility;
import com.revature.karl.model.Garrison;

public class GarrisonDAO {
	
	Logger logger = Logger.getLogger(GarrisonDAO.class);

	public ArrayList<Garrison> getAllGarrisons() {
		
		logger.debug("Request for all garrisons");
		ArrayList<Garrison> garrisons = new ArrayList<>();

		try (Connection connection = JDBCUtility.getConnection()) {

			String sqlQuery = "SELECT * " + "FROM garrisons";
			PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String chapter = resultSet.getString(2);
				int size = resultSet.getInt(3);

				Garrison garrison = new Garrison(id, chapter, size);
				garrisons.add(garrison);
			}
			logger.debug("Successfully got all garrisons");
			return garrisons;
		} catch (SQLException e) {
			logger.debug("Failed to get all garrisons");
			e.printStackTrace();
		}
		return garrisons;
	}

	public Garrison getGarrison(int idParam) {
		
		logger.debug("Request for one garrison");
		String sqlQuery = "SELECT * " + "FROM garrisons " + "WHERE id = ?";
		
		try (Connection connection = JDBCUtility.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, idParam);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			int id = resultSet.getInt(1);
			String chapter = resultSet.getString(2);
			int size = resultSet.getInt(3);
			logger.debug("Successfully got garrison");
			return new Garrison(id, chapter, size);
		} catch (SQLException e) {
			logger.debug("Failed to get garrison");
			e.printStackTrace();
		}
		return null;
	}

	public Garrison insertGarrison(Garrison garrison) {
		
		logger.debug("Request to insert garrison");
		
		try (Connection connection = JDBCUtility.getConnection()) {
			connection.setAutoCommit(false);

			String sqlQuery = "INSERT INTO garrisons " 
					+ "(chapter, size) " 
					+ "VALUES " 
					+ "(?, ?)";

			PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, garrison.getChapter());
			statement.setInt(2, garrison.getSize());

			if (statement.executeUpdate() != 1) {
				throw new SQLException("Inserting garrison failed, no rows were affected.");
			}

			int autoID = 0;
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				autoID = generatedKeys.getInt(1);
			} else {
				throw new SQLException("Inserting garrison failed, no ID generated.");
			}

			connection.commit();
			logger.debug("Successfully inserted garrison");
			return new Garrison(autoID, garrison.getChapter(), garrison.getSize());
		} catch (SQLException e) {
			logger.debug("Failed to insert garrison");
			e.printStackTrace();
		}
		return null;
	}

	public Garrison updateGarrison(int id, Garrison garrison) {
		
		logger.debug("Request to update garrison");
		
		try (Connection connection = JDBCUtility.getConnection()) {
			connection.setAutoCommit(false);

			String sqlQuery = "UPDATE garrisons "
					+ "set chapter = ?, size = ?"
					+ " where id = ?"; 

			PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, garrison.getChapter());
			statement.setInt(2, garrison.getSize());
			statement.setInt(3, id);

			if (statement.executeUpdate() != 1) {
				throw new SQLException("Updating garrison failed, no rows were affected.");
			}

			connection.commit();
			logger.debug("Successfully updated garrison");
			return new Garrison(id, garrison.getChapter(), garrison.getSize());
		} catch (SQLException e) {
			logger.debug("Failed to update garrison");
			e.printStackTrace();
		}
		return null;
	}

	public void deleteGarrison(int id) {
		
		logger.debug("Request to delete garrison");
		
		try (Connection connection = JDBCUtility.getConnection()) {
			connection.setAutoCommit(false);

			String sqlQuery = "DELETE FROM garrisons "
					+ " where id = ?"; 

			PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, id);

			if (statement.executeUpdate() != 1) {
				throw new SQLException("Deleting garrison failed, no rows were affected.");
			}
			
			logger.debug("Successfully deleted garrison");
			connection.commit();
		} catch (SQLException e) {
			logger.debug("Failed to delete garrison");
			e.printStackTrace();
		}
	}
}
