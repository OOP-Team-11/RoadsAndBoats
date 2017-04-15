package game.model.structures.resourceProducer;

import game.model.resources.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.Structure;

public abstract class ResourceHolder extends Structure {

    private ResourceManager resourceManager;

    protected ResourceHolder() {
        this.resourceManager = new ResourceManager();
    }

    protected int getWealthPoints() {
        return resourceManager.getWealthPoints();
    }

    protected void addToResourceManager(ResourceType resourceType, Integer amount) {
        resourceManager.addResource(resourceType, amount);
    }

    protected boolean removeFromResourceManager(ResourceType resourceType, Integer amount) {
        return resourceManager.removeResource(resourceType, amount);
    }

    protected boolean hasResourceInResourceManager(ResourceType resourceType) {
        return resourceManager.hasResource(resourceType);
    }

    protected int getResourceCountFromResourceManager(ResourceType resourceType) {
        return resourceManager.getResourceCount(resourceType);
    }

    public abstract boolean produce();

}
