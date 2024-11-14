package io.htmlcss.model;

import java.util.List;

import io.htmlcss.db.*;

import java.util.ArrayList;

/**
 * This object is a model object for passing or storing a tray object.
 * The database will return this model.
 */
public abstract class Tray {
    private static DatabaseFetcher db = null;  // Database fetcher object
    private static List<Tray> trays = null;  // List of all trays in the system
    protected String trayID;  // Unique identifier for the tray
    protected int donutID;    // ID of the donut type on the tray
    protected int quantity;   // Number of donuts on the tray


    // Constructor implementation
    public Tray(int donutID, int quantity) {
        this.donutID = donutID;
        this.quantity = quantity;
        addTray(this); // Whenever a tray is added (whether it is Baking or Inventory), add it to the list of trays
    }

    public Tray(Donut d, int quantity) {
        this(d.getId(), quantity);
    }

    private static void prefetchTrays() {
        if(db == null) {
            db = DBFactory.getDatabaseFetcher();
        }
        if (trays == null) {
        	trays = new ArrayList<Tray>(); // Prevent infinite loop by temporarily setting trays
            trays = db.getListOfTrays();
        }
    }

    protected static void addTray(Tray tray) {
        prefetchTrays();
        trays.add(tray);
    }

    /**
     * Get a list of trays that are currently being used for baking.
     */
    public static List<Tray> getBakingTrays() {
        prefetchTrays();
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
        prefetchTrays();
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
        prefetchTrays();
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
        prefetchTrays();
        List<Tray> bakedTrays = new ArrayList<>();

        // Check each tray in the list to see if it is a BakingTray
        for (Tray tray : trays) {
            if (tray instanceof BakingTray && ((BakingTray) tray).isDoneBaking()) {
                // Convert the BakingTray to an InventoryTray
                InventoryTray inventoryTray = new InventoryTray((BakingTray) tray);
                
                // Add the InventoryTray to the output list of trays & database
                bakedTrays.add(inventoryTray);
                db.insertTray(inventoryTray);
                
                // Remove the BakingTray from the list of trays & database
                trays.remove(tray);
                db.deleteTray(tray);
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
        prefetchTrays();
        List<Tray> staleTrays = new ArrayList<>();

        // Check each tray in the list to see if it is an InventoryTray
        for (Tray tray : trays) {
            if (tray instanceof InventoryTray && ((InventoryTray) tray).isStale()) {
                staleTrays.add(tray);

                trays.remove(tray);
                db.deleteTray(tray);
            }
        }

        return staleTrays;
    }

    /**
     * Get a list of all trays in the system.
     */
    public static List<Tray> getTrays() {
        prefetchTrays();
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