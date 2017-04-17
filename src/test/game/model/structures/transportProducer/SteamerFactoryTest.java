package model.structures.transportProducer;

import game.model.PlayerId;
import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.TransportManager;
import game.model.resources.ResourceType;
import game.model.structures.transportProducer.SteamerFactory;
import game.model.tile.TileCompartment;
import game.model.transport.SteamShipTransport;
import game.model.transport.TransportId;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class SteamerFactoryTest {

    private static final int IRON_REQ = 1;
    private static final int FUEL_REQ = 2;

    private TransportManager transportManager;

    @Before
    public void setUp() {
        this.transportManager = mock(TransportManager.class);
    }

    @Test
    public void produceUsingTransport() {
        TileCompartmentLocation tcl = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
        SteamerFactory steamerFactory = new SteamerFactory(tcl);
        SteamShipTransport steamship = new SteamShipTransport(new PlayerId(1), new TransportId());
        steamship.storeResource(ResourceType.IRON, IRON_REQ);
        steamship.storeResource(ResourceType.FUEL, FUEL_REQ);
        assertTrue(steamerFactory.produce(transportManager, steamship, tcl));
        assertEquals(steamship.getResourceCount(ResourceType.IRON), 0);
        assertEquals(steamship.getResourceCount(ResourceType.FUEL), 0);
        verify(transportManager, times(1)).addTransportVisit(any(SteamShipTransport.class), any(TileCompartmentLocation.class));
    }

    @Test
    public void produceNothingUsingTransport() {
        TileCompartmentLocation tcl = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
        SteamerFactory steamerFactory = new SteamerFactory(tcl);
        SteamShipTransport steamship = new SteamShipTransport(new PlayerId(1), new TransportId());
        assertFalse(steamerFactory.produce(transportManager, steamship, tcl));
        verify(transportManager, never()).addTransportVisit(any(SteamShipTransport.class), any(TileCompartmentLocation.class));
    }

    @Test
    public void produceUsingTileCompartment() {
        TileCompartmentLocation tcl = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
        SteamerFactory steamerFactory = new SteamerFactory(tcl);
        TileCompartment tileCompartment = new TileCompartment();
        tileCompartment.storeResource(ResourceType.IRON, IRON_REQ);
        tileCompartment.storeResource(ResourceType.FUEL, FUEL_REQ);
        assertTrue(steamerFactory.produce(tileCompartment));
        assertEquals(tileCompartment.getResourceCount(ResourceType.IRON), 0);
        assertEquals(tileCompartment.getResourceCount(ResourceType.FUEL), 0);
    }

    @Test
    public void produceNothingUsingTileCompartment() {
        TileCompartmentLocation tcl = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
        SteamerFactory steamerFactory = new SteamerFactory(tcl);
        TileCompartment tileCompartment = new TileCompartment();
        assertFalse(steamerFactory.produce(tileCompartment));
    }

}
