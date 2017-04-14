package game.model.structures.secondaryProducer;

import game.model.resources.ResourceType;
import game.model.structures.Structure;

import java.util.Map;

public abstract class SecondaryProducer extends Structure {

    SecondaryProducer() {

    }

    public abstract Map<ResourceType, Integer> produce(Map<ResourceType, Integer> inputResources);

}
