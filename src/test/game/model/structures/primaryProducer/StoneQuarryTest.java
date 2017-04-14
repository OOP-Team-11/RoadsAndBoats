package model.structures.primaryProducer;

import game.model.resources.ResourceType;
import game.model.structures.primaryProducer.StoneQuarry;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class StoneQuarryTest {

    @Test
    public void produce() {
        StoneQuarry stoneQuarry = new StoneQuarry();
        Integer resourceNum = stoneQuarry.produce().get(ResourceType.STONE);
        assertEquals(resourceNum, new Integer(1));
    }

}
