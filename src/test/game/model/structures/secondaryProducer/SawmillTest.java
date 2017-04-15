package model.structures.secondaryProducer;

import game.model.resources.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.secondaryProducer.Sawmill;
import org.junit.Test;

import static org.junit.Assert.*;

public class SawmillTest {

    @Test
    public void produceOnce() {
        Sawmill sawmill = new Sawmill();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.TRUNKS, 1);
        assertTrue(sawmill.produce(rm));
        assertEquals(rm.getResourceCount(ResourceType.BOARDS), 2);
    }

    @Test
    public void produceNone() {
        Sawmill sawmill = new Sawmill();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.TRUNKS, 0);
        assertFalse(sawmill.produce(rm));
        assertEquals(rm.getResourceCount(ResourceType.BOARDS), 0);
    }

    @Test
    public void produceMany() {
        Sawmill sawmill = new Sawmill();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.TRUNKS, 3);

        for (int i = 0; i < 3; ++i) {
            sawmill.produce(rm);
        }

        assertEquals(rm.getResourceCount(ResourceType.TRUNKS), 0);
        assertEquals(rm.getResourceCount(ResourceType.BOARDS), 6);
    }

    @Test
    public void produceToLimit() {
        Sawmill sawmill = new Sawmill();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.TRUNKS, 10);

        while (sawmill.getProductionLimit() != 0) {
            sawmill.produce(rm);
        }

        assertEquals(rm.getResourceCount(ResourceType.TRUNKS), 4);
        assertEquals(rm.getResourceCount(ResourceType.BOARDS), 12);
        assertEquals(sawmill.getProductionLimit(), 0);
    }

    @Test
    public void resetProductionLimit() {
        Sawmill sawmill = new Sawmill();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.TRUNKS, 12);

        while (sawmill.getProductionLimit() != 0) {
            sawmill.produce(rm);
        }

        sawmill.resetProductionLimit();

        while (sawmill.getProductionLimit() != 0) {
            sawmill.produce(rm);
        }

        assertEquals(rm.getResourceCount(ResourceType.TRUNKS), 0);
        assertEquals(rm.getResourceCount(ResourceType.BOARDS), 24);
        assertEquals(sawmill.getProductionLimit(), 0);
    }

    @Test
    public void produceInvalid() {
        Sawmill sawmill = new Sawmill();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.GOOSE, 5);
        assertFalse(sawmill.produce(rm));
        assertEquals(rm.getResourceCount(ResourceType.BOARDS), 0);
    }
}
