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
    protected int trayID;  // Unique identifier for the tray
    protected int donutID;    // ID of the donut type on the tray
    protected int quantity;   // Number of donuts on the tray


    // Constructor implementation
    protected Tray(int trayID, int donutID, int quantity) {
        this.trayID = trayID;
        this.donutID = donutID;
        this.quantity = quantity;
        addTray(this); // Whenever a tray is added (whether it is Baking or Inventory), add it to the list of trays
    }

    protected Tray(int trayid, Donut d, int quantity) {
        this(trayid, d.getId(), quantity);
    }

    protected Tray(Tray t) {
        this(t.getTrayID(), t.getDonutID(), t.getQuantity());
    }

    /**
     * Prefetch the list of trays from the database.
     */
    private static void prefetchTrays() {
        if(db == null) {
            db = DBFactory.getDatabaseFetcher();
        }
        if (trays == null) {
        	trays = new ArrayList<Tray>(); // Prevent infinite loop by temporarily setting trays
            trays = db.getListOfTrays();
        }
    }

    /**
     * Add a tray to the list of trays. (Called by Tray constructor to self-append)
     */
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
     * Get a list of trays that have finished baking.
     */
    public static List<Tray> getBakedTrays() {
        prefetchTrays();
        List<Tray> bakedTrays = new ArrayList<>();

        // Check each tray in the list to see if it is an InventoryTray
        for (Tray tray : trays) {
            if (tray instanceof BakingTray && ((BakingTray) tray).isDoneBaking()) {
                bakedTrays.add(tray);
            }
        }

        return bakedTrays;
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
    public int getTrayID() {
        return this.trayID;
    }

    /**
     * Check if the inventory has enough donuts of a certain type.
     * @param donutID The ID of the donut to check for
     * @param quantity The number of donuts to check for
     * @return
     */
    public static boolean hasEnoughDonutsInInventory(int donutID, int quantity) {
        int q_needed = quantity;

        int q_total = 0;
        // Check if there are enough donuts in the inventory
        for (Tray t : getInventoryTrays()) {
            if (t.getDonutID() == donutID) {
                q_total += t.getQuantity();
            }
        }

        return q_total >= q_needed;
    }

    /**
     * Check if the inventory has enough donuts of a certain type.
     * @param d The type of the donut to check for
     * @param quantity The number of donuts to check for
     * @return
     */
    public static boolean hasEnoughDonutsInInventory(Donut d, int quantity) {
        return hasEnoughDonutsInInventory(d.getId(), quantity);
    }

    /**
     * Take donuts out of the inventory.
     * @param donutID The ID of the donut to take out
     * @param quantity The number of donuts to take out
     * @return The number of donuts taken out, or -1 if there are not enough donuts in the inventory
     */
    public static int takeDonutsFromInventory(int donutID, int quantity) {
        int taken = 0;
        int q_needed = quantity;

        // Check if there are enough donuts in the inventory
        if (!hasEnoughDonutsInInventory(donutID, quantity)) {
            return -1;
        }

        // The logical pass-thru
        for (Tray t : getInventoryTrays()) {
            if (t.getDonutID() == donutID) {
                int q = t.getQuantity();
                if (q > q_needed) {
                    t.quantity -= q_needed;
                    taken += q_needed;
                    q_needed = 0;
                    db.updateTray(t);
                } else {
                    taken += q;
                    q_needed -= q;
                    t.quantity = 0;
                    db.deleteTray(t);
                }
                if (quantity == 0) {
                    break;
                }
            }
        }
        return taken;
    }

    /**
     * Take donuts out of the inventory.
     * @param d The type of the donut to take out
     * @param quantity The number of donuts to take out
     * @return The number of donuts taken out, or -1 if there are not enough donuts in the inventory
     */
    public static int takeDonutsFromInventory(Donut d, int quantity) {
        return takeDonutsFromInventory(d.getId(), quantity);
    }
}