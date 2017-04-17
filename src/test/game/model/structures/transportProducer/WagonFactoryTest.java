//package model.structures.transportProducer;
//
//import game.model.PlayerId;
//import game.model.resources.ResourceType;
//import game.model.structures.transportProducer.WagonFactory;
//import game.model.tile.TileCompartment;
//import game.model.transport.DonkeyTransport;
//import game.model.transport.TransportId;
//import game.model.transport.WagonTransport;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//import static org.junit.Assert.assertEquals;
//
//public class WagonFactoryTest {
//
//    private static final int BOARDS_REQ = 2;
//
//    @Test
//    public void produceUsingTransport() {
//        WagonFactory wagonFactory = new WagonFactory();
//        DonkeyTransport donkey = new DonkeyTransport(new PlayerId(1), new TransportId());
//        donkey.storeResource(ResourceType.BOARDS, BOARDS_REQ);
//        try {
//            WagonTransport wagon = (WagonTransport) wagonFactory.produce(donkey);
//        } catch (ClassCastException c) {
//            fail();
//        }
//        //TODO change test to check that TransportManager has the new Wagon by id
//        assertEquals(donkey.getResourceCount(ResourceType.BOARDS), 0);
//    }
//
//    @Test
//    public void produceNothingUsingTransport() {
//        WagonFactory wagonFactory = new WagonFactory();
//        DonkeyTransport donkey = new DonkeyTransport(new PlayerId(1), new TransportId());
//        try {
//            WagonTransport wagon = (WagonTransport) wagonFactory.produce(donkey);
//        } catch (ClassCastException c) {
//            assertEquals(donkey.getResourceCount(ResourceType.BOARDS), 0);
//        }
//    }
//
//    @Test
//    public void produceUsingTileCompartment() {
//        WagonFactory wagonFactory = new WagonFactory();
//        TileCompartment tileCompartment = new TileCompartment();
//        tileCompartment.storeResource(ResourceType.BOARDS, BOARDS_REQ);
//        assertTrue(wagonFactory.produce(tileCompartment));
//        assertEquals(tileCompartment.getResourceCount(ResourceType.BOARDS), 0);
//    }
//
//    @Test
//    public void produceNothingUsingTileCompartment() {
//        WagonFactory wagonFactory = new WagonFactory();
//        TileCompartment tileCompartment = new TileCompartment();
//        assertFalse(wagonFactory.produce(tileCompartment));
//        assertEquals(tileCompartment.getResourceCount(ResourceType.BOARDS), 0);
//    }
//
//}
