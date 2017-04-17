package model.structures.secondaryProducer;

import game.model.PlayerId;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.secondaryProducer.Sawmill;
import game.model.tile.TileCompartment;
import game.model.transport.SteamShipTransport;
import game.model.transport.TransportId;
import org.junit.Test;

import static org.junit.Assert.*;

public class SawmillTest {

    @Test
    public void produceOnce() {
        Sawmill sawmill = new Sawmill();
        SteamShipTransport ss = new SteamShipTransport(new PlayerId(1), new TransportId());
        ss.storeResource(ResourceType.TRUNKS, 1);
        assertTrue(sawmill.produce(ss));
        assertEquals(ss.getResourceCount(ResourceType.BOARDS), 2);
    }

    @Test
    public void produceNone() {
        Sawmill sawmill = new Sawmill();
        SteamShipTransport ss = new SteamShipTransport(new PlayerId(1), new TransportId());
        ss.storeResource(ResourceType.TRUNKS, 0);
        assertFalse(sawmill.produce(ss));
        assertEquals(ss.getResourceCount(ResourceType.BOARDS), 0);
    }

    @Test
    public void produceMany() {
        Sawmill sawmill = new Sawmill();
        SteamShipTransport ss = new SteamShipTransport(new PlayerId(1), new TransportId());
        ss.storeResource(ResourceType.TRUNKS, 3);

        for (int i = 0; i < 3; ++i) {
            sawmill.produce(ss);
        }

        assertEquals(ss.getResourceCount(ResourceType.TRUNKS), 0);
        assertEquals(ss.getResourceCount(ResourceType.BOARDS), 6);
    }

    @Test
    public void produceToLimit() {
        Sawmill sawmill = new Sawmill();
        TileCompartment tc = new TileCompartment();
        tc.storeResource(ResourceType.TRUNKS, 10);

        while (sawmill.getProductionLimit() != 0) {
            sawmill.produce(tc);
        }

        assertEquals(tc.getResourceCount(ResourceType.TRUNKS), 4);
        assertEquals(tc.getResourceCount(ResourceType.BOARDS), 12);
        assertEquals(sawmill.getProductionLimit(), 0);
    }

    @Test
    public void resetProductionLimit() {
        Sawmill sawmill = new Sawmill();
        TileCompartment tc = new TileCompartment();
        tc.storeResource(ResourceType.TRUNKS, 12);

        while (sawmill.getProductionLimit() != 0) {
            sawmill.produce(tc);
        }

        sawmill.resetProductionLimit();

        while (sawmill.getProductionLimit() != 0) {
            sawmill.produce(tc);
        }

        assertEquals(tc.getResourceCount(ResourceType.TRUNKS), 0);
        assertEquals(tc.getResourceCount(ResourceType.BOARDS), 24);
        assertEquals(sawmill.getProductionLimit(), 0);
    }

}
