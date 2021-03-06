package com.revature.karl.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.postgresql.Driver;

public class JDBCUtility {

	public static Connection getConnection() throws SQLException {
//		Local
//		String url = System.getenv("DB_URL");
//		String username = System.getenv("DB_USERNAME");
//		String password = System.getenv("DB_PASSWORD");
		
		//Amazon
//		String url = System.getenv("RDS_URL");
//		String username = System.getenv("RDS_USERNAME");
//		String password = System.getenv("RDS_PASSWORD");

		Connection connection = null;

		DriverManager.registerDriver(new Driver());
		connection = DriverManager.getConnection("jdbc:postgresql://karl-database-2.c6ffouydqidt.us-west-1.rds.amazonaws.com:5432/postgres?user=postgres&password=livinginthedatabase");

		System.out.println("Connected to database.");
		
		return connection;
	}
}
