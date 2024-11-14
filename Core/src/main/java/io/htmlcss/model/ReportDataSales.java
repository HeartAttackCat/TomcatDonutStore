package io.htmlcss.model;
import java.util.Dictionary;
import java.util.Enumeration;
import java.util.Hashtable;

public class ReportDataSales extends ReportData{
	private Dictionary<Integer, Float> dPrice = null;
	
	/**
	 * Constructor
	 */
	public ReportDataSales() {
		super();
		this.dPrice = new Hashtable<Integer, Float>();
	}

	public void addItemPrice(int id, float price) {
		this.dPrice.put(id, price);
	}
	
	public float generateTotalSales() {
		float sales = 0;
		Enumeration<Integer> keys = this.dPrice.keys(); 
		Integer temp;
		while(keys.hasMoreElements()) {
			temp = keys.nextElement();
			sales += this.dPrice.get(temp) * this.getdQuant().get(temp);
		}
		
		return sales;
	}
	
	public float obtainItemSales(int id) {
	    Float sales = this.dPrice.get(id);
		if (sales == null) {
			return (float) -1;
		}
		return sales * super.getdQuant().get(id);
	}
	
}
