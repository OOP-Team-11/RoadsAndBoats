package game.model.structures.resourceProducer.secondaryProducer;

import game.model.resources.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.ResourceDropper;

public class CoalBurner extends ResourceDropper {

    private static final int LIMIT = 6;         // limit of uses per turn

    private static final int TRUNKS_REQ2 = 2;   // 2 Trunks input requirement
    private static final int BOARDS_REQ2 = 2;   // 2 Boards input requirement
    private static final int TRUNKS_REQ1 = 1;   // 1 Trunks + 1 Boards input requirement
    private static final int BOARDS_REQ1 = 1;

    private static final int FUEL_AMT = 1;      // 1 Fuel output amount

    private int productionLimit;

    public CoalBurner() {
        this.productionLimit = LIMIT;
    }

    // 1 Fuel <= 2 Trunks
    // 1 Fuel <= 2 Boards
    // 1 Fuel <= 1 Trunks + 1 Boards
    @Override
    public boolean produce(ResourceManager resourceManager) {
        if (canProduceCoalWithTrunks(resourceManager)) {
            resourceManager.addResource(ResourceType.FUEL, FUEL_AMT);
            decrementProductionLimit();
            return true;
        }
        else if (canProduceCoalWithBoth(resourceManager)) {
            resourceManager.addResource(ResourceType.FUEL, FUEL_AMT);
            decrementProductionLimit();
            return true;
        }
        else if (canProduceCoalWithBoards(resourceManager)) {
            resourceManager.addResource(ResourceType.FUEL, FUEL_AMT);
            decrementProductionLimit();
            return true;
        }
        return false;
    }

    private boolean canProduceCoalWithTrunks(ResourceManager resourceManager) {
        return ((productionLimit != 0)
                && resourceManager.removeResource(ResourceType.TRUNKS, TRUNKS_REQ2));
    }

    private boolean canProduceCoalWithBoards(ResourceManager resourceManager) {
        return ((productionLimit != 0)
                && resourceManager.removeResource(ResourceType.BOARDS, BOARDS_REQ2));
    }

    private boolean canProduceCoalWithBoth(ResourceManager resourceManager) {
        return ((productionLimit != 0)
                && resourceManager.removeResource(ResourceType.TRUNKS, TRUNKS_REQ1)
                && resourceManager.removeResource(ResourceType.BOARDS, BOARDS_REQ1));
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
