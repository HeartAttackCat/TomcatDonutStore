/**
 * A basic container for holding information related holding customer card 
 * information.
 */
package io.htmlcss.model;
import java.util.List;
import java.util.ArrayList;

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
}
