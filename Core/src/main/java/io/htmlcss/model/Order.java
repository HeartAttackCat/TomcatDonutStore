/**
 * A basic container for information related to a singular item being ordered.
 */
package io.htmlcss.model;

public class Order {
    private Donut item;
    private int quantity;

    /**
     * Order constructor
     * @param item Donut object
     * @param quantity Quantity of donuts
     */
    public Order(Donut item, int quantity) throws IllegalArgumentException {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        this.item = item;
        this.quantity = quantity;
    }
    
    /**
     * Get the donut object
     * @return Donut object
     */
    public Donut getItem(){
        return this.item;
    }

    /**
     * Get the quantity of donuts
     * @return Quantity of donuts
     */
    public int getQuantity(){
        return this.quantity;
    }

    /**
     * Set the quantity of donuts
     * @param quantity Quantity of donuts
     */
    public void setQuantity(int quantity) throws IllegalArgumentException {
        if (quantity < 0) {
            throw new IllegalArgumentException("Quantity must be greater than 0");
        }
        this.quantity = quantity;
    }
}
