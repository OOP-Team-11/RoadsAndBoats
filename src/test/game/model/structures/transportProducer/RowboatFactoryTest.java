package model.structures.transportProducer;

import game.model.PlayerId;
import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.TransportManager;
import game.model.resources.ResourceType;
import game.model.structures.transportProducer.RowboatFactory;
import game.model.tile.TileCompartment;
import game.model.transport.RowboatTransport;
import game.model.transport.SteamShipTransport;
import game.model.transport.TransportId;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RowboatFactoryTest {

    private static final int BOARDS_REQ = 5;

    private TransportManager transportManager;

    @Before
    public void setUp() {
        this.transportManager = mock(TransportManager.class);
    }

    @Test
    public void produceUsingTransport() {
        TileCompartmentLocation tcl = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
        RowboatFactory rowboatFactory = new RowboatFactory(tcl);
        SteamShipTransport steamship = new SteamShipTransport(new PlayerId(1), new TransportId());
        steamship.storeResource(ResourceType.BOARDS, BOARDS_REQ);
        assertTrue(rowboatFactory.produce(transportManager, steamship, tcl));
        assertEquals(steamship.getResourceCount(ResourceType.BOARDS), 0);
        verify(transportManager, times(1)).addTransportVisit(any(RowboatTransport.class), any(TileCompartmentLocation.class));
    }

    @Test
    public void produceNothingUsingTransport() {
        TileCompartmentLocation tcl = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
        RowboatFactory rowboatFactory = new RowboatFactory(tcl);
        SteamShipTransport steamship = new SteamShipTransport(new PlayerId(1), new TransportId());
        assertFalse(rowboatFactory.produce(transportManager, steamship, tcl));
        verify(transportManager, never()).addTransportVisit(any(RowboatTransport.class), any(TileCompartmentLocation.class));
    }

    @Test
    public void produceUsingTileCompartment() {
        TileCompartmentLocation tcl = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
        RowboatFactory rowboatFactory = new RowboatFactory(tcl);
        TileCompartment tileCompartment = new TileCompartment();
        tileCompartment.storeResource(ResourceType.BOARDS, BOARDS_REQ);
        assertTrue(rowboatFactory.produce(tileCompartment));
        assertEquals(tileCompartment.getResourceCount(ResourceType.BOARDS), 0);
    }

    @Test
    public void produceNothingUsingTileCompartment() {
        TileCompartmentLocation tcl = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
        RowboatFactory rowboatFactory = new RowboatFactory(tcl);
        TileCompartment tileCompartment = new TileCompartment();
        assertFalse(rowboatFactory.produce(tileCompartment));
        assertEquals(tileCompartment.getResourceCount(ResourceType.BOARDS), 0);
    }

}
