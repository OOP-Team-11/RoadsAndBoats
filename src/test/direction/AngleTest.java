package direction;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AngleTest {

    @Test
    public void constructorInRange() {
        try {
            new Angle(0);
            new Angle(163);
            new Angle(360);
        } catch (AngleValueOutOfRangeException e) {
            Assert.fail();
        }
    }

    @Test(expected=AngleValueOutOfRangeException.class)
    public void constructorOutOfUpperRange() throws AngleValueOutOfRangeException {
        new Angle(361);
    }

    @Test(expected=AngleValueOutOfRangeException.class)
    public void constructorOutOfLowerRange() throws AngleValueOutOfRangeException {
        new Angle(-1);
    }

    @Test
    public void getAngleTest() {
        try {
            Angle a = new Angle(50);
            assertEquals(a.getDegrees(), 50);
        } catch (AngleValueOutOfRangeException e) {
            Assert.fail();
        }
    }
}
