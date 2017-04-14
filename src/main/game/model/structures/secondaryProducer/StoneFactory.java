package game.model.structures.secondaryProducer;

import game.model.resources.ResourceManager;
import game.model.resources.ResourceType;

public class StoneFactory extends SecondaryProducer {

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
            resourceManager.removeResource(ResourceType.CLAY, CLAY_REQ);
            resourceManager.addResource(ResourceType.STONE, STONE_AMT);

            decrementProductionLimit();

            return true;
        }
        return false;
    }

    private boolean canProduceStone(ResourceManager resourceManager) {
        if (productionLimit != 0) {
            if (resourceManager.getResource(ResourceType.CLAY) != null) {
                if (resourceManager.getResource(ResourceType.CLAY) >= CLAY_REQ) {
                    return true;
                }
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
        return  productionLimit;
    }

}
