package io.htmlcss.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import io.htmlcss.model.Donut;


public class DatabaseFetcher {
	Connection dbConnection = null;
	private String dbUrl = "jdbc:mysql://localhost:3306/DonutFactory";
	/**
	 * WARNING DO NOT USE THESE TO SET DATABASE CREDENTIALS.
	 * 
	 * 
	 * If you want to set the credentials please follow the directions below
	 * - Make a folder in your home directory called .env
	 * - 

	 */
	private String dbUser = "no"; // PLEASE DO NOT SET THIS
	private String dbPassword = "no"; // PLEASE DO NOT SET THIS
	
	/**
	 * Constructor for the databaseFetcher class
	 */
	public DatabaseFetcher() {

		// Check for a ~/.env/DB_CONFIG file
		// If it exists, read the database connection information from it
		// If it doesn't exist, use the default values
		File configFile = new File(System.getProperty("user.home") + "/.env/DB_CONFIG");
		if (configFile.exists()) {
			System.out.println("Using database connection information from " + configFile);
			// Read the database connection information from the config file
			// and update the dbUrl, dbUser, and dbPassword variables accordingly
			boolean hasUrl = false, hasUser = false, hasPassword = false;
			
			try (BufferedReader reader = new BufferedReader(new FileReader(configFile))) {
				String line;
				while ((line = reader.readLine()) != null) {
					String[] parts = line.split("=");
					if (parts.length == 2) {
						String key = parts[0].trim();
						String value = parts[1].trim();
						if (key.equalsIgnoreCase("dbUrl")) {
							dbUrl = value;
							hasUrl = true;
						} else if (key.equalsIgnoreCase("dbUser")) {
							dbUser = value;
							hasUser = true;
						} else if (key.equalsIgnoreCase("dbPassword")) {
							dbPassword = value;
							hasPassword = true;
						}
					}
				}
				if (!hasUrl || !hasUser || !hasPassword) {
					System.err.println("Missing required database connection information in " + configFile);
					System.exit(1);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			System.out.println("Using default database connection information");
		}

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			dbConnection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		}
		catch (Exception e) {e.printStackTrace();} 
	}
	
	public boolean checkDonut(int donutID) {
		Statement stmt;
		try {
			stmt = dbConnection.createStatement();
			ResultSet records = stmt.executeQuery("SELECT * FROM donutfactory.donuts WHERE id = " + donutID);
			if (records.next())
				return true;
			else
				return false;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}
	
	public Donut getDonut(int donutID) {
		Donut donut = new Donut();
		Statement stmt;
		try {
			stmt = dbConnection.createStatement();
			ResultSet records = stmt.executeQuery("SELECT * FROM donutfactory.donuts WHERE id = " + donutID);
			
			records.next();
			
			return parseDonut(records);
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	public ArrayList<Donut> getMenu() {
		ArrayList<Donut> donuts = new ArrayList<Donut>();
		Statement stmt;
		try {
			stmt = dbConnection.createStatement();
			ResultSet records = stmt.executeQuery("SELECT * FROM donutfactory.donuts");
			
			while(records.next())
			{
				Donut donut = parseDonut(records);
				donuts.add(donut);
			}
			
			return donuts;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return donuts;
	}
	
	private Donut parseDonut(ResultSet record) throws SQLException {
		Donut donut = new Donut();
		donut.setId(record.getInt(1));
		donut.setType(record.getString(2));
		donut.setFlavor(record.getString(3));
		donut.setPrice(record.getFloat(4));
		donut.setDescription(record.getString(5));
		donut.setImg(record.getString(6));
		return donut;
	}
	
}
