package game.model.structures.primaryProducer;

import game.model.resources.*;
import game.model.structures.primaryProducer.OilRig;
import org.apache.logging.log4j.core.pattern.FullLocationPatternConverter;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class OilRigTest {

    @Test
    public void produce() {
        ResourceManager rm = new ResourceManager();
        OilRig oilRig = new OilRig(rm);
        oilRig.produce();
        Integer resourceNum = rm.getResourceCount(ResourceType.FUEL);
        assertEquals(resourceNum, new Integer(1));
    }

    @Test
    public void storeResource() {
        ResourceManager rm = new ResourceManager();
        OilRig oilRig = new OilRig(rm);
        oilRig.storeResource(ResourceType.GOOSE, 5);
        Integer resourceNum = rm.getResourceCount(ResourceType.GOOSE);
        assertEquals(resourceNum, new Integer(5));
    }

    @Test
    public void removeResource() {
        ResourceManager rm = new ResourceManager();
        OilRig oilRig = new OilRig(rm);
        oilRig.storeResource(ResourceType.GOOSE, 5);
        oilRig.takeResource(ResourceType.GOOSE, 3);
        Integer resourceNum = rm.getResourceCount(ResourceType.GOOSE);
        assertEquals(resourceNum, new Integer(2));
    }

    @Test
    public void storeProduceResource() {
        ResourceManager rm = new ResourceManager();
        OilRig oilRig = new OilRig(rm);
        oilRig.produce();
        oilRig.storeResource(ResourceType.GOOSE, 5);
        assertEquals(rm.getResourceCount(ResourceType.FUEL), 1);
        assertEquals(rm.getResourceCount(ResourceType.GOOSE), 5);
    }

    @Test
    public void takeProduceResource() {
        ResourceManager rm = new ResourceManager();
        OilRig oilRig = new OilRig(rm);
        oilRig.produce();
        oilRig.takeResource(ResourceType.FUEL, 1);
        assertEquals(rm.getResourceCount(ResourceType.FUEL), 0);
    }

    @Test
    public void storeAllResources() {
        ResourceManager rm = new ResourceManager();
        OilRig oilRig = new OilRig(rm);
        oilRig.storeResource(ResourceType.BOARDS, 1);
        oilRig.storeResource(ResourceType.CLAY, 2);
        oilRig.storeResource(ResourceType.COINS, 3);
        oilRig.storeResource(ResourceType.FUEL, 4);
        oilRig.storeResource(ResourceType.GOLD, 5);
        oilRig.storeResource(ResourceType.GOOSE, 6);
        oilRig.storeResource(ResourceType.IRON, 7);
        oilRig.storeResource(ResourceType.PAPER, 8);
        oilRig.storeResource(ResourceType.STOCKBOND, 9);
        oilRig.storeResource(ResourceType.STONE, 10);
        oilRig.storeResource(ResourceType.TRUNKS, 11);
        assertEquals(rm.getResourceCount(ResourceType.BOARDS), 1);
        assertEquals(rm.getResourceCount(ResourceType.CLAY), 2);
        assertEquals(rm.getResourceCount(ResourceType.COINS), 3);
        assertEquals(rm.getResourceCount(ResourceType.FUEL), 4);
        assertEquals(rm.getResourceCount(ResourceType.GOLD), 5);
        assertEquals(rm.getResourceCount(ResourceType.GOOSE), 6);
        assertEquals(rm.getResourceCount(ResourceType.IRON), 7);
        assertEquals(rm.getResourceCount(ResourceType.PAPER), 8);
        assertEquals(rm.getResourceCount(ResourceType.STOCKBOND), 9);
        assertEquals(rm.getResourceCount(ResourceType.STONE), 10);
        assertEquals(rm.getResourceCount(ResourceType.TRUNKS),11);
    }

}
