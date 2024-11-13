package io.htmlcss.model;

import io.htmlcss.db.DBFactory;
import io.htmlcss.db.DatabaseFetcher;

/**
 * @author Hunter Lane
 * 
 * This object is a model object for passing or storing a donut object.
 * The database will return this model.
 */
public class Donut {
	
	private String type;
	private Integer id;
	private String description;
	private String img;
	private String flavor;
	private float price;
	
	/**
	 * Donut object constructor
	 * @param id Donut ID
	 * @param type Donut type
	 * @param flavor Donut flavor
	 * @param desc Donut description
	 * @param img Donut image path
	 */
	public Donut(Integer id, String type, String flavor, String desc, String img)
		throws IllegalArgumentException {
		if (id == null || type == null || flavor == null || desc == null || img == null) {
			throw new IllegalArgumentException("All fields must be provided");
		}
		this.id = id;
		this.type = type;
		this.flavor = flavor;
		this.description = desc;
		this.img = img;
	}
	
	/**
	 * Set the price of the donut
	 * @param price Price of the donut
	 */
	public void setPrice(float price) {
		this.price = price;
	}
	
	/**
	 * Get the price of the donut
	 * @return Price of the donut
	 */
	public float getPrice() {
		return this.price;
	}
	
	/**
	 * Get the type of the donut
	 * @return Type of the donut
	 */
	public String getType() {
		return type;
	}
	
	/**
	 * Set the type of the donut
	 * @param type Type of the donut
	 */
	public void setType(String type) {
		this.type = type;
	}
	
	/**
	 * Get the ID of the donut
	 * @return ID of the donut
	 */
	public Integer getId() {
		return id;
	}
	
	/**
	 * Set the ID of the donut
	 * @param id ID of the donut
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	
	/**
	 * Get the description of the donut
	 * @return Description of the donut
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * Set the description of the donut
	 * @param description Description of the donut
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	
	/**
	 * Get the image path of the donut
	 * @return Image path of the donut
	 */
	public String getImg() {
		return img;
	}
	
	/**
	 * Set the image path of the donut
	 * @param img Image path of the donut
	 */
	public void setImg(String img) {
		this.img = img;
	}
	
	/**
	 * Get the flavor of the donut
	 * @return Flavor of the donut
	 */
	public String getFlavor() {
		return flavor;
	}
	
	/**
	 * Set the flavor of the donut
	 * @param flavor Flavor of the donut
	 */
	public void setFlavor(String flavor) {
		this.flavor = flavor;
	}

	/**
	 * Get a donut object from the database
	 * @param donutID ID of the donut
	 * @return Donut object
	 */
	public static Donut getDonut(int donutID) {
		DatabaseFetcher db = DBFactory.getDatabaseFetcher();
		return db.getDonut(donutID);
	}
}
