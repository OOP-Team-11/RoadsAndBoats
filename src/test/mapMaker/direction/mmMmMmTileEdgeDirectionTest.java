package mapMaker.direction;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class mmMmMmTileEdgeDirectionTest {

    @Test
    public void getNorthEastTest() {
        mmTileEdgeDirection northEast = mmTileEdgeDirection.getNorthEast();
        assertEquals(northEast.getMmAngle(), mmCompassAngles.NORTHEAST.getMmAngle());
    }

    @Test
    public void getNorthTest() {
        mmTileEdgeDirection north = mmTileEdgeDirection.getNorth();
        assertEquals(north.getMmAngle(), mmCompassAngles.NORTH.getMmAngle());
    }

    @Test
    public void getNorthWestTest() {
        mmTileEdgeDirection northWest = mmTileEdgeDirection.getNorthWest();
        assertEquals(northWest.getMmAngle(), mmCompassAngles.NORTHWEST.getMmAngle());
    }

    @Test
    public void getSouthWestTest() {
        mmTileEdgeDirection southWest = mmTileEdgeDirection.getSouthWest();
        assertEquals(southWest.getMmAngle(), mmCompassAngles.SOUTHWEST.getMmAngle());
    }

    @Test
    public void getSouthTest() {
        mmTileEdgeDirection south = mmTileEdgeDirection.getSouth();
        assertEquals(south.getMmAngle(), mmCompassAngles.SOUTH.getMmAngle());
    }

    @Test
    public void getSouthEastTest() {
        mmTileEdgeDirection southEast = mmTileEdgeDirection.getSouthEast();
        assertEquals(southEast.getMmAngle(), mmCompassAngles.SOUTHEAST.getMmAngle());
    }
}
