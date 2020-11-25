package com.revature.karl.service;

import java.util.ArrayList;

import com.revature.karl.dao.PlanetDAO;
import com.revature.karl.model.Planet;

public class PlanetService {

	private PlanetDAO planetDAO;

	public PlanetService() {
		this.planetDAO = new PlanetDAO();
	}

	public PlanetService(PlanetDAO planetDAO) {
		this.planetDAO = planetDAO;
	}

	public ArrayList<Planet> getAllPlanets() {
		return planetDAO.getAllPlanets();
	}

	public Planet getPlanet(int idParam) {
		return planetDAO.getPlanet(idParam);
	}

	public Planet insertPlanet(Planet planet) {
		return planetDAO.insertPlanet(planet);
	}

	public Planet updatePlanet(int id, Planet planet) {
		return planetDAO.updatePlanet(id, planet);
	}

	public void deletePlanet(int id) {
		planetDAO.deletePlanet(id);
	}

}
