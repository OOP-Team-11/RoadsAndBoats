package model.structures.secondaryProducer;

import game.model.PlayerId;
import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.secondaryProducer.Papermill;
import game.model.tile.Tile;
import game.model.tile.TileCompartment;
import game.model.transport.SteamShipTransport;
import game.model.transport.TransportId;
import org.junit.Test;

import static org.junit.Assert.*;

public class PapermillTest {

    @Test
    public void produceOnceWithTrunks() {
        Papermill papermill = new Papermill();
        SteamShipTransport ss = new SteamShipTransport(new PlayerId(1), new TransportId());
        ss.storeResource(ResourceType.TRUNKS, 2);
        assertTrue(papermill.produce(ss));
        assertEquals(ss.getResourceCount(ResourceType.PAPER), 1);
    }

    @Test
    public void produceOnceWithBoards() {
        Papermill papermill = new Papermill();
        SteamShipTransport ss = new SteamShipTransport(new PlayerId(1), new TransportId());
        ss.storeResource(ResourceType.BOARDS, 2);
        assertTrue(papermill.produce(ss));
        assertEquals(ss.getResourceCount(ResourceType.PAPER), 1);
    }

    @Test
    public void produceOnceWithBoth() {
        Papermill papermill = new Papermill();
        SteamShipTransport ss = new SteamShipTransport(new PlayerId(1), new TransportId());
        ss.storeResource(ResourceType.BOARDS, 1);
        ss.storeResource(ResourceType.TRUNKS, 1);
        assertTrue(papermill.produce(ss));
        assertEquals(ss.getResourceCount(ResourceType.PAPER), 1);
    }

    @Test
    public void produceNoneWithTrunks() {
        Papermill papermill = new Papermill();
        SteamShipTransport ss = new SteamShipTransport(new PlayerId(1), new TransportId());
        ss.storeResource(ResourceType.TRUNKS, 0);
        assertFalse(papermill.produce(ss));
        assertEquals(ss.getResourceCount(ResourceType.PAPER), 0);
    }

    @Test
    public void produceNoneWithBoards() {
        Papermill papermill = new Papermill();
        SteamShipTransport ss = new SteamShipTransport(new PlayerId(1), new TransportId());
        ss.storeResource(ResourceType.BOARDS, 0);
        assertFalse(papermill.produce(ss));
        assertEquals(ss.getResourceCount(ResourceType.PAPER), 0);
    }

    @Test
    public void produceNoneWithBoth() {
        Papermill papermill = new Papermill();
        SteamShipTransport ss = new SteamShipTransport(new PlayerId(1), new TransportId());
        ss.storeResource(ResourceType.BOARDS, 0);
        ss.storeResource(ResourceType.TRUNKS, 0);
        assertFalse(papermill.produce(ss));
        assertEquals(ss.getResourceCount(ResourceType.PAPER), 0);
    }

    @Test
    public void produceManyWithTrunks() {
        Papermill papermill = new Papermill();
        TileCompartment tc = new TileCompartment();
        tc.storeResource(ResourceType.TRUNKS, 6);

        for (int i = 0; i < 3; ++i) {
            papermill.produce(tc);
        }

        assertEquals(tc.getResourceCount(ResourceType.TRUNKS), 0);
        assertEquals(tc.getResourceCount(ResourceType.PAPER), 3);
    }

    @Test
    public void produceManyWithBoards() {
        Papermill papermill = new Papermill();
        TileCompartment tc = new TileCompartment();
        tc.storeResource(ResourceType.BOARDS, 6);

        for (int i = 0; i < 3; ++i) {
            papermill.produce(tc);
        }

        assertEquals(tc.getResourceCount(ResourceType.BOARDS), 0);
        assertEquals(tc.getResourceCount(ResourceType.PAPER), 3);
    }

    @Test
    public void produceManyWithBoth() {
        Papermill papermill = new Papermill();
        TileCompartment tc = new TileCompartment();
        tc.storeResource(ResourceType.TRUNKS, 6);
        tc.storeResource(ResourceType.BOARDS, 6);

        for (int i = 0; i < 3; ++i) {
            papermill.produce(tc);
        }

        assertEquals(tc.getResourceCount(ResourceType.TRUNKS), 0);
        assertEquals(tc.getResourceCount(ResourceType.BOARDS), 6);
        assertEquals(tc.getResourceCount(ResourceType.PAPER), 3);
    }

}
