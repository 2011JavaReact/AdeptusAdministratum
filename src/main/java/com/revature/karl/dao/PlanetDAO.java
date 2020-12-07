package com.revature.karl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.revature.karl.model.Planet;
import com.revature.karl.model.Garrison;
import com.revature.karl.util.JDBCUtility;

public class PlanetDAO {

	Logger logger = Logger.getLogger(PlanetDAO.class);

	public ArrayList<Planet> getAllPlanets() {

		logger.debug("Request for all planets");
		ArrayList<Planet> planets = new ArrayList<>();

		try (Connection connection = JDBCUtility.getConnection()) {

			String sqlQuery = "SELECT * from planets p INNER JOIN garrisons g ON p.garrison_id = g.id";
			PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int planet_id = resultSet.getInt(1);
				String name = resultSet.getString(2);
				String inhabitants = resultSet.getString(3);
				int population = resultSet.getInt(4);
				int garrison_id = resultSet.getInt(5);

				String chapter = resultSet.getString(7);
				int size = resultSet.getInt(8);

				planets.add(new Planet(planet_id, name, inhabitants, population, garrison_id,
						new Garrison(garrison_id, chapter, size)));
			}
			logger.debug("Successfully got all planets");
			return planets;
		} catch (SQLException e) {
			logger.debug("Failed to get all planets");
			e.printStackTrace();
		}
		return planets;
	}

	public Planet getPlanet(int idParam) {

		logger.debug("Request for one planet");
		String sqlQuery = "SELECT * FROM planets p INNER JOIN garrisons g ON p.garrison_id = g.id WHERE id = ?";

		try (Connection connection = JDBCUtility.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, idParam);
			ResultSet resultSet = statement.executeQuery();
			resultSet.next();
			int id = resultSet.getInt(1);
			String name = resultSet.getString(2);
			String inhabitants = resultSet.getString(3);
			int population = resultSet.getInt(4);
			int garrison_id = resultSet.getInt(5);

			String chapter = resultSet.getString(7);
			int size = resultSet.getInt(8);

			logger.debug("Successfully got planet");
			return new Planet(id, name, inhabitants, population, garrison_id,
					new Garrison(garrison_id, chapter, size));
		} catch (SQLException e) {
			logger.debug("Failed to get planet");
			e.printStackTrace();
		}
		return null;
	}

	public Planet insertPlanet(Planet planet) {

		logger.debug("Request to insert planet");

		try (Connection connection = JDBCUtility.getConnection()) {
			connection.setAutoCommit(false);

			String sqlQuery = "INSERT INTO planets " + "(name, inhabitants, population, garrison_id) " + "VALUES "
					+ "(?, ?, ?, ?)";

			PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, planet.getName());
			statement.setString(2, planet.getInhabitants());
			statement.setInt(3, planet.getPopulation());
			statement.setInt(4, planet.getGarrison_id());

			if (statement.executeUpdate() != 1) {
				throw new SQLException("Inserting planet failed, no rows were affected.");
			}

			int autoID = 0;
			ResultSet generatedKeys = statement.getGeneratedKeys();
			if (generatedKeys.next()) {
				autoID = generatedKeys.getInt(1);
			} else {
				throw new SQLException("Inserting planet failed, no ID generated.");
			}

			logger.debug("Successfully inserted planet");
			connection.commit();
			return new Planet(autoID, planet.getName(), planet.getInhabitants(), planet.getPopulation(),
					planet.getGarrison_id());
		} catch (SQLException e) {
			logger.debug("Failed to insert planet");
			e.printStackTrace();
		}
		return null;
	}

	public Planet updatePlanet(int id, Planet planet) {

		logger.debug("Request to update planet");

		try (Connection connection = JDBCUtility.getConnection()) {
			connection.setAutoCommit(false);

			String sqlQuery = "UPDATE planets " + "SET name = ?," + "inhabitants = ?," + "population = ?,"
					+ "garrison_id = ?" + "where id = ?";

			PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, planet.getName());
			statement.setString(2, planet.getInhabitants());
			statement.setInt(3, planet.getPopulation());
			statement.setInt(4, planet.getGarrison_id());
			statement.setInt(5, id);

			if (statement.executeUpdate() != 1) {
				throw new SQLException("Updating planet failed, no rows were affected.");
			}

			connection.commit();
			logger.debug("Successfully updated planet");
			return new Planet(id, planet.getName(), planet.getInhabitants(), planet.getPopulation(),
					planet.getGarrison_id());
		} catch (SQLException e) {
			logger.debug("Failed to update planet");
			e.printStackTrace();
		}
		return null;
	}

	public void deletePlanet(int id) {

		logger.debug("Request to delete planet");

		try (Connection connection = JDBCUtility.getConnection()) {
			connection.setAutoCommit(false);

			String sqlQuery = "DELETE FROM planets " + " where id = ?";

			PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			statement.setInt(1, id);

			if (statement.executeUpdate() != 1) {
				throw new SQLException("Deleting planet failed, no rows were affected.");
			}

			logger.debug("Successfully deleted planet");
			connection.commit();
		} catch (SQLException e) {
			logger.debug("Failed to delete planet");
			e.printStackTrace();
		}
	}
}
