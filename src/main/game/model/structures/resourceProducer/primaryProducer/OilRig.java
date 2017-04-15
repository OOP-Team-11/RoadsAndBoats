package game.model.structures.resourceProducer.primaryProducer;

import game.model.resources.ResourceType;
import game.model.resources.ResourceManager;
import game.model.structures.resourceProducer.ResourceProducerHolder;

public class OilRig extends ResourceProducerHolder {

    public OilRig(ResourceManager resourceManager) {
        super(resourceManager);
    }

    public void storeResource(ResourceType resourceType, int amount) {
        addToResourceManager(resourceType, amount);
    }

    public void takeResource(ResourceType resourceType, int amount) {
        removeFromResourceManager(resourceType, amount);
    }


    @Override
    public boolean produce(ResourceManager resourceManager) {
        return false;
    }
}
