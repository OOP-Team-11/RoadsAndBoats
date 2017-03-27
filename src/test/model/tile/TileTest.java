package model.tile;

import direction.TileCompartmentDirection;
import direction.TileEdgeDirection;
import org.junit.Assert;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

public class TileTest {

    // Creating Tiles using all the Terrain Types --------------------------------------------------
    @Test
    public void constructTestSeaTile() {
        Tile t = new Tile(Terrain.Sea);
        assertEquals(t.getTerrain(), Terrain.Sea);
    }

    @Test
    public void constructTestPastureTile() {
        Tile t = new Tile(Terrain.Pasture);
        assertEquals(t.getTerrain(), Terrain.Pasture);
    }

    @Test
    public void constructTestWoodsTile() {
        Tile t = new Tile(Terrain.Woods);
        assertEquals(t.getTerrain(), Terrain.Woods);
    }

    @Test
    public void constructTestRockTile() {
        Tile t = new Tile(Terrain.Rock);
        assertEquals(t.getTerrain(), Terrain.Rock);
    }

    @Test
    public void constructTestDesertTile() {
        Tile t = new Tile(Terrain.Desert);
        assertEquals(t.getTerrain(), Terrain.Desert);
    }

    @Test
    public void constructTestMountainTile() {
        Tile t = new Tile(Terrain.Mountain);
        assertEquals(t.getTerrain(), Terrain.Mountain);
    }
    // ----------------------------------------------------------------------------------------------------


    // Toggle TileCompartment in Tile
    @Test
    public void getTileCompartmentTest() {
        Tile t = new Tile(Terrain.Sea);
        t.toggleHasWater(TileCompartmentDirection.getNorth(), true);
        assertEquals(t.getTileCompartment(TileCompartmentDirection.getNorth()).hasWater(), true);
    }
    // ----------------------------------------------------------------------------------------------------


    // Getting TileEdge from Tile
    @Test
    public void getTileEdgeTest() {
        Tile t = new Tile(Terrain.Sea);
        t.toggleCanConnectWater(TileEdgeDirection.getNorth(), true);
        assertEquals(t.getTileEdge(TileEdgeDirection.getNorth()).canConnectRiver(), true);
    }
    // ----------------------------------------------------------------------------------------------------


}
