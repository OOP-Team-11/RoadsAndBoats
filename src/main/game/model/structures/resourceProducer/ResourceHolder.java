package game.model.structures.resourceProducer;

import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.Structure;

import java.util.HashMap;

public abstract class ResourceHolder extends Structure {

    private ResourceManager resourceManager;

    protected ResourceHolder() {
        this.resourceManager = new ResourceManager();
    }

    public int getWealthPoints() {
        return resourceManager.getWealthPoints();
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

    public HashMap<ResourceType,Integer> getResourceCounts(){
        HashMap<ResourceType,Integer> resourceCounts = new HashMap<>();
        ResourceType[] resourceTypes = {
                ResourceType.BOARDS, ResourceType.CLAY, ResourceType.GOLD, ResourceType.COINS,
                ResourceType.FUEL, ResourceType.GOOSE, ResourceType.IRON, ResourceType.PAPER,
                ResourceType.STOCKBOND, ResourceType.STONE, ResourceType.TRUNKS
        };
        for(ResourceType type : resourceTypes){
            int count = resourceManager.getResourceCount(type);
            if(count > 0)
                resourceCounts.put(type,count);

        }
        return resourceCounts;

    }

    public int getResourceCount(ResourceType desiredType) {
        return resourceManager.getResourceCount(desiredType);
    }

    public abstract boolean produce();

}
