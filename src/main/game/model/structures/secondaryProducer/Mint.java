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
    public Map<ResourceType, Integer> produce(Map<ResourceType, Integer> inputResource) {
        if (inputResource.containsKey(ResourceType.FUEL) && inputResource.containsKey(ResourceType.GOLD)) {

            if ((inputResource.get(ResourceType.FUEL) >= FUEL_REQ) && (inputResource.get(ResourceType.GOLD)) >= GOLD_REQ) {
                Map<ResourceType, Integer> outputResource = new HashMap<>();
                outputResource.put(ResourceType.COINS, 1);
                return outputResource;
            }

        }

        return null;
    }

}
