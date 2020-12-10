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
import com.revature.karl.service.UserService;

import org.apache.log4j.Logger;

public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private ObjectMapper objectMapper = new ObjectMapper();
	private UserService userService = new UserService();
	
	Logger logger = Logger.getLogger(UserServlet.class);

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		logger.debug("UserServlet's doPOST hit");
		
		String inOut = request.getRequestURI().split("/")[2];
		
		BufferedReader reader = request.getReader();
		StringBuilder builder = new StringBuilder();
		String line;
		while ((line = reader.readLine()) != null) {
			builder.append(line);
		}
		String jsonString = builder.toString();

		try {
			User user = objectMapper.readValue(jsonString, User.class);
			User logInOutAttempt = userService.logInOutUser(inOut, user);
			String returnString = objectMapper.writeValueAsString(logInOutAttempt);

			response.getWriter().append(returnString);
			response.setContentType("application/json");
			response.setStatus(201);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
			response.setStatus(400);
		}
	}

}
