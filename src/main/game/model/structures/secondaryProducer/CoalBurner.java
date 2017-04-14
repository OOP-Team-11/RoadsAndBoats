package game.model.structures.secondaryProducer;

import game.model.resources.ResourceType;

import java.util.Map;

public class CoalBurner extends SecondaryProducer {

    private static final int LIMIT = 6;
    private static final int TRUNKS_REQ = 2;
    private static final int BOARDS_REQ = 2;

    private int productionLimit;

    public CoalBurner() {
        productionLimit = LIMIT;
    }

    // 1 Coal <= 2 Trunks
    // 1 Coal <= 2 Boards
    // 1 Coal <= 1 Trunks + 1 Boards
    @Override
    public Map<ResourceType, Integer> produce(Map<ResourceType, Integer> inputResources) {
//
//        if (inputResources.containsKey(ResourceType.TRUNKS)) {
//            if (inputResources.get(ResourceType.TRUNKS) > TRUNKS_REQ) {
//
//                System.out.println("2 Trunks");
//            }
//        }
//
//        if (inputResources.containsKey(ResourceType.TRUNKS) || inputResources.containsKey(ResourceType.TRUNKS)) {
//            if (inputResources.get(ResourceType.TRUNKS) > 2) && (inputResources.get(ResourceType.TRUNKS) > 2) {
//                System.out.println("2 Trunks");
//            }
//        }

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
