package game.model.structures.secondaryProducer;

import game.model.resources.ResourceType;

import java.util.HashMap;
import java.util.Map;

public class StockMarket extends SecondaryProducer {

    private static final int LIMIT = 6;
    private static final int PAPER_REQ = 1;
    private static final int COINS_REQ = 2;

    private int productionLimit;

    public StockMarket() {
        productionLimit = LIMIT;
    }

    // 1 Stock <= 1 Paper + 2 Coins
    @Override
    public Map<ResourceType, Integer> produce(Map<ResourceType, Integer> inputResources) {
        if (canProduce(inputResources)) {
            inputResources.put(ResourceType.PAPER, inputResources.get(ResourceType.PAPER) - PAPER_REQ);
            inputResources.put(ResourceType.COINS, inputResources.get(ResourceType.COINS) - COINS_REQ);

            Map<ResourceType, Integer> outputResource = new HashMap<>();
            outputResource.put(ResourceType.STOCKBOND, 1);
            --productionLimit;

            return outputResource;
        }

        return null;
    }

    public void resetProductionLimit() {
        productionLimit = LIMIT;
    }

    public int getProductionLimit() {
        return  productionLimit;
    }

    private boolean canProduce(Map<ResourceType, Integer> inputResources) {
        if (productionLimit != 0) {
            if (inputResources.containsKey(ResourceType.PAPER) && inputResources.containsKey(ResourceType.COINS)) {
                if ((inputResources.get(ResourceType.PAPER) >= PAPER_REQ) && (inputResources.get(ResourceType.COINS) >= COINS_REQ)) {
                    return true;
                }
            }
        }

        return false;
    }

}
