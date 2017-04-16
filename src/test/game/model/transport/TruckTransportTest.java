package model.transport;

import game.model.PlayerId;
import game.model.resources.ResourceType;
import game.model.transport.TransportId;
import game.model.transport.TruckTransport;
import org.junit.Test;

import static junit.framework.TestCase.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TruckTransportTest {

    //TODO Not all tests are done. Only ResourceManager is tested

    private static final int CARRY_CAP = 6;

    @Test
    public void storeResource() {
        TruckTransport truck = new TruckTransport(new PlayerId(1), new TransportId());
        assertTrue(truck.storeResource(ResourceType.GOOSE, 1));
        assertEquals(truck.getCarryCapacity(), CARRY_CAP-1);
        assertEquals(truck.getResourceCount(ResourceType.GOOSE), 1);
    }

    @Test
    public void takeResource() {
        TruckTransport truck = new TruckTransport(new PlayerId(1), new TransportId());
        assertTrue(truck.storeResource(ResourceType.GOOSE, 1));
        assertTrue(truck.takeResource(ResourceType.GOOSE, 1));
        assertEquals(truck.getCarryCapacity(), CARRY_CAP);
        assertEquals(truck.getResourceCount(ResourceType.GOOSE), 0);
    }

    @Test
    public void storeOverCapacity() {
        TruckTransport truck = new TruckTransport(new PlayerId(1), new TransportId());
        assertFalse(truck.storeResource(ResourceType.GOOSE, 10));
        assertEquals(truck.getCarryCapacity(), CARRY_CAP);
        assertEquals(truck.getResourceCount(ResourceType.GOOSE), 0);
    }

    @Test
    public void takeOverCapacity() {
        TruckTransport truck = new TruckTransport(new PlayerId(1), new TransportId());
        assertTrue(truck.storeResource(ResourceType.GOOSE, 5));
        assertFalse(truck.takeResource(ResourceType.GOOSE, 15));
        assertEquals(truck.getCarryCapacity(), CARRY_CAP-5);
        assertEquals(truck.getResourceCount(ResourceType.GOOSE), 5);
    }

}
