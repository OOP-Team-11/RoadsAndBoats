package model.structures.secondaryProducer;

import game.model.PlayerId;
import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.secondaryProducer.StockMarket;
import game.model.tile.TileCompartment;
import game.model.transport.SteamShipTransport;
import game.model.transport.TransportId;
import org.junit.Test;

import static org.junit.Assert.*;

public class StockMarketTest {

    @Test
    public void produceOnce() {
        StockMarket stockMarket = new StockMarket();
        SteamShipTransport ss = new SteamShipTransport(new PlayerId(1), new TransportId());
        ss.storeResource(ResourceType.PAPER, 1);
        ss.storeResource(ResourceType.COINS, 2);
        assertTrue(stockMarket.produce(ss));
        assertEquals(ss.getResourceCount(ResourceType.STOCKBOND), 1);
    }

    @Test
    public void produceNone() {
        StockMarket stockMarket = new StockMarket();
        SteamShipTransport ss = new SteamShipTransport(new PlayerId(1), new TransportId());
        ss.storeResource(ResourceType.PAPER, 0);
        ss.storeResource(ResourceType.COINS, 0);
        assertFalse(stockMarket.produce(ss));
        assertEquals(ss.getResourceCount(ResourceType.STOCKBOND), 0);
    }

    @Test
    public void produceMany() {
        StockMarket stockMarket = new StockMarket();
        TileCompartment tc = new TileCompartment();

        tc.storeResource(ResourceType.PAPER, 3);
        tc.storeResource(ResourceType.COINS, 6);

        for (int i = 0; i < 3; ++i) {
            stockMarket.produce(tc);
        }

        assertEquals(tc.getResourceCount(ResourceType.PAPER), 0);
        assertEquals(tc.getResourceCount(ResourceType.COINS), 0);
        assertEquals(tc.getResourceCount(ResourceType.STOCKBOND), 3);
    }

    @Test
    public void produceToLimit() {
        StockMarket stockMarket = new StockMarket();
        TileCompartment tc = new TileCompartment();

        tc.storeResource(ResourceType.PAPER, 10);
        tc.storeResource(ResourceType.COINS, 20);

        while (stockMarket.getProductionLimit() != 0) {
            stockMarket.produce(tc);
        }

        assertEquals(tc.getResourceCount(ResourceType.PAPER), 4);
        assertEquals(tc.getResourceCount(ResourceType.COINS), 8);
        assertEquals(tc.getResourceCount(ResourceType.STOCKBOND), 6);
        assertEquals(stockMarket.getProductionLimit(), 0);
    }

    @Test
    public void resetProductionLimit() {
        StockMarket stockMarket = new StockMarket();
        TileCompartment tc = new TileCompartment();

        tc.storeResource(ResourceType.PAPER, 12);
        tc.storeResource(ResourceType.COINS, 24);

        while (stockMarket.getProductionLimit() != 0) {
            stockMarket.produce(tc);
        }

        stockMarket.resetProductionLimit();

        while (stockMarket.getProductionLimit() != 0) {
            stockMarket.produce(tc);
        }

        assertEquals(tc.getResourceCount(ResourceType.PAPER), 0);
        assertEquals(tc.getResourceCount(ResourceType.COINS), 0);
        assertEquals(tc.getResourceCount(ResourceType.STOCKBOND), 12);
        assertEquals(stockMarket.getProductionLimit(), 0);
    }

}
