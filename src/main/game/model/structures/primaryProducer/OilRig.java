package game.model.structures.primaryProducer;

import game.model.resources.Fuel;
import game.model.resources.Resource;
import game.model.resources.ResourceManager;

import java.util.Map;

public class OilRig extends ResourceHolder {

    public OilRig(ResourceManager resourceManager) {
        super(resourceManager);
    }

    public void storeResource(Resource resource, int amount) {
        addToResourceManager(resource, amount);
    }

    public void takeResource(Resource resource, int amount) {
        removeFromResourceManager(resource, amount);
    }

    @Override
    public Map<Resource, Integer> produce() {
        addToResourceManager(new Fuel(), 1);
        return null;
    }

}
