package model.structures.primaryProducer;

import game.model.resources.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.primaryProducer.Woodcutter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class WoodcutterTest {

    @Test
    public void produceOnce() {
        Woodcutter woodcutter = new Woodcutter();
        ResourceManager rm = new ResourceManager();
        assertTrue(woodcutter.produce(rm));
        assertEquals(rm.getResourceCount(ResourceType.TRUNKS), 1);
    }

    @Test
    public void produceMany() {
        Woodcutter woodcutter = new Woodcutter();
        ResourceManager rm = new ResourceManager();
        for (int i = 0; i < 3; ++i) {
            woodcutter.produce(rm);
        }
        assertEquals(rm.getResourceCount(ResourceType.TRUNKS), 3);
    }

}
