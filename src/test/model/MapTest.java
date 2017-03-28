package model;

import model.tile.InvalidLocationException;
import model.tile.Location;
import model.tile.Terrain;
import model.tile.Tile;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static org.junit.Assert.assertTrue;

public class MapTest
{

    @Before
    public void setUp() throws InvalidLocationException {

    }

    @Test
    public void getTileTest() throws InvalidLocationException {
        Map map = new Map();
        map.placeTile(new Location(0,0,0), new Tile(Terrain.DESERT));
        Tile centerTile = map.getTile(new Location(0,0,0));
        assertEquals(Terrain.DESERT, centerTile.getTerrain());
    }

    @Test
    public void clusterRecenterTest() throws InvalidLocationException {
        Map map = new Map();
//        Create circular cluster with the true center being (0,1,-1)
        map.placeTile(new Location(0,0,0), new Tile(Terrain.SEA));
        map.placeTile(new Location(1,-1,0), new Tile(Terrain.SEA));
        map.placeTile(new Location(2,-1,-1), new Tile(Terrain.SEA));
        map.placeTile(new Location(2,0,-2), new Tile(Terrain.SEA));
        map.placeTile(new Location(1,1,-2), new Tile(Terrain.SEA));
        map.placeTile(new Location(0,1,-1), new Tile(Terrain.SEA));
        map.placeTile(new Location(1,0,-1), new Tile(Terrain.DESERT));
        map.recenter();
        // New center should be the tile that was at (1,-1,0) which has terrain type SEA
        assertEquals(Terrain.DESERT, map.getTile(new Location(0,0,0)).getTerrain());
    }

    @Test
    public void lineRecenterTest() throws InvalidLocationException {
//        Build line of tiles to the right starting at 0,0,0
        Map map = new Map();
        map.placeTile(new Location(0,0,0), new Tile(Terrain.SEA));
        map.placeTile(new Location(1,-1,0), new Tile(Terrain.SEA));
        map.placeTile(new Location(2,-2,0), new Tile(Terrain.DESERT));
        map.placeTile(new Location(3,-3,0), new Tile(Terrain.SEA));
        map.placeTile(new Location(4,-4,0), new Tile(Terrain.SEA));
        map.recenter();
//        New center should be Desert terrain tile
        assertEquals(map.getTile(new Location(0,0,0)).getTerrain(), Terrain.DESERT);
    }

    @Test
    public void emptyRecenterTest() throws InvalidLocationException {
        Map map = new Map();
        map.recenter();
        assertEquals(null, map.getTile(new Location(0,0,0)));
    }

    @Test
    public void hasMatchingEdgesTest() throws InvalidLocationException {
        Map map = new Map();
        map.placeTile(new Location(0,0,0), new Tile(Terrain.SEA));
        assertTrue(map.hasMatchingEdges(new Location(1,-1,0), new Tile(Terrain.SEA)));
    }

}
