package com.sras.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.google.appengine.api.utils.SystemProperty;
import com.sras.datamodel.exceptions.TMException;

public final class ConnectionManagerImpl {

	private static Logger logger = Logger
			.getLogger(ConnectionManagerImpl.class);
	private String url;
	private static ConnectionManagerImpl instance = new ConnectionManagerImpl();

	private ConnectionManagerImpl() {

	}

	public static ConnectionManagerImpl getConnectionManager() {
		return instance;
	}

	/**
	 * Connection object from mysql
	 * 
	 * @return Connection to the DB
	 */
	public Connection getConnection() throws TMException {
		logger.debug("Inside mySQLConnection method, trying to acquire MYSQL connection");
		String driverClassName = null;
		try {
			if (SystemProperty.environment.value() == SystemProperty.Environment.Value.Production) {
				// Load the class that provides the new "jdbc:google:mysql://"
				// prefix.
				driverClassName = "com.mysql.jdbc.GoogleDriver";
				url = "jdbc:google:mysql://deal70-1146:deal70db?user=root";
			} else {
				// Local MySQL instance to use during development.
				driverClassName = "com.mysql.jdbc.Driver";
				url = "jdbc:mysql://127.0.0.1:3306/deal70?user=root";
				// Alternatively, connect to a Google Cloud SQL instance using:
				// jdbc:mysql://ip-address-of-google-cloud-sql-instance:3306/guestbook?user=root
			}
			Class.forName(driverClassName);
			return DriverManager.getConnection(url);
		} catch (ClassNotFoundException e) {
			throw new RuntimeException(
					"Cannot find the SQL driver in the classpath." + " driver="
							+ driverClassName, e);
		} catch (Exception sql) {
			logger.error("Unable to get connection ..", sql);
			throw new TMException("No Connection", sql.getMessage());
		}

	}

	/**
	 * Rollback a connection if autocommit on the connection is set to false.
	 * 
	 * @param conn
	 * @throws TMException
	 */
	public static void rollback(Connection conn) throws TMException {
		try {
			if (conn != null && !conn.getAutoCommit()) {
				logger.info("Rolling back");
				conn.rollback();
			}
		} catch (SQLException e) {
			logger.error("Error while rolling back", e);
			throw new TMException("SQL Exception", e.getMessage());
		}
	}

	/**
	 * Closes the connection (inturn returns the connection to the
	 * ConnectionPool)
	 * 
	 * @param conn
	 *            db connection instance
	 */
	public void returnConnection(Connection conn) throws TMException {
		try {
			if (conn != null) {
				conn.setAutoCommit(true);
				conn.close();
			}

		} catch (SQLException sql) {
			logger.error("Unable to close connection object", sql);
			throw new TMException("SQL Exception", sql.getMessage());
		}
	}
}