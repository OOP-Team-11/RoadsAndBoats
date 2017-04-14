package game.model.structures.secondaryProducer;

import game.model.resources.ResourceManager;
import game.model.resources.ResourceType;

public class CoalBurner extends SecondaryProducer {

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
            resourceManager.removeResource(ResourceType.TRUNKS, TRUNKS_REQ2);
            resourceManager.addResource(ResourceType.FUEL, FUEL_AMT);

            decrementProductionLimit();

            return true;
        }
        else if (canProduceCoalWithBoth(resourceManager)) {
            resourceManager.removeResource(ResourceType.TRUNKS, TRUNKS_REQ1);
            resourceManager.removeResource(ResourceType.BOARDS, BOARDS_REQ1);
            resourceManager.addResource(ResourceType.FUEL, FUEL_AMT);

            decrementProductionLimit();

            return true;
        }
        else if (canProduceCoalWithBoards(resourceManager)) {
            resourceManager.removeResource(ResourceType.BOARDS, BOARDS_REQ2);
            resourceManager.addResource(ResourceType.FUEL, FUEL_AMT);

            decrementProductionLimit();

            return true;
        }
        return false;
    }

    private boolean canProduceCoalWithTrunks(ResourceManager resourceManager) {
        if (productionLimit != 0) {
            if (resourceManager.getResource(ResourceType.TRUNKS) != null) {
                if (resourceManager.getResource(ResourceType.TRUNKS) >= TRUNKS_REQ2) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean canProduceCoalWithBoards(ResourceManager resourceManager) {
        if (productionLimit != 0) {
            if (resourceManager.getResource(ResourceType.BOARDS) != null) {
                if (resourceManager.getResource(ResourceType.BOARDS) >= BOARDS_REQ2) {
                    return true;
                }
            }
        }
        return false;
    }

    private boolean canProduceCoalWithBoth(ResourceManager resourceManager) {
        if (productionLimit != 0) {
            if ((resourceManager.getResource(ResourceType.TRUNKS) != null) && (resourceManager.getResource(ResourceType.BOARDS) != null)) {
                if ((resourceManager.getResource(ResourceType.TRUNKS) >= TRUNKS_REQ1) && (resourceManager.getResource(ResourceType.BOARDS) >= BOARDS_REQ1)) {
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
