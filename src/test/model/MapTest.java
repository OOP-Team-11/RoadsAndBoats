package model;

import direction.Angle;
import model.tile.InvalidLocationException;
import model.tile.Location;
import model.tile.Terrain;
import model.tile.Tile;
import model.tile.riverConfiguration.RiverConfiguration;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapTest
{
    private Map map;
    private RiverConfiguration riverConfiguration;

    @Before
    public void setUp() throws InvalidLocationException
    {
        map = new Map();
        riverConfiguration = RiverConfiguration.getNoRivers();

        setUpTiles();
    }

    private void setUpTiles()
    {
        try
        {
            map.placeTile(new Location(-1, 3, -2), new Tile(Terrain.WOODS, riverConfiguration));
            map.placeTile(new Location(-1, 2, -1), new Tile(Terrain.SEA, riverConfiguration));
            map.placeTile(new Location(-1, 1, 0), new Tile(Terrain.MOUNTAIN, riverConfiguration));
            map.placeTile(new Location(-1, 0, 1), new Tile(Terrain.SEA, riverConfiguration));
            map.placeTile(new Location(-1, -1, 2), new Tile(Terrain.ROCK, riverConfiguration));
            map.placeTile(new Location(-1, -2, 3), new Tile(Terrain.DESERT, riverConfiguration));
            map.placeTile(new Location(-2, 3, -1), new Tile(Terrain.WOODS, riverConfiguration));
            map.placeTile(new Location(-2, 2, 0), new Tile(Terrain.ROCK, riverConfiguration));
            map.placeTile(new Location(-2, 1, 1), new Tile(Terrain.SEA, riverConfiguration));
            map.placeTile(new Location(-2, 0, 2), new Tile(Terrain.ROCK, riverConfiguration));
            map.placeTile(new Location(-2, -1, 3), new Tile(Terrain.MOUNTAIN, RiverConfiguration.getSpringHead()));
            map.placeTile(new Location(-3, 3, 0), new Tile(Terrain.SEA, riverConfiguration));
            map.placeTile(new Location(-3, 2, 1), new Tile(Terrain.PASTURE, riverConfiguration));
            map.placeTile(new Location(-3, 1, 2), new Tile(Terrain.WOODS, riverConfiguration));
            map.placeTile(new Location(-3, 0, 3), new Tile(Terrain.PASTURE, RiverConfiguration.getSpringHead()));
        } catch (InvalidLocationException e)
        {
            fail("Invalid Location created while setting up tiles");
        }
    }

    @Test

    public void getTile_invalidLocation_returnsNull() throws InvalidLocationException
    {
        Tile tile = map.getTile(new Location(1, 0, -1));
        assertNull(tile);
    }

    @Test
    public void getTile_validLocation_returnsCorrectTerrain() throws InvalidLocationException
    {
        Tile tile = map.getTile(new Location(-1, -2, 3));
        assertEquals(Terrain.DESERT, tile.getTerrain());
    }

    @Test
    public void isValidPlacement_validWithoutRivers_returnsTrue() throws InvalidLocationException
    {
        Location loc = new Location(0, -3, 3);
        Tile tile = new Tile(Terrain.MOUNTAIN, riverConfiguration);
        boolean isValid = map.isValidPlacement(loc, tile);
        assertTrue(isValid);
    }

    @Test
    public void isValidPlacement_invalidUnattatchedTile_returnsFalse() throws InvalidLocationException
    {
        Location loc = new Location(10, -3, -7);
        Tile tile = new Tile(Terrain.MOUNTAIN, riverConfiguration);
        boolean isValid = map.isValidPlacement(loc, tile);
        assertFalse(isValid);
    }

    //0 -1 1 and 1 -1 0
    @Test
    public void isValidPlacement_validSecondTile_returnsTrue() throws InvalidLocationException
    {
        Map map = new Map();
        map.placeTile(new Location(0, -1, 1), new Tile(Terrain.MOUNTAIN, riverConfiguration));
        Location loc = new Location(1, -1, 0);
        Tile tile = new Tile(Terrain.DESERT, RiverConfiguration.getNoRivers());
        boolean isValid = map.isValidPlacement(loc, tile);

        assertTrue(isValid);
    }

    @Test
    public void isValidPlacement_validWithTwoRiversConnectingToSeas_returnsTrue() throws InvalidLocationException
    {
        Location loc = new Location(-1, 0, 1);
        Tile tile = new Tile(Terrain.MOUNTAIN, RiverConfiguration.getAdjacentFaces());
        tile.rotate(new Angle(60));
        map.removeTileAtLocation(loc);
        boolean isValid = map.isValidPlacement(loc, tile);
        assertTrue(isValid);
    }

    @Test
    public void isValidPlacement_validWithThreeRiversConnectingToSeas_returnsTrue() throws InvalidLocationException
    {
        Location loc = new Location(-2, 2, 0);
        Tile tile = new Tile(Terrain.MOUNTAIN, RiverConfiguration.getEveryOtherFace());
        tile.rotate(new Angle(60));
        map.removeTileAtLocation(loc);
        boolean isValid = map.isValidPlacement(loc, tile);
        assertTrue(isValid);
    }

    @Test
    public void isValidPlacement_validWithRiverConnectingToSeaAndRiver_returnsTrue() throws InvalidLocationException
    {
        Location loc = new Location(-2, 0, 2);
        Tile tile = new Tile(Terrain.MOUNTAIN, RiverConfiguration.getOppositeFaces());
        map.removeTileAtLocation(loc);
        boolean isValid = map.isValidPlacement(loc, tile);
        assertTrue(isValid);
    }

    @Test
    public void isValidPlacement_validWithRiverConnectingToRiverAndOffMap_returnsTrue() throws InvalidLocationException
    {
        Location loc = new Location(-3, 1, 2);
        Tile tile = new Tile(Terrain.MOUNTAIN, RiverConfiguration.getAdjacentFaces());
        tile.rotate(new Angle(180));
        map.removeTileAtLocation(loc);
        boolean isValid = map.isValidPlacement(loc, tile);
        assertTrue(isValid);
    }

    @Test
    public void isValidPlacement_unconnectedRivers_returnsFalse() throws InvalidLocationException
    {
        Location loc = new Location(0, -2, 2);
        Tile tile = new Tile(Terrain.MOUNTAIN, RiverConfiguration.getAdjacentFaces());
        tile.rotate(new Angle(180));
        boolean isValid = map.isValidPlacement(loc, tile);
        assertFalse(isValid);
    }

    @Test
    public void isValidPlacement_doesntConnectExistingRiver_returnsFalse() throws InvalidLocationException
    {
        Location loc = new Location(-3, 1, 2);
        Tile tile = new Tile(Terrain.MOUNTAIN, riverConfiguration);
        tile.rotate(new Angle(180));
        boolean isValid = map.isValidPlacement(loc, tile);
        assertFalse(isValid);
    }

    @Test
    public void recenter_preConstructedMap_properCenterTerrain() throws InvalidLocationException
    {
        map.recenter();
        assertEquals(Terrain.ROCK, map.getTile(new Location(0, 0, 0)).getTerrain());
    }

    @Test
    public void recenter_preConstructedMap_properDesertLocation() throws InvalidLocationException
    {
        map.recenter();
        assertEquals(Terrain.DESERT, map.getTile(new Location(1, -2, 1)).getTerrain());
    }

    @Test
    public void recenter_lineOfTiles_correctCenter() throws InvalidLocationException
    {
//        Build line of tiles to the right starting at 0,0,0
        Map map = new Map();
        map.placeTile(new Location(0, 0, 0), new Tile(Terrain.SEA, riverConfiguration));
        map.placeTile(new Location(1, -1, 0), new Tile(Terrain.SEA, riverConfiguration));
        map.placeTile(new Location(2, -2, 0), new Tile(Terrain.DESERT, riverConfiguration));
        map.placeTile(new Location(3, -3, 0), new Tile(Terrain.SEA, riverConfiguration));
        map.placeTile(new Location(4, -4, 0), new Tile(Terrain.SEA, riverConfiguration));
        map.recenter();
//        New center should be Desert terrain tile
        assertEquals(map.getTile(new Location(0, 0, 0)).getTerrain(), Terrain.DESERT);
    }

    @Test
    public void recenter_emptyMap_nullTile() throws InvalidLocationException
    {
        Map map = new Map();
        map.recenter();
        assertEquals(null, map.getTile(new Location(0, 0, 0)));
    }
}
