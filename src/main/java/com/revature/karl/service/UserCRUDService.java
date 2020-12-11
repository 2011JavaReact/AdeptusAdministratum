package com.revature.karl.service;

import java.util.ArrayList;

import com.revature.karl.dao.UserCRUDDAO;
import com.revature.karl.model.User;

public class UserCRUDService {

	private UserCRUDDAO userCRUDDAO;

	public UserCRUDService() {
		this.userCRUDDAO = new UserCRUDDAO();
	}

	public UserCRUDService(UserCRUDDAO userCRUDDAO) {
		this.userCRUDDAO = userCRUDDAO;
	}

	public ArrayList<User> getAllUsers() {
		return userCRUDDAO.getAllUsers();
	}

	public User getUser(int idParam) {
		return userCRUDDAO.getUser(idParam);
	}

	public User insertUser(User user) {
		return userCRUDDAO.insertUser(user);
	}

	public User updateUser(int id, User user) {
		return userCRUDDAO.updateUser(id, user);
	}

	public void deleteUser(int id) {
		userCRUDDAO.deleteUser(id);
	}
}
