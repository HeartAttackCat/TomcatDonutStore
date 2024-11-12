/**
 * A basic container for information related to a singular item being ordered.
 */
package io.htmlcss.model;

public class Order {
    private Donut item;
    private int quantity;

    /**
     * Constructor
     * @param item
     * @param quantity
     */
    public Order(Donut item, int quantity){
        this.item = item;
        this.quantity = quantity;
    }


    public Donut getItem(){
        return this.item;
    }

    public int getQuantity(){
        return this.quantity;
    }
}
