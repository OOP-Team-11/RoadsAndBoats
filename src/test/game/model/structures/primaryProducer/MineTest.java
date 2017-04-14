package model.structures.primaryProducer;

import game.model.resources.ResourceType;
import game.model.structures.primaryProducer.Mine;
import game.model.structures.secondaryProducer.Mint;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class MineTest {

    @Test
    public void produceRandom() {
        Mine mine = new Mine(3, 3);
        Map<ResourceType, Integer> resources = new HashMap<>();
        Map<ResourceType, Integer> mineOutput;

        while ( (mine.getCurrentGoldCount() != 0) || (mine.getCurrentIronCount() != 0) ) {
            mineOutput = mine.produce();

            if (mineOutput.containsKey(ResourceType.GOLD)) {
                if (resources.get(ResourceType.GOLD) == null) {
                    resources.put(ResourceType.GOLD, mineOutput.get(ResourceType.GOLD));
                }
                else {
                    resources.put(ResourceType.GOLD, mineOutput.get(ResourceType.GOLD) + resources.get(ResourceType.GOLD));
                }
            }

            if (mineOutput.containsKey(ResourceType.IRON)) {
                if (resources.get(ResourceType.IRON) == null) {
                    resources.put(ResourceType.IRON, mineOutput.get(ResourceType.IRON));
                }
                else {
                    resources.put(ResourceType.IRON, mineOutput.get(ResourceType.IRON) + resources.get(ResourceType.IRON));
                }
            }
        }

        assertEquals(mine.getCurrentGoldCount(), 0);
        assertEquals(mine.getCurrentIronCount(), 0);
        assertEquals(resources.get(ResourceType.GOLD), new Integer(3));
        assertEquals(resources.get(ResourceType.IRON), new Integer(3));
    }

    @Test
    public void addShaft() {
        Mine mine = new Mine(5, 5);

        Map<ResourceType, Integer> resources = new HashMap<>();
        Map<ResourceType, Integer> mineOutput;

        while ( (mine.getCurrentGoldCount() != 0) || (mine.getCurrentIronCount() != 0) ) {
            mineOutput = mine.produce();

            if (mineOutput.containsKey(ResourceType.GOLD)) {
                if (resources.get(ResourceType.GOLD) == null) {
                    resources.put(ResourceType.GOLD, mineOutput.get(ResourceType.GOLD));
                }
                else {
                    resources.put(ResourceType.GOLD, mineOutput.get(ResourceType.GOLD) + resources.get(ResourceType.GOLD));
                }
            }

            if (mineOutput.containsKey(ResourceType.IRON)) {
                if (resources.get(ResourceType.IRON) == null) {
                    resources.put(ResourceType.IRON, mineOutput.get(ResourceType.IRON));
                }
                else {
                    resources.put(ResourceType.IRON, mineOutput.get(ResourceType.IRON) + resources.get(ResourceType.IRON));
                }
            }
        }

        mine.addShaft();

        assertEquals(mine.getCurrentGoldCount(), 5);
        assertEquals(mine.getCurrentIronCount(), 5);
        assertEquals(resources.get(ResourceType.GOLD), new Integer(5));
        assertEquals(resources.get(ResourceType.GOLD), new Integer(5));
    }

}