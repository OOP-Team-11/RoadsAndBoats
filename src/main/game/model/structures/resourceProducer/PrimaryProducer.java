package game.model.structures.resourceProducer;

import game.model.resources.ResourceType;
import game.model.structures.Structure;
import game.model.visitors.TileCompartmentVisitor;

public abstract class PrimaryProducer extends Structure {

    protected PrimaryProducer() {

    }

    public abstract boolean produce(TileCompartmentVisitor tcv);

    protected void acceptStoreResources(TileCompartmentVisitor visitor, ResourceType resourceType, Integer amountToAdd) {
        visitor.addResourcesVisit(resourceType, amountToAdd);
    }
}
