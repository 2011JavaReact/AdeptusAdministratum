package com.revature.karl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.revature.karl.util.JDBCUtility;
import com.revature.karl.model.Garrison;

public class GarrisonDAO {

	public ArrayList<Garrison> getAllGarrisons() {
		
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

			return garrisons;
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return garrisons;
	}

	public Garrison getGarrison(int idParam) {
		String sqlQuery = "SELECT * " + "FROM garrisons " + "WHERE id = ?";
		try (Connection connection = JDBCUtility.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, idParam);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			int id = resultSet.getInt(1);
			String chapter = resultSet.getString(2);
			int size = resultSet.getInt(3);
			return new Garrison(id, chapter, size);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Garrison insertGarrison(Garrison garrison) {
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

			return new Garrison(autoID, garrison.getChapter(), garrison.getSize());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public Garrison updateGarrison(int id, Garrison garrison) {
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

			return new Garrison(id, garrison.getChapter(), garrison.getSize());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public void deleteGarrison(int id) {
		try (Connection connection = JDBCUtility.getConnection()) {
			connection.setAutoCommit(false);

			String sqlQuery = "DELETE FROM garrisons "
					+ " where id = ?"; 

			PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, id);

			if (statement.executeUpdate() != 1) {
				throw new SQLException("Deleting garrison failed, no rows were affected.");
			}

			connection.commit();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
