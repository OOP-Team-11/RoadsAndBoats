package mapMaker.direction;

import mapMaker.model.tile.mmLocation;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import mapMaker.model.tile.mmInvalidLocationException;

import static junit.framework.TestCase.assertTrue;

public class mmDirectionToMmLocationTest {

    private mmLocation startingMmLocation;
    private mmTileEdgeDirection northEast;
    private mmTileEdgeDirection north;
    private mmTileEdgeDirection northWest;
    private mmTileEdgeDirection southWest;
    private mmTileEdgeDirection south;
    private mmTileEdgeDirection southEast;


    @Before
    public void setUp() throws mmInvalidLocationException {
        this.startingMmLocation = new mmLocation(0,0, 0);
        this.northEast = mmTileEdgeDirection.getNorthEast();
        this.north = mmTileEdgeDirection.getNorth();
        this.northWest = mmTileEdgeDirection.getNorthWest();
        this.southWest = mmTileEdgeDirection.getSouthWest();
        this.south = mmTileEdgeDirection.getSouth();
        this.southEast = mmTileEdgeDirection.getSouthEast();
    }

    @Test
    public void northEastTest() {
        try {
            mmLocation destination = mmDirectionToLocation.getLocation(this.startingMmLocation, this.northEast);
            assertTrue(destination.equals(new mmLocation(1, 0, -1)));
        } catch (mmInvalidLocationException e) {
            Assert.fail();
        }
    }

    @Test
    public void northTest() {
        try {
            mmLocation destination = mmDirectionToLocation.getLocation(this.startingMmLocation, this.north);
            assertTrue(destination.equals(new mmLocation(0, 1, -1)));
        } catch (mmInvalidLocationException e) {
            Assert.fail();
        }
    }

    @Test
    public void northWest() {
        try {
            mmLocation destination = mmDirectionToLocation.getLocation(this.startingMmLocation, this.northWest);
            assertTrue(destination.equals(new mmLocation(-1, 1, 0)));
        } catch (mmInvalidLocationException e) {
            Assert.fail();
        }
    }

    @Test
    public void southWest() {
        try {
            mmLocation destination = mmDirectionToLocation.getLocation(this.startingMmLocation, this.southWest);
            assertTrue(destination.equals(new mmLocation(-1, 0, 1)));
        } catch (mmInvalidLocationException e) {
            Assert.fail();
        }
    }

    @Test
    public void south() {
        try {
            mmLocation destination = mmDirectionToLocation.getLocation(this.startingMmLocation, this.south);
            assertTrue(destination.equals(new mmLocation(0, -1, 1)));
        } catch (mmInvalidLocationException e) {
            Assert.fail();
        }
    }

    @Test
    public void southEast() {
        try {
            mmLocation destination = mmDirectionToLocation.getLocation(this.startingMmLocation, this.southEast);
            assertTrue(destination.equals(new mmLocation(1, -1, 0)));
        } catch (mmInvalidLocationException e) {
            Assert.fail();
        }
    }

}
