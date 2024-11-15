/**
 * A basic container for holding information related holding customer card 
 * information.
 */
package io.htmlcss.model;
import java.util.ArrayList;
import io.htmlcss.db.*;
import java.util.List;

public class Cart {
    private Customer buyer = null;
    private List<Order> items;
    private boolean status;
    private int cartID;
    private String date;

    /**
     * Constructor
     * @param buyer Customer placing the order
     * @param items List of orders
     */
    public Cart(Customer buyer, List<Order> items)
        throws IllegalArgumentException {
        if (buyer == null) {
            throw new IllegalArgumentException("Buyer cannot be null");
        }
        if (items == null || items.isEmpty()) {
            throw new IllegalArgumentException("Items cannot be null or empty");
        }
        this.buyer = buyer;
        this.items = items;
        this.status = false;
    }
    
    /**
     * This is a constructor for when we are fetching orders.
     */
    public Cart(Customer buyer, List<Order> items, boolean status, int id, String date){
    	this(buyer, items);
    	this.status = status;
    	this.cartID = id;
    	this.date = date;
    }
    
    /**
     * Constructor for when providing a singular order. This is mainly to 
     * allow us combine the carts later.
     * @param buyer
     * @param items
     * @param status
     * @param id
     * @param date
     */
    public Cart(Customer buyer, Order items, boolean status, int id, String date){
    	this();
    	this.items.add(items);
    	this.buyer = buyer;
    	this.status = status;
    	this.cartID = id;
    	this.date = date;
    }

    /**
     * Constructor for cart object when first loaded in on front end.
     */
    public Cart(){
    	this.status = false;
        items = new ArrayList<Order>();
    }
    


    /**
     * Get the buyer of the cart
     * @return Customer object
     */
    public Customer getBuyer(){
        return this.buyer;
    }

    /**
     * Get the list of orders in the cart
     * @return List of orders
     */
    public List<Order> getItems(){
        return this.items;
    }
    
    /*
     * Get the total cost of the Cart
     */
    public float getTotalCost() {
    	float cost = 0;
    	for(Order i: items) {
    		Donut d = i.getItem();
    		int q = i.getQuantity();
    		cost += d.getPrice() * q;
    	}
    	return cost;
    }

    /*
     * Add a donut to the cart
     * @param donutID ID of the donut to add
     * @param quantity Quantity of the donut to add
     */
    public boolean addDonuts(int donutID, int quantity) throws IllegalArgumentException {
    	for(Order i: items) {
    		if(i.getItem().getId() == donutID) {
                int q = i.getQuantity() + quantity;
    			if (q <= 0) {
                    // This is a removal
    				items.remove(i);
    			} else {
    				i.setQuantity(q);
    			}
    			return true;
    		}
    	}

        // Donut not found in cart, add new order
        Donut d = Donut.getDonut(donutID);
        if(d == null) {
        	return false;
        }
        items.add(new Order(d, quantity));
    	return true;
    }

    /**
     * Remove a donut from the cart
     * @param donutID ID of the donut to remove
     */
    public boolean removeDonuts(int donutID, int quantity) {
        return addDonuts(donutID, quantity * -1);
    }

    /**
     * Sets the customer.
     */
    public void setCustomer(Customer customer){
        this.buyer = customer;
    }
    
    public void addOrder(Order order) {
    	this.items.add(order);    	
    }
    
    /**
     * sets itself as complete and updates the database 
     */
    public void setComplete() {
    	DBFactory.getDatabaseFetcher().updateOrder(this.date, this.cartID);
    	this.status = true;
    }
    
    public int getOrderID() {
    	return this.cartID;
    }
    
    public String getDate() {
    	return this.date;
    }
    
    public boolean getStatus() {
    	return this.status;
    }
    
    
    /**
     * Gets a list of active orders that need to be satisfied.
     * @return
     */
    public static ArrayList<Cart> getActiveOrders(){
    	DatabaseFetcher db = DBFactory.getDatabaseFetcher();
    	return (ArrayList<Cart>) db.getActiveOrders();
    }
    
    /**
     * Gets a list of the active orders.
     * @return
     */
    public static ArrayList<Cart> activeOrders(){
    	return getActiveOrders();
    }
}
