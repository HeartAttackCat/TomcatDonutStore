package io.htmlcss.model;
import java.time.LocalDateTime;

import io.htmlcss.db.DBFactory;

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
        super(DBFactory.getDatabaseFetcher().getTrayID(), donutID, quantity);
        this.startBakingTime = startBakingTime;
        endBakingTime = startBakingTime.plusMinutes(20);
    }

    public BakingTray(int trayId, int donutID, int quantity, LocalDateTime startBakingTime) {
        // End baking time is 20 minutes after start baking time
        this(donutID, quantity, startBakingTime);
        this.trayID = trayId;
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