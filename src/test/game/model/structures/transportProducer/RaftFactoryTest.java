package model.structures.transportProducer;

import game.model.PlayerId;
import game.model.resources.ResourceType;
import game.model.structures.transportProducer.RaftFactory;
import game.model.tile.TileCompartment;
import game.model.transport.*;
import org.junit.Test;

import static org.junit.Assert.*;

public class RaftFactoryTest {

    private static final int TRUNKS_REQ = 2;

    @Test
    public void produceUsingTransport() {
        RaftFactory raftFactory = new RaftFactory();
        SteamShipTransport steamship = new SteamShipTransport(new PlayerId(1), new TransportId());
        steamship.storeResource(ResourceType.TRUNKS, TRUNKS_REQ);
        try {
            RaftTransport raft = (RaftTransport) raftFactory.produce(steamship);
        } catch (ClassCastException c) {
            fail();
        }
        assertEquals(steamship.getResourceCount(ResourceType.TRUNKS), 0);
    }

    @Test
    public void produceNothingUsingTransport() {
        RaftFactory raftFactory = new RaftFactory();
        SteamShipTransport steamship = new SteamShipTransport(new PlayerId(1), new TransportId());
        try {
            RaftTransport raft = (RaftTransport) raftFactory.produce(steamship);
        } catch (ClassCastException c) {
            assertEquals(steamship.getResourceCount(ResourceType.TRUNKS), 0);
        }
    }

    @Test
    public void produceUsingTileCompartment() {
        RaftFactory raftFactory = new RaftFactory();
        TileCompartment tileCompartment = new TileCompartment(false);
        tileCompartment.storeResource(ResourceType.TRUNKS, TRUNKS_REQ);
        assertTrue(raftFactory.produce(tileCompartment));
        assertEquals(tileCompartment.getResourceCount(ResourceType.TRUNKS), 0);
    }

    @Test
    public void produceNothingUsingTileCompartment() {
        RaftFactory raftFactory = new RaftFactory();
        TileCompartment tileCompartment = new TileCompartment(false);
        assertFalse(raftFactory.produce(tileCompartment));
    }

}