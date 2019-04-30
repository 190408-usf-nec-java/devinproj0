package com.revature.bankdao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	public static Connection getConnection() throws SQLException {
		String dbUrl = System.getenv("LOCAL_JDBC_URL");
		String dbUserName = System.getenv("LOCAL_JDBC_USER");
		String dbPassword = System.getenv("LOCAL_JDBC_PASS");
		Connection connection = DriverManager.getConnection(dbUrl, dbUserName, dbPassword);
		return connection;
	}
}
