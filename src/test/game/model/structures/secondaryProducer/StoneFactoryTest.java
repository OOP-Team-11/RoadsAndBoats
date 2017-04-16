package model.structures.secondaryProducer;

import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.secondaryProducer.StoneFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class StoneFactoryTest {

    @Test
    public void produceOnce() {
        StoneFactory stoneFactory = new StoneFactory();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.CLAY, 1);
        assertTrue(stoneFactory.produce(rm));
        assertEquals(rm.getResourceCount(ResourceType.STONE), 2);
    }

    @Test
    public void produceNone() {
        StoneFactory stoneFactory = new StoneFactory();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.CLAY, 0);
        assertFalse(stoneFactory.produce(rm));
        assertEquals(rm.getResourceCount(ResourceType.STONE), 0);
    }

    @Test
    public void produceMany() {
        StoneFactory stoneFactory = new StoneFactory();
        ResourceManager rm = new ResourceManager();

        rm.addResource(ResourceType.CLAY, 3);

        for (int i = 0; i < 3; ++i) {
            stoneFactory.produce(rm);
        }

        assertEquals(rm.getResourceCount(ResourceType.CLAY), 0);
        assertEquals(rm.getResourceCount(ResourceType.STONE), 6);
    }

    @Test
    public void produceToLimit() {
        StoneFactory stoneFactory = new StoneFactory();
        ResourceManager rm = new ResourceManager();

        rm.addResource(ResourceType.CLAY, 10);

        while (stoneFactory.getProductionLimit() != 0) {
            stoneFactory.produce(rm);
        }

        assertEquals(rm.getResourceCount(ResourceType.CLAY), 4);
        assertEquals(rm.getResourceCount(ResourceType.STONE), 12);
        assertEquals(stoneFactory.getProductionLimit(), 0);
    }

    @Test
    public void resetProductionLimit() {
        StoneFactory stoneFactory = new StoneFactory();
        ResourceManager rm = new ResourceManager();

        rm.addResource(ResourceType.CLAY, 12);

        while (stoneFactory.getProductionLimit() != 0) {
            stoneFactory.produce(rm);
        }

        stoneFactory.resetProductionLimit();

        while (stoneFactory.getProductionLimit() != 0) {
            stoneFactory.produce(rm);
        }

        assertEquals(rm.getResourceCount(ResourceType.CLAY), 0);
        assertEquals(rm.getResourceCount(ResourceType.STONE), 24);
        assertEquals(stoneFactory.getProductionLimit(), 0);

    }

    @Test
    public void produceInvalid() {
        StoneFactory stoneFactory = new StoneFactory();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.GOOSE, 5);
        assertFalse(stoneFactory.produce(rm));
        assertEquals(rm.getResourceCount(ResourceType.STONE), 0);
    }
}
