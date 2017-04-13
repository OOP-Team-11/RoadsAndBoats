package model.structures.primaryProducer;

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
        Integer resourceNum = rm.getResource(new Fuel());
        assertEquals(resourceNum, new Integer(1));
    }

    @Test
    public void storeResource() {
        ResourceManager rm = new ResourceManager();
        OilRig oilRig = new OilRig(rm);
        oilRig.storeResource(new Goose(), 5);
        Integer resourceNum = rm.getResource(new Goose());
        assertEquals(resourceNum, new Integer(5));
    }

    @Test
    public void removeResource() {
        ResourceManager rm = new ResourceManager();
        OilRig oilRig = new OilRig(rm);
        oilRig.storeResource(new Goose(), 5);
        oilRig.takeResource(new Goose(), 3);
        Integer resourceNum = rm.getResource(new Goose());
        assertEquals(resourceNum, new Integer(2));
    }

    @Test
    public void storeProduceResource() {
        ResourceManager rm = new ResourceManager();
        OilRig oilRig = new OilRig(rm);
        oilRig.produce();
        oilRig.storeResource(new Goose(), 5);
        assertEquals(rm.getResource(new Fuel()), new Integer(1));
        assertEquals(rm.getResource(new Goose()), new Integer(5));
    }

    @Test
    public void takeProduceResource() {
        ResourceManager rm = new ResourceManager();
        OilRig oilRig = new OilRig(rm);
        oilRig.produce();
        oilRig.takeResource(new Fuel(), 1);
        assertEquals(rm.getResource(new Fuel()), new Integer(0));
    }

    @Test
    public void storeAllResources() {
        ResourceManager rm = new ResourceManager();
        OilRig oilRig = new OilRig(rm);
        oilRig.storeResource(new Boards(), 1);
        oilRig.storeResource(new Clay(), 2);
        oilRig.storeResource(new Coins(), 3);
        oilRig.storeResource(new Fuel(), 4);
        oilRig.storeResource(new Gold(), 5);
        oilRig.storeResource(new Goose(), 6);
        oilRig.storeResource(new Iron(), 7);
        oilRig.storeResource(new Paper(), 8);
        oilRig.storeResource(new StockBond(), 9);
        oilRig.storeResource(new Stone(), 10);
        oilRig.storeResource(new Trunks(), 11);
        assertEquals(rm.getResource(new Boards()), new Integer(1));
        assertEquals(rm.getResource(new Clay()), new Integer(2));
        assertEquals(rm.getResource(new Coins()), new Integer(3));
        assertEquals(rm.getResource(new Fuel()), new Integer(4));
        assertEquals(rm.getResource(new Gold()), new Integer(5));
        assertEquals(rm.getResource(new Goose()), new Integer(6));
        assertEquals(rm.getResource(new Iron()), new Integer(7));
        assertEquals(rm.getResource(new Paper()), new Integer(8));
        assertEquals(rm.getResource(new StockBond()), new Integer(9));
        assertEquals(rm.getResource(new Stone()), new Integer(10));
        assertEquals(rm.getResource(new Trunks()), new Integer(11));
    }

}
