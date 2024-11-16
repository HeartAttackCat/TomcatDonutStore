package io.htmlcss.model;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

import io.htmlcss.db.DBFactory;

public class ReportData {
	// Dictionaries which will contain our item information.
	private Dictionary<Integer, Integer> dQuant = null; // id -> quantity
	
	/**
	 * Constructor
	 */
	public ReportData() {
		this.dQuant = new Hashtable<Integer, Integer>();
	}
	
	/**
	 * Adds a given items sales data.
	 * @param id
	 * @param quant
	 */
	public void addItemQuantity(int id, int quant) {
		Integer sum = this.dQuant.get(id);
		if (sum == null) {
			this.dQuant.put(id, quant);
		} else {
			this.dQuant.put(id, sum + quant);
		}
	}
	
	public Dictionary<Integer, Integer> getdQuant() {
		return this.dQuant;
	}
	
	/**
	 * Obtains the total amount of items that have been sold.
	 * @return
	 */
	public int generateTotalQuantity() {
		int quantity = 0;
		Enumeration<Integer> keys = this.dQuant.keys(); 
		
		while(keys.hasMoreElements()) {
			quantity += this.dQuant.get(keys.nextElement());
		}
		
		return quantity;
	}
	
	/**
	 * Obtains the quantity of a specific item.
	 * @param id
	 * @return
	 */
	public int obtainItemQuantity(int id) {
	    Integer quantity = this.dQuant.get(id);
		if (quantity == null) {
			return -1;
		}
		return quantity;
	}
	
	public Enumeration<Integer> getDonutIds() {
		return this.dQuant.keys();
	}

	public static ReportData getSalesReport(String date, int range) {
		// Verify that the date is in the correct format.
		if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
			throw new IllegalArgumentException("Invalid date format. Expected format: yyyy-MM-dd");
		}
		// Verify that the range is a positive integer.
		if (range < 0) {
			throw new IllegalArgumentException("Invalid range. Expected a positive integer.");
		}
		
		return DBFactory.getDatabaseFetcher().generateSalesReport(date, range);
	}

	public static ReportData getStaleReport(String date, int range) {
		// Verify that the date is in the correct format.
		if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
			throw new IllegalArgumentException("Invalid date format. Expected format: yyyy-MM-dd");
		}
		// Verify that the range is a positive integer.
		if (range < 0) {
			throw new IllegalArgumentException("Invalid range. Expected a positive integer.");
		}
		
		return DBFactory.getDatabaseFetcher().generateStaleReport(date, range);

	}
}
