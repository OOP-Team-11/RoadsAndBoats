package game.model.structures.secondaryProducer;

import game.model.resources.ResourceType;

import java.util.Map;

public class Papermill extends SecondaryProducer {

    public Papermill() {

    }

    // 1 Paper <= 2 Trunks
    // 1 Paper <= 2 Boards
    // 1 Paper <= 1 Trunks + 1 Boards
    @Override
    public Map<ResourceType, Integer> produce(Map<ResourceType, Integer> inputResources) {
        return null;
    }



}
