package game.model.structures.resourceProducer.primaryProducer;

import game.model.resources.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.ResourceDropper;

public class StoneQuarry extends ResourceDropper {

    private static final int STONE_AMT = 1;

    public StoneQuarry() {

    }

    @Override
    public boolean produce(ResourceManager resourceManager) {
        resourceManager.addResource(ResourceType.STONE, STONE_AMT);
        return true;
    }

}
