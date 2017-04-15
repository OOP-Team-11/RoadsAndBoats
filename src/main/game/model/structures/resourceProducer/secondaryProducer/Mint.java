package game.model.structures.resourceProducer.secondaryProducer;

import game.model.resources.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.ResourceProducer;

public class Mint extends ResourceProducer {

    private static final int FUEL_REQ = 1;      // 1 Fuel input requirement
    private static final int GOLD_REQ = 2;      // 2 Gold input requirement

    private static final int COINS_AMT = 1;     // 1 Coins output amount

    public Mint() {

    }

    // 1 Coins <= 1 Fuel + 2 Gold
    @Override
    public boolean produce(ResourceManager resourceManager) {
        if (canProduceCoins(resourceManager)) {
            resourceManager.addResource(ResourceType.COINS, COINS_AMT);
            return true;
        }
        return false;
    }

    private boolean canProduceCoins(ResourceManager resourceManager) {
        return (resourceManager.removeResource(ResourceType.FUEL, FUEL_REQ)
                && resourceManager.removeResource(ResourceType.GOLD, GOLD_REQ));
    }

}