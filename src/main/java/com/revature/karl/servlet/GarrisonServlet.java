package com.revature.karl.servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.karl.model.Garrison;
import com.revature.karl.service.GarrisonService;
import com.revature.karl.util.CheckParamter;

public class GarrisonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ObjectMapper objectMapper = new ObjectMapper();
	private GarrisonService garrisonService = new GarrisonService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idParam = request.getPathInfo();
		int id = CheckParamter.hasParameter(idParam);

		if (id != 0) {
			String jsonString = objectMapper.writeValueAsString(garrisonService.getGarrison(id));
			response.getWriter().append(jsonString);
		} else {
			String jsonString = objectMapper.writeValueAsString(garrisonService.getAllGarrisons());
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
			Garrison garrison = objectMapper.readValue(jsonString, Garrison.class);
			Garrison insertedGarrison = garrisonService.insertGarrison(garrison);
			String printGarrison = objectMapper.writeValueAsString(insertedGarrison);

			response.getWriter().append(printGarrison);
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
			Garrison garrison = objectMapper.readValue(jsonString, Garrison.class);
			Garrison updatedGarrison = garrisonService.updateGarrison(id, garrison);
			String printGarrison = objectMapper.writeValueAsString(updatedGarrison);

			response.getWriter().append(printGarrison);
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
			garrisonService.deleteGarrison(id);

			response.getWriter().append("Entry deleted.");
			response.setContentType("application/json");
			response.setStatus(201);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			response.setStatus(400);
		}
	}
}
