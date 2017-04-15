package game.model.structures.resourceProducer.primaryProducer;

import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.ResourceHolder;

public class OilRig extends ResourceHolder {

    private static final int FUEL_AMT = 1;

    public OilRig() {

    }

    @Override
    public boolean produce() {
        storeResource(ResourceType.FUEL, FUEL_AMT);
        return true;
    }

    public void storeResource(ResourceType resourceType, int amount) {
        addToResourceManager(resourceType, amount);
    }

    public boolean takeResource(ResourceType resourceType, int amount) {
        return removeFromResourceManager(resourceType, amount);
    }

    public boolean hasResource(ResourceType resourceType) {
        return hasResourceInResourceManager(resourceType);
    }

    public int getResourceCount(ResourceType resourceType) {
        return getResourceCountFromResourceManager(resourceType);
    }


}
