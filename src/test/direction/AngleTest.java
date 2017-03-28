package direction;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class AngleTest {

    @Test
    public void positiveDegreeCreationTest(){
        Angle a = new Angle(123);
        assertEquals(123,a.getDegrees());
    }

    @Test
    public void positiveDegreeOver360CreationTest(){
        Angle a = new Angle(400);               //360 + 40
        assertEquals(40,a.getDegrees());
    }

    @Test
    public void negativeDegreeCreationTest(){
        Angle a = new Angle(-800);  //-720 (two full turns) and another 80 degrees anticlockwise = 280
        assertEquals(280,a.getDegrees());
    }

    @Test
    public void createNegativeAngleDegreeAndEquivalentPositiveAngleDegreeAndCheckEqualityTest(){
        Angle a = new Angle(-800);  //-720 (two full turns) and another 80 degrees anticlockwise = 280
        Angle b = new Angle(280);   //Simple 280 degree angle
        assertTrue(a.equals(b));
    }

    @Test
    public void getAngleTest() {
        Angle a = new Angle(50);
        assertEquals(a.getDegrees(), 50);
    }
}
