package mapMaker.model.tile.riverConfiguration;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RiverConfigurationTest {

    @Test
    public void noRivers() {
        RiverConfiguration riverConfiguration = RiverConfiguration.getNoRivers();
        assertFalse(riverConfiguration.canConnectNorth());
        assertFalse(riverConfiguration.canConnectNortheast());
        assertFalse(riverConfiguration.canConnectNorthwest());
        assertFalse(riverConfiguration.canConnectSouth());
        assertFalse(riverConfiguration.canConnectSoutheast());
        assertFalse(riverConfiguration.canConnectSouthwest());
    }

    @Test
    public void springHead() {
        RiverConfiguration riverConfiguration = RiverConfiguration.getSpringHead();
        assertTrue(riverConfiguration.canConnectNorth());
        assertFalse(riverConfiguration.canConnectNortheast());
        assertFalse(riverConfiguration.canConnectNorthwest());
        assertFalse(riverConfiguration.canConnectSouth());
        assertFalse(riverConfiguration.canConnectSoutheast());
        assertFalse(riverConfiguration.canConnectSouthwest());
    }

    @Test
    public void adjacentFaces() {
        RiverConfiguration riverConfiguration = RiverConfiguration.getAdjacentFaces();
        assertTrue(riverConfiguration.canConnectNorth());
        assertTrue(riverConfiguration.canConnectNortheast());
        assertFalse(riverConfiguration.canConnectNorthwest());
        assertFalse(riverConfiguration.canConnectSouth());
        assertFalse(riverConfiguration.canConnectSoutheast());
        assertFalse(riverConfiguration.canConnectSouthwest());
    }

    @Test
    public void skipAFace() {
        RiverConfiguration riverConfiguration = RiverConfiguration.getSkipAFace();
        assertTrue(riverConfiguration.canConnectNorth());
        assertFalse(riverConfiguration.canConnectNortheast());
        assertTrue(riverConfiguration.canConnectSoutheast());
        assertFalse(riverConfiguration.canConnectSouth());
        assertFalse(riverConfiguration.canConnectSouthwest());
        assertFalse(riverConfiguration.canConnectNorthwest());
    }

    @Test
    public void oppositeFaces() {
        RiverConfiguration riverConfiguration = RiverConfiguration.getOppositeFaces();
        assertTrue(riverConfiguration.canConnectNorth());
        assertFalse(riverConfiguration.canConnectNortheast());
        assertFalse(riverConfiguration.canConnectNorthwest());
        assertTrue(riverConfiguration.canConnectSouth());
        assertFalse(riverConfiguration.canConnectSoutheast());
        assertFalse(riverConfiguration.canConnectSouthwest());
    }

    @Test
    public void everyOtherFace() {
        RiverConfiguration riverConfiguration = RiverConfiguration.getEveryOtherFace();
        assertTrue(riverConfiguration.canConnectNorth());
        assertFalse(riverConfiguration.canConnectNortheast());
        assertTrue(riverConfiguration.canConnectSoutheast());
        assertFalse(riverConfiguration.canConnectSouth());
        assertTrue(riverConfiguration.canConnectSouthwest());
        assertFalse(riverConfiguration.canConnectNorthwest());
    }

    @Test
    public void oneSidedNoRotation() {
        RiverConfiguration riverConfiguration = new RiverConfiguration(1);
        assertEquals(riverConfiguration.getRotationAmount(), 0);
    }

    @Test
    public void oneSidedNormalRotation() {
        RiverConfiguration riverConfiguration = new RiverConfiguration(3);
        assertEquals(riverConfiguration.getRotationAmount(), 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void oneSideIllegalArgumentOverSix() {
        RiverConfiguration riverConfiguration = new RiverConfiguration(7);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void oneSideIllegalArgumentLessThanOne() {
        RiverConfiguration riverConfiguration = new RiverConfiguration(0);
        Assert.fail();
    }

    @Test
    public void twoSidedNoRotation() {
        RiverConfiguration riverConfiguration = new RiverConfiguration(1, 2);
        assertEquals(riverConfiguration.getRotationAmount(), 0);
    }

    @Test
    public void twoSidedSkipNoRotation() {
        RiverConfiguration riverConfiguration = new RiverConfiguration(1, 3);
        assertEquals(riverConfiguration.getRotationAmount(), 0);
    }

    @Test
    public void twoSidedOppositeNoRotation() {
        RiverConfiguration riverConfiguration = new RiverConfiguration(1, 4);
        assertEquals(riverConfiguration.getRotationAmount(), 0);
    }

    @Test
    public void twoSidedNormalRotation() {
        RiverConfiguration riverConfiguration = new RiverConfiguration(5, 6);
        assertEquals(riverConfiguration.getRotationAmount(), 4);
    }

    @Test
    public void twoSidedSkipRotation() {
        RiverConfiguration riverConfiguration = new RiverConfiguration(4, 6);
        assertEquals(riverConfiguration.getRotationAmount(), 3);
    }

    @Test
    public void twoSidedOppositeRotation() {
        RiverConfiguration riverConfiguration = new RiverConfiguration(3, 6);
        assertEquals(riverConfiguration.getRotationAmount(), 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void twoSidedIllegalArgument() {
        RiverConfiguration riverConfiguration = new RiverConfiguration(6, 2);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void twoSidedIllegalArgumentSide1() {
        RiverConfiguration riverConfiguration = new RiverConfiguration(7, 8);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void twoSidedIllegalArgumentSide2() {
        RiverConfiguration riverConfiguration = new RiverConfiguration(5, 7);
        Assert.fail();
    }

    @Test
    public void threeSidedNoRotation() {
        RiverConfiguration riverConfiguration = new RiverConfiguration(1, 3, 5);
        assertEquals(riverConfiguration.getRotationAmount(), 0);
    }

    @Test
    public void threeSidedOneRotation() {
        RiverConfiguration riverConfiguration = new RiverConfiguration(2, 4, 6);
        assertEquals(riverConfiguration.getRotationAmount(), 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void threeSidedSide1Larger() {
        RiverConfiguration riverConfiguration = new RiverConfiguration(2, 1, 3);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void threeSidedSide2Larger() {
        RiverConfiguration riverConfiguration = new RiverConfiguration(1, 3, 2);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void threeSidedSide1OutOfRangeUpper() {
        RiverConfiguration riverConfiguration = new RiverConfiguration(7, 3, 2);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void threeSidedSide1OutOfRangeLower() {
        RiverConfiguration riverConfiguration = new RiverConfiguration(0, 3, 2);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void threeSidedSide2OutOfRangeUpper() {
        RiverConfiguration riverConfiguration = new RiverConfiguration(1, 7, 2);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void threeSidedSide2OutOfRangeLower() {
        RiverConfiguration riverConfiguration = new RiverConfiguration(1, 0, 2);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void threeSidedSide3OutOfRangeUpper() {
        RiverConfiguration riverConfiguration = new RiverConfiguration(1, 3, 7);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void threeSidedSide3OutOfRangeLower() {
        RiverConfiguration riverConfiguration = new RiverConfiguration(1, 3, 0);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void threeSidedDiffTwo() {
        RiverConfiguration riverConfiguration = new RiverConfiguration(1, 3, 6);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void threeSidedDiffTwoAlso() {
        RiverConfiguration riverConfiguration = new RiverConfiguration(1, 2, 4);
        Assert.fail();
    }
}
