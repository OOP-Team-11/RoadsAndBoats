package game.model.structures.primaryProducer;

import game.model.resources.ResourceType;

import java.util.HashMap;
import java.util.Map;

public class ClayPit extends ResourceDropper {

    public ClayPit() {

    }

    @Override
    public Map<ResourceType, Integer> produce() {
        Map<ResourceType, Integer> resource = new HashMap<>();
        resource.put(ResourceType.CLAY, 1);
        return resource;
    }

}
