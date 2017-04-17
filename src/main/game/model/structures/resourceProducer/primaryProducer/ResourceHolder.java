package game.model.structures.resourceProducer.primaryProducer;

import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.Structure;

public abstract class ResourceHolder extends Structure {

    private ResourceManager resourceManager;

    protected ResourceHolder() {
        this.resourceManager = new ResourceManager();
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

    public abstract boolean produce();

}
