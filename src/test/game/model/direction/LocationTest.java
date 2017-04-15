package game.model.direction;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class LocationTest {

    @Test
    public void equalLocations() {
        Location loc1 = new Location(0,0,0);
        Location loc2 = new Location(0,0,0);
        assertTrue(loc1.equals(loc2));
        assertEquals(loc1.hashCode(),loc2.hashCode());
    }
}
