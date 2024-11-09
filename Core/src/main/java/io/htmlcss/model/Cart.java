/**
 * A basic container for holding information related holding customer card 
 * information.
 */
package io.htmlcss.model;
import java.util.ArrayList;

public class Cart {
    private Customer buyer;
    private ArrayList<Order> items;

    /**
     * Constructor
     * @param buyer
     * @param items
     */
    public Cart(Customer buyer, ArrayList<Donut> items){
        this.buyer = buyer;
        this.items = items;

    }


    public Customer getBuyer(){
        return this.buyer;
    }

    public ArrayList<Order> getItems(){
        return this.items
    }
}
