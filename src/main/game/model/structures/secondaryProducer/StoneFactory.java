package game.model.structures.secondaryProducer;

import game.model.resources.ResourceType;

import java.util.HashMap;
import java.util.Map;

public class StoneFactory extends SecondaryProducer {

    private static final int LIMIT = 6;
    private static final int CLAY_REQ = 1;

    private int productionLimit;

    public StoneFactory() {
        productionLimit = LIMIT;
    }

    // 2 Stone <= 1 Clay
    @Override
    public Map<ResourceType, Integer> produce(Map<ResourceType, Integer> inputResources) {
        if (canProduce(inputResources)) {
            inputResources.put(ResourceType.CLAY, inputResources.get(ResourceType.CLAY) - CLAY_REQ);

            Map<ResourceType, Integer> outputResource = new HashMap<>();
            outputResource.put(ResourceType.STONE, 2);
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
            if (inputResources.containsKey(ResourceType.CLAY)) {
                if (inputResources.get(ResourceType.CLAY) >= CLAY_REQ) {
                    return true;
                }
            }
        }

        return false;
    }

}
