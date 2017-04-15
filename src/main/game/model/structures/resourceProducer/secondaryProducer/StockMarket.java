package game.model.structures.resourceProducer.secondaryProducer;

import game.model.resources.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.StructureType;
import game.model.structures.resourceProducer.ResourceDropper;

public class StockMarket extends ResourceDropper {

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
            resourceManager.addResource(ResourceType.STOCKBOND, STOCKBOND_AMT);
            decrementProductionLimit();
            return true;
        }
        return false;
    }

    private boolean canProduceStock(ResourceManager resourceManager) {
        return ((productionLimit != 0)
                && resourceManager.removeResource(ResourceType.PAPER, PAPER_REQ)
                && resourceManager.removeResource(ResourceType.COINS, COINS_REQ));
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
        return StructureType.STOCK_MARKET;
    }

    @Override
    public String getExportString() {
        // TODO add other things?
        return this.getType().toString();
    }
}
