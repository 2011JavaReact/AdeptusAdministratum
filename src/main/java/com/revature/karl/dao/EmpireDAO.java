package com.revature.karl.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.revature.karl.model.Planet;
import com.revature.karl.util.JDBCUtility;

public class EmpireDAO {
	
	Logger logger = Logger.getLogger(EmpireDAO.class);

	public ArrayList<Planet> getEmpire(String inhabitants) {
		
		logger.debug("Request for all garrisons");
		ArrayList<Planet> empire = new ArrayList<>();
		String sqlQuery = "select * " + "FROM planets " + "WHERE inhabitants = ?";
		
		try (Connection connection = JDBCUtility.getConnection()) {
			PreparedStatement statement = connection.prepareStatement(sqlQuery, Statement.RETURN_GENERATED_KEYS);
			statement.setString(1, inhabitants);
			ResultSet resultSet = statement.executeQuery();
			
			while (resultSet.next()) {
				int id = resultSet.getInt(1);
				String name = resultSet.getString(2);
//				String inhabitants = resultSet.getString(3);
				int population = resultSet.getInt(4);
				int garrison_id = resultSet.getInt(5);

				Planet planet = new Planet(id, name, inhabitants, population, garrison_id);
				empire.add(planet);
			}
			logger.debug("Successfully got empire planets");
			return empire;

		} catch (SQLException e) {
			logger.debug("Failed to get empire planets");
			e.printStackTrace();
		}
		return null;
	}
}
