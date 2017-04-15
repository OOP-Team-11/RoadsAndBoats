package game.model.structures.resourceProducer;

import game.model.resources.ResourceManager;
import game.model.structures.Structure;

public abstract class ResourceProducer extends Structure {

    protected ResourceProducer() {

    }

    public abstract boolean produce(ResourceManager resourceManager);

}
