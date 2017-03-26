package direction;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TileCompartmentDirectionTest {

    @Test
    public void getEastTest() {
        TileCompartmentDirection east = TileCompartmentDirection.getEast();
        assertEquals(east.getAngle(), CompassAngles.EAST.getAngle());
    }

    @Test
    public void getNorthEastTest() {
        TileCompartmentDirection northEast = TileCompartmentDirection.getNorthEast();
        assertEquals(northEast.getAngle(), CompassAngles.NORTHEAST.getAngle());
    }

    @Test
    public void getNorthNorthEastTest() {
        TileCompartmentDirection northNorthEast = TileCompartmentDirection.getNorthNorthEast();
        assertEquals(northNorthEast.getAngle(), CompassAngles.NORTH_NORTHEAST.getAngle());
    }

    @Test
    public void getNorthTest() {
        TileCompartmentDirection north = TileCompartmentDirection.getNorth();
        assertEquals(north.getAngle(), CompassAngles.NORTH.getAngle());
    }

    @Test
    public void getNorthNorthWestTest() {
        TileCompartmentDirection northNorthWest = TileCompartmentDirection.getNorthNorthWest();
        assertEquals(northNorthWest.getAngle(), CompassAngles.NORTH_NORTHWEST.getAngle());
    }

    @Test
    public void getNorthWestTest() {
        TileCompartmentDirection northWest = TileCompartmentDirection.getNorthWest();
        assertEquals(northWest.getAngle(), CompassAngles.NORTHWEST.getAngle());
    }

    @Test
    public void getWestTest() {
        TileCompartmentDirection west = TileCompartmentDirection.getWest();
        assertEquals(west.getAngle(), CompassAngles.WEST.getAngle());
    }

    @Test
    public void getSouthWestTest() {
        TileCompartmentDirection southWest = TileCompartmentDirection.getSouthWest();
        assertEquals(southWest.getAngle(), CompassAngles.SOUTHWEST.getAngle());
    }

    @Test
    public void getSouthSouthWestTest() {
        TileCompartmentDirection southSouthWest = TileCompartmentDirection.getSouthSouthWest();
        assertEquals(southSouthWest.getAngle(), CompassAngles.SOUTH_SOUTHWEST.getAngle());
    }

    @Test
    public void getSouthTest() {
        TileCompartmentDirection south = TileCompartmentDirection.getSouth();
        assertEquals(south.getAngle(), CompassAngles.SOUTH.getAngle());
    }

    @Test
    public void getSouthEastTest() {
        TileCompartmentDirection southEast = TileCompartmentDirection.getSouthEast();
        assertEquals(southEast.getAngle(), CompassAngles.SOUTHEAST.getAngle());
    }

    @Test
    public void getSouthSouthEast() {
        TileCompartmentDirection southSouthEast = TileCompartmentDirection.getSouthSouthEast();
        assertEquals(southSouthEast.getAngle(), CompassAngles.SOUTH_SOUTHEAST.getAngle());
    }
}
