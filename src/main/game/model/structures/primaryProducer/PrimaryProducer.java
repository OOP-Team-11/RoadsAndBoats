package game.model.structures.primaryProducer;

import game.model.resources.ResourceType;
import game.model.structures.Structure;

import java.util.Map;

public abstract class PrimaryProducer extends Structure {

    PrimaryProducer() {

    }

    public abstract Map<ResourceType, Integer> produce();

}
