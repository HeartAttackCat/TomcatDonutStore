/**
 * A basic container for holding information related holding customer card 
 * information.
 */
package io.htmlcss.model;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private Customer buyer = null;
    private List<Order> items;

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
    }

    /**
     * Constructor for cart object when first loaded in on front end.
     */
    public Cart(){
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
}
