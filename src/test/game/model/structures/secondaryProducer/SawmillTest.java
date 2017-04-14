package model.structures.secondaryProducer;

import game.model.resources.ResourceType;
import game.model.structures.secondaryProducer.Sawmill;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class SawmillTest {

    @Test
    public void produceOnce() {
        Sawmill sawmill = new Sawmill();
        Map<ResourceType, Integer> resourceInput = new HashMap<>();
        resourceInput.put(ResourceType.TRUNKS, 1);
        Integer resourceNum = sawmill.produce(resourceInput).get(ResourceType.BOARDS);
        assertEquals(resourceNum, new Integer(2));
    }

    @Test
    public void produceNone() {
        Sawmill sawmill = new Sawmill();
        Map<ResourceType, Integer> resourceInput = new HashMap<>();
        resourceInput.put(ResourceType.TRUNKS, 0);
        assertNull(sawmill.produce(resourceInput));
    }

    @Test
    public void produceMany() {
        Sawmill sawmill = new Sawmill();
        Map<ResourceType, Integer> resourceInput = new HashMap<>();
        Map<ResourceType, Integer> resourceOutput = new HashMap<>();
        Map<ResourceType, Integer> sawmillOutput;

        resourceInput.put(ResourceType.TRUNKS, 3);

        for (int i = 0; i < 3; ++i) {
            sawmillOutput = sawmill.produce(resourceInput);

            if (resourceOutput.get(ResourceType.BOARDS) == null) {
                resourceOutput.put(ResourceType.BOARDS, sawmillOutput.get(ResourceType.BOARDS));
            } else {
                resourceOutput.put(ResourceType.BOARDS, sawmillOutput.get(ResourceType.BOARDS) + resourceOutput.get(ResourceType.BOARDS));
            }
        }

        assertEquals(resourceInput.get(ResourceType.TRUNKS), new Integer(0));
        assertEquals(resourceOutput.get(ResourceType.BOARDS), new Integer(6));
    }

    @Test
    public void produceToLimit() {
        Sawmill sawmill = new Sawmill();
        Map<ResourceType, Integer> resourceInput = new HashMap<>();
        Map<ResourceType, Integer> resourceOutput = new HashMap<>();
        Map<ResourceType, Integer> sawmillOutput;

        resourceInput.put(ResourceType.TRUNKS, 10);

        while (sawmill.getProductionLimit() != 0) {
            sawmillOutput = sawmill.produce(resourceInput);

            if (resourceOutput.get(ResourceType.BOARDS) == null) {
                resourceOutput.put(ResourceType.BOARDS, sawmillOutput.get(ResourceType.BOARDS));
            } else {
                resourceOutput.put(ResourceType.BOARDS, sawmillOutput.get(ResourceType.BOARDS) + resourceOutput.get(ResourceType.BOARDS));
            }
        }

        assertEquals(resourceInput.get(ResourceType.TRUNKS), new Integer(4));
        assertEquals(resourceOutput.get(ResourceType.BOARDS), new Integer(12));
        assertEquals(sawmill.getProductionLimit(), 0);
    }

    @Test
    public void resetProductionLimit() {

        Sawmill sawmill = new Sawmill();
        Map<ResourceType, Integer> resourceInput = new HashMap<>();
        Map<ResourceType, Integer> resourceOutput = new HashMap<>();
        Map<ResourceType, Integer> sawmillOutput;

        resourceInput.put(ResourceType.TRUNKS, 12);

        while (sawmill.getProductionLimit() != 0) {
            sawmillOutput = sawmill.produce(resourceInput);

            if (resourceOutput.get(ResourceType.BOARDS) == null) {
                resourceOutput.put(ResourceType.BOARDS, sawmillOutput.get(ResourceType.BOARDS));
            } else {
                resourceOutput.put(ResourceType.BOARDS, sawmillOutput.get(ResourceType.BOARDS) + resourceOutput.get(ResourceType.BOARDS));
            }
        }

        sawmill.resetProductionLimit();

        while (sawmill.getProductionLimit() != 0) {
            sawmillOutput = sawmill.produce(resourceInput);

            if (resourceOutput.get(ResourceType.BOARDS) == null) {
                resourceOutput.put(ResourceType.BOARDS, sawmillOutput.get(ResourceType.BOARDS));
            } else {
                resourceOutput.put(ResourceType.BOARDS, sawmillOutput.get(ResourceType.BOARDS) + resourceOutput.get(ResourceType.BOARDS));
            }
        }

        assertEquals(resourceInput.get(ResourceType.TRUNKS), new Integer(0));
        assertEquals(resourceOutput.get(ResourceType.BOARDS), new Integer(24));
        assertEquals(sawmill.getProductionLimit(), 0);

    }

    @Test
    public void produceInvalid() {
        Sawmill sawmill = new Sawmill();
        Map<ResourceType, Integer> resourceInput = new HashMap<>();
        resourceInput.put(ResourceType.GOOSE, 5);
        assertNull(sawmill.produce(resourceInput));
    }
}
