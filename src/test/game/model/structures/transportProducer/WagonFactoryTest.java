package model.structures.transportProducer;

import game.model.PlayerId;
import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.TransportManager;
import game.model.resources.ResourceType;
import game.model.structures.transportProducer.WagonFactory;
import game.model.tile.TileCompartment;
import game.model.transport.DonkeyTransport;
import game.model.transport.TransportId;
import game.model.transport.WagonTransport;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class WagonFactoryTest {

    private static final int BOARDS_REQ = 2;

    private TransportManager transportManager;

    @Before
    public void setUp() {
        this.transportManager = mock(TransportManager.class);
    }

    @Test
    public void produceUsingTransport() {
        TileCompartmentLocation tcl = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
        WagonFactory wagonFactory = new WagonFactory(tcl);
        DonkeyTransport donkey = new DonkeyTransport(new PlayerId(1), new TransportId());
        donkey.storeResource(ResourceType.BOARDS, BOARDS_REQ);
        assertTrue(wagonFactory.produce(transportManager, donkey, tcl));
        assertEquals(donkey.getResourceCount(ResourceType.BOARDS), 0);
        verify(transportManager, times(1)).addTransportVisit(any(WagonTransport.class), any(TileCompartmentLocation.class));
        verify(transportManager, times(1)).removeTransportVisit(any(DonkeyTransport.class));
    }

    @Test
    public void produceNothingUsingTransport() {
        TileCompartmentLocation tcl = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
        WagonFactory wagonFactory = new WagonFactory(tcl);
        DonkeyTransport donkey = new DonkeyTransport(new PlayerId(1), new TransportId());
        assertFalse(wagonFactory.produce(transportManager, donkey, tcl));
        verify(transportManager, never()).addTransportVisit(any(WagonTransport.class), any(TileCompartmentLocation.class));
        verify(transportManager, never()).removeTransportVisit(any(DonkeyTransport.class));
    }

    @Test
    public void produceUsingTileCompartment() {
        TileCompartmentLocation tcl = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
        WagonFactory wagonFactory = new WagonFactory(tcl);
        TileCompartment tileCompartment = new TileCompartment();
        tileCompartment.storeResource(ResourceType.BOARDS, BOARDS_REQ);
        assertTrue(wagonFactory.produce(tileCompartment));
        assertEquals(tileCompartment.getResourceCount(ResourceType.BOARDS), 0);
    }

    @Test
    public void produceNothingUsingTileCompartment() {
        TileCompartmentLocation tcl = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
        WagonFactory wagonFactory = new WagonFactory(tcl);
        TileCompartment tileCompartment = new TileCompartment();
        assertFalse(wagonFactory.produce(tileCompartment));
        assertEquals(tileCompartment.getResourceCount(ResourceType.BOARDS), 0);
    }

}
