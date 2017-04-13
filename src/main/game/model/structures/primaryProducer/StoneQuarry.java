package game.model.structures.primaryProducer;

import game.model.resources.ResourceType;

import java.util.HashMap;
import java.util.Map;

public class StoneQuarry extends ResourceDropper {

    public StoneQuarry() {

    }

    @Override
    public Map<ResourceType, Integer> produce() {
        Map<ResourceType, Integer> resource = new HashMap<>();
        resource.put(ResourceType.STONE, 1);
        return resource;
    }

}
