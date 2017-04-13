package model.structures.primaryProducer;

import game.model.resources.Clay;
import game.model.structures.primaryProducer.ClayPit;
import org.junit.Test;
import static org.junit.Assert.assertEquals;

public class ClayPitTest {

    @Test
    public void produce() {
        ClayPit clayPit = new ClayPit();
        Integer resourceNum = clayPit.produce().get(new Clay());
        assertEquals(resourceNum, new Integer(1));
    }

}
