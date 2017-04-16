package game.model.tile;

import game.model.resources.ResourceManager;
import game.model.resources.ResourceType;

public class TileCompartment {
    private boolean hasWater;
    private ResourceManager resourceManager;

    public TileCompartment(boolean hasWater) {
        this.hasWater = hasWater;
        resourceManager = new ResourceManager();
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

    public ResourceManager getResourceManager() {
        return this.resourceManager;
    }

    public void storeResource(ResourceType type, Integer numberToAdd) {
        resourceManager.addResource(type, numberToAdd);
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

    public boolean hasNoResources() {
        return resourceManager.hasNoResources();
    }
}
