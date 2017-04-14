package game.model.structures.primaryProducer;

import game.model.resources.ResourceType;

import java.util.HashMap;
import java.util.Map;

public class Woodcutter extends ResourceDropper {

    public Woodcutter() {

    }

    @Override
    public Map<ResourceType, Integer> produce() {
        Map<ResourceType, Integer> resource = new HashMap<>();
        resource.put(ResourceType.TRUNKS, 1);
        return resource;
    }

}
