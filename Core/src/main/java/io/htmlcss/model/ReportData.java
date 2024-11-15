package io.htmlcss.model;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

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

	public Dictionary<Integer, Integer> getdQuant() {
		return this.dQuant;
	}
}
