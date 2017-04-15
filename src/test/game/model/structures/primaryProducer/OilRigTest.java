//<<<<<<< HEAD
//package model.structures.primaryProducer;
//
//import game.model.resources.*;
//import game.model.structures.resourceProducer.primaryProducer.OilRig;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
//public class OilRigTest {
//
//    @Test
//    public void produce() {
//        ResourceManager rm = new ResourceManager();
//        OilRig oilRig = new OilRig(rm);
//        oilRig.produce();
//        Integer resourceNum = rm.getResource(ResourceType.FUEL);
//        assertEquals(resourceNum, new Integer(1));
//    }
//
//    @Test
//    public void storeResource() {
//        ResourceManager rm = new ResourceManager();
//        OilRig oilRig = new OilRig(rm);
//        oilRig.storeResource(ResourceType.GOOSE, 5);
//        Integer resourceNum = rm.getResource(ResourceType.GOOSE);
//        assertEquals(resourceNum, new Integer(5));
//    }
//
//    @Test
//    public void removeResource() {
//        ResourceManager rm = new ResourceManager();
//        OilRig oilRig = new OilRig(rm);
//        oilRig.storeResource(ResourceType.GOOSE, 5);
//        oilRig.takeResource(ResourceType.GOOSE, 3);
//        Integer resourceNum = rm.getResource(ResourceType.GOOSE);
//        assertEquals(resourceNum, new Integer(2));
//    }
//
//    @Test
//    public void storeProduceResource() {
//        ResourceManager rm = new ResourceManager();
//        OilRig oilRig = new OilRig(rm);
//        oilRig.produce();
//        oilRig.storeResource(ResourceType.GOOSE, 5);
//        assertEquals(rm.getResource(ResourceType.FUEL), new Integer(1));
//        assertEquals(rm.getResource(ResourceType.GOOSE), new Integer(5));
//    }
//
//    @Test
//    public void takeProduceResource() {
//        ResourceManager rm = new ResourceManager();
//        OilRig oilRig = new OilRig(rm);
//        oilRig.produce();
//        oilRig.takeResource(ResourceType.FUEL, 1);
//        assertEquals(rm.getResource(ResourceType.FUEL), new Integer(0));
//    }
//
//    @Test
//    public void storeAllResources() {
//        ResourceManager rm = new ResourceManager();
//        OilRig oilRig = new OilRig(rm);
//        oilRig.storeResource(ResourceType.BOARDS, 1);
//        oilRig.storeResource(ResourceType.CLAY, 2);
//        oilRig.storeResource(ResourceType.COINS, 3);
//        oilRig.storeResource(ResourceType.FUEL, 4);
//        oilRig.storeResource(ResourceType.GOLD, 5);
//        oilRig.storeResource(ResourceType.GOOSE, 6);
//        oilRig.storeResource(ResourceType.IRON, 7);
//        oilRig.storeResource(ResourceType.PAPER, 8);
//        oilRig.storeResource(ResourceType.STOCKBOND, 9);
//        oilRig.storeResource(ResourceType.STONE, 10);
//        oilRig.storeResource(ResourceType.TRUNKS, 11);
//        assertEquals(rm.getResource(ResourceType.BOARDS), new Integer(1));
//        assertEquals(rm.getResource(ResourceType.CLAY), new Integer(2));
//        assertEquals(rm.getResource(ResourceType.COINS), new Integer(3));
//        assertEquals(rm.getResource(ResourceType.FUEL), new Integer(4));
//        assertEquals(rm.getResource(ResourceType.GOLD), new Integer(5));
//        assertEquals(rm.getResource(ResourceType.GOOSE), new Integer(6));
//        assertEquals(rm.getResource(ResourceType.IRON), new Integer(7));
//        assertEquals(rm.getResource(ResourceType.PAPER), new Integer(8));
//        assertEquals(rm.getResource(ResourceType.STOCKBOND), new Integer(9));
//        assertEquals(rm.getResource(ResourceType.STONE), new Integer(10));
//        assertEquals(rm.getResource(ResourceType.TRUNKS), new Integer(11));
//    }
//
//}
//=======
//package game.model.structures.primaryProducer;
//
//import game.model.resources.*;
//import game.model.structures.primaryProducer.OilRig;
//import org.apache.logging.log4j.core.pattern.FullLocationPatternConverter;
//import org.junit.Test;
//
//import static org.junit.Assert.assertEquals;
//
//public class OilRigTest {
//
//    @Test
//    public void produce() {
//        ResourceManager rm = new ResourceManager();
//        OilRig oilRig = new OilRig(rm);
//        oilRig.produce();
//        Integer resourceNum = rm.getResourceCount(ResourceType.FUEL);
//        assertEquals(resourceNum, new Integer(1));
//    }
//
//    @Test
//    public void storeResource() {
//        ResourceManager rm = new ResourceManager();
//        OilRig oilRig = new OilRig(rm);
//        oilRig.storeResource(ResourceType.GOOSE, 5);
//        Integer resourceNum = rm.getResourceCount(ResourceType.GOOSE);
//        assertEquals(resourceNum, new Integer(5));
//    }
//
//    @Test
//    public void removeResource() {
//        ResourceManager rm = new ResourceManager();
//        OilRig oilRig = new OilRig(rm);
//        oilRig.storeResource(ResourceType.GOOSE, 5);
//        oilRig.takeResource(ResourceType.GOOSE, 3);
//        Integer resourceNum = rm.getResourceCount(ResourceType.GOOSE);
//        assertEquals(resourceNum, new Integer(2));
//    }
//
//    @Test
//    public void storeProduceResource() {
//        ResourceManager rm = new ResourceManager();
//        OilRig oilRig = new OilRig(rm);
//        oilRig.produce();
//        oilRig.storeResource(ResourceType.GOOSE, 5);
//        assertEquals(rm.getResourceCount(ResourceType.FUEL), 1);
//        assertEquals(rm.getResourceCount(ResourceType.GOOSE), 5);
//    }
//
//    @Test
//    public void takeProduceResource() {
//        ResourceManager rm = new ResourceManager();
//        OilRig oilRig = new OilRig(rm);
//        oilRig.produce();
//        oilRig.takeResource(ResourceType.FUEL, 1);
//        assertEquals(rm.getResourceCount(ResourceType.FUEL), 0);
//    }
//
//    @Test
//    public void storeAllResources() {
//        ResourceManager rm = new ResourceManager();
//        OilRig oilRig = new OilRig(rm);
//        oilRig.storeResource(ResourceType.BOARDS, 1);
//        oilRig.storeResource(ResourceType.CLAY, 2);
//        oilRig.storeResource(ResourceType.COINS, 3);
//        oilRig.storeResource(ResourceType.FUEL, 4);
//        oilRig.storeResource(ResourceType.GOLD, 5);
//        oilRig.storeResource(ResourceType.GOOSE, 6);
//        oilRig.storeResource(ResourceType.IRON, 7);
//        oilRig.storeResource(ResourceType.PAPER, 8);
//        oilRig.storeResource(ResourceType.STOCKBOND, 9);
//        oilRig.storeResource(ResourceType.STONE, 10);
//        oilRig.storeResource(ResourceType.TRUNKS, 11);
//        assertEquals(rm.getResourceCount(ResourceType.BOARDS), 1);
//        assertEquals(rm.getResourceCount(ResourceType.CLAY), 2);
//        assertEquals(rm.getResourceCount(ResourceType.COINS), 3);
//        assertEquals(rm.getResourceCount(ResourceType.FUEL), 4);
//        assertEquals(rm.getResourceCount(ResourceType.GOLD), 5);
//        assertEquals(rm.getResourceCount(ResourceType.GOOSE), 6);
//        assertEquals(rm.getResourceCount(ResourceType.IRON), 7);
//        assertEquals(rm.getResourceCount(ResourceType.PAPER), 8);
//        assertEquals(rm.getResourceCount(ResourceType.STOCKBOND), 9);
//        assertEquals(rm.getResourceCount(ResourceType.STONE), 10);
//        assertEquals(rm.getResourceCount(ResourceType.TRUNKS),11);
//    }
//
//}
//>>>>>>> 6570e63fb09f0104a2a54ed1ac2a62100f80e928
