package direction;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import model.tile.InvalidLocationException;
import model.tile.Location;

import static junit.framework.TestCase.assertTrue;

public class DirectionToLocationTest {

    private Location startingLocation;
    private TileEdgeDirection northEast;
    private TileEdgeDirection north;
    private TileEdgeDirection northWest;
    private TileEdgeDirection southWest;
    private TileEdgeDirection south;
    private TileEdgeDirection southEast;


    @Before
    public void setUp() throws InvalidLocationException {
        this.startingLocation = new Location(0,0, 0);
        this.northEast = TileEdgeDirection.getNorthEast();
        this.north = TileEdgeDirection.getNorth();
        this.northWest = TileEdgeDirection.getNorthWest();
        this.southWest = TileEdgeDirection.getSouthWest();
        this.south = TileEdgeDirection.getSouth();
        this.southEast = TileEdgeDirection.getSouthEast();
    }

    @Test
    public void northEastTest() {
        try {
            Location destination = DirectionToLocation.getLocation(this.startingLocation, this.northEast);
            assertTrue(destination.equals(new Location(1, 0, -1)));
        } catch (InvalidLocationException e) {
            Assert.fail();
        }
    }

    @Test
    public void northTest() {
        try {
            Location destination = DirectionToLocation.getLocation(this.startingLocation, this.north);
            assertTrue(destination.equals(new Location(0, 1, -1)));
        } catch (InvalidLocationException e) {
            Assert.fail();
        }
    }

    @Test
    public void northWest() {
        try {
            Location destination = DirectionToLocation.getLocation(this.startingLocation, this.northWest);
            assertTrue(destination.equals(new Location(-1, 1, 0)));
        } catch (InvalidLocationException e) {
            Assert.fail();
        }
    }

    @Test
    public void southWest() {
        try {
            Location destination = DirectionToLocation.getLocation(this.startingLocation, this.southWest);
            assertTrue(destination.equals(new Location(-1, 0, 1)));
        } catch (InvalidLocationException e) {
            Assert.fail();
        }
    }

    @Test
    public void south() {
        try {
            Location destination = DirectionToLocation.getLocation(this.startingLocation, this.south);
            assertTrue(destination.equals(new Location(0, -1, 1)));
        } catch (InvalidLocationException e) {
            Assert.fail();
        }
    }

    @Test
    public void southEast() {
        try {
            Location destination = DirectionToLocation.getLocation(this.startingLocation, this.southEast);
            assertTrue(destination.equals(new Location(1, -1, 0)));
        } catch (InvalidLocationException e) {
            Assert.fail();
        }
    }

}
