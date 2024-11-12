package io.htmlcss.db;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.text.ParseException; 
import java.text.SimpleDateFormat; 
import java.util.Date; 
import io.htmlcss.model.*;


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

	public int insertCart(Cart cart){
		float totalPrice = 0;
		int totalQuantity = 0;
		int orderID = 0;
		int customerID = 0;

        SimpleDateFormat str = new SimpleDateFormat("yyyy-MM-dd"); 
        String date = str.format(new Date()); 
        orderID = this.generateOrderID(date);
		ArrayList<Order> items = cart.getItems();
		Order temp = null;

		this.insertCustomer(cart.getBuyer());
		customerID = this.getCustomerID();

		// Obtain total price and quantity
		for (int i = 0; i < items.size(); i++) {
            temp = items.get(i);
			totalQuantity += temp.getQuantity();
			totalPrice += temp.getQuantity() * temp.getItem().getPrice();
        }

		// Mass insert into the table!
		for (int i = 0; i < items.size(); i++){
			this.insertOrder(items.get(i), orderID, totalPrice, totalQuantity, customerID, date);
		}

		return 0;
	}

	private int insertOrder(Order order, int orderID, float tPrice, int tQuantity, int customerID, String date){
	
		try {
		    String sql = "Insert Into dOrder (orderID, itemID, purchaseDate, customerID, quantity, price, totalQuant, totalPrice, complete) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = dbConnection.prepareStatement(sql);
			stmt.setInt(1, orderID);
	        stmt.setInt(2, order.getItem().getId());
	        stmt.setString(3, date);
	        stmt.setInt(4, customerID);
            stmt.setInt(5, order.getQuantity());
			stmt.setFloat(6, order.getItem().getPrice()); // Pretty sure this will need to change data types.
			stmt.setInt(7, tQuantity);
			stmt.setFloat(8, tPrice);
			stmt.setInt(9, 0); // Incomplete order.
			
			stmt.executeUpdate();
			return 0; // Success
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1; // failure
	}

	/**
	 * This will save the customers data to the table.
	 */
	private int insertCustomer(Customer customer){
		try {
		    String sql = "INSERT INTO donutFactory.customerInfo (firstName, lastName, zipCode, customerAddress, phoneNumber, email, cardID) VALUES (?, ?, ?, ?, ?, ?, ?)";
			PreparedStatement stmt = dbConnection.prepareStatement(sql);
			

			
			stmt.setString(1, customer.getFirstName());
	        stmt.setString(2, customer.getLastName());
	        stmt.setInt(3, customer.getZipCode());
	        stmt.setString(4, customer.getAddress());
            stmt.setString(5, customer.getPhoneNumber());
			stmt.setString(6, customer.getEmail());
			stmt.setString(7, customer.getCardID());
			
			stmt.executeUpdate();
			return 0; // Success
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1; // failure
	}

	/**
	 * This will generate a new order id for us to insert assign to our order.
	 */
	private int generateOrderID(String date){
		int max = 0;
		Statement stmt;
		try {
			stmt = dbConnection.createStatement();
			String sql = "select max(orderID) from donutFactory.dOrder where purchaseDate=\"" + date + "\"";
			ResultSet records = stmt.executeQuery(sql);
			while(records.next()) {
				max = records.getInt(1);
			}
			if (max == 0){
				return max;
			}
			return max + 1;
			// If no orders for that day currently exist.
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
//			return 0;
		}
		
		return 0;
	}

	/**
	 * Obtains newest customer ID assuming they are the most recent customer.
	 */
	private int getCustomerID(){
		int max = 0;
		Statement stmt;
		try {
			stmt = dbConnection.createStatement();
			ResultSet records = stmt.executeQuery("select max(customerID) from customerInfo");
			while(records.next()) {
				max = records.getInt(1);
			}
			return max;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	
	}
	
	
	
	public int insertDonut(Donut donut) {
		try {
		    String sql = "INSERT INTO donutFactory.donuts (dType, flavor, price, donutDesc, img) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement stmt = dbConnection.prepareStatement(sql);
			
			stmt.setString(1, donut.getType());
			stmt.setString(2, donut.getFlavor());
			stmt.setFloat(3, donut.getPrice());
			stmt.setString(4, donut.getDescription());
			stmt.setString(5, donut.getImg());
			
			stmt.executeUpdate();
			return 0; // Success
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1; // failure
	}
	
	
	public int modifyDonut(Donut donut) {
		try {
			if (this.checkDonutExist(donut.getId()) == -1) {
				return -1;
			}
		    String sql = "Update donutFactory.donuts SET dType=?, flavor=?, price=?, donutDesc=?, img=? where id=?";
			PreparedStatement stmt = dbConnection.prepareStatement(sql);
			
			stmt.setString(1, donut.getType());
			stmt.setString(2, donut.getFlavor());
			stmt.setFloat(3, donut.getPrice());
			stmt.setString(4, donut.getDescription());
			stmt.setString(5, donut.getImg());
			stmt.setInt(6, donut.getId());
			
			stmt.executeUpdate();
			return 0; // Success
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1; // failure
	
	}
	
	public int deleteDonut(int donut) {
		try {
			if (this.checkDonutExist(donut) == -1) {
				return -1;
			}
		    String sql = "delete from donutFactory.donuts where id=?";
			PreparedStatement stmt = dbConnection.prepareStatement(sql);
			stmt.setInt(1, donut);
			
			stmt.executeUpdate();
			return 0; // Success
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return -1; // failure
	
	}
	
	private int checkDonutExist(int donut) {
		int max = -1;
		Statement stmt;
		try {
			stmt = dbConnection.createStatement();
			ResultSet records = stmt.executeQuery("select max(customerID) from customerInfo");
			while(records.next()) {
				max = records.getInt(1);
			}
			return max;
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return -1;
	}
}
