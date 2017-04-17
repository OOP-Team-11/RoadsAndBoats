package game.model.structures.resourceProducer.secondaryProducer;

import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.StructureType;
import game.model.structures.resourceProducer.SecondaryProducer;
import game.model.tile.TileCompartment;
import game.model.transport.Transport;

public class StockMarket extends SecondaryProducer {

    private static final int LIMIT = 6;             // limit of uses per turn

    private static final int PAPER_REQ = 1;         // 1 Paper input requirement
    private static final int COINS_REQ = 2;         // 2 Coins input requirement

    private static final int STOCKBOND_AMT = 1;     // 1 Stockbond output amount

    private int productionLimit;

    public StockMarket() {
        this.productionLimit = LIMIT;
    }

    public StockMarket(int productionLimit) {
        this.productionLimit = productionLimit;
    }

    // 1 Stock <= 1 Paper + 2 Coins
    @Override
    public boolean produce(ResourceManager resourceManager) {
        if (canProduceStock(resourceManager)) {
            produceStock(resourceManager);
            return true;
        }
        return false;
    }

    @Override
    public boolean produce(TileCompartment tileCompartment) {
        if (canProduceStock(tileCompartment)) {
            produceStock(tileCompartment);
            return true;
        }
        return false;
    }

    @Override
    public boolean produce(Transport transport) {
        if (canProduceStock(transport)) {
            produceStock(transport);
            return true;
        }
        return false;
    }

    private void produceStock(TileCompartment tileCompartment) {
        tileCompartment.storeResource(ResourceType.STOCKBOND, STOCKBOND_AMT);
        decrementProductionLimit();
    }

    private void produceStock(Transport transport) {
        transport.storeResource(ResourceType.STOCKBOND, STOCKBOND_AMT);
        decrementProductionLimit();
    }

    private void produceStock(ResourceManager resourceManager) {
        resourceManager.removeResource(ResourceType.STOCKBOND, STOCKBOND_AMT);
        decrementProductionLimit();
    }


    private boolean canProduceStock(TileCompartment tileCompartment) {
        return (productionLimit != 0)
                && tileCompartment.takeResource(ResourceType.PAPER, PAPER_REQ)
                && tileCompartment.takeResource(ResourceType.COINS, COINS_REQ);
    }

    private boolean canProduceStock(Transport transport) {
        return (productionLimit != 0)
                && transport.takeResource(ResourceType.PAPER, PAPER_REQ)
                && transport.takeResource(ResourceType.COINS, COINS_REQ);
    }

    private boolean canProduceStock(ResourceManager resourceManager) {
        return (productionLimit != 0)
                && resourceManager.removeResource(ResourceType.PAPER, PAPER_REQ)
                && resourceManager.removeResource(ResourceType.COINS, COINS_REQ);
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
        //Has limit, so getExportString should return its limit
        return "LIMIT=" + this.getProductionLimit();
    }
}
