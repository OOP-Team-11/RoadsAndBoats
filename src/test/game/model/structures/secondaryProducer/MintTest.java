package model.structures.secondaryProducer;

import game.model.resources.ResourceType;
import game.model.structures.secondaryProducer.Mint;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class MintTest {

    @Test
    public void produceOnce() {
        Mint mint = new Mint();
        Map<ResourceType, Integer> resourceInput = new HashMap<>();
        resourceInput.put(ResourceType.FUEL, 1);
        resourceInput.put(ResourceType.GOLD, 2);
        Integer resourceNum = mint.produce(resourceInput).get(ResourceType.COINS);
        assertEquals(resourceNum, new Integer(1));
    }

    @Test
    public void produceNone() {
        Mint mint = new Mint();
        Map<ResourceType, Integer> resourceInput = new HashMap<>();
        resourceInput.put(ResourceType.FUEL, 1);
        resourceInput.put(ResourceType.GOLD, 1);
        assertNull(mint.produce(resourceInput));
    }

    @Test
    public void produceMany() {
        Mint mint = new Mint();
        Map<ResourceType, Integer> resourceInput = new HashMap<>();
        Map<ResourceType, Integer> resourceOutput = new HashMap<>();
        Map<ResourceType, Integer> mintOutput;

        resourceInput.put(ResourceType.FUEL, 3);
        resourceInput.put(ResourceType.GOLD, 6);

        for (int i = 0; i < 3; ++i) {
            mintOutput = mint.produce(resourceInput);

            if (resourceOutput.get(ResourceType.COINS) == null) {
                resourceOutput.put(ResourceType.COINS, mintOutput.get(ResourceType.COINS));
            } else {
                resourceOutput.put(ResourceType.COINS, mintOutput.get(ResourceType.COINS) + resourceOutput.get(ResourceType.COINS));
            }
        }

        assertEquals(resourceOutput.get(ResourceType.COINS), new Integer(3));
    }

    @Test
    public void produceInvalid() {
        Mint mint = new Mint();
        Map<ResourceType, Integer> resourceInput = new HashMap<>();
        resourceInput.put(ResourceType.GOOSE, 5);
        assertNull(mint.produce(resourceInput));
    }

}
