package model.structures.secondaryProducer;

import game.model.PlayerId;
import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.secondaryProducer.Mint;
import game.model.tile.TileCompartment;
import game.model.transport.SteamShipTransport;
import game.model.transport.TransportId;
import org.junit.Test;

import static org.junit.Assert.*;

public class MintTest {

    @Test
    public void produceOnce() {
        Mint mint = new Mint();
        SteamShipTransport ss = new SteamShipTransport(new PlayerId(1), new TransportId());
        ss.storeResource(ResourceType.FUEL, 1);
        ss.storeResource(ResourceType.GOLD, 2);
        assertTrue(mint.produce(ss));
        assertEquals(ss.getResourceCount(ResourceType.COINS), 1);
    }

    @Test
    public void produceNone() {
        Mint mint = new Mint();
        SteamShipTransport ss = new SteamShipTransport(new PlayerId(1), new TransportId());
        ss.storeResource(ResourceType.FUEL, 0);
        ss.storeResource(ResourceType.GOLD, 0);
        assertFalse(mint.produce(ss));
        assertEquals(ss.getResourceCount(ResourceType.COINS), 0);
    }

    @Test
    public void produceMany() {
        Mint mint = new Mint();
        TileCompartment tc = new TileCompartment();
        tc.storeResource(ResourceType.FUEL, 3);
        tc.storeResource(ResourceType.GOLD, 6);

        for (int i = 0; i < 3; ++i) {
            mint.produce(tc);
        }

        assertEquals(tc.getResourceCount(ResourceType.FUEL), 0);
        assertEquals(tc.getResourceCount(ResourceType.GOLD), 0);
        assertEquals(tc.getResourceCount(ResourceType.COINS), 3);
    }

}
