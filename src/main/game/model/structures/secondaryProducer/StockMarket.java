package game.model.structures.secondaryProducer;

import game.model.resources.ResourceType;

import java.util.Map;

public class StockMarket extends SecondaryProducer {

    private static final int LIMIT = 6;

    private int productionLimit;

    public StockMarket() {
        productionLimit = LIMIT;
    }

    // 1 Stock <= 1 Paper + 2 Coins
    @Override
    public Map<ResourceType, Integer> produce(Map<ResourceType, Integer> inputResources) {
        --productionLimit;
        return null;
    }

    public void resetProductionLimit() {
        this.productionLimit = LIMIT;
    }

    public int getProductionLimit() {
        return  productionLimit;
    }

}
