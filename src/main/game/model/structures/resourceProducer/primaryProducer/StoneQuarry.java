package game.model.structures.resourceProducer.primaryProducer;

import game.model.resources.ResourceManager;
import game.model.structures.resourceProducer.ResourceProducer;

public class StoneQuarry extends ResourceProducer {

    public StoneQuarry() {

    }

    @Override
    public boolean produce(ResourceManager resourceManager) {
        return false;
    }


//    @Override
//    public Map<ResourceType, Integer> produce() {
//        Map<ResourceType, Integer> resource = new HashMap<>();
//        resource.put(ResourceType.STONE, 1);
//        return resource;
//    }

}
