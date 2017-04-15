package game.model.structures.resourceProducer.secondaryProducer;

import game.model.resources.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.ResourceProducer;

public class StockMarket extends ResourceProducer {

    private static final int LIMIT = 6;             // limit of uses per turn

    private static final int PAPER_REQ = 1;         // 1 Paper input requirement
    private static final int COINS_REQ = 2;         // 2 Coins input requirement

    private static final int STOCKBOND_AMT = 1;     // 1 Stockbond output amount

    private int productionLimit;

    public StockMarket() {
        this.productionLimit = LIMIT;
    }

    // 1 Stock <= 1 Paper + 2 Coins
    @Override
    public boolean produce(ResourceManager resourceManager) {
        if (canProduceStock(resourceManager)) {
            resourceManager.removeResource(ResourceType.PAPER, PAPER_REQ);
            resourceManager.removeResource(ResourceType.COINS, COINS_REQ);
            resourceManager.addResource(ResourceType.STOCKBOND, STOCKBOND_AMT);

            decrementProductionLimit();

            return true;
        }
        return false;
    }

    private boolean canProduceStock(ResourceManager resourceManager) {
        if (productionLimit != 0) {
            if ((resourceManager.getResource(ResourceType.PAPER) != null) && (resourceManager.getResource(ResourceType.COINS) != null)) {
                if ((resourceManager.getResource(ResourceType.PAPER) >= PAPER_REQ) && ((resourceManager.getResource(ResourceType.COINS) >= COINS_REQ))) {
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
