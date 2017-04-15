package model.structures.primaryProducer;

import game.model.resources.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.primaryProducer.Mine;
import org.junit.Test;

import static org.junit.Assert.*;

public class MineTest {

    @Test
    public void produceAllRandom() {
        Mine mine = new Mine(3, 3);
        ResourceManager rm = new ResourceManager();

        while ( (mine.getCurrentGoldCount() != 0) || (mine.getCurrentIronCount() != 0) ) {
            mine.produce(rm);
        }

        assertEquals(mine.getCurrentGoldCount(), 0);
        assertEquals(mine.getCurrentIronCount(), 0);
        assertEquals(rm.getResourceCount(ResourceType.GOLD), 3);
        assertEquals(rm.getResourceCount(ResourceType.IRON), 3);
    }

    @Test
    public void addShaft() {
        Mine mine = new Mine(5, 5);
        ResourceManager rm = new ResourceManager();

        while ( (mine.getCurrentGoldCount() != 0) || (mine.getCurrentIronCount() != 0) ) {
            mine.produce(rm);
        }

        mine.addShaft();

        assertEquals(mine.getCurrentGoldCount(), 5);
        assertEquals(mine.getCurrentIronCount(), 5);
        assertEquals(rm.getResourceCount(ResourceType.GOLD), 5);
        assertEquals(rm.getResourceCount(ResourceType.IRON), 5);
    }

    @Test
    public void produceAddShaft() {
        Mine mine = new Mine(5, 5);
        ResourceManager rm = new ResourceManager();

        while ( (mine.getCurrentGoldCount() != 0) || (mine.getCurrentIronCount() != 0) ) {
            mine.produce(rm);
        }

        mine.addShaft();

        while ( (mine.getCurrentGoldCount() != 0) || (mine.getCurrentIronCount() != 0) ) {
            mine.produce(rm);
        }

        assertEquals(mine.getCurrentGoldCount(), 0);
        assertEquals(mine.getCurrentIronCount(), 0);
        assertEquals(rm.getResourceCount(ResourceType.GOLD), 10);
        assertEquals(rm.getResourceCount(ResourceType.IRON), 10);
    }

}