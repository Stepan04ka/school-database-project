package com.project.db;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

	private Connection connection;
	private Statement statement;

	public void openConnetion() {
		try {
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/testingdb","","12345");
			System.out.println("Соединение с базой данных было открыто!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void createConnectionStatement() {
		try {
			statement = connection.createStatement();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void makeQuery(String sql) {
		try {
			statement.executeUpdate(sql);
			System.out.println("Запрос был совершен успешно!");
		} catch (SQLException e) {

		}
	}

	public void closeResources() {
		try {
			connection.close();
			System.out.println("Соединение с базой данных закрыто.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeConnection() {
		try {
			connection.close();
			System.out.println("Соединение с базой данных закрыто.");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void closeStatement() {
		try {
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}