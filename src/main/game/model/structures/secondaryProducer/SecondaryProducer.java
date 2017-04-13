package game.model.structures.secondaryProducer;

import game.model.resources.Resource;
import game.model.structures.Structure;

import java.util.Map;

public abstract class SecondaryProducer extends Structure {

    SecondaryProducer() {

    }

    public abstract Map<Resource, Integer> produce(Resource resource);

}
