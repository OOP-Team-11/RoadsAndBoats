package model.structures.transportProducer;

import game.model.PlayerId;
import game.model.resources.ResourceType;
import game.model.structures.transportProducer.SteamerFactory;
import game.model.tile.TileCompartment;
import game.model.transport.SteamShipTransport;
import game.model.transport.TransportId;
import game.model.transport.TruckTransport;
import org.junit.Test;

import static org.junit.Assert.*;

public class SteamerFactoryTest {

    private static final int IRON_REQ = 1;
    private static final int FUEL_REQ = 2;

    @Test
    public void produceUsingTransport() {
        SteamerFactory steamerFactory = new SteamerFactory();
        SteamShipTransport steamship = new SteamShipTransport(new PlayerId(1), new TransportId());
        steamship.storeResource(ResourceType.IRON, IRON_REQ);
        steamship.storeResource(ResourceType.FUEL, FUEL_REQ);
        try {
            SteamShipTransport steamshipBaby = (SteamShipTransport) steamerFactory.produce(steamship);
        } catch (ClassCastException c) {
            fail();
        }
        assertEquals(steamship.getResourceCount(ResourceType.IRON), 0);
        assertEquals(steamship.getResourceCount(ResourceType.FUEL), 0);
    }

    @Test
    public void produceNothingUsingTransport() {
        SteamerFactory steamerFactory = new SteamerFactory();
        SteamShipTransport steamship = new SteamShipTransport(new PlayerId(1), new TransportId());
        try {
            SteamShipTransport steamshipBaby = (SteamShipTransport) steamerFactory.produce(steamship);
        } catch (ClassCastException c) {
            assertEquals(steamship.getResourceCount(ResourceType.IRON), 0);
            assertEquals(steamship.getResourceCount(ResourceType.FUEL), 0);
        }
    }

    @Test
    public void produceUsingTileCompartment() {
        SteamerFactory steamerFactory = new SteamerFactory();
        TileCompartment tileCompartment = new TileCompartment(false);
        tileCompartment.storeResource(ResourceType.IRON, IRON_REQ);
        tileCompartment.storeResource(ResourceType.FUEL, FUEL_REQ);
        assertTrue(steamerFactory.produce(tileCompartment));
        assertEquals(tileCompartment.getResourceCount(ResourceType.IRON), 0);
        assertEquals(tileCompartment.getResourceCount(ResourceType.FUEL), 0);
    }

    @Test
    public void produceNothingUsingTileCompartment() {
        SteamerFactory steamerFactory = new SteamerFactory();
        TileCompartment tileCompartment = new TileCompartment(false);
        assertFalse(steamerFactory.produce(tileCompartment));
    }

}
