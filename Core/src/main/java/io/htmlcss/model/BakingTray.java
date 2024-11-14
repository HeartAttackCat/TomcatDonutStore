package io.htmlcss.model;
import java.time.LocalDateTime;

/**
 * This object is a model object for passing or storing a baking tray object.
 * The database will return this model.
 */
public class BakingTray extends Tray {
    private LocalDateTime startBakingTime;
    private LocalDateTime endBakingTime;

    public BakingTray(Donut d, int quantity, LocalDateTime startBakingTime) {
        // End baking time is 20 minutes after start baking time
        this(d.getId(), quantity, startBakingTime);
    }

    public BakingTray(int donutID, int quantity, LocalDateTime startBakingTime) {
        // End baking time is 20 minutes after start baking time
        super(donutID, quantity);
        this.startBakingTime = startBakingTime;
        endBakingTime = startBakingTime.plusMinutes(20);
    }

    public LocalDateTime getStartBakingTime() {
        return this.startBakingTime;
    }

    public LocalDateTime getEndBakingTime() {
        return this.endBakingTime;
    }

    public boolean isBaking() {
        return LocalDateTime.now().isAfter(startBakingTime) && LocalDateTime.now().isBefore(endBakingTime);
    }

    public boolean isDoneBaking() {
        return LocalDateTime.now().isAfter(endBakingTime);
    }
}