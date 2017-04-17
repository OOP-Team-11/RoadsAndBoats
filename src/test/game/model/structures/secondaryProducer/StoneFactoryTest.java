package model.structures.secondaryProducer;

import game.model.PlayerId;
import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.secondaryProducer.StoneFactory;
import game.model.tile.TileCompartment;
import game.model.transport.SteamShipTransport;
import game.model.transport.TransportId;
import org.junit.Test;

import static org.junit.Assert.*;

public class StoneFactoryTest {

    @Test
    public void produceOnce() {
        StoneFactory stoneFactory = new StoneFactory();
        SteamShipTransport ss = new SteamShipTransport(new PlayerId(1), new TransportId());
        ss.storeResource(ResourceType.CLAY, 1);
        assertTrue(stoneFactory.produce(ss));
        assertEquals(ss.getResourceCount(ResourceType.STONE), 2);
    }

    @Test
    public void produceNone() {
        StoneFactory stoneFactory = new StoneFactory();
        SteamShipTransport ss = new SteamShipTransport(new PlayerId(1), new TransportId());
        ss.storeResource(ResourceType.CLAY, 0);
        assertFalse(stoneFactory.produce(ss));
        assertEquals(ss.getResourceCount(ResourceType.STONE), 0);
    }

    @Test
    public void produceMany() {
        StoneFactory stoneFactory = new StoneFactory();
        SteamShipTransport ss = new SteamShipTransport(new PlayerId(1), new TransportId());

        ss.storeResource(ResourceType.CLAY, 3);

        for (int i = 0; i < 3; ++i) {
            stoneFactory.produce(ss);
        }

        assertEquals(ss.getResourceCount(ResourceType.CLAY), 0);
        assertEquals(ss.getResourceCount(ResourceType.STONE), 6);
    }

    @Test
    public void produceToLimit() {
        StoneFactory stoneFactory = new StoneFactory();
        TileCompartment tc = new TileCompartment();

        tc.storeResource(ResourceType.CLAY, 10);

        while (stoneFactory.getProductionLimit() != 0) {
            stoneFactory.produce(tc);
        }

        assertEquals(tc.getResourceCount(ResourceType.CLAY), 4);
        assertEquals(tc.getResourceCount(ResourceType.STONE), 12);
        assertEquals(stoneFactory.getProductionLimit(), 0);
    }

    @Test
    public void resetProductionLimit() {
        StoneFactory stoneFactory = new StoneFactory();
        TileCompartment tc = new TileCompartment();

        tc.storeResource(ResourceType.CLAY, 12);

        while (stoneFactory.getProductionLimit() != 0) {
            stoneFactory.produce(tc);
        }

        stoneFactory.resetProductionLimit();

        while (stoneFactory.getProductionLimit() != 0) {
            stoneFactory.produce(tc);
        }

        assertEquals(tc.getResourceCount(ResourceType.CLAY), 0);
        assertEquals(tc.getResourceCount(ResourceType.STONE), 24);
        assertEquals(stoneFactory.getProductionLimit(), 0);

    }

}
