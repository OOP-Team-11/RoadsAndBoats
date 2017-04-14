package model.structures.secondaryProducer;

import game.model.resources.ResourceType;
import game.model.structures.secondaryProducer.StockMarket;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class StockMarketTest {

    @Test
    public void produceOnce() {
        StockMarket stockMarket = new StockMarket();
        Map<ResourceType, Integer> resourceInput = new HashMap<>();
        resourceInput.put(ResourceType.PAPER, 1);
        resourceInput.put(ResourceType.COINS, 2);
        Integer resourceNum = stockMarket.produce(resourceInput).get(ResourceType.STOCKBOND);
        assertEquals(resourceNum, new Integer(1));
    }

    @Test
    public void produceNone() {
        StockMarket stockMarket = new StockMarket();
        Map<ResourceType, Integer> resourceInput = new HashMap<>();
        resourceInput.put(ResourceType.PAPER, 0);
        resourceInput.put(ResourceType.COINS, 0);
        assertNull(stockMarket.produce(resourceInput));
    }

    @Test
    public void produceMany() {
        StockMarket stockMarket = new StockMarket();
        Map<ResourceType, Integer> resourceInput = new HashMap<>();
        Map<ResourceType, Integer> resourceOutput = new HashMap<>();
        Map<ResourceType, Integer> stockMarketOutput;

        resourceInput.put(ResourceType.PAPER, 3);
        resourceInput.put(ResourceType.COINS, 6);

        for (int i = 0; i < 3; ++i) {
            stockMarketOutput = stockMarket.produce(resourceInput);

            if (resourceOutput.get(ResourceType.STOCKBOND) == null) {
                resourceOutput.put(ResourceType.STOCKBOND, stockMarketOutput.get(ResourceType.STOCKBOND));
            } else {
                resourceOutput.put(ResourceType.STOCKBOND, stockMarketOutput.get(ResourceType.STOCKBOND) + resourceOutput.get(ResourceType.STOCKBOND));
            }
        }

        assertEquals(resourceInput.get(ResourceType.PAPER), new Integer(0));
        assertEquals(resourceInput.get(ResourceType.COINS), new Integer(0));
        assertEquals(resourceOutput.get(ResourceType.STOCKBOND), new Integer(3));
    }

    @Test
    public void produceToLimit() {
        StockMarket stockMarket = new StockMarket();
        Map<ResourceType, Integer> resourceInput = new HashMap<>();
        Map<ResourceType, Integer> resourceOutput = new HashMap<>();
        Map<ResourceType, Integer> stockMarketOutput;

        resourceInput.put(ResourceType.PAPER, 10);
        resourceInput.put(ResourceType.COINS, 20);

        while (stockMarket.getProductionLimit() != 0) {
            stockMarketOutput = stockMarket.produce(resourceInput);

            if (resourceOutput.get(ResourceType.STOCKBOND) == null) {
                resourceOutput.put(ResourceType.STOCKBOND, stockMarketOutput.get(ResourceType.STOCKBOND));
            } else {
                resourceOutput.put(ResourceType.STOCKBOND, stockMarketOutput.get(ResourceType.STOCKBOND) + resourceOutput.get(ResourceType.STOCKBOND));
            }
        }

        assertEquals(resourceInput.get(ResourceType.PAPER), new Integer(4));
        assertEquals(resourceInput.get(ResourceType.COINS), new Integer(8));
        assertEquals(resourceOutput.get(ResourceType.STOCKBOND), new Integer(6));
        assertEquals(stockMarket.getProductionLimit(), 0);
    }

    @Test
    public void resetProductionLimit() {
        StockMarket stockMarket = new StockMarket();
        Map<ResourceType, Integer> resourceInput = new HashMap<>();
        Map<ResourceType, Integer> resourceOutput = new HashMap<>();
        Map<ResourceType, Integer> stockMarketOutput;

        resourceInput.put(ResourceType.PAPER, 12);
        resourceInput.put(ResourceType.COINS, 24);

        while (stockMarket.getProductionLimit() != 0) {
            stockMarketOutput = stockMarket.produce(resourceInput);

            if (resourceOutput.get(ResourceType.STOCKBOND) == null) {
                resourceOutput.put(ResourceType.STOCKBOND, stockMarketOutput.get(ResourceType.STOCKBOND));
            } else {
                resourceOutput.put(ResourceType.STOCKBOND, stockMarketOutput.get(ResourceType.STOCKBOND) + resourceOutput.get(ResourceType.STOCKBOND));
            }
        }

        stockMarket.resetProductionLimit();

        while (stockMarket.getProductionLimit() != 0) {
            stockMarketOutput = stockMarket.produce(resourceInput);

            if (resourceOutput.get(ResourceType.STOCKBOND) == null) {
                resourceOutput.put(ResourceType.STOCKBOND, stockMarketOutput.get(ResourceType.STOCKBOND));
            } else {
                resourceOutput.put(ResourceType.STOCKBOND, stockMarketOutput.get(ResourceType.STOCKBOND) + resourceOutput.get(ResourceType.STOCKBOND));
            }
        }

        assertEquals(resourceInput.get(ResourceType.PAPER), new Integer(0));
        assertEquals(resourceInput.get(ResourceType.COINS), new Integer(0));
        assertEquals(resourceOutput.get(ResourceType.STOCKBOND), new Integer(12));
        assertEquals(stockMarket.getProductionLimit(), 0);
    }

    @Test
    public void produceInvalid() {
        StockMarket stockMarket = new StockMarket();
        Map<ResourceType, Integer> resourceInput = new HashMap<>();
        resourceInput.put(ResourceType.GOOSE, 5);
        assertNull(stockMarket.produce(resourceInput));
    }
}
