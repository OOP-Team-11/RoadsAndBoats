package game.model.structures.primaryProducer;

import game.model.resources.Resource;
import game.model.resources.Stone;

import java.util.HashMap;
import java.util.Map;

public class StoneQuarry extends ResourceDropper {

    public StoneQuarry() {

    }

    @Override
    public Map<Resource, Integer> produce() {
        Map<Resource, Integer> resource = new HashMap<>();
        resource.put(new Stone(), 1);
        return resource;
    }

}
