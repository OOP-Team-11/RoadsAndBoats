package tile;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class LocationTest {

    @Test
    public void constructorTest() {
        Location l = new Location(1,2,3);
        assertEquals(l.getX(), 1);
        assertEquals(l.getY(), 2);
        assertEquals(l.getZ(), 3);
    }

    @Test
    public void locationEquals() {
        Location l1 = new Location(5,6,7);
        Location l2 = new Location(5,6,7);
        assertTrue(l1.equals(l2));
        assertTrue(l2.equals(l1));
    }

    @Test
    public void locationNotEquals() {
        Location l1 = new Location(5,6,7);
        Location l2 = new Location(5,6,8);
        assertFalse(l1.equals(l2));
        assertFalse(l2.equals(l1));
    }
}
