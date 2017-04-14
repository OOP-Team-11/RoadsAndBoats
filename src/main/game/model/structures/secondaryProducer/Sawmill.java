package game.model.structures.secondaryProducer;

import game.model.resources.ResourceManager;
import game.model.resources.ResourceType;

public class Sawmill extends SecondaryProducer {

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
            resourceManager.removeResource(ResourceType.TRUNKS, TRUNKS_REQ);
            resourceManager.addResource(ResourceType.BOARDS, BOARDS_AMT);

            decrementProductionLimit();

            return true;
        }
        return false;
    }

    private boolean canProduceBoards(ResourceManager resourceManager) {
        if (resourceManager.getResource(ResourceType.TRUNKS) != null) {
            if (resourceManager.getResource(ResourceType.TRUNKS) >= TRUNKS_REQ) {
                return true;
            }
        }
        return false;
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

}
