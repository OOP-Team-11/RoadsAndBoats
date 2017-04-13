package game.model.structures.secondaryProducer;

import game.model.resources.ResourceType;

import java.util.Map;

public class StoneFactory extends SecondaryProducer {

    private static final int LIMIT = 6;

    private int productionLimit;

    public StoneFactory() {
        productionLimit = LIMIT;
    }

    // 2 Stone <= 1 Clay
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
