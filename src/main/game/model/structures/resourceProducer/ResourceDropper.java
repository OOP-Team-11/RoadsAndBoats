package game.model.structures.resourceProducer;

import game.model.resources.ResourceManager;
import game.model.structures.Structure;

public abstract class ResourceDropper extends Structure {

    protected ResourceDropper() {

    }

    public abstract boolean produce(ResourceManager resourceManager);

}
