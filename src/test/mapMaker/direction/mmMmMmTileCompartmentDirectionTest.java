package mapMaker.direction;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class mmMmMmTileCompartmentDirectionTest {

    @Test
    public void getEastTest() {
        mmTileCompartmentDirection east = mmTileCompartmentDirection.getEast();
        assertEquals(east.getMmAngle(), mmCompassAngles.EAST.getMmAngle());
    }

    @Test
    public void getNorthEastTest() {
        mmTileCompartmentDirection northEast = mmTileCompartmentDirection.getNorthEast();
        assertEquals(northEast.getMmAngle(), mmCompassAngles.NORTHEAST.getMmAngle());
    }

    @Test
    public void getNorthNorthEastTest() {
        mmTileCompartmentDirection northNorthEast = mmTileCompartmentDirection.getNorthNorthEast();
        assertEquals(northNorthEast.getMmAngle(), mmCompassAngles.NORTH_NORTHEAST.getMmAngle());
    }

    @Test
    public void getNorthTest() {
        mmTileCompartmentDirection north = mmTileCompartmentDirection.getNorth();
        assertEquals(north.getMmAngle(), mmCompassAngles.NORTH.getMmAngle());
    }

    @Test
    public void getNorthNorthWestTest() {
        mmTileCompartmentDirection northNorthWest = mmTileCompartmentDirection.getNorthNorthWest();
        assertEquals(northNorthWest.getMmAngle(), mmCompassAngles.NORTH_NORTHWEST.getMmAngle());
    }

    @Test
    public void getNorthWestTest() {
        mmTileCompartmentDirection northWest = mmTileCompartmentDirection.getNorthWest();
        assertEquals(northWest.getMmAngle(), mmCompassAngles.NORTHWEST.getMmAngle());
    }

    @Test
    public void getWestTest() {
        mmTileCompartmentDirection west = mmTileCompartmentDirection.getWest();
        assertEquals(west.getMmAngle(), mmCompassAngles.WEST.getMmAngle());
    }

    @Test
    public void getSouthWestTest() {
        mmTileCompartmentDirection southWest = mmTileCompartmentDirection.getSouthWest();
        assertEquals(southWest.getMmAngle(), mmCompassAngles.SOUTHWEST.getMmAngle());
    }

    @Test
    public void getSouthSouthWestTest() {
        mmTileCompartmentDirection southSouthWest = mmTileCompartmentDirection.getSouthSouthWest();
        assertEquals(southSouthWest.getMmAngle(), mmCompassAngles.SOUTH_SOUTHWEST.getMmAngle());
    }

    @Test
    public void getSouthTest() {
        mmTileCompartmentDirection south = mmTileCompartmentDirection.getSouth();
        assertEquals(south.getMmAngle(), mmCompassAngles.SOUTH.getMmAngle());
    }

    @Test
    public void getSouthEastTest() {
        mmTileCompartmentDirection southEast = mmTileCompartmentDirection.getSouthEast();
        assertEquals(southEast.getMmAngle(), mmCompassAngles.SOUTHEAST.getMmAngle());
    }

    @Test
    public void getSouthSouthEast() {
        mmTileCompartmentDirection southSouthEast = mmTileCompartmentDirection.getSouthSouthEast();
        assertEquals(southSouthEast.getMmAngle(), mmCompassAngles.SOUTH_SOUTHEAST.getMmAngle());
    }
}
