package game.model.structures.resourceProducer.primaryProducer;

import game.model.resources.ResourceManager;
import game.model.structures.resourceProducer.ResourceProducer;

public class Woodcutter extends ResourceProducer {

    public Woodcutter() {

    }

    @Override
    public boolean produce(ResourceManager resourceManager) {
        return false;
    }


//    @Override
//    public Map<ResourceType, Integer> produce() {
//        Map<ResourceType, Integer> resource = new HashMap<>();
//        resource.put(ResourceType.TRUNKS, 1);
//        return resource;
//    }

}
