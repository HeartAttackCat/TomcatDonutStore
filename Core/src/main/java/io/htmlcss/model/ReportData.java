package io.htmlcss.model;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class ReportData {
	// Dictionaries which will contain our item information.
	private Dictionary<Integer, Integer> dQuant = null; // id -> quantity
	private Dictionary<Integer, Float> dPrice = null;
	
	/**
	 * Constructor
	 */
	public ReportData() {
		this.dQuant = new Hashtable<Integer, Integer>();
		this.dPrice = new Hashtable<Integer, Float>();
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
	
	/**
	 * Adds a given item's total sales.
	 * @param id
	 * @param price
	 */
	public void addItemPrice(int id, float price) {
		this.dPrice.put(id, price);
	}
	
	/**
	 * Gets the total sales for an item.
	 * @return
	 */
	public float generateTotalSales() {
		float sales = 0;
		Enumeration<Integer> keys = this.dPrice.keys(); 
		Integer temp;
		while(keys.hasMoreElements()) {
			temp = keys.nextElement();
			sales += this.dPrice.get(temp) * this.dQuant.get(temp);
		}
		
		return sales;
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
	 * Obtains the total sales for a requested item.
	 * @param id
	 * @return
	 */
	public float obtainItemSales(int id) {
	    Float sales = this.dPrice.get(id);
		if (sales == null) {
			return (float) -1;
		}
		return sales * this.dQuant.get(id);
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
}
