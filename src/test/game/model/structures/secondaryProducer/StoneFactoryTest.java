package model.structures.secondaryProducer;

import game.model.resources.ResourceType;
import game.model.structures.secondaryProducer.StoneFactory;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class StoneFactoryTest {

    @Test
    public void produceOnce() {
        StoneFactory stoneFactory = new StoneFactory();
        Map<ResourceType, Integer> resourceInput = new HashMap<>();
        resourceInput.put(ResourceType.CLAY, 1);
        Integer resourceNum = stoneFactory.produce(resourceInput).get(ResourceType.STONE);
        assertEquals(resourceNum, new Integer(2));
    }

    @Test
    public void produceNone() {
        StoneFactory sawmill = new StoneFactory();
        Map<ResourceType, Integer> resourceInput = new HashMap<>();
        resourceInput.put(ResourceType.CLAY, 0);
        assertNull(sawmill.produce(resourceInput));
    }

    @Test
    public void produceMany() {
        StoneFactory sawmill = new StoneFactory();
        Map<ResourceType, Integer> resourceInput = new HashMap<>();
        Map<ResourceType, Integer> resourceOutput = new HashMap<>();
        Map<ResourceType, Integer> sawmillOutput;

        resourceInput.put(ResourceType.CLAY, 3);

        for (int i = 0; i < 3; ++i) {
            sawmillOutput = sawmill.produce(resourceInput);

            if (resourceOutput.get(ResourceType.STONE) == null) {
                resourceOutput.put(ResourceType.STONE, sawmillOutput.get(ResourceType.STONE));
            } else {
                resourceOutput.put(ResourceType.STONE, sawmillOutput.get(ResourceType.STONE) + resourceOutput.get(ResourceType.STONE));
            }
        }

        assertEquals(resourceInput.get(ResourceType.CLAY), new Integer(0));
        assertEquals(resourceOutput.get(ResourceType.STONE), new Integer(6));
    }

    @Test
    public void produceToLimit() {
        StoneFactory sawmill = new StoneFactory();
        Map<ResourceType, Integer> resourceInput = new HashMap<>();
        Map<ResourceType, Integer> resourceOutput = new HashMap<>();
        Map<ResourceType, Integer> sawmillOutput;

        resourceInput.put(ResourceType.CLAY, 10);

        while (sawmill.getProductionLimit() != 0) {
            sawmillOutput = sawmill.produce(resourceInput);

            if (resourceOutput.get(ResourceType.STONE) == null) {
                resourceOutput.put(ResourceType.STONE, sawmillOutput.get(ResourceType.STONE));
            } else {
                resourceOutput.put(ResourceType.STONE, sawmillOutput.get(ResourceType.STONE) + resourceOutput.get(ResourceType.STONE));
            }
        }

        assertEquals(resourceInput.get(ResourceType.CLAY), new Integer(4));
        assertEquals(resourceOutput.get(ResourceType.STONE), new Integer(12));
        assertEquals(sawmill.getProductionLimit(), 0);
    }

    @Test
    public void resetProductionLimit() {

        StoneFactory sawmill = new StoneFactory();
        Map<ResourceType, Integer> resourceInput = new HashMap<>();
        Map<ResourceType, Integer> resourceOutput = new HashMap<>();
        Map<ResourceType, Integer> sawmillOutput;

        resourceInput.put(ResourceType.CLAY, 12);

        while (sawmill.getProductionLimit() != 0) {
            sawmillOutput = sawmill.produce(resourceInput);

            if (resourceOutput.get(ResourceType.STONE) == null) {
                resourceOutput.put(ResourceType.STONE, sawmillOutput.get(ResourceType.STONE));
            } else {
                resourceOutput.put(ResourceType.STONE, sawmillOutput.get(ResourceType.STONE) + resourceOutput.get(ResourceType.STONE));
            }
        }

        sawmill.resetProductionLimit();

        while (sawmill.getProductionLimit() != 0) {
            sawmillOutput = sawmill.produce(resourceInput);

            if (resourceOutput.get(ResourceType.STONE) == null) {
                resourceOutput.put(ResourceType.STONE, sawmillOutput.get(ResourceType.STONE));
            } else {
                resourceOutput.put(ResourceType.STONE, sawmillOutput.get(ResourceType.STONE) + resourceOutput.get(ResourceType.STONE));
            }
        }

        assertEquals(resourceInput.get(ResourceType.CLAY), new Integer(0));
        assertEquals(resourceOutput.get(ResourceType.STONE), new Integer(24));
        assertEquals(sawmill.getProductionLimit(), 0);

    }

    @Test
    public void produceInvalid() {
        StoneFactory sawmill = new StoneFactory();
        Map<ResourceType, Integer> resourceInput = new HashMap<>();
        resourceInput.put(ResourceType.GOOSE, 5);
        assertNull(sawmill.produce(resourceInput));
    }
}
