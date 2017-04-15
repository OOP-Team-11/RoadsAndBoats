package game.model.structures.resourceProducer;

import game.model.resources.ResourceManager;
import game.model.resources.ResourceType;

public abstract class ResourceProducerHolder extends ResourceProducer {

    private ResourceManager resourceManager;

    protected ResourceProducerHolder(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    protected void addToResourceManager(ResourceType resourceType, Integer amount) {
        resourceManager.addResource(resourceType, amount);
    }

    protected void removeFromResourceManager(ResourceType resourceType, Integer amount) {
        resourceManager.removeResource(resourceType, amount);
    }

}
