package com.revature.karl.model;

public class User {

	private int id;
	private String username;
	private String password;
	private boolean isLoggedIn;
	private boolean isAdmin;

	public User() {
		super();
	}

	public User(String username, String password) {
		this.username = username;
		this.password = password;
	}
	
	public User(String username, String password, boolean isLoggedIn, boolean isAdmin) {
		this.username = username;
		this.password = password;
		this.isLoggedIn = isLoggedIn;
		this.isAdmin = isAdmin;
	}

	public User(int id, String username, String password, boolean isLoggedIn, boolean isAdmin) {
		this.id = id;
		this.username = username;
		this.password = password;
		this.isLoggedIn = isLoggedIn;
		this.isAdmin = isAdmin;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isLoggedIn() {
		return isLoggedIn;
	}

	public void setLoggedIn(boolean isLoggedIn) {
		this.isLoggedIn = isLoggedIn;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

}
