package mapMaker.direction;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TileEdgeDirectionTest {

    @Test
    public void getNorthEastTest() {
        TileEdgeDirection northEast = TileEdgeDirection.getNorthEast();
        assertEquals(northEast.getAngle(), CompassAngles.NORTHEAST.getAngle());
    }

    @Test
    public void getNorthTest() {
        TileEdgeDirection north = TileEdgeDirection.getNorth();
        assertEquals(north.getAngle(), CompassAngles.NORTH.getAngle());
    }

    @Test
    public void getNorthWestTest() {
        TileEdgeDirection northWest = TileEdgeDirection.getNorthWest();
        assertEquals(northWest.getAngle(), CompassAngles.NORTHWEST.getAngle());
    }

    @Test
    public void getSouthWestTest() {
        TileEdgeDirection southWest = TileEdgeDirection.getSouthWest();
        assertEquals(southWest.getAngle(), CompassAngles.SOUTHWEST.getAngle());
    }

    @Test
    public void getSouthTest() {
        TileEdgeDirection south = TileEdgeDirection.getSouth();
        assertEquals(south.getAngle(), CompassAngles.SOUTH.getAngle());
    }

    @Test
    public void getSouthEastTest() {
        TileEdgeDirection southEast = TileEdgeDirection.getSouthEast();
        assertEquals(southEast.getAngle(), CompassAngles.SOUTHEAST.getAngle());
    }
}
