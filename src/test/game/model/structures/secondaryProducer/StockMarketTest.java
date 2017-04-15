package model.structures.secondaryProducer;

import game.model.resources.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.secondaryProducer.StockMarket;
import org.junit.Test;

import static org.junit.Assert.*;

public class StockMarketTest {

    @Test
    public void produceOnce() {
        StockMarket stockMarket = new StockMarket();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.PAPER, 1);
        rm.addResource(ResourceType.COINS, 2);
        assertTrue(stockMarket.produce(rm));
        assertEquals(rm.getResource(ResourceType.STOCKBOND), new Integer(1));
    }

    @Test
    public void produceNone() {
        StockMarket stockMarket = new StockMarket();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.PAPER, 0);
        rm.addResource(ResourceType.COINS, 0);
        assertFalse(stockMarket.produce(rm));
        assertNull(rm.getResource(ResourceType.STOCKBOND));
    }

    @Test
    public void produceMany() {
        StockMarket stockMarket = new StockMarket();
        ResourceManager rm = new ResourceManager();

        rm.addResource(ResourceType.PAPER, 3);
        rm.addResource(ResourceType.COINS, 6);

        for (int i = 0; i < 3; ++i) {
            stockMarket.produce(rm);
        }

        assertEquals(rm.getResource(ResourceType.PAPER), new Integer(0));
        assertEquals(rm.getResource(ResourceType.COINS), new Integer(0));
        assertEquals(rm.getResource(ResourceType.STOCKBOND), new Integer(3));
    }

    @Test
    public void produceToLimit() {
        StockMarket stockMarket = new StockMarket();
        ResourceManager rm = new ResourceManager();

        rm.addResource(ResourceType.PAPER, 10);
        rm.addResource(ResourceType.COINS, 20);

        while (stockMarket.getProductionLimit() != 0) {
            stockMarket.produce(rm);
        }

        assertEquals(rm.getResource(ResourceType.PAPER), new Integer(4));
        assertEquals(rm.getResource(ResourceType.COINS), new Integer(8));
        assertEquals(rm.getResource(ResourceType.STOCKBOND), new Integer(6));
        assertEquals(stockMarket.getProductionLimit(), 0);
    }

    @Test
    public void resetProductionLimit() {
        StockMarket stockMarket = new StockMarket();
        ResourceManager rm = new ResourceManager();

        rm.addResource(ResourceType.PAPER, 12);
        rm.addResource(ResourceType.COINS, 24);

        while (stockMarket.getProductionLimit() != 0) {
            stockMarket.produce(rm);
        }

        stockMarket.resetProductionLimit();

        while (stockMarket.getProductionLimit() != 0) {
            stockMarket.produce(rm);
        }

        assertEquals(rm.getResource(ResourceType.PAPER), new Integer(0));
        assertEquals(rm.getResource(ResourceType.COINS), new Integer(0));
        assertEquals(rm.getResource(ResourceType.STOCKBOND), new Integer(12));
        assertEquals(stockMarket.getProductionLimit(), 0);
    }

    @Test
    public void produceInvalid() {
        StockMarket stockMarket = new StockMarket();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.GOOSE, 5);
        assertFalse(stockMarket.produce(rm));
        assertNull(rm.getResource(ResourceType.STOCKBOND));
    }
}
