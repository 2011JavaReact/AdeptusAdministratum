package com.revature.karl.service;

import com.revature.karl.dao.UserDAO;
import com.revature.karl.model.User;

public class UserService {

	private UserDAO userDAO;

	public UserService() {
		this.userDAO = new UserDAO();
	}

	public UserService(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public String logInOutUser(String inOut, User user) {
		return userDAO.logInOutUser(inOut, user);
	}

}
