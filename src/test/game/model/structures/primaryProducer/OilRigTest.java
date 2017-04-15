package model.structures.primaryProducer;

import game.model.resources.*;
import game.model.structures.resourceProducer.primaryProducer.OilRig;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class OilRigTest {

    @Test
    public void produceOnce() {
        OilRig oilRig = new OilRig();
        assertTrue(oilRig.produce());
        assertEquals(oilRig.getResourceCount(ResourceType.FUEL), 1);
    }

    @Test
    public void produceMany() {
        OilRig oilRig = new OilRig();
        for (int i = 0; i < 3; ++i) {
            oilRig.produce();
        }
        assertEquals(oilRig.getResourceCount(ResourceType.FUEL), 3);
    }

    @Test
    public void storeResource() {
        OilRig oilRig = new OilRig();
        oilRig.storeResource(ResourceType.GOOSE, 5);
        assertEquals(oilRig.getResourceCount(ResourceType.GOOSE), 5);
    }

    @Test
    public void takeValidResource() {
        OilRig oilRig = new OilRig();
        oilRig.storeResource(ResourceType.GOOSE, 5);
        assertTrue(oilRig.takeResource(ResourceType.GOOSE, 3));
        assertEquals(oilRig.getResourceCount(ResourceType.GOOSE), 2);
    }

    @Test
    public void takeInvalidResource() {
        OilRig oilRig = new OilRig();
        assertFalse(oilRig.takeResource(ResourceType.GOOSE, 5));
        assertEquals(oilRig.getResourceCount(ResourceType.GOOSE), 0);
    }

    @Test
    public void takeTooManyResource() {
        OilRig oilRig = new OilRig();
        oilRig.storeResource(ResourceType.GOOSE, 5);
        assertFalse(oilRig.takeResource(ResourceType.GOOSE, 10));
        assertEquals(oilRig.getResourceCount(ResourceType.GOOSE), 5);
    }


    @Test
    public void storeAllResources() {
        OilRig oilRig = new OilRig();
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
        assertEquals(oilRig.getResourceCount(ResourceType.BOARDS), 1);
        assertEquals(oilRig.getResourceCount(ResourceType.CLAY), 2);
        assertEquals(oilRig.getResourceCount(ResourceType.COINS), 3);
        assertEquals(oilRig.getResourceCount(ResourceType.FUEL), 4);
        assertEquals(oilRig.getResourceCount(ResourceType.GOLD), 5);
        assertEquals(oilRig.getResourceCount(ResourceType.GOOSE), 6);
        assertEquals(oilRig.getResourceCount(ResourceType.IRON), 7);
        assertEquals(oilRig.getResourceCount(ResourceType.PAPER), 8);
        assertEquals(oilRig.getResourceCount(ResourceType.STOCKBOND), 9);
        assertEquals(oilRig.getResourceCount(ResourceType.STONE), 10);
        assertEquals(oilRig.getResourceCount(ResourceType.TRUNKS), 11);
    }

    @Test
    public void takeAllResource() {
        OilRig oilRig = new OilRig();
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
        assertTrue(oilRig.takeResource(ResourceType.BOARDS, 1));
        assertTrue(oilRig.takeResource(ResourceType.CLAY, 2));
        assertTrue(oilRig.takeResource(ResourceType.COINS, 3));
        assertTrue(oilRig.takeResource(ResourceType.FUEL, 4));
        assertTrue(oilRig.takeResource(ResourceType.GOLD, 5));
        assertTrue(oilRig.takeResource(ResourceType.GOOSE, 6));
        assertTrue(oilRig.takeResource(ResourceType.IRON, 7));
        assertTrue(oilRig.takeResource(ResourceType.PAPER, 8));
        assertTrue(oilRig.takeResource(ResourceType.STOCKBOND, 9));
        assertTrue(oilRig.takeResource(ResourceType.STONE, 10));
        assertTrue(oilRig.takeResource(ResourceType.TRUNKS, 11));
        assertEquals(oilRig.getResourceCount(ResourceType.BOARDS), 0);
        assertEquals(oilRig.getResourceCount(ResourceType.CLAY), 0);
        assertEquals(oilRig.getResourceCount(ResourceType.COINS), 0);
        assertEquals(oilRig.getResourceCount(ResourceType.FUEL), 0);
        assertEquals(oilRig.getResourceCount(ResourceType.GOLD), 0);
        assertEquals(oilRig.getResourceCount(ResourceType.GOOSE), 0);
        assertEquals(oilRig.getResourceCount(ResourceType.IRON), 0);
        assertEquals(oilRig.getResourceCount(ResourceType.PAPER), 0);
        assertEquals(oilRig.getResourceCount(ResourceType.STOCKBOND), 0);
        assertEquals(oilRig.getResourceCount(ResourceType.STONE), 0);
        assertEquals(oilRig.getResourceCount(ResourceType.TRUNKS), 0);
    }

    @Test
    public void getWealthPointsGold() {
        OilRig oilRig = new OilRig();
        oilRig.storeResource(ResourceType.GOLD, 1);
        assertEquals(oilRig.getWealthPoints(), 10);
    }

    @Test
    public void getWealthPointsCoins() {
        OilRig oilRig = new OilRig();
        oilRig.storeResource(ResourceType.COINS, 1);
        assertEquals(oilRig.getWealthPoints(), 40);
    }

    @Test
    public void getWealthPointsStockBond() {
        OilRig oilRig = new OilRig();
        oilRig.storeResource(ResourceType.STOCKBOND, 1);
        assertEquals(oilRig.getWealthPoints(), 120);
    }

    @Test
    public void getWealthPointsAll() {
        OilRig oilRig = new OilRig();
        oilRig.storeResource(ResourceType.GOLD, 1);
        oilRig.storeResource(ResourceType.COINS, 1);
        oilRig.storeResource(ResourceType.STOCKBOND, 1);
        assertEquals(oilRig.getWealthPoints(), 120+40+10);
    }

}
