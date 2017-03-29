package model.tile;

import direction.Angle;
import direction.TileCompartmentDirection;
import direction.TileEdgeDirection;
import model.tile.riverConfiguration.RiverConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

public class TileTest {

    private RiverConfiguration springHeadConfiguration;
    @Before
    public void setUp() {
        this.springHeadConfiguration = RiverConfiguration.getSpringHead();
    }

    // Creating Tiles using all the Terrain Types --------------------------------------------------
    @Test
    public void constructTestSeaTile() {
        Tile t = new Tile(Terrain.SEA, this.springHeadConfiguration);
        assertEquals(t.getTerrain(), Terrain.SEA);
    }

    @Test
    public void constructTestPastureTile() {
        Tile t = new Tile(Terrain.PASTURE, this.springHeadConfiguration);
        assertEquals(t.getTerrain(), Terrain.PASTURE);
    }

    @Test
    public void constructTestWoodsTile() {
        Tile t = new Tile(Terrain.WOODS, this.springHeadConfiguration);
        assertEquals(t.getTerrain(), Terrain.WOODS);
    }

    @Test
    public void constructTestRockTile() {
        Tile t = new Tile(Terrain.ROCK, this.springHeadConfiguration);
        assertEquals(t.getTerrain(), Terrain.ROCK);
    }

    @Test
    public void constructTestDesertTile() {
        Tile t = new Tile(Terrain.DESERT, this.springHeadConfiguration);
        assertEquals(t.getTerrain(), Terrain.DESERT);
    }

    @Test
    public void constructTestMountainTile() {

        Tile t = new Tile(Terrain.MOUNTAIN, this.springHeadConfiguration);
        assertEquals(t.getTerrain(), Terrain.MOUNTAIN);
    }
    // ----------------------------------------------------------------------------------------------------


    // Toggle TileCompartment in Tile
    @Test
    public void getTileCompartmentTest() {

        Tile t = new Tile(Terrain.SEA, this.springHeadConfiguration);
        t.setHasWater(TileCompartmentDirection.getNorth(), true);
        assertEquals(t.getTileCompartment(TileCompartmentDirection.getNorth()).hasWater(), true);
    }
    // ----------------------------------------------------------------------------------------------------


    // Getting TileEdge from Tile
    @Test
    public void getTileEdgeTest() {

        Tile t = new Tile(Terrain.SEA, this.springHeadConfiguration);
        t.setCanConnectWater(TileEdgeDirection.getNorth(), true);
        assertEquals(t.getTileEdge(TileEdgeDirection.getNorth()).canConnectRiver(), true);
    }
    // ----------------------------------------------------------------------------------------------------

    // Check if a tile edge is rotating properly
    @Test
    public void rotateTileEdgeTest() {

        Tile t = new Tile(Terrain.SEA, this.springHeadConfiguration);
        TileEdge northEdge = t.getTileEdge(TileEdgeDirection.getNorth());
        t.rotate(new Angle(60));
//        Make sure the north edge rotated ONCE to the right, making it the northEast edge now
        assertEquals(northEdge, t.getTileEdge(TileEdgeDirection.getNorthEast()));
    }
//    Check if a tile compartment is rotating properly
    @Test
    public void rotateTileCompartmentTest() {

        Tile t = new Tile(Terrain.SEA, this.springHeadConfiguration);
        TileCompartment northCompartment = t.getTileCompartment(TileCompartmentDirection.getNorth());
        t.rotate(new Angle(120));
//        Make sure the north edge rotated TWICE to the right, making it the northEast edge now
        assertEquals(northCompartment, t.getTileCompartment(TileCompartmentDirection.getSouthEast()));
    }

    @Test
    public void rotateTileForSingleRiverConfig() {
        RiverConfiguration riverConfiguration = new RiverConfiguration(4);
        Tile t = new Tile(Terrain.SEA, riverConfiguration);
        assertTrue(t.getTileEdge(TileEdgeDirection.getSouth()).canConnectRiver());
    }

    @Test
    public void rotateTileForAdjacentRiverConfig() {
        RiverConfiguration riverConfiguration = new RiverConfiguration(5, 6);
        Tile t = new Tile(Terrain.SEA, riverConfiguration);
        assertTrue(t.getTileEdge(TileEdgeDirection.getSouthWest()).canConnectRiver());
        assertTrue(t.getTileEdge(TileEdgeDirection.getNorthWest()).canConnectRiver());
    }

    @Test
    public void rotateTileForSkipOneRiverConfig() {
        RiverConfiguration riverConfiguration = new RiverConfiguration(2, 4);
        Tile t = new Tile(Terrain.SEA, riverConfiguration);
        assertTrue(t.getTileEdge(TileEdgeDirection.getNorthEast()).canConnectRiver());
        assertTrue(t.getTileEdge(TileEdgeDirection.getSouth()).canConnectRiver());
    }

    @Test
    public void rotateTileForOppositeRiverConfig() {
        RiverConfiguration riverConfiguration = new RiverConfiguration(2, 5);
        Tile t = new Tile(Terrain.SEA, riverConfiguration);
        assertTrue(t.getTileEdge(TileEdgeDirection.getNorthEast()).canConnectRiver());
        assertTrue(t.getTileEdge(TileEdgeDirection.getSouthWest()).canConnectRiver());
    }

    @Test
    public void dontRotateTileForEveryOtherRiverConfig() {
        RiverConfiguration riverConfiguration = new RiverConfiguration(1, 3,5);
        Tile t = new Tile(Terrain.SEA, riverConfiguration);
        assertTrue(t.getTileEdge(TileEdgeDirection.getNorth()).canConnectRiver());
        assertTrue(t.getTileEdge(TileEdgeDirection.getSouthEast()).canConnectRiver());
        assertTrue(t.getTileEdge(TileEdgeDirection.getSouthWest()).canConnectRiver());
    }

    @Test
    public void rotateTileForEveryOtherRiverConfig() {
        RiverConfiguration riverConfiguration = new RiverConfiguration(2, 4,6);
        Tile t = new Tile(Terrain.SEA, riverConfiguration);
        assertTrue(t.getTileEdge(TileEdgeDirection.getNorthEast()).canConnectRiver());
        assertTrue(t.getTileEdge(TileEdgeDirection.getSouth()).canConnectRiver());
        assertTrue(t.getTileEdge(TileEdgeDirection.getNorthWest()).canConnectRiver());
    }
}
