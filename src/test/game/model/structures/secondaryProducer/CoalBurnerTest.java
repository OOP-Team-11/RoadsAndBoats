package model.structures.secondaryProducer;

import game.model.resources.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.secondaryProducer.CoalBurner;
import org.junit.Test;

public class CoalBurnerTest {

    @Test
    public void produce() {
        CoalBurner coalBurner = new CoalBurner();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.TRUNKS, 5);
        coalBurner.produce(rm.giveResource(ResourceType.TRUNKS, 2));
    }

}
