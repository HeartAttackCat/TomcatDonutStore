package io.htmlcss.api;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class databaseFetcher {
	Connection dbConnection = null;
	private String dbUrl = "jdbc:mysql://localhost:3306/DonutShop";
	private String dbUser = "webdev";
	private String dbPassword = "abc123";
	
	/**
	 * Constructor for the databaseFetcher class
	 */
	public databaseFetcher() {

		// Check for a ~/.env/DB_CONFIG file
		// If it exists, read the database connection information from it
		// If it doesn't exist, use the default values
		File configFile = new File(System.getProperty("user.home") + "/.env/DB_CONFIG");
		if (configFile.exists()) {
			System.out.println("Using database connection information from " + configFile);
			// Read the database connection information from the config file
			// and update the dbUrl, dbUser, and dbPassword variables accordingly
			boolean hasUrl = false;
			boolean hasUser = false;
			boolean hasPassword = false;
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

	/**
	 * Convert a query result to a JSON string
	 * @param query The query result to convert
	 * @return The JSON string representation of the query result
	 */
    public static String toJSON(List<List<String>> query) {
        if (query == null || query.isEmpty()) {
            return "[]";  // Return empty JSON array if input is null or empty
        }

        StringBuilder json = new StringBuilder();
        json.append("[\n");  // Start of the JSON array

        // The first list contains column names
        List<String> columns = query.get(0);

        // Iterate over each row starting from index 1 (skip the column names)
        for (int i = 1; i < query.size(); i++) {
            List<String> row = query.get(i);
            json.append("  {");

            for (int j = 0; j < columns.size(); j++) {
                String columnName = columns.get(j);
                String cellValue = row.get(j);

                // Append column name and value in JSON format
                json.append("\"").append(columnName).append("\": ")
                    .append("\"").append(escapeJson(cellValue)).append("\"");

                // Append a comma if not the last column
                if (j < columns.size() - 1) {
                    json.append(", ");
                }
            }

            json.append("}");
            // Append a comma and newline if not the last row
            if (i < query.size() - 1) {
                json.append(",");
            }
            json.append("\n");
        }

        json.append("]");  // End of the JSON array
        return json.toString();
    }

    // Helper function to escape special characters in JSON
    private static String escapeJson(String value) {
        if (value == null) {
            return "";  // Handle null values as empty strings
        }
        return value.replace("\\", "\\\\")  // Escape backslashes
                    .replace("\"", "\\\"")  // Escape quotes
                    .replace("\n", "\\n")   // Escape newlines
                    .replace("\r", "\\r")   // Escape carriage returns
                    .replace("\t", "\\t");  // Escape tabs
    }

	/**
	 * Execute a query on the database
	 * @param query The query to execute
	 * @return The result of the query as a list of lists of strings
	 */
	public List<List<String>> executeQuery(String query) {
		// This is a cursed way to do this, but I'm not sure how to do it better...
		// Outer list is the rows, inner list is the column values. 0th index is the column names.
		List<List<String>> return_query = null;
		
		try {
			Statement stmt = dbConnection.createStatement();
			ResultSet records = stmt.executeQuery(query);
			ResultSetMetaData rsmd = records.getMetaData();
			
			return_query = new ArrayList<List<String>>();
			
			List<String> column_names = new ArrayList<String>();
			int col_len = rsmd.getColumnCount();
			for(int i = 1; i <= col_len; i++) {
				column_names.add(rsmd.getColumnName(i));
			}
			return_query.add(column_names);
			
			while(records.next()) {
				List<String> row = new ArrayList<String>();
				for(int i = 1; i <= col_len; i++) {
					row.add(records.getString(i));
				}
				return_query.add(row);
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return return_query;
	}
	
	public List<List<String>> getOrderById(int order_id){
		return executeQuery("SELECT * FROM orders WHERE order_id = " + order_id);
	}

	public List<List<String>> getOrdersByProductId(int product_id){
		return executeQuery("SELECT * FROM orders WHERE product_id = " + product_id);
	}


	public List<List<String>> getProducts(){
		return executeQuery("select * from Products");
	}

	public List<List<String>> getDonuts() {
		return executeQuery("SELECT * from Donuts");
	}
}
