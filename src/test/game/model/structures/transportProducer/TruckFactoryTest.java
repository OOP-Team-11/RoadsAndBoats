package model.structures.transportProducer;

import game.model.PlayerId;
import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.TransportManager;
import game.model.resources.ResourceType;
import game.model.structures.transportProducer.TruckFactory;
import game.model.tile.TileCompartment;
import game.model.transport.TransportId;
import game.model.transport.TruckTransport;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TruckFactoryTest {

    private static final int IRON_REQ = 1;
    private static final int FUEL_REQ = 1;

    private TransportManager transportManager;

    @Before
    public void setUp() {
        this.transportManager = mock(TransportManager.class);
    }

    @Test
    public void produceUsingTransport() {
        TileCompartmentLocation tcl = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
        TruckFactory truckFactory = new TruckFactory(tcl);
        TruckTransport truck = new TruckTransport(new PlayerId(1), new TransportId());
        truck.storeResource(ResourceType.IRON, IRON_REQ);
        truck.storeResource(ResourceType.FUEL, FUEL_REQ);
        assertTrue(truckFactory.produce(transportManager, truck, tcl));
        assertEquals(truck.getResourceCount(ResourceType.IRON), 0);
        assertEquals(truck.getResourceCount(ResourceType.FUEL), 0);
        verify(transportManager, times(1)).addTransportVisit(any(TruckTransport.class), any(TileCompartmentLocation.class));
    }

    @Test
    public void produceNothingUsingTransport() {
        TileCompartmentLocation tcl = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
        TruckFactory truckFactory = new TruckFactory(tcl);
        TruckTransport truck = new TruckTransport(new PlayerId(1), new TransportId());
        assertFalse(truckFactory.produce(transportManager, truck, tcl));
        verify(transportManager, never()).addTransportVisit(any(TruckTransport.class), any(TileCompartmentLocation.class));
    }

    @Test
    public void produceUsingTileCompartment() {
        TileCompartmentLocation tcl = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
        TruckFactory truckFactory = new TruckFactory(tcl);
        TileCompartment tileCompartment = new TileCompartment();
        tileCompartment.storeResource(ResourceType.IRON, IRON_REQ);
        tileCompartment.storeResource(ResourceType.FUEL, FUEL_REQ);
        assertTrue(truckFactory.produce(tileCompartment));
        assertEquals(tileCompartment.getResourceCount(ResourceType.IRON), 0);
        assertEquals(tileCompartment.getResourceCount(ResourceType.FUEL), 0);
    }

    @Test
    public void produceNothingUsingTileCompartment() {
        TileCompartmentLocation tcl = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
        TruckFactory truckFactory = new TruckFactory(tcl);
        TileCompartment tileCompartment = new TileCompartment();
        assertFalse(truckFactory.produce(tileCompartment));
    }

}
