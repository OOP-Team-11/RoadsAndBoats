package game.model.structures.secondaryProducer;

import game.model.resources.ResourceManager;
import game.model.resources.ResourceType;

public class Mint extends SecondaryProducer {

    private static final int FUEL_REQ = 1;      // 1 Fuel input requirement
    private static final int GOLD_REQ = 2;      // 2 Gold input requirement

    private static final int COINS_AMT = 1;     // 1 Coins output amount

    public Mint() {

    }

    // 1 Coins <= 1 Fuel + 2 Gold
    @Override
    public boolean produce(ResourceManager resourceManager) {
        if (canProduceCoins(resourceManager)) {
            resourceManager.removeResource(ResourceType.FUEL, FUEL_REQ);
            resourceManager.removeResource(ResourceType.GOLD, GOLD_REQ);
            resourceManager.addResource(ResourceType.COINS, COINS_AMT);

            return true;
        }
        return false;
    }

    private boolean canProduceCoins(ResourceManager resourceManager) {
        if ((resourceManager.getResource(ResourceType.FUEL) != null) && (resourceManager.getResource(ResourceType.GOLD) != null)) {
            if ((resourceManager.getResource(ResourceType.GOLD) >= FUEL_REQ) && (resourceManager.getResource(ResourceType.GOLD) >= GOLD_REQ)) {
                return true;
            }
        }
        return false;
    }

}