package model.structures.secondaryProducer;

import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.secondaryProducer.Papermill;
import org.junit.Test;

import static org.junit.Assert.*;

public class PapermillTest {

    @Test
    public void produceOnceWithTrunks() {
        Papermill papermill = new Papermill();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.TRUNKS, 2);
        assertTrue(papermill.produce(rm));
        assertEquals(rm.getResourceCount(ResourceType.PAPER), 1);
    }

    @Test
    public void produceOnceWithBoards() {
        Papermill papermill = new Papermill();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.BOARDS, 2);
        assertTrue(papermill.produce(rm));
        assertEquals(rm.getResourceCount(ResourceType.PAPER), 1);
    }

    @Test
    public void produceOnceWithBoth() {
        Papermill papermill = new Papermill();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.BOARDS, 1);
        rm.addResource(ResourceType.TRUNKS, 1);
        assertTrue(papermill.produce(rm));
        assertEquals(rm.getResourceCount(ResourceType.PAPER), 1);
    }

    @Test
    public void produceNoneWithTrunks() {
        Papermill papermill = new Papermill();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.TRUNKS, 0);
        assertFalse(papermill.produce(rm));
        assertEquals(rm.getResourceCount(ResourceType.PAPER), 0);
    }

    @Test
    public void produceNoneWithBoards() {
        Papermill papermill = new Papermill();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.BOARDS, 0);
        assertFalse(papermill.produce(rm));
        assertEquals(rm.getResourceCount(ResourceType.PAPER), 0);
    }

    @Test
    public void produceNoneWithBoth() {
        Papermill papermill = new Papermill();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.BOARDS, 0);
        rm.addResource(ResourceType.TRUNKS, 0);
        assertFalse(papermill.produce(rm));
        assertEquals(rm.getResourceCount(ResourceType.PAPER), 0);
    }

    @Test
    public void produceManyWithTrunks() {
        Papermill papermill = new Papermill();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.TRUNKS, 6);

        for (int i = 0; i < 3; ++i) {
            papermill.produce(rm);
        }

        assertEquals(rm.getResourceCount(ResourceType.TRUNKS), 0);
        assertEquals(rm.getResourceCount(ResourceType.PAPER), 3);
    }

    @Test
    public void produceManyWithBoards() {
        Papermill papermill = new Papermill();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.BOARDS, 6);

        for (int i = 0; i < 3; ++i) {
            papermill.produce(rm);
        }

        assertEquals(rm.getResourceCount(ResourceType.BOARDS), 0);
        assertEquals(rm.getResourceCount(ResourceType.PAPER), 3);
    }

    @Test
    public void produceManyWithBoth() {
        Papermill papermill = new Papermill();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.TRUNKS, 6);
        rm.addResource(ResourceType.BOARDS, 6);

        for (int i = 0; i < 3; ++i) {
            papermill.produce(rm);
        }

        assertEquals(rm.getResourceCount(ResourceType.TRUNKS), 0);
        assertEquals(rm.getResourceCount(ResourceType.BOARDS), 6);
        assertEquals(rm.getResourceCount(ResourceType.PAPER), 3);
    }

    @Test
    public void produceInvalid() {
        Papermill papermill = new Papermill();
        ResourceManager rm = new ResourceManager();
        rm.addResource(ResourceType.GOOSE, 0);
        assertFalse(papermill.produce(rm));
        assertEquals(rm.getResourceCount(ResourceType.PAPER), 0);
    }

}
