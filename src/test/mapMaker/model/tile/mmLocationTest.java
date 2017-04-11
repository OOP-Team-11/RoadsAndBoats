package mapMaker.model.tile;

import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class mmLocationTest {

    @Test
    public void constructorTest() {
        try {
            mmLocation l = new mmLocation(1, 2, -3);
            assertEquals(l.getX(), 1);
            assertEquals(l.getY(), 2);
            assertEquals(l.getZ(), -3);
        } catch (mmInvalidLocationException e) {
            Assert.fail();
        }
    }

    @Test(expected=mmInvalidLocationException.class)
    public void invalidConstructorTest() throws mmInvalidLocationException {
        mmLocation l = new mmLocation(1, 5, -3);
        assertEquals(l.getX(), 1);
        assertEquals(l.getY(), 2);
        assertEquals(l.getZ(), -3);
    }

    @Test
    public void locationEquals() {
        try {
            mmLocation l1 = new mmLocation(5, -12, 7);
            mmLocation l2 = new mmLocation(5, -12, 7);
            assertTrue(l1.equals(l2));
            assertTrue(l2.equals(l1));
        } catch (mmInvalidLocationException e) {
            Assert.fail();
        }
    }

    @Test
    public void locationNotEquals() {
        try {
            mmLocation l1 = new mmLocation(5, -12, 7);
            mmLocation l2 = new mmLocation(5, -13, 8);
            assertFalse(l1.equals(l2));
            assertFalse(l2.equals(l1));
        } catch (mmInvalidLocationException e) {
            Assert.fail();
        }
    }
}
