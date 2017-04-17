package model.structures.transportProducer;

import game.model.PlayerId;
import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.TransportManager;
import game.model.resources.ResourceType;
import game.model.structures.transportProducer.RaftFactory;
import game.model.tile.TileCompartment;
import game.model.transport.*;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RaftFactoryTest {

    private static final int TRUNKS_REQ = 2;

    private TransportManager transportManager;

    @Before
    public void setUp() {
        this.transportManager = mock(TransportManager.class);
    }

    @Test
    public void produceUsingTransport() {
        TileCompartmentLocation tcl = new TileCompartmentLocation(new Location(0, 0, 0), TileCompartmentDirection.getNorth());
        RaftFactory raftFactory = new RaftFactory(tcl);
        SteamShipTransport steamship = new SteamShipTransport(new PlayerId(1), new TransportId());
        steamship.storeResource(ResourceType.TRUNKS, TRUNKS_REQ);
        assertTrue(raftFactory.produce(transportManager, steamship, tcl));
        assertEquals(steamship.getResourceCount(ResourceType.TRUNKS), 0);
        verify(transportManager, times(1)).addTransportVisit(any(RaftTransport.class), any(TileCompartmentLocation.class));
    }

    @Test
    public void produceNothingUsingTransport() {
        TileCompartmentLocation tcl = new TileCompartmentLocation(new Location(0, 0, 0), TileCompartmentDirection.getNorth());
        RaftFactory raftFactory = new RaftFactory(tcl);
        SteamShipTransport steamship = new SteamShipTransport(new PlayerId(1), new TransportId());
        assertFalse(raftFactory.produce(transportManager, steamship, tcl));
        verify(transportManager, never()).addTransportVisit(any(RaftTransport.class), any(TileCompartmentLocation.class));
    }

    @Test
    public void produceUsingTileCompartment() {
        TileCompartmentLocation tcl = new TileCompartmentLocation(new Location(0, 0, 0), TileCompartmentDirection.getNorth());
        RaftFactory raftFactory = new RaftFactory(tcl);
        TileCompartment tileCompartment = new TileCompartment();
        tileCompartment.storeResource(ResourceType.TRUNKS, TRUNKS_REQ);
        assertTrue(raftFactory.produce(tileCompartment));
        assertEquals(tileCompartment.getResourceCount(ResourceType.TRUNKS), 0);
    }

    @Test
    public void produceNothingUsingTileCompartment() {
        TileCompartmentLocation tcl = new TileCompartmentLocation(new Location(0, 0, 0), TileCompartmentDirection.getNorth());
        RaftFactory raftFactory = new RaftFactory(tcl);
        TileCompartment tileCompartment = new TileCompartment();
        assertFalse(raftFactory.produce(tileCompartment));
    }

}