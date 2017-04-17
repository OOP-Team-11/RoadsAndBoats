//package model.structures.transportProducer;
//
//import game.model.PlayerId;
//import game.model.resources.ResourceType;
//import game.model.structures.transportProducer.TruckFactory;
//import game.model.tile.TileCompartment;
//import game.model.transport.TransportId;
//import game.model.transport.TruckTransport;
//import org.junit.Test;
//
//import static org.junit.Assert.*;
//
//public class TruckFactoryTest {
//
//    private static final int IRON_REQ = 1;
//    private static final int FUEL_REQ = 1;
//
//    @Test
//    public void produceUsingTransport() {
//        TruckFactory truckFactory = new TruckFactory();
//        TruckTransport truck = new TruckTransport(new PlayerId(1), new TransportId());
//        truck.storeResource(ResourceType.IRON, IRON_REQ);
//        truck.storeResource(ResourceType.FUEL, FUEL_REQ);
//        try {
//            TruckTransport truckBaby = (TruckTransport) truckFactory.produce(truck);
//        } catch (ClassCastException c) {
//            fail();
//        }
//        assertEquals(truck.getResourceCount(ResourceType.IRON), 0);
//        assertEquals(truck.getResourceCount(ResourceType.FUEL), 0);
//    }
//
//    @Test
//    public void produceNothingUsingTransport() {
//        TruckFactory truckFactory = new TruckFactory();
//        TruckTransport truck = new TruckTransport(new PlayerId(1), new TransportId());
//        try {
//            TruckTransport truckBaby = (TruckTransport) truckFactory.produce(truck);
//        } catch (ClassCastException c) {
//            assertEquals(truck.getResourceCount(ResourceType.IRON), 0);
//            assertEquals(truck.getResourceCount(ResourceType.FUEL), 0);
//        }
//    }
//
//    @Test
//    public void produceUsingTileCompartment() {
//        TruckFactory truckFactory = new TruckFactory();
//        TileCompartment tileCompartment = new TileCompartment();
//        tileCompartment.storeResource(ResourceType.IRON, IRON_REQ);
//        tileCompartment.storeResource(ResourceType.FUEL, FUEL_REQ);
//        assertTrue(truckFactory.produce(tileCompartment));
//        assertEquals(tileCompartment.getResourceCount(ResourceType.IRON), 0);
//        assertEquals(tileCompartment.getResourceCount(ResourceType.FUEL), 0);
//    }
//
//    @Test
//    public void produceNothingUsingTileCompartment() {
//        TruckFactory truckFactory = new TruckFactory();
//        TileCompartment tileCompartment = new TileCompartment();
//        assertFalse(truckFactory.produce(tileCompartment));
//    }
//
//}
