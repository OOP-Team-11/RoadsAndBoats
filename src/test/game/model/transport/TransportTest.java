package model.transport;

import game.model.PlayerId;
import game.model.resources.ResourceType;
import game.model.transport.DonkeyTransport;
import game.model.transport.Transport;
import game.model.transport.TransportId;
import org.junit.Before;
import org.junit.Test;

import javax.annotation.Resource;

import static junit.framework.TestCase.assertFalse;
import static junit.framework.TestCase.assertNull;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

public class TransportTest {

    private PlayerId playerId;
    private Transport transport;
    private Transport transport1;

    @Before
    public void setUp() {
        playerId = new PlayerId(1);
        transport = new DonkeyTransport(playerId, new TransportId());
        transport1 = new DonkeyTransport(playerId, new TransportId());
    }

    @Test
    public void addTransport_toEmptyTransport() {
        Transport transport1 = new DonkeyTransport(new PlayerId(1), new TransportId());
        assertTrue(transport.canStoreTransport(transport1));
        assertTrue(transport.storeTransport(transport1));
        assertEquals(transport.getCarryCapacity(), 0);
    }

    @Test
    public void addNonEmptyTransport_toEmptyTransport() {
        int initialCapacity = transport.getCarryCapacity();
        transport1.storeResource(ResourceType.CLAY,1);
        assertFalse(transport.canStoreTransport(transport1));
        assertFalse(transport.storeTransport(transport1));
        assertEquals(transport.getCarryCapacity(), initialCapacity);
    }

    @Test
    public void addTransport_toNonEmptyTransport() {
        int initialCapacity = transport.getCarryCapacity();
        transport.storeResource(ResourceType.BOARDS, 1);
        assertFalse(transport.canStoreTransport(transport1));
        assertFalse(transport.storeTransport(transport1));
        assertEquals(transport.getCarryCapacity(), initialCapacity - 1);
    }

    @Test
    public void addTransport_toTransportWithTransport() {
        transport.storeTransport(transport1);
        Transport transport2 = new DonkeyTransport(playerId, new TransportId());
        assertFalse(transport.canStoreTransport(transport2));
        assertFalse(transport.storeTransport(transport2));
        assertSame(transport.removeTransport(), transport1);
        assertEquals(transport.getCarryCapacity(), 0);
    }

    @Test
    public void removeTransport_fromEmptyTransport() {
        assertNull(transport.removeTransport());
    }

    @Test
    public void removeTransport_fromTransportWithResources() {
        transport.storeResource(ResourceType.COINS, 1);
        assertNull(transport.removeTransport());
    }

    @Test
    public void removeTransport_sameInstance() {
        transport.storeTransport(transport1);
        assertSame(transport.removeTransport(), transport1);
    }

    @Test
    public void addResource_toTransportWithTransport() {
        transport.storeTransport(transport1);
        assertFalse(transport.storeResource(ResourceType.BOARDS, 1));
    }


}
