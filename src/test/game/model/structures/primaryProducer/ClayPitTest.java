package game.model.structures.primaryProducer;

import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.primaryProducer.ClayPit;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ClayPitTest {

    @Test
    public void produceOnce() {
        ClayPit clayPit = new ClayPit();
        ResourceManager rm = new ResourceManager();
        assertTrue(clayPit.produce(rm));
        assertEquals(rm.getResourceCount(ResourceType.CLAY), 1);
    }

    @Test
    public void produceMany() {
        ClayPit clayPit = new ClayPit();
        ResourceManager rm = new ResourceManager();
        for (int i = 0; i < 3; ++i) {
            clayPit.produce(rm);
        }
        assertEquals(rm.getResourceCount(ResourceType.CLAY), 3);
    }

}
