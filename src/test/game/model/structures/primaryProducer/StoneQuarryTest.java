package game.model.structures.primaryProducer;

import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.primaryProducer.StoneQuarry;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StoneQuarryTest {

    @Test
    public void produceOnce() {
        StoneQuarry stoneQuarry = new StoneQuarry();
        ResourceManager rm = new ResourceManager();
        assertTrue(stoneQuarry.produce(rm));
        assertEquals(rm.getResourceCount(ResourceType.STONE), 1);
    }

    @Test
    public void produceMany() {
        StoneQuarry stoneQuarry = new StoneQuarry();
        ResourceManager rm = new ResourceManager();
        for (int i = 0; i < 3; ++i) {
            stoneQuarry.produce(rm);
        }
        assertEquals(rm.getResourceCount(ResourceType.STONE), 3);
    }

}
