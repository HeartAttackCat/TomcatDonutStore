/**
 * A basic container for holding information related holding customer card 
 * information.
 */
package io.htmlcss.model;
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
    public boolean addDonut(int donutID, int quantity) throws IllegalArgumentException {
    	for(Order i: items) {
    		if(i.getItem().getId() == donutID) {
    			i.setQuantity(i.getQuantity() + quantity);
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
}
