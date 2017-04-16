package game.model.structures.resourceProducer.primaryProducer;

import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.StructureType;
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

    @Override
    public StructureType getType() {
        return StructureType.STONE_QUARRY;
    }

    @Override
    public String getExportString() {
        // TODO add other things?
        return this.getType().toString();
    }

}
