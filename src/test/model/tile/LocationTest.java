package model.tile;

import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class LocationTest {

    @Test
    public void constructorTest() {
        try {
            Location l = new Location(1, 2, -3);
            assertEquals(l.getX(), 1);
            assertEquals(l.getY(), 2);
            assertEquals(l.getZ(), -3);
        } catch (InvalidLocationException e) {
            Assert.fail();
        }
    }

    @Test(expected=InvalidLocationException.class)
    public void invalidConstructorTest() throws InvalidLocationException {
        Location l = new Location(1, 5, -3);
        assertEquals(l.getX(), 1);
        assertEquals(l.getY(), 2);
        assertEquals(l.getZ(), -3);
    }

    @Test
    public void locationEquals() {
        try {
            Location l1 = new Location(5, -12, 7);
            Location l2 = new Location(5, -12, 7);
            assertTrue(l1.equals(l2));
            assertTrue(l2.equals(l1));
        } catch (InvalidLocationException e) {
            Assert.fail();
        }
    }

    @Test
    public void locationNotEquals() {
        try {
            Location l1 = new Location(5, -12, 7);
            Location l2 = new Location(5, -13, 8);
            assertFalse(l1.equals(l2));
            assertFalse(l2.equals(l1));
        } catch (InvalidLocationException e) {
            Assert.fail();
        }
    }
}
