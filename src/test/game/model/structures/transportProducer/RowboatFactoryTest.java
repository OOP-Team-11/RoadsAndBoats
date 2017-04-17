package model.structures.transportProducer;

import game.model.PlayerId;
import game.model.resources.ResourceType;
import game.model.structures.transportProducer.RowboatFactory;
import game.model.tile.TileCompartment;
import game.model.transport.RowboatTransport;
import game.model.transport.SteamShipTransport;
import game.model.transport.TransportId;
import game.model.transport.TruckTransport;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;

public class RowboatFactoryTest {

    private static final int BOARDS_REQ = 5;

    @Test
    public void produceUsingTransport() {
        RowboatFactory rowboatFactory = new RowboatFactory();
        SteamShipTransport steamship = new SteamShipTransport(new PlayerId(1), new TransportId());
        steamship.storeResource(ResourceType.BOARDS, BOARDS_REQ);
        try {
            RowboatTransport rowboat = (RowboatTransport) rowboatFactory.produce(steamship);
        } catch (ClassCastException c) {
            fail();
        }
        assertEquals(steamship.getResourceCount(ResourceType.BOARDS), 0);
    }

    @Test
    public void produceNothingUsingTransport() {
        RowboatFactory rowboatFactory = new RowboatFactory();
        SteamShipTransport steamship = new SteamShipTransport(new PlayerId(1), new TransportId());
        try {
            RowboatTransport rowboat = (RowboatTransport) rowboatFactory.produce(steamship);
        } catch (ClassCastException c) {
            assertEquals(steamship.getResourceCount(ResourceType.BOARDS), 0);
        }
    }

    @Test
    public void produceUsingTileCompartment() {
        RowboatFactory rowboatFactory = new RowboatFactory();
        TileCompartment tileCompartment = new TileCompartment();
        tileCompartment.storeResource(ResourceType.BOARDS, BOARDS_REQ);
        assertTrue(rowboatFactory.produce(tileCompartment));
        assertEquals(tileCompartment.getResourceCount(ResourceType.BOARDS), 0);
    }

    @Test
    public void produceNothingUsingTileCompartment() {
        RowboatFactory rowboatFactory = new RowboatFactory();
        TileCompartment tileCompartment = new TileCompartment();
        assertFalse(rowboatFactory.produce(tileCompartment));
        assertEquals(tileCompartment.getResourceCount(ResourceType.BOARDS), 0);
    }

}
