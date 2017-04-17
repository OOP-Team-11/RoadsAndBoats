package game.model.visitors;

import game.model.resources.ResourceType;

public interface TileCompartmentVisitor {
    void addResourcesVisit(ResourceType resourceType, Integer amountToAdd);
}
