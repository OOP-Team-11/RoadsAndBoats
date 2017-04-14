package game.model.structures.primaryProducer;

import game.model.resources.ResourceType;
import game.model.resources.ResourceManager;

import java.util.Map;

public class OilRig extends ResourceHolder {

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
    public Map<ResourceType, Integer> produce() {
        addToResourceManager(ResourceType.FUEL, 1);
        return null;
    }

}
