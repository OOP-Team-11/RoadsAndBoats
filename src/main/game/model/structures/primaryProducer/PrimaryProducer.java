package game.model.structures.primaryProducer;

import game.model.resources.Resource;
import game.model.structures.Structure;

import java.util.Map;

public abstract class PrimaryProducer extends Structure {

    PrimaryProducer() {

    }

    public abstract Map<Resource, Integer> produce();

}
