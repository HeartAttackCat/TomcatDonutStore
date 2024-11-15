package io.htmlcss.model;
import java.time.LocalDateTime;

/**
 * This object is a model object for passing or storing an inventory tray object.
 * The database will return this model.
 * 
 * InventoryTrays are automatically created when a BakingTray finishes baking.
 * These constructors should not be called by other classes.
 */
public class InventoryTray extends Tray {
    private LocalDateTime expirationDate;

    /**
     * This should only be called by DatabaseFetcher, not by other classes.
     * InventoryTrays are automatically created when a BakingTray finishes baking.
     * @param donutID
     * @param quantity
     * @param expirationDate
     */
    public InventoryTray(int trayId, int donutID, int quantity, LocalDateTime expirationDate) {
        super(trayId, donutID, quantity);
        this.expirationDate = expirationDate;
    }

    /**
     * This should only be called by Tray, not by other classes.
     * @param bakingTray
     */
    public InventoryTray(BakingTray bakingTray) {
        super(bakingTray);
        // We make a new InventoryTray from a BakingTray that has finished baking
        
        // Expire 3 days after the end of baking
        this.expirationDate = bakingTray.getEndBakingTime().plusDays(3);
    }

    /**
     * Get the expiration date of the InventoryTray.
     * @return expirationDate
     */
    public LocalDateTime getExpirationDate() {
        return this.expirationDate;
    }

    /**
     * Check if the InventoryTray is stale.
     * @return true if the InventoryTray is stale, false otherwise
     */
    public boolean isStale() {
        return LocalDateTime.now().isAfter(expirationDate);
    }

}