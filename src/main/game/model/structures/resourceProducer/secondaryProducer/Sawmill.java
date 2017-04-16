package game.model.structures.resourceProducer.secondaryProducer;

import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.StructureType;
import game.model.structures.resourceProducer.ResourceDropper;

public class Sawmill extends ResourceDropper {

    private static final int LIMIT = 6;         // limit of uses per turn

    private static final int TRUNKS_REQ = 1;    // 1 Trunks input requirement

    private static final int BOARDS_AMT = 2;    // 2 Boards output amount

    private int productionLimit;

    public Sawmill() {
        this.productionLimit = LIMIT;
    }

    // 2 Boards <= 1 Trunk
    @Override
    public boolean produce(ResourceManager resourceManager) {
        if (canProduceBoards(resourceManager)) {
            resourceManager.addResource(ResourceType.BOARDS, BOARDS_AMT);
            decrementProductionLimit();
            return true;
        }
        return false;
    }

    private boolean canProduceBoards(ResourceManager resourceManager) {
        return resourceManager.removeResource(ResourceType.TRUNKS, TRUNKS_REQ);
    }

    private void decrementProductionLimit() {
        --productionLimit;
    }

    public void resetProductionLimit() {
        productionLimit = LIMIT;
    }

    public int getProductionLimit() {
        return productionLimit;
    }

    @Override
    public StructureType getType() {
        return StructureType.SAWMILL;
    }

    @Override
    public String getExportString() {
        // TODO add other things?
        return this.getType().toString();
    }
}
