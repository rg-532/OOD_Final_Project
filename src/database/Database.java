package database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import database.interfaces.*;
import model.entities.interfaces.Referable;

public class Database {
	
	private static Database _instance = null;
	
	private String name;
	
	private Database(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public static Database getInstance() {
		if (_instance == null)
			_instance = new Database("connectedDB");
		return _instance;
	}
	
	public synchronized <T extends Referable> void runSelect(SelectionStrategy<T> strategy) {

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String dbUrl = "jdbc:mysql://localhost:3306/" + getName();

		try (Connection conn = DriverManager.getConnection(dbUrl, "root", "R@dg515253")) {
			try (PreparedStatement pstmt = conn.prepareStatement(strategy.getTemplate())) {
				strategy.editStatement(pstmt);
				try (ResultSet rs = pstmt.executeQuery()) {
					strategy.parseInformation(rs);
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	public synchronized void runUpdate(ManipulationStrategy strategy) {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		String dbUrl = "jdbc:mysql://localhost:3306/" + getName();

		try (Connection conn = DriverManager.getConnection(dbUrl, "root", "R@dg515253")) {
			try (PreparedStatement pstmt = conn.prepareStatement(strategy.getTemplate())) {
				strategy.editStatement(pstmt);
				int affected = pstmt.executeUpdate();
				strategy.parseInformation(affected);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
