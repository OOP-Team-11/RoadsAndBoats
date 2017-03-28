package model;

import model.tile.InvalidLocationException;
import model.tile.Location;
import model.tile.Terrain;
import model.tile.Tile;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;

public class MapTest
{
    private Map map;

    public MapTest() throws InvalidLocationException {

    }

    @Before
    public void setUp() throws InvalidLocationException {
        map = new Map();
        map.placeTile(new Location(0,0,0), new Tile(Terrain.DESERT));
        map.placeTile(new Location(1,-1,0), new Tile(Terrain.SEA));
        map.placeTile(new Location(2,-2,0), new Tile(Terrain.DESERT));
    }

    @Test
    public void getTile() throws InvalidLocationException {
        Tile centerTile = map.getTile(new Location(0,0,0));
        assertEquals(Terrain.DESERT, centerTile.getTerrain());
    }

    @Test
    public void addTile()
    {

    }

    @Test
    public void recenter() throws InvalidLocationException {
        map.recenter();
        // New center should be the tile that was at (1,-1,0) which has terrain type SEA
        assertEquals(Terrain.SEA, map.getTile(new Location(0,0,0)).getTerrain());
    }

}
