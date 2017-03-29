package model;

import direction.TileEdgeDirection;
import model.tile.InvalidLocationException;
import model.tile.Location;
import model.tile.Terrain;
import model.tile.Tile;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class MapTest
{
    private Map map;

    @Before
    public void setUp() throws InvalidLocationException
    {
        map=new Map();

        setUpTiles();
        setUpRivers();
    }

    private void setUpTiles()
    {
        try
        {
            map.placeTile(new Location(-1, 3, -2), new Tile(Terrain.WOODS));
            map.placeTile(new Location(-1, 2, -1), new Tile(Terrain.SEA));
            map.placeTile(new Location(-1, 1, 0), new Tile(Terrain.MOUNTAIN));
            map.placeTile(new Location(-1, 0, 1), new Tile(Terrain.SEA));
            map.placeTile(new Location(-1, -1, 2), new Tile(Terrain.ROCK));
            map.placeTile(new Location(-1, -2, 3), new Tile(Terrain.DESERT));
            map.placeTile(new Location(-2, 3, -1), new Tile(Terrain.WOODS));
            map.placeTile(new Location(-2, 2, 0), new Tile(Terrain.ROCK));
            map.placeTile(new Location(-2, 1, 1), new Tile(Terrain.SEA));
            map.placeTile(new Location(-2, 0, 2), new Tile(Terrain.ROCK));
            map.placeTile(new Location(-2, -1, 3), new Tile(Terrain.MOUNTAIN));
            map.placeTile(new Location(-3, 3, 0), new Tile(Terrain.SEA));
            map.placeTile(new Location(-3, 2, 1), new Tile(Terrain.PASTURE));
            map.placeTile(new Location(-3, 1, 2), new Tile(Terrain.WOODS));
            map.placeTile(new Location(-3, 0, 3), new Tile(Terrain.PASTURE));
        } catch(InvalidLocationException e)
        {
            fail("Invalid Location created while setting up tiles");
        }
    }

    private void setUpRivers()
    {
        try
        {
            map.getTile(new Location(-2,2,0)).setCanConnectWater(TileEdgeDirection.getNorthEast(), true);
            map.getTile(new Location(-2,2,0)).setCanConnectWater(TileEdgeDirection.getNorthWest(), true);
            map.getTile(new Location(-2,2,0)).setCanConnectWater(TileEdgeDirection.getSouth(), true);
            map.getTile(new Location(-2,1,1)).setCanConnectWater(TileEdgeDirection.getNorthEast(), true);
            map.getTile(new Location(-2,1,1)).setCanConnectWater(TileEdgeDirection.getNorth(), true);
        } catch(InvalidLocationException e)
        {
            fail("Invalid Location created while setting up rivers");
        }
    }

    @Test
    public void getTile_invalidLocation_returnsNull() throws InvalidLocationException
    {
        Tile tile = map.getTile(new Location(1,0,-1));
        assertNull(tile);
    }

    @Test
    public void getTile_validLocation_returnsCorrectTerrain() throws InvalidLocationException
    {
        Tile tile = map.getTile(new Location(-1,-2,3));
        assertEquals(Terrain.DESERT, tile.getTerrain());
    }

    @Test
    public void isValidPlacement_validWithoutRivers_returnsTrue() throws InvalidLocationException
    {
        Location loc = new Location(0, -3, 3);
        Tile tile = new Tile(Terrain.MOUNTAIN);
        boolean isValid = map.isValidPlacement(loc, tile);
        assertTrue(isValid);
    }

    @Test
    public void isValidPlacement_validWithTwoRiversConnectingToRivers_returnsTrue() throws InvalidLocationException
    {
        Location loc = new Location(-1, 0, 1);
        Tile tile = new Tile(Terrain.MOUNTAIN);
        tile.setCanConnectWater(TileEdgeDirection.getNorth(), true);
        tile.setCanConnectWater(TileEdgeDirection.getNorthWest(), true);
        boolean isValid = map.isValidPlacement(loc, tile);
        assertTrue(isValid);
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
        map.placeTile(new Location(0, 0, 0), new Tile(Terrain.SEA));
        map.placeTile(new Location(1, -1, 0), new Tile(Terrain.SEA));
        map.placeTile(new Location(2, -2, 0), new Tile(Terrain.DESERT));
        map.placeTile(new Location(3, -3, 0), new Tile(Terrain.SEA));
        map.placeTile(new Location(4, -4, 0), new Tile(Terrain.SEA));
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
