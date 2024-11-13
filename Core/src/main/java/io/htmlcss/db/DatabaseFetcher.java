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
	/**
	 * Configuration guide:
	 * 
	 * - Make a folder in your home directory called .env
	 * - Create a file called DB_CONFIG in the .env folder
	 * - Add the following lines to the file:
	 * dbUrl=YOUR_URL
	 * dbUser=YOUR_USERNAME
	 * dbPassword=YOUR_PASSWORD
	 * 
	 * Or just run this line in your terminal:
	 * mkdir -p ~/.env && echo "dbUrl=YOUR_URL" > ~/.env/DB_CONFIG && echo "dbUser=YOUR_USERNAME" >> ~/.env/DB_CONFIG && echo "dbPassword=YOUR_PASSWORD" >> ~/.env/DB_CONFIG
	 */
	private String dbUrl = "jdbc:mysql://localhost:3306/DonutFactory"; // Read from the DB_CONFIG file (see above)
	private String dbUser = null; // Read from the DB_CONFIG file (see above)
	private String dbPassword = null; // Read from the DB_CONFIG file (see above)

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
			// Exit if the config file doesn't exist
			System.err.println("Database connection file not found: " + configFile);
			System.exit(1);
		}

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			dbConnection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		}
		catch (Exception e) {e.printStackTrace();} 
	}
	
	/**
	 * This method will check if the donut exists in the database.
	 * @param donutID The id of the donut to check
	 * @return True if the donut exists, false otherwise
	 */
	public boolean checkDonut(int donutID) {
		try {
			String query = "SELECT * FROM donutfactory.donuts WHERE id = ?";
			PreparedStatement stmt = dbConnection.prepareStatement(query);
			stmt.setInt(1, donutID);
			ResultSet records = stmt.executeQuery();
			if (records.next())
				return true;
			else
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	/**
	 * This method will return a donut object from the database.
	 * @param donutID The id of the donut to retrieve
	 * @return The donut object
	 */
	public Donut getDonut(int donutID) {
		try {
			PreparedStatement stmt = dbConnection.prepareStatement("SELECT * FROM donutfactory.donuts WHERE id = ?");
			stmt.setInt(1, donutID);
			ResultSet records = stmt.executeQuery();
			
			records.next();
			return parseDonut(records);
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}
	
	/**
	 * This method will return a list of all donuts in the database.
	 * @return A list of all donuts
	 */
	public List<Donut> getMenu() {
		List<Donut> donuts = new ArrayList<Donut>();
		try {
			Statement stmt = dbConnection.createStatement();
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
	
	/**
	 * Converts a ResultSet record into a Donut object.
	 * @param record The record to convert
	 * @return The Donut object
	 * @throws SQLException If there is an error parsing the record
	 */
	private Donut parseDonut(ResultSet record) throws SQLException {
		int id = 0;
		String type = "";
		String flavor = "";
		float price = (float)0;
		String desc = "";
		String img = "";
		
		id = record.getInt(1);
		type = record.getString(2);
		flavor = record.getString(3);
		price = record.getFloat(4);
		desc = record.getString(5);
		img = record.getString(6);
		Donut donut = new Donut(id, type, flavor, desc, img);
		donut.setPrice(price);
		return donut;
	}

	/**
	 * Adds a customers cart to the current orders
	 * @param cart The cart to add
	 * @return True if the cart was added, false otherwise
	 */
	public boolean insertCart(Cart cart){
		float totalPrice = 0;
		int totalQuantity = 0;
		int orderID = 0; // TODO fix this
		int customerID = 0;

        SimpleDateFormat str = new SimpleDateFormat("yyyy-MM-dd"); 
        String date = str.format(new Date()); 
        orderID = this.generateOrderID(date);
		List<Order> items = cart.getItems();
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
		System.out.println(items.size());
		for (int i = 0; i < items.size(); i++){
			if (!this.insertOrder(items.get(i), orderID, totalPrice, totalQuantity, customerID, date))
				return false;
		}

		return true;
	}

	/**
	 * This will insert an order into the database.
	 * @param order The order to insert
	 * @param orderID The order id
	 * @param tPrice The total price
	 * @param tQuantity The total quantity
	 * @param customerID The customer id
	 * @param date The date of the order (yyyy-MM-dd)
	 * @return True if the order was inserted, false otherwise
	 */
	private boolean insertOrder(Order order, int orderID, float tPrice, int tQuantity, int customerID, String date){
	
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
			
			return true; // Success
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false; // failure
		}
		
	}

	/**
	 * Inserts a customer into the database.
	 * @param customer The customer to insert
	 * @return True if the customer was inserted, false otherwise
	 */
	private boolean insertCustomer(Customer customer){
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
			return true; // Success
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; // failure
	}

	/**
	 * This will generate a new order id for us to insert assign to our order.
	 * @param date The date of the order
	 * @return The order id
	 */
	private int generateOrderID(String date){
		int max = -1;
		try {
			PreparedStatement stmt = dbConnection.prepareStatement("SELECT MAX(orderID) FROM donutFactory.dOrder WHERE purchaseDate = ?");
			stmt.setString(1, date);
			ResultSet records = stmt.executeQuery();
			while(records.next()) {
				max = records.getInt(1);
			}
			System.out.println(max);
			if (max == -1){
				return 0;
			}
			return max + 1;
			// If no orders for that day currently exist.
		} catch (SQLException e) {
			e.printStackTrace();
			// return 0;
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
			e.printStackTrace();
		}
		
		return -1;
	
	}
	
	/**
	 * Inserts a donut into the database.
	 * @param donut The donut to insert
	 */
	public boolean insertDonut(Donut donut) {
		try {
		    String sql = "INSERT INTO donutFactory.donuts (dType, flavor, price, donutDesc, img) VALUES (?, ?, ?, ?, ?)";
			PreparedStatement stmt = dbConnection.prepareStatement(sql);
			
			stmt.setString(1, donut.getType());
			stmt.setString(2, donut.getFlavor());
			stmt.setFloat(3, donut.getPrice());
			stmt.setString(4, donut.getDescription());
			stmt.setString(5, donut.getImg());
			
			stmt.executeUpdate();
			return true; // Success
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; // failure
	}
	
	/**
	 * Modifies a donut in the database.
	 * @param donut The donut to modify
	 * @return True if the donut was modified, false otherwise
	 */
	public boolean modifyDonut(Donut donut) {
		try {
			if (!this.checkDonut(donut.getId())) {
				return false;
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
			return true; // Success
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; // failure
	
	}
	
	/**
	 * Deletes a donut from the database.
	 * @param donut The donutID to delete
	 * @return True if the donut was deleted, false otherwise (donut likely doesn't exist)
	 */
	public boolean deleteDonut(int donut) {
		try {
			if (!this.checkDonut(donut)) {
				return false;
			}
		    String sql = "delete from donutFactory.donuts where id=?";
			PreparedStatement stmt = dbConnection.prepareStatement(sql);
			stmt.setInt(1, donut);
			
			stmt.executeUpdate();
			return true; // Success
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false; // failure
	
	}
	
	
	/**
	 * Obtains data starting from a given date and until a certain range.
	 * @param date Our starting date.
	 * @param range The number of days back we wish to go.
	 * @return Raw sales data.
	 */
	public ReportData generateSalesReport(String date, int dateRange) {
		ReportData data = new ReportData();
		Integer id;
		Integer quant;
		Float price;
		try {
			String query = "select itemID, quantity, price from dOrder where purchaseDate > STR_TO_DATE(?, \"%Y-%m-%d\") - ? and purchaseDate <= STR_TO_DATE(?, \"%Y-%m-%d\")";
			PreparedStatement stmt = dbConnection.prepareStatement(query);
			
			stmt.setString(1, date);
			stmt.setInt(2, dateRange);
			stmt.setString(3, date);
			ResultSet records = stmt.executeQuery();
			
			// Adds it all into the data object
			while (records.next()){
				id = records.getInt(1);
				quant = records.getInt(2);
				price = records.getFloat(3);
				data.addItemQuantity(id, quant);
				data.addItemPrice(id, price);
			}
			
			return data;
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
		
		public ReportData generateStaleReport(String date, int range) {
			ReportData data = new ReportData();
			Integer id;
			Integer quant;
			try {
				String query = "select id, quantity from inventory where expireTime > STR_TO_DATE(?, \"%Y-%m-%d\") - ? and expireTime <= STR_TO_DATE(?, \"%Y-%m-%d\")";
				PreparedStatement stmt = dbConnection.prepareStatement(query);
				stmt.setString(1, date);
				stmt.setInt(2, range);
				stmt.setString(3, date);
				ResultSet records = stmt.executeQuery();
				
				// Adds it all into the data object
				while (records.next()){
					id = records.getInt(1);
					quant = records.getInt(2);
					data.addItemQuantity(id, quant);
				}
				
				return data;
			} catch (SQLException e) {
				e.printStackTrace();
				return null;
			}
	}

}
