package game.model.structures.secondaryProducer;

import game.model.resources.ResourceType;

import java.util.HashMap;
import java.util.Map;

public class Sawmill extends SecondaryProducer {

    private static final int LIMIT = 6;
    private static final int TRUNKS_REQ = 1;

    private int productionLimit;

    public Sawmill() {
        productionLimit = LIMIT;
    }

    // 2 Boards <= 1 Trunk
    @Override
    public Map<ResourceType, Integer> produce(Map<ResourceType, Integer> inputResources) {

        if (canProduce(inputResources)) {
            inputResources.put(ResourceType.TRUNKS, inputResources.get(ResourceType.TRUNKS) - TRUNKS_REQ);

            Map<ResourceType, Integer> outputResource = new HashMap<>();
            outputResource.put(ResourceType.BOARDS, 2);
            --productionLimit;

            return outputResource;
        }

        return null;
    }

    public void resetProductionLimit() {
        productionLimit = LIMIT;
    }

    public int getProductionLimit() {
        return productionLimit;
    }

    private boolean canProduce(Map<ResourceType, Integer> inputResources) {
        if (productionLimit != 0) {
            if (inputResources.containsKey(ResourceType.TRUNKS)) {
                if (inputResources.get(ResourceType.TRUNKS) >= TRUNKS_REQ) {
                    return true;
                }
            }
        }

        return false;
    }

}
