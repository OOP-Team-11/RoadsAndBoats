package game.model.structures.secondaryProducer;

import game.model.PlayerId;
import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.secondaryProducer.CoalBurner;
import game.model.tile.TileCompartment;
import game.model.transport.SteamShipTransport;
import game.model.transport.TransportId;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoalBurnerTest {

    @Test
    public void produceOnceWithTrunks() {
        CoalBurner coalBurner = new CoalBurner();
        SteamShipTransport ss = new SteamShipTransport(new PlayerId(1), new TransportId());
        ss.storeResource(ResourceType.TRUNKS, 2);
        assertTrue(coalBurner.produce(ss));
        assertEquals(ss.getResourceCount(ResourceType.FUEL), 1);
    }

    @Test
    public void produceOnceWithBoards() {
        CoalBurner coalBurner = new CoalBurner();
        SteamShipTransport ss = new SteamShipTransport(new PlayerId(1), new TransportId());
        ss.storeResource(ResourceType.BOARDS, 2);
        assertTrue(coalBurner.produce(ss));
        assertEquals(ss.getResourceCount(ResourceType.FUEL), 1);
    }

    @Test
    public void produceOnceWithBoth() {
        CoalBurner coalBurner = new CoalBurner();
        SteamShipTransport ss = new SteamShipTransport(new PlayerId(1), new TransportId());
        ss.storeResource(ResourceType.BOARDS, 1);
        ss.storeResource(ResourceType.TRUNKS, 1);
        assertTrue(coalBurner.produce(ss));
        assertEquals(ss.getResourceCount(ResourceType.FUEL), 1);
    }

    @Test
    public void produceNoneWithTrunks() {
        CoalBurner coalBurner = new CoalBurner();
        SteamShipTransport ss = new SteamShipTransport(new PlayerId(1), new TransportId());
        ss.storeResource(ResourceType.TRUNKS, 0);
        assertFalse(coalBurner.produce(ss));
        assertEquals(ss.getResourceCount(ResourceType.FUEL), 0);
    }

    @Test
    public void produceNoneWithBoards() {
        CoalBurner coalBurner = new CoalBurner();
        SteamShipTransport ss = new SteamShipTransport(new PlayerId(1), new TransportId());
        ss.storeResource(ResourceType.BOARDS, 0);
        assertFalse(coalBurner.produce(ss));
        assertEquals(ss.getResourceCount(ResourceType.FUEL), 0);
    }

    @Test
    public void produceNoneWithBoth() {
        CoalBurner coalBurner = new CoalBurner();
        SteamShipTransport ss = new SteamShipTransport(new PlayerId(1), new TransportId());
        ss.storeResource(ResourceType.BOARDS, 0);
        ss.storeResource(ResourceType.TRUNKS, 0);
        assertFalse(coalBurner.produce(ss));
        assertEquals(ss.getResourceCount(ResourceType.FUEL), 0);
    }

    @Test
    public void produceManyWithTrunks() {
        CoalBurner coalBurner = new CoalBurner();
        TileCompartment tc = new TileCompartment();
        tc.storeResource(ResourceType.TRUNKS, 6);

        for (int i = 0; i < 3; ++i) {
            coalBurner.produce(tc);
        }

        assertEquals(tc.getResourceCount(ResourceType.TRUNKS), 0);
        assertEquals(tc.getResourceCount(ResourceType.FUEL), 3);
    }

    @Test
    public void produceManyWithBoards() {
        CoalBurner coalBurner = new CoalBurner();
        TileCompartment tc = new TileCompartment();
        tc.storeResource(ResourceType.BOARDS, 6);

        for (int i = 0; i < 3; ++i) {
            coalBurner.produce(tc);
        }

        assertEquals(tc.getResourceCount(ResourceType.BOARDS), 0);
        assertEquals(tc.getResourceCount(ResourceType.FUEL), 3);
    }

    @Test
    public void produceManyWithBoth() {
        CoalBurner coalBurner = new CoalBurner();
        TileCompartment tc = new TileCompartment();
        tc.storeResource(ResourceType.TRUNKS, 6);
        tc.storeResource(ResourceType.BOARDS, 6);

        for (int i = 0; i < 3; ++i) {
            coalBurner.produce(tc);
        }

        assertEquals(tc.getResourceCount(ResourceType.TRUNKS), 0);
        assertEquals(tc.getResourceCount(ResourceType.BOARDS), 6);
        assertEquals(tc.getResourceCount(ResourceType.FUEL), 3);
    }

    @Test
    public void produceToLimitWithTrunks() {
        CoalBurner coalBurner = new CoalBurner();
        TileCompartment tc = new TileCompartment();
        tc.storeResource(ResourceType.TRUNKS, 20);

        while (coalBurner.getProductionLimit() != 0) {
            coalBurner.produce(tc);
        }

        assertEquals(tc.getResourceCount(ResourceType.TRUNKS), 8);
        assertEquals(tc.getResourceCount(ResourceType.FUEL), 6);
        assertEquals(coalBurner.getProductionLimit(), 0);
    }

    @Test
    public void produceToLimitWithBoards() {
        CoalBurner coalBurner = new CoalBurner();
        TileCompartment tc = new TileCompartment();
        tc.storeResource(ResourceType.BOARDS, 20);

        while (coalBurner.getProductionLimit() != 0) {
            coalBurner.produce(tc);
        }

        assertEquals(tc.getResourceCount(ResourceType.BOARDS), 8);
        assertEquals(tc.getResourceCount(ResourceType.FUEL), 6);
        assertEquals(coalBurner.getProductionLimit(), 0);
    }

    @Test
    public void produceToLimitWithBoth() {
        CoalBurner coalBurner = new CoalBurner();
        TileCompartment tc = new TileCompartment();
        tc.storeResource(ResourceType.TRUNKS, 20);
        tc.storeResource(ResourceType.BOARDS, 20);

        while (coalBurner.getProductionLimit() != 0) {
            coalBurner.produce(tc);
        }

        assertEquals(tc.getResourceCount(ResourceType.TRUNKS), 8);
        assertEquals(tc.getResourceCount(ResourceType.BOARDS), 20);
        assertEquals(tc.getResourceCount(ResourceType.FUEL), 6);
        assertEquals(coalBurner.getProductionLimit(), 0);
    }

    @Test
    public void resetProductionLimitWithTrunks() {
        CoalBurner coalBurner = new CoalBurner();
        TileCompartment tc = new TileCompartment();
        tc.storeResource(ResourceType.TRUNKS, 24);

        while (coalBurner.getProductionLimit() != 0) {
            coalBurner.produce(tc);
        }

        coalBurner.resetProductionLimit();

        while (coalBurner.getProductionLimit() != 0) {
            coalBurner.produce(tc);
        }

        assertEquals(tc.getResourceCount(ResourceType.TRUNKS), 0);
        assertEquals(tc.getResourceCount(ResourceType.FUEL), 12);
        assertEquals(coalBurner.getProductionLimit(), 0);
    }

    @Test
    public void resetProductionLimitWithBoards() {
        CoalBurner coalBurner = new CoalBurner();
        TileCompartment tc = new TileCompartment();
        tc.storeResource(ResourceType.BOARDS, 24);

        while (coalBurner.getProductionLimit() != 0) {
            coalBurner.produce(tc);
        }

        coalBurner.resetProductionLimit();

        while (coalBurner.getProductionLimit() != 0) {
            coalBurner.produce(tc);
        }

        assertEquals(tc.getResourceCount(ResourceType.BOARDS), 0);
        assertEquals(tc.getResourceCount(ResourceType.FUEL), 12);
        assertEquals(coalBurner.getProductionLimit(), 0);
    }

    @Test
    public void resetProductionLimitWithBoth() {
        CoalBurner coalBurner = new CoalBurner();
        TileCompartment tc = new TileCompartment();
        tc.storeResource(ResourceType.TRUNKS, 12);
        tc.storeResource(ResourceType.BOARDS, 12);

        while (coalBurner.getProductionLimit() != 0) {
            coalBurner.produce(tc);
        }

        coalBurner.resetProductionLimit();

        while (coalBurner.getProductionLimit() != 0) {
            coalBurner.produce(tc);
        }

        assertEquals(tc.getResourceCount(ResourceType.TRUNKS), 0);
        assertEquals(tc.getResourceCount(ResourceType.BOARDS), 0);
        assertEquals(tc.getResourceCount(ResourceType.FUEL), 12);
        assertEquals(coalBurner.getProductionLimit(), 0);
    }

}
