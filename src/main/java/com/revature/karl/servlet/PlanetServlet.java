package com.revature.karl.servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.karl.service.PlanetService;
import com.revature.karl.model.Planet;
import com.revature.karl.util.CheckParamter;

public class PlanetServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ObjectMapper objectMapper = new ObjectMapper();
	private PlanetService planetService = new PlanetService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idParam = request.getPathInfo();
		int id = CheckParamter.hasParameter(idParam);

		if (id != 0) {
			String jsonString = objectMapper.writeValueAsString(planetService.getPlanet(id));
			response.getWriter().append(jsonString);
		} else {
			String jsonString = objectMapper.writeValueAsString(planetService.getAllPlanets());
			response.getWriter().append(jsonString);
		}
		response.setContentType("application/json");
		response.setStatus(200);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BufferedReader reader = request.getReader();
		StringBuilder builder = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}
		String jsonString = builder.toString();

		try {
			Planet planet = objectMapper.readValue(jsonString, Planet.class);
			Planet insertedPlanet = planetService.insertPlanet(planet);
			String printPlanet = objectMapper.writeValueAsString(insertedPlanet);

			response.getWriter().append(printPlanet);
			response.setContentType("application/json");
			response.setStatus(201);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			response.setStatus(400);
		}
	}
	
	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		BufferedReader reader = request.getReader();
		StringBuilder builder = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}
		String jsonString = builder.toString();
		String idParam = request.getPathInfo();
		int id = CheckParamter.hasParameter(idParam);

		try {
			Planet planet = objectMapper.readValue(jsonString, Planet.class);
			Planet updatedPlanet = planetService.updatePlanet(id, planet);
			String printPlanet = objectMapper.writeValueAsString(updatedPlanet);

			response.getWriter().append(printPlanet);
			response.setContentType("application/json");
			response.setStatus(201);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			response.setStatus(400);
		}
	}
	
	@Override
	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idParam = request.getPathInfo();
		int id = CheckParamter.hasParameter(idParam);

		try {
			planetService.deletePlanet(id);

			response.getWriter().append("Entry deleted. For the Emperor.");
			response.setContentType("application/json");
			response.setStatus(201);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			response.setStatus(400);
		}
	}
}
