package model.structures.secondaryProducer;

import game.model.resources.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.secondaryProducer.CoalBurner;
import org.junit.Test;

import static org.junit.Assert.*;

public class CoalBurnerTest {

    @Test
    public void produceOnceWithTrunks() {
        CoalBurner coalBurner = new CoalBurner();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.TRUNKS, 2);
        assertTrue(coalBurner.produce(rm));
        assertEquals(rm.getResourceCount(ResourceType.FUEL), 1);
    }

    @Test
    public void produceOnceWithBoards() {
        CoalBurner coalBurner = new CoalBurner();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.BOARDS, 2);
        assertTrue(coalBurner.produce(rm));
        assertEquals(rm.getResourceCount(ResourceType.FUEL), 1);
    }

    @Test
    public void produceOnceWithBoth() {
        CoalBurner coalBurner = new CoalBurner();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.BOARDS, 1);
        rm.addResource(ResourceType.TRUNKS, 1);
        assertTrue(coalBurner.produce(rm));
        assertEquals(rm.getResourceCount(ResourceType.FUEL), 1);
    }

    @Test
    public void produceNoneWithTrunks() {
        CoalBurner coalBurner = new CoalBurner();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.TRUNKS, 0);
        assertFalse(coalBurner.produce(rm));
        assertEquals(rm.getResourceCount(ResourceType.FUEL), 0);
    }

    @Test
    public void produceNoneWithBoards() {
        CoalBurner coalBurner = new CoalBurner();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.BOARDS, 0);
        assertFalse(coalBurner.produce(rm));
        assertEquals(rm.getResourceCount(ResourceType.FUEL), 0);
    }

    @Test
    public void produceNoneWithBoth() {
        CoalBurner coalBurner = new CoalBurner();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.BOARDS, 0);
        rm.addResource(ResourceType.TRUNKS, 0);
        assertFalse(coalBurner.produce(rm));
        assertEquals(rm.getResourceCount(ResourceType.FUEL), 0);
    }

    @Test
    public void produceManyWithTrunks() {
        CoalBurner coalBurner = new CoalBurner();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.TRUNKS, 6);

        for (int i = 0; i < 3; ++i) {
            coalBurner.produce(rm);
        }

        assertEquals(rm.getResourceCount(ResourceType.TRUNKS), 0);
        assertEquals(rm.getResourceCount(ResourceType.FUEL), 3);
    }

    @Test
    public void produceManyWithBoards() {
        CoalBurner coalBurner = new CoalBurner();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.BOARDS, 6);

        for (int i = 0; i < 3; ++i) {
            coalBurner.produce(rm);
        }

        assertEquals(rm.getResourceCount(ResourceType.BOARDS), 0);
        assertEquals(rm.getResourceCount(ResourceType.FUEL), 3);
    }

    @Test
    public void produceManyWithBoth() {
        CoalBurner coalBurner = new CoalBurner();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.TRUNKS, 6);
        rm.addResource(ResourceType.BOARDS, 6);

        for (int i = 0; i < 3; ++i) {
            coalBurner.produce(rm);
        }

        assertEquals(rm.getResourceCount(ResourceType.TRUNKS), 0);
        assertEquals(rm.getResourceCount(ResourceType.BOARDS), 6);
        assertEquals(rm.getResourceCount(ResourceType.FUEL), 3);
    }

    @Test
    public void produceToLimitWithTrunks() {
        CoalBurner coalBurner = new CoalBurner();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.TRUNKS, 20);

        while (coalBurner.getProductionLimit() != 0) {
            coalBurner.produce(rm);
        }

        assertEquals(rm.getResourceCount(ResourceType.TRUNKS), 8);
        assertEquals(rm.getResourceCount(ResourceType.FUEL), 6);
        assertEquals(coalBurner.getProductionLimit(), 0);
    }

    @Test
    public void produceToLimitWithBoards() {
        CoalBurner coalBurner = new CoalBurner();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.BOARDS, 20);

        while (coalBurner.getProductionLimit() != 0) {
            coalBurner.produce(rm);
        }

        assertEquals(rm.getResourceCount(ResourceType.BOARDS), 8);
        assertEquals(rm.getResourceCount(ResourceType.FUEL), 6);
        assertEquals(coalBurner.getProductionLimit(), 0);
    }

    @Test
    public void produceToLimitWithBoth() {
        CoalBurner coalBurner = new CoalBurner();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.TRUNKS, 20);
        rm.addResource(ResourceType.BOARDS, 20);

        while (coalBurner.getProductionLimit() != 0) {
            coalBurner.produce(rm);
        }

        assertEquals(rm.getResourceCount(ResourceType.TRUNKS), 8);
        assertEquals(rm.getResourceCount(ResourceType.BOARDS), 20);
        assertEquals(rm.getResourceCount(ResourceType.FUEL), 6);
        assertEquals(coalBurner.getProductionLimit(), 0);
    }

    @Test
    public void resetProductionLimitWithTrunks() {
        CoalBurner coalBurner = new CoalBurner();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.TRUNKS, 24);

        while (coalBurner.getProductionLimit() != 0) {
            coalBurner.produce(rm);
        }

        coalBurner.resetProductionLimit();

        while (coalBurner.getProductionLimit() != 0) {
            coalBurner.produce(rm);
        }

        assertEquals(rm.getResourceCount(ResourceType.TRUNKS), 0);
        assertEquals(rm.getResourceCount(ResourceType.FUEL), 12);
        assertEquals(coalBurner.getProductionLimit(), 0);
    }

    @Test
    public void resetProductionLimitWithBoards() {
        CoalBurner coalBurner = new CoalBurner();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.BOARDS, 24);

        while (coalBurner.getProductionLimit() != 0) {
            coalBurner.produce(rm);
        }

        coalBurner.resetProductionLimit();

        while (coalBurner.getProductionLimit() != 0) {
            coalBurner.produce(rm);
        }

        assertEquals(rm.getResourceCount(ResourceType.BOARDS), 0);
        assertEquals(rm.getResourceCount(ResourceType.FUEL), 12);
        assertEquals(coalBurner.getProductionLimit(), 0);
    }

    @Test
    public void resetProductionLimitWithBoth() {
        CoalBurner coalBurner = new CoalBurner();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.TRUNKS, 12);
        rm.addResource(ResourceType.BOARDS, 12);

        while (coalBurner.getProductionLimit() != 0) {
            coalBurner.produce(rm);
        }

        coalBurner.resetProductionLimit();

        while (coalBurner.getProductionLimit() != 0) {
            coalBurner.produce(rm);
        }

        assertEquals(rm.getResourceCount(ResourceType.TRUNKS), 0);
        assertEquals(rm.getResourceCount(ResourceType.BOARDS), 0);
        assertEquals(rm.getResourceCount(ResourceType.FUEL), 12);
        assertEquals(coalBurner.getProductionLimit(), 0);
    }

    @Test
    public void produceInvalid() {
        CoalBurner coalBurner = new CoalBurner();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.GOOSE, 0);
        assertFalse(coalBurner.produce(rm));
        assertEquals(rm.getResourceCount(ResourceType.FUEL), 0);
    }

}
