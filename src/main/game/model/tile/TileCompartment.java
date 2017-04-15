package game.model.tile;

import game.model.resources.ResourceManager;
import game.model.resources.ResourceType;

import java.util.HashMap;

public class TileCompartment {
    private boolean hasWater;
    private ResourceManager resourceManager;

    public TileCompartment(boolean hasWater) {
        this.hasWater = hasWater;
    }

    public boolean hasWater() {
        return this.hasWater;
    }
    public void setHasWater(boolean hasWater) {
        this.hasWater = hasWater;
    }

    //Increments the TileCompartment's count of the specified resource by 1
    public void incrementResource(ResourceType resource){
        this.resourceManager.addResource(resource,1);
    }

    //Decrement the TileCompartment's count of the specified resource by 1
    public void decrementResource(ResourceType resource){
        this.resourceManager.removeResource(resource,1);
    }

    public int getResourceCount(ResourceType rt){
        return resourceManager.getResourceCount(rt);
    }
}
