package game.model.structures.primaryProducer;

import game.model.resources.ResourceType;
import game.model.resources.ResourceManager;

public abstract class ResourceHolder extends PrimaryProducer {

    private ResourceManager resourceManager;

    ResourceHolder(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    protected void addToResourceManager(ResourceType resourceType, Integer amount) {
        resourceManager.addResource(resourceType, amount);
    }

    protected void removeFromResourceManager(ResourceType resourceType, Integer amount) {
        resourceManager.removeResource(resourceType, amount);
    }

}
