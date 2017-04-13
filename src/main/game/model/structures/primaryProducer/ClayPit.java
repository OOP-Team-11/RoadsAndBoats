package game.model.structures.primaryProducer;

import game.model.resources.Clay;
import game.model.resources.Resource;

import java.util.HashMap;
import java.util.Map;

public class ClayPit extends ResourceDropper {

    public ClayPit() {

    }

    @Override
    public Map<Resource, Integer> produce() {
        Map<Resource, Integer> resource = new HashMap<>();
        resource.put(new Clay(), 1);
        return resource;
    }

}
