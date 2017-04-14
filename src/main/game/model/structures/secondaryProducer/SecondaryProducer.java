package game.model.structures.secondaryProducer;

import game.model.resources.ResourceManager;
import game.model.structures.Structure;

public abstract class SecondaryProducer extends Structure {

    SecondaryProducer() {

    }

    public abstract boolean produce(ResourceManager resourceManager);

}
