package game.model.structures.secondaryProducer;

import game.model.resources.ResourceType;

import java.util.Map;

public class Sawmill extends SecondaryProducer {

    private int productionLimit = 6;

    public Sawmill() {

    }

    // 2 Boards <= 1 Trunk
    @Override
    public Map<ResourceType, Integer> produce(Map<ResourceType, Integer> inputResources) {
        return null;
    }

}
