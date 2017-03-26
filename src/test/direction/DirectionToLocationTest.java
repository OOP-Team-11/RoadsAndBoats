package direction;

import org.junit.Before;
import org.junit.Test;
import tile.Location;

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
    public void setUp() {
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
        Location destination = DirectionToLocation.getLocation(this.startingLocation, this.northEast);
        assertTrue(destination.equals(new Location(1, 0, -1)));
    }

    @Test
    public void northTest() {
        Location destination = DirectionToLocation.getLocation(this.startingLocation, this.north);
        assertTrue(destination.equals(new Location(0, 1, -1)));
    }

    @Test
    public void northWest() {
        Location destination = DirectionToLocation.getLocation(this.startingLocation, this.northWest);
        assertTrue(destination.equals(new Location(-1, 1, 0)));
    }

    @Test
    public void southWest() {
        Location destination = DirectionToLocation.getLocation(this.startingLocation, this.southWest);
        assertTrue(destination.equals(new Location(-1, 0, 1)));
    }

    @Test
    public void south() {
        Location destination = DirectionToLocation.getLocation(this.startingLocation, this.south);
        assertTrue(destination.equals(new Location(0, -1, 1)));
    }

    @Test
    public void southEast() {
        Location destination = DirectionToLocation.getLocation(this.startingLocation, this.southEast);
        assertTrue(destination.equals(new Location(1, -1, 0)));
    }

}
