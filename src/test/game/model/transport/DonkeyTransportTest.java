package model.transport;

import game.model.PlayerId;
import game.model.resources.ResourceType;
import game.model.transport.DonkeyTransport;
import game.model.transport.TransportId;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DonkeyTransportTest {

    //TODO Not all tests are done. Only ResourceManager is tested

    private final static int CARRY_CAP = 2;

    @Test
    public void storeResource() {
        DonkeyTransport donkey = new DonkeyTransport(new PlayerId(1), new TransportId());
        assertTrue(donkey.storeResource(ResourceType.GOOSE, 1));
        assertEquals(donkey.getCarryCapacity(), CARRY_CAP-1);
        assertEquals(donkey.getResourceCount(ResourceType.GOOSE), 1);
    }

    @Test
    public void takeResource() {
        DonkeyTransport donkey = new DonkeyTransport(new PlayerId(1), new TransportId());
        assertTrue(donkey.storeResource(ResourceType.GOOSE, 1));
        assertTrue(donkey.takeResource(ResourceType.GOOSE, 1));
        assertEquals(donkey.getCarryCapacity(), CARRY_CAP);
        assertEquals(donkey.getResourceCount(ResourceType.GOOSE), 0);
    }

    @Test
    public void storeOverCapacity() {
        DonkeyTransport donkey = new DonkeyTransport(new PlayerId(1), new TransportId());
        assertFalse(donkey.storeResource(ResourceType.GOOSE, 5));
        assertEquals(donkey.getCarryCapacity(), CARRY_CAP);
        assertEquals(donkey.getResourceCount(ResourceType.GOOSE), 0);
    }

    @Test
    public void takeOverCapacity() {
        DonkeyTransport donkey = new DonkeyTransport(new PlayerId(1), new TransportId());
        assertTrue(donkey.storeResource(ResourceType.GOOSE, 1));
        assertFalse(donkey.takeResource(ResourceType.GOOSE, 5));
        assertEquals(donkey.getCarryCapacity(), CARRY_CAP-1);
        assertEquals(donkey.getResourceCount(ResourceType.GOOSE), 1);
    }
}
