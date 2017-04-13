package game.model.structures.primaryProducer;

import game.model.resources.Resource;
import game.model.resources.Trunks;

import java.util.HashMap;
import java.util.Map;

public class Woodcutter extends ResourceDropper {

    public Woodcutter() {

    }

    @Override
    public Map<Resource, Integer> produce() {
        Map<Resource, Integer> resource = new HashMap<>();
        resource.put(new Trunks(), 1);
        return resource;
    }

}
