package model.tile;

import direction.Angle;
import direction.TileCompartmentDirection;
import direction.TileEdgeDirection;
import model.tile.riverConfiguration.RiverConfiguration;
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
        Tile t = new Tile(Terrain.SEA, RiverConfiguration.getNoRivers());
        assertEquals(t.getTerrain(), Terrain.SEA);
    }

    @Test
    public void constructTestPastureTile() {
        Tile t = new Tile(Terrain.PASTURE, RiverConfiguration.getNoRivers());
        assertEquals(t.getTerrain(), Terrain.PASTURE);
    }

    @Test
    public void constructTestWoodsTile() {
        Tile t = new Tile(Terrain.WOODS, RiverConfiguration.getNoRivers());
        assertEquals(t.getTerrain(), Terrain.WOODS);
    }

    @Test
    public void constructTestRockTile() {
        Tile t = new Tile(Terrain.ROCK, RiverConfiguration.getNoRivers());
        assertEquals(t.getTerrain(), Terrain.ROCK);
    }

    @Test
    public void constructTestDesertTile() {
        Tile t = new Tile(Terrain.DESERT, RiverConfiguration.getNoRivers());
        assertEquals(t.getTerrain(), Terrain.DESERT);
    }

    @Test
    public void constructTestMountainTile() {
        Tile t = new Tile(Terrain.MOUNTAIN, RiverConfiguration.getNoRivers());
        assertEquals(t.getTerrain(), Terrain.MOUNTAIN);
    }
    // ----------------------------------------------------------------------------------------------------


    // Toggle TileCompartment in Tile
    @Test
    public void getTileCompartmentTest() {
        Tile t = new Tile(Terrain.SEA, RiverConfiguration.getNoRivers());
        t.setHasWater(TileCompartmentDirection.getNorth(), true);
        assertEquals(t.getTileCompartment(TileCompartmentDirection.getNorth()).hasWater(), true);
    }
    // ----------------------------------------------------------------------------------------------------


    // Getting TileEdge from Tile
    @Test
    public void getTileEdgeTest() {
        Tile t = new Tile(Terrain.SEA, RiverConfiguration.getNoRivers());
        t.setCanConnectWater(TileEdgeDirection.getNorth(), true);
        assertEquals(t.getTileEdge(TileEdgeDirection.getNorth()).canConnectRiver(), true);
    }
    // ----------------------------------------------------------------------------------------------------

    // Check if a tile edge is rotating properly
    @Test
    public void rotateTileEdgeTest() {
        Tile t = new Tile(Terrain.SEA, RiverConfiguration.getNoRivers());
        TileEdge northEdge = t.getTileEdge(TileEdgeDirection.getNorth());
        t.rotate(new Angle(60));
//        Make sure the north edge rotated ONCE to the right, making it the northEast edge now
        assertEquals(northEdge, t.getTileEdge(TileEdgeDirection.getNorthEast()));
    }
//    Check if a tile compartment is rotating properly
    @Test
    public void rotateTileCompartmentTest() {
        Tile t = new Tile(Terrain.SEA, RiverConfiguration.getNoRivers());
        TileCompartment northCompartment = t.getTileCompartment(TileCompartmentDirection.getNorth());
        t.rotate(new Angle(120));
//        Make sure the north edge rotated TWICE to the right, making it the northEast edge now
        assertEquals(northCompartment, t.getTileCompartment(TileCompartmentDirection.getSouthEast()));
    }
}
