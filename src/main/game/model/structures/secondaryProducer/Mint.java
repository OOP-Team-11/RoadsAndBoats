package game.model.structures.secondaryProducer;

import game.model.resources.ResourceType;

import java.util.HashMap;
import java.util.Map;

public class Mint extends SecondaryProducer {

    private static final int FUEL_REQ = 1;
    private static final int GOLD_REQ = 2;

    public Mint() {

    }

    // 1 Coins <= 1 Fuel + 2 Gold
    @Override
    public Map<ResourceType, Integer> produce(Map<ResourceType, Integer> inputResources) {
        if (canProduce(inputResources)) {
            inputResources.put(ResourceType.FUEL, inputResources.get(ResourceType.FUEL) - FUEL_REQ);
            inputResources.put(ResourceType.GOLD, inputResources.get(ResourceType.GOLD) - GOLD_REQ);

            Map<ResourceType, Integer> outputResource = new HashMap<>();
            outputResource.put(ResourceType.COINS, 1);
            return outputResource;
        }

        return null;
    }

    private boolean canProduce(Map<ResourceType, Integer> inputResources) {
        if (inputResources.containsKey(ResourceType.FUEL) && inputResources.containsKey(ResourceType.GOLD)) {
            if ((inputResources.get(ResourceType.FUEL) >= FUEL_REQ) && (inputResources.get(ResourceType.GOLD) >= GOLD_REQ)) {
                return true;
            }
        }

        return false;
    }

}
