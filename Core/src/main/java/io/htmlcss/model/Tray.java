package io.htmlcss.model;

import java.util.List;
import java.util.ArrayList;
import io.htmlcss.model.Donut;

/**
 * This object is a model object for passing or storing a tray object.
 * The database will return this model.
 */
public abstract class Tray {
    private static List<Tray> trays;  // List of all trays in the system
    protected String trayID;  // Unique identifier for the tray
    protected int donutID;    // ID of the donut type on the tray
    protected int quantity;   // Number of donuts on the tray


    // Constructor implementation
    public Tray(int donutID, int quantity) {
        this.donutID = donutID;
        this.quantity = quantity;
    }

    public Tray(Donut d, int quantity) {
        this(d.getId(), quantity);
    }

    /**
     * Get a list of trays that are currently being used for baking.
     */
    public static List<Tray> getBakingTrays() {
        List<Tray> bakingTrays = new ArrayList<>();

        // Check each tray in the list to see if it is a BakingTray
        for (Tray tray : trays) {
            if (tray instanceof BakingTray) {
                bakingTrays.add(tray);
            }
        }

        return bakingTrays;
    }

    /**
     * Get a list of trays that are in the current inventory AND not stale.
     */
    public static List<Tray> getInventoryTrays() {
        List<Tray> inventoryTrays = new ArrayList<>();

        // Check each tray in the list to see if it is an InventoryTray
        for (Tray tray : trays) {
            if (tray instanceof InventoryTray && !((InventoryTray) tray).isStale()) {
                inventoryTrays.add(tray);
            }
        }
        return inventoryTrays;
    }

    /**
     * Get a list of trays that have gone stale.
     */
    public static List<Tray> getStaleTrays() {
        List<Tray> staleTrays = new ArrayList<>();

        // Check each tray in the list to see if it is an InventoryTray
        for (Tray tray : trays) {
            if (tray instanceof InventoryTray && ((InventoryTray) tray).isStale()) {
                staleTrays.add(tray);
            }
        }
        return staleTrays;
    }

    /**
     * Update the list of trays when the trays in the oven are done baking. (BakingTray -> InventoryTray)
     * This should be called after the baker has removed the trays from the oven.
     * @return List of trays that have finished baking and should be moved to the inventory
     */
    public static List<Tray> updateBakedTrays() {
        List<Tray> bakedTrays = new ArrayList<>();

        // Check each tray in the list to see if it is a BakingTray
        for (Tray tray : trays) {
            if (tray instanceof BakingTray && ((BakingTray) tray).isDoneBaking()) {
                // Convert the BakingTray to an InventoryTray
                InventoryTray inventoryTray = new InventoryTray((BakingTray) tray);
                trays.add(inventoryTray);
                bakedTrays.add(inventoryTray);
                
                // Remove the BakingTray from the list of trays
                trays.remove(tray);
            }
        }

        return bakedTrays;
    }

    /**
     * Update the list of trays when the trays in the inventory have gone stale.
     * This should be called after the baker has thrown away the stale trays.
     * @return List of trays that have gone stale and should be removed from the inventory
     */
    public static List<Tray> updateStaleTrays() {
        List<Tray> staleTrays = new ArrayList<>();

        // Check each tray in the list to see if it is an InventoryTray
        for (Tray tray : trays) {
            if (tray instanceof InventoryTray && ((InventoryTray) tray).isStale()) {
                staleTrays.add(tray);
                trays.remove(tray);
            }
        }

        return staleTrays;
    }

    /**
     * Get a list of all trays in the system.
     */
    public static List<Tray> getTrays() {
        return trays;
    }

    /**
     * Get the type of donut on the tray.
     */
    public int getDonutID() {
        return this.donutID;
    }

    /**
     * Get the quantity of donuts on the tray.
     */
    public int getQuantity() {
        return this.quantity;
    }

    /**
     * Get the unique identifier for the tray.
     */
    public String getTrayID() {
        return this.trayID;
    }
}