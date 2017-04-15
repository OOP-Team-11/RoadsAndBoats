package game.model.structures.resourceProducer.primaryProducer;

import game.model.resources.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.ResourceDropper;


public class ClayPit extends ResourceDropper {

    private static final int CLAY_AMT = 1;

    public ClayPit() {

    }

    @Override
    public boolean produce(ResourceManager resourceManager) {
        resourceManager.addResource(ResourceType.CLAY, CLAY_AMT);
        return true;
    }


}
