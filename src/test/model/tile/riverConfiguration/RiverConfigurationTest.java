package model.tile.riverConfiguration;

import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class RiverConfigurationTest {

    @Test
    public void noRivers() {
        RiverConfiguration riverConfiguration = RiverConfiguration.getSpringHead();
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
        assertTrue(riverConfiguration.canConnectNorthwest());
        assertFalse(riverConfiguration.canConnectSouth());
        assertFalse(riverConfiguration.canConnectSoutheast());
        assertFalse(riverConfiguration.canConnectSouthwest());
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
        assertTrue(riverConfiguration.canConnectNorthwest());
        assertFalse(riverConfiguration.canConnectSouth());
        assertTrue(riverConfiguration.canConnectSoutheast());
        assertFalse(riverConfiguration.canConnectSouthwest());
    }
}
