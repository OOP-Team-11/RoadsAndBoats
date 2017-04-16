package game.model.structures.resourceProducer.secondaryProducer;

import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.StructureType;
import game.model.structures.resourceProducer.ResourceDropper;

public class StoneFactory extends ResourceDropper {

    private static final int LIMIT = 6;         // limit of uses per turn

    private static final int CLAY_REQ = 1;      // 1 Clay input requirement

    private static final int STONE_AMT = 2;     // 2 Stone output amount

    private int productionLimit;

    public StoneFactory() {
        this.productionLimit = LIMIT;
    }

    // 2 Stone <= 1 Clay
    @Override
    public boolean produce(ResourceManager resourceManager) {
        if (canProduceStone(resourceManager)) {
            resourceManager.addResource(ResourceType.STONE, STONE_AMT);
            decrementProductionLimit();
            return true;
        }
        return false;
    }

    private boolean canProduceStone(ResourceManager resourceManager) {
        return ((productionLimit != 0)
                && resourceManager.removeResource(ResourceType.CLAY, CLAY_REQ));
    }

    private void decrementProductionLimit() {
        --productionLimit;
    }

    public void resetProductionLimit() {
        productionLimit = LIMIT;
    }

    public int getProductionLimit() {
        return  productionLimit;
    }

    @Override
    public StructureType getType() {
        return StructureType.STONE_FACTORY;
    }

    @Override
    public String getExportString() {
        // TODO add other things?
        return this.getType().toString();
    }
}
