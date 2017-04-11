package mapMaker.model.tile.riverConfiguration;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class mmRiverConfigurationTest {

    @Test
    public void noRivers() {
        mmRiverConfiguration riverConfig = mmRiverConfiguration.getNoRivers();
        assertFalse(riverConfig.canConnectNorth());
        assertFalse(riverConfig.canConnectNortheast());
        assertFalse(riverConfig.canConnectNorthwest());
        assertFalse(riverConfig.canConnectSouth());
        assertFalse(riverConfig.canConnectSoutheast());
        assertFalse(riverConfig.canConnectSouthwest());
    }

    @Test
    public void springHead() {
        mmRiverConfiguration riverConfig = mmRiverConfiguration.getSpringHead();
        assertTrue(riverConfig.canConnectNorth());
        assertFalse(riverConfig.canConnectNortheast());
        assertFalse(riverConfig.canConnectNorthwest());
        assertFalse(riverConfig.canConnectSouth());
        assertFalse(riverConfig.canConnectSoutheast());
        assertFalse(riverConfig.canConnectSouthwest());
    }

    @Test
    public void adjacentFaces() {
        mmRiverConfiguration riverConfig = mmRiverConfiguration.getAdjacentFaces();
        assertTrue(riverConfig.canConnectNorth());
        assertTrue(riverConfig.canConnectNortheast());
        assertFalse(riverConfig.canConnectNorthwest());
        assertFalse(riverConfig.canConnectSouth());
        assertFalse(riverConfig.canConnectSoutheast());
        assertFalse(riverConfig.canConnectSouthwest());
    }

    @Test
    public void skipAFace() {
        mmRiverConfiguration riverConfig = mmRiverConfiguration.getSkipAFace();
        assertTrue(riverConfig.canConnectNorth());
        assertFalse(riverConfig.canConnectNortheast());
        assertTrue(riverConfig.canConnectSoutheast());
        assertFalse(riverConfig.canConnectSouth());
        assertFalse(riverConfig.canConnectSouthwest());
        assertFalse(riverConfig.canConnectNorthwest());
    }

    @Test
    public void oppositeFaces() {
        mmRiverConfiguration riverConfig = mmRiverConfiguration.getOppositeFaces();
        assertTrue(riverConfig.canConnectNorth());
        assertFalse(riverConfig.canConnectNortheast());
        assertFalse(riverConfig.canConnectNorthwest());
        assertTrue(riverConfig.canConnectSouth());
        assertFalse(riverConfig.canConnectSoutheast());
        assertFalse(riverConfig.canConnectSouthwest());
    }

    @Test
    public void everyOtherFace() {
        mmRiverConfiguration riverConfig = mmRiverConfiguration.getEveryOtherFace();
        assertTrue(riverConfig.canConnectNorth());
        assertFalse(riverConfig.canConnectNortheast());
        assertTrue(riverConfig.canConnectSoutheast());
        assertFalse(riverConfig.canConnectSouth());
        assertTrue(riverConfig.canConnectSouthwest());
        assertFalse(riverConfig.canConnectNorthwest());
    }

    @Test
    public void oneSidedNoRotation() {
        mmRiverConfiguration mmRiverConfiguration = new mmRiverConfiguration(1);
        assertEquals(mmRiverConfiguration.getRotationAmount(), 0);
    }

    @Test
    public void oneSidedNormalRotation() {
        mmRiverConfiguration mmRiverConfiguration = new mmRiverConfiguration(3);
        assertEquals(mmRiverConfiguration.getRotationAmount(), 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void oneSideIllegalArgumentOverSix() {
        mmRiverConfiguration mmRiverConfiguration = new mmRiverConfiguration(7);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void oneSideIllegalArgumentLessThanOne() {
        mmRiverConfiguration mmRiverConfiguration = new mmRiverConfiguration(0);
        Assert.fail();
    }

    @Test
    public void twoSidedNoRotation() {
        mmRiverConfiguration mmRiverConfiguration = new mmRiverConfiguration(1, 2);
        assertEquals(mmRiverConfiguration.getRotationAmount(), 0);
    }

    @Test
    public void twoSidedSkipNoRotation() {
        mmRiverConfiguration mmRiverConfiguration = new mmRiverConfiguration(1, 3);
        assertEquals(mmRiverConfiguration.getRotationAmount(), 0);
    }

    @Test
    public void twoSidedOppositeNoRotation() {
        mmRiverConfiguration mmRiverConfiguration = new mmRiverConfiguration(1, 4);
        assertEquals(mmRiverConfiguration.getRotationAmount(), 0);
    }

    @Test
    public void twoSidedNormalRotation() {
        mmRiverConfiguration mmRiverConfiguration = new mmRiverConfiguration(5, 6);
        assertEquals(mmRiverConfiguration.getRotationAmount(), 4);
    }

    @Test
    public void twoSidedSkipRotation() {
        mmRiverConfiguration mmRiverConfiguration = new mmRiverConfiguration(4, 6);
        assertEquals(mmRiverConfiguration.getRotationAmount(), 3);
    }

    @Test
    public void twoSidedOppositeRotation() {
        mmRiverConfiguration mmRiverConfiguration = new mmRiverConfiguration(3, 6);
        assertEquals(mmRiverConfiguration.getRotationAmount(), 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void twoSidedIllegalArgument() {
        mmRiverConfiguration mmRiverConfiguration = new mmRiverConfiguration(6, 2);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void twoSidedIllegalArgumentSide1() {
        mmRiverConfiguration mmRiverConfiguration = new mmRiverConfiguration(7, 8);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void twoSidedIllegalArgumentSide2() {
        mmRiverConfiguration mmRiverConfiguration = new mmRiverConfiguration(5, 7);
        Assert.fail();
    }

    @Test
    public void threeSidedNoRotation() {
        mmRiverConfiguration mmRiverConfiguration = new mmRiverConfiguration(1, 3, 5);
        assertEquals(mmRiverConfiguration.getRotationAmount(), 0);
    }

    @Test
    public void threeSidedOneRotation() {
        mmRiverConfiguration mmRiverConfiguration = new mmRiverConfiguration(2, 4, 6);
        assertEquals(mmRiverConfiguration.getRotationAmount(), 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void threeSidedSide1Larger() {
        mmRiverConfiguration mmRiverConfiguration = new mmRiverConfiguration(2, 1, 3);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void threeSidedSide2Larger() {
        mmRiverConfiguration mmRiverConfiguration = new mmRiverConfiguration(1, 3, 2);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void threeSidedSide1OutOfRangeUpper() {
        mmRiverConfiguration mmRiverConfiguration = new mmRiverConfiguration(7, 3, 2);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void threeSidedSide1OutOfRangeLower() {
        mmRiverConfiguration mmRiverConfiguration = new mmRiverConfiguration(0, 3, 2);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void threeSidedSide2OutOfRangeUpper() {
        mmRiverConfiguration mmRiverConfiguration = new mmRiverConfiguration(1, 7, 2);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void threeSidedSide2OutOfRangeLower() {
        mmRiverConfiguration mmRiverConfiguration = new mmRiverConfiguration(1, 0, 2);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void threeSidedSide3OutOfRangeUpper() {
        mmRiverConfiguration mmRiverConfiguration = new mmRiverConfiguration(1, 3, 7);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void threeSidedSide3OutOfRangeLower() {
        mmRiverConfiguration mmRiverConfiguration = new mmRiverConfiguration(1, 3, 0);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void threeSidedDiffTwo() {
        mmRiverConfiguration mmRiverConfiguration = new mmRiverConfiguration(1, 3, 6);
        Assert.fail();
    }

    @Test(expected = IllegalArgumentException.class)
    public void threeSidedDiffTwoAlso() {
        mmRiverConfiguration mmRiverConfiguration = new mmRiverConfiguration(1, 2, 4);
        Assert.fail();
    }
}
