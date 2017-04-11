package mapMaker.direction;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class mmAngleTest {

    @Test
    public void positiveDegreeCreationTest(){
        mmAngle a = new mmAngle(123);
        assertEquals(123,a.getDegrees());
    }

    @Test
    public void positiveDegreeOver360CreationTest(){
        mmAngle a = new mmAngle(400);               //360 + 40
        assertEquals(40,a.getDegrees());
    }

    @Test
    public void negativeDegreeCreationTest(){
        mmAngle a = new mmAngle(-800);  //-720 (two full turns) and another 80 degrees anticlockwise = 280
        assertEquals(280,a.getDegrees());
    }

    @Test
    public void zeroAngleShouldHaveDegreeValueZeroTest(){
        mmAngle a = new mmAngle(0);  //-720 (two full turns) and another 80 degrees anticlockwise = 280
        assertEquals(0,a.getDegrees());
    }

    @Test
    public void threeSixtyAngleShouldHaveDegreeValueZeroTest(){
        mmAngle a = new mmAngle(360);  //-720 (two full turns) and another 80 degrees anticlockwise = 280
        assertEquals(0,a.getDegrees());
    }

    @Test
    public void negativeThreeSixtyAngleShouldHaveDegreeValueZeroTest(){
        mmAngle a = new mmAngle(-360);  //-720 (two full turns) and another 80 degrees anticlockwise = 280
        assertEquals(0,a.getDegrees());
    }

    @Test
    public void createNegativeAngleDegreeAndEquivalentPositiveAngleDegreeAndCheckEqualityTest(){
        mmAngle a = new mmAngle(-800);  //-720 (two full turns) and another 80 degrees anticlockwise = 280
        mmAngle b = new mmAngle(280);   //Simple 280 degree angle
        assertTrue(a.equals(b));
    }

    @Test
    public void getAngleTest() {
        mmAngle a = new mmAngle(50);
        assertEquals(a.getDegrees(), 50);
    }
}
