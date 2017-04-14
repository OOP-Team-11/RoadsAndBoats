package model.structures.primaryProducer;

import game.model.resources.ResourceType;
import game.model.structures.primaryProducer.Woodcutter;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class WoodcutterTest {

    @Test
    public void produce() {
        Woodcutter woodcutter = new Woodcutter();
        Integer resourceNum = woodcutter.produce().get(ResourceType.TRUNKS);
        assertEquals(resourceNum, new Integer(1));
    }

}
