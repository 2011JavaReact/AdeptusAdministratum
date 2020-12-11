package com.revature.karl.servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.karl.model.User;
import com.revature.karl.service.UserCRUDService;
import com.revature.karl.util.CheckParamter;

public class UserCRUDServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ObjectMapper objectMapper = new ObjectMapper();
	private UserCRUDService userCRUDService = new UserCRUDService();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String idParam = request.getPathInfo();
		int id = CheckParamter.hasParameter(idParam);

		if (id != 0) {
			String jsonString = objectMapper.writeValueAsString(userCRUDService.getUser(id));
			response.getWriter().append(jsonString);
		} else {
			String jsonString = objectMapper.writeValueAsString(userCRUDService.getAllUsers());
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
			User user = objectMapper.readValue(jsonString, User.class);
			User insertedUser = userCRUDService.insertUser(user);
			String printUser = objectMapper.writeValueAsString(insertedUser);

			response.getWriter().append(printUser);
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
			User user = objectMapper.readValue(jsonString, User.class);
			User updatedUser = userCRUDService.updateUser(id, user);
			String printUser = objectMapper.writeValueAsString(updatedUser);

			response.getWriter().append(printUser);
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
			userCRUDService.deleteUser(id);

			response.getWriter().append("Entry deleted.");
			response.setContentType("application/json");
			response.setStatus(201);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			response.setStatus(400);
		}
	}
}
