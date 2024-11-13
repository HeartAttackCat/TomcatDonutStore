import java.time.LocalDateTime;

/**
 * This object is a model object for passing or storing an inventory tray object.
 * The database will return this model.
 */
public class InventoryTray extends Tray {
    private LocalDateTime expirationDate;

    private InventoryTray(int donutID, int quantity, LocalDateTime expirationDate) {
        super(donutID, quantity);
        this.expirationDate = expirationDate;
    }

    public InventoryTray(BakingTray bakingTray) {
        // We make a new InventoryTray from a BakingTray that has finished baking
        
        // Expire 3 days after the end of baking
        this(bakingTray.getDonutID(), bakingTray.getQuantity(), bakingTray.getEndBakingTime().plusDays(3));
    }

    public LocalDateTime getExpirationDate() {
        return this.expirationDate;
    }

    public boolean isStale() {
        return LocalDateTime.now().isAfter(expirationDate);
    }

}