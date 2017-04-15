package game.model.tile;

import game.model.resources.ResourceManager;
import game.model.resources.ResourceType;

public class TileCompartment {
    private boolean hasWater;
    private ResourceManager resourceManager;

    public TileCompartment(boolean hasWater) {
        this.hasWater = hasWater;
    }

    public boolean hasWater() {
        return this.hasWater;
    }

    public void setHasWater(boolean hasWater) {
        this.hasWater = hasWater;
    }

    public int getWealthPoints() {
        return resourceManager.getWealthPoints();
    }

    public void storeResource(ResourceType type, Integer numberToRemove) {
        resourceManager.addResource(type, numberToRemove);
    }

    public boolean takeResource(ResourceType type, Integer numberToRemove) {
        return resourceManager.removeResource(type, numberToRemove);
    }

    public boolean hasResource(ResourceType wellDoesIt) {
        return resourceManager.hasResource(wellDoesIt);
    }

    public int getResourceCount(ResourceType desiredType) {
        return resourceManager.getResourceCount(desiredType);
    }
}
