package game.model.structures.primaryProducer;

import game.model.resources.Resource;
import game.model.resources.ResourceManager;

public abstract class ResourceHolder extends PrimaryProducer {

    private ResourceManager resourceManager;

    ResourceHolder(ResourceManager resourceManager) {
        this.resourceManager = resourceManager;
    }

    protected void addToResourceManager(Resource resource, Integer amount) {
        resourceManager.addResource(resource, amount);
    }

    protected void removeFromResourceManager(Resource resource, Integer amount) {
        resourceManager.removeResource(resource, amount);
    }

}
