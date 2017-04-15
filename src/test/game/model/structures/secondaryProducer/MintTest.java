package model.structures.secondaryProducer;

import game.model.resources.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.secondaryProducer.Mint;
import org.junit.Test;

import static org.junit.Assert.*;

public class MintTest {

    @Test
    public void produceOnce() {
        Mint mint = new Mint();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.FUEL, 1);
        rm.addResource(ResourceType.GOLD, 2);
        assertTrue(mint.produce(rm));
        assertEquals(rm.getResourceCount(ResourceType.COINS), 1);
    }

    @Test
    public void produceNone() {
        Mint mint = new Mint();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.FUEL, 0);
        rm.addResource(ResourceType.GOLD, 0);
        assertFalse(mint.produce(rm));
        assertEquals(rm.getResourceCount(ResourceType.COINS), 0);
    }

    @Test
    public void produceMany() {
        Mint mint = new Mint();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.FUEL, 3);
        rm.addResource(ResourceType.GOLD, 6);

        for (int i = 0; i < 3; ++i) {
            mint.produce(rm);
        }

        assertEquals(rm.getResourceCount(ResourceType.FUEL), 0);
        assertEquals(rm.getResourceCount(ResourceType.GOLD), 0);
        assertEquals(rm.getResourceCount(ResourceType.COINS), 3);
    }

    @Test
    public void produceInvalid() {
        Mint mint = new Mint();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.GOOSE, 5);
        assertFalse(mint.produce(rm));
        assertEquals(rm.getResourceCount(ResourceType.COINS), 0);
    }

}
