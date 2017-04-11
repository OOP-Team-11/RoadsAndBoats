package mapMaker.model.tile;

import mapMaker.direction.mmAngle;
import mapMaker.direction.mmTileCompartmentDirection;
import mapMaker.direction.mmTileEdgeDirection;
import mapMaker.model.tile.riverConfiguration.mmRiverConfiguration;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

public class mmTileTest {

    private mmRiverConfiguration springHeadConfiguration;
    @Before
    public void setUp() {
        this.springHeadConfiguration = mmRiverConfiguration.getSpringHead();
    }

    // Creating Tiles using all the mmTerrain Types --------------------------------------------------
    @Test
    public void constructTestSeaTile() {
        mmTile t = new mmTile(mmTerrain.SEA, this.springHeadConfiguration);
        assertEquals(t.getMmTerrain(), mmTerrain.SEA);
    }

    @Test
    public void constructTestPastureTile() {
        mmTile t = new mmTile(mmTerrain.PASTURE, this.springHeadConfiguration);
        assertEquals(t.getMmTerrain(), mmTerrain.PASTURE);
    }

    @Test
    public void constructTestWoodsTile() {
        mmTile t = new mmTile(mmTerrain.WOODS, this.springHeadConfiguration);
        assertEquals(t.getMmTerrain(), mmTerrain.WOODS);
    }

    @Test
    public void constructTestRockTile() {
        mmTile t = new mmTile(mmTerrain.ROCK, this.springHeadConfiguration);
        assertEquals(t.getMmTerrain(), mmTerrain.ROCK);
    }

    @Test
    public void constructTestDesertTile() {
        mmTile t = new mmTile(mmTerrain.DESERT, this.springHeadConfiguration);
        assertEquals(t.getMmTerrain(), mmTerrain.DESERT);
    }

    @Test
    public void constructTestMountainTile() {

        mmTile t = new mmTile(mmTerrain.MOUNTAIN, this.springHeadConfiguration);
        assertEquals(t.getMmTerrain(), mmTerrain.MOUNTAIN);
    }
    // ----------------------------------------------------------------------------------------------------


    // Toggle mmTileCompartment in mmTile
    @Test
    public void getTileCompartmentTest() {

        mmTile t = new mmTile(mmTerrain.SEA, this.springHeadConfiguration);
        t.setHasWater(mmTileCompartmentDirection.getNorth(), true);
        assertEquals(t.getTileCompartment(mmTileCompartmentDirection.getNorth()).hasWater(), true);
    }
    // ----------------------------------------------------------------------------------------------------


    // Getting mmTileEdge from mmTile
    @Test
    public void getTileEdgeTest() {

        mmTile t = new mmTile(mmTerrain.SEA, this.springHeadConfiguration);
        assertEquals(t.getTileEdge(mmTileEdgeDirection.getNorth()).canConnectRiver(), true);
    }
    // ----------------------------------------------------------------------------------------------------

    // Check if a tile edge is rotating properly
    @Test
    public void rotateTileEdgeTest() {

        mmTile t = new mmTile(mmTerrain.SEA, this.springHeadConfiguration);
        mmTileEdge northEdge = t.getTileEdge(mmTileEdgeDirection.getNorth());
        t.rotate(new mmAngle(60));
//        Make sure the north edge rotated ONCE to the right, making it the northEast edge now
        assertEquals(northEdge, t.getTileEdge(mmTileEdgeDirection.getNorthEast()));
    }
//    Check if a tile compartment is rotating properly
    @Test
    public void rotateTileCompartmentTest() {

        mmTile t = new mmTile(mmTerrain.SEA, this.springHeadConfiguration);
        mmTileCompartment northCompartment = t.getTileCompartment(mmTileCompartmentDirection.getNorth());
        t.rotate(new mmAngle(120));
//        Make sure the north edge rotated TWICE to the right, making it the northEast edge now
        assertEquals(northCompartment, t.getTileCompartment(mmTileCompartmentDirection.getSouthEast()));
    }

    @Test
    public void rotateTileForSingleRiverConfig() {
        mmRiverConfiguration mmRiverConfiguration = new mmRiverConfiguration(4);
        mmTile t = new mmTile(mmTerrain.SEA, mmRiverConfiguration);
        assertTrue(t.getTileEdge(mmTileEdgeDirection.getSouth()).canConnectRiver());
    }

    @Test
    public void rotateTileForAdjacentRiverConfig() {
        mmRiverConfiguration mmRiverConfiguration = new mmRiverConfiguration(5, 6);
        mmTile t = new mmTile(mmTerrain.SEA, mmRiverConfiguration);
        assertTrue(t.getTileEdge(mmTileEdgeDirection.getSouthWest()).canConnectRiver());
        assertTrue(t.getTileEdge(mmTileEdgeDirection.getNorthWest()).canConnectRiver());
    }

    @Test
    public void rotateTileForSkipOneRiverConfig() {
        mmRiverConfiguration mmRiverConfiguration = new mmRiverConfiguration(2, 4);
        mmTile t = new mmTile(mmTerrain.SEA, mmRiverConfiguration);
        assertTrue(t.getTileEdge(mmTileEdgeDirection.getNorthEast()).canConnectRiver());
        assertTrue(t.getTileEdge(mmTileEdgeDirection.getSouth()).canConnectRiver());
    }

    @Test
    public void rotateTileForOppositeRiverConfig() {
        mmRiverConfiguration mmRiverConfiguration = new mmRiverConfiguration(2, 5);
        mmTile t = new mmTile(mmTerrain.SEA, mmRiverConfiguration);
        assertTrue(t.getTileEdge(mmTileEdgeDirection.getNorthEast()).canConnectRiver());
        assertTrue(t.getTileEdge(mmTileEdgeDirection.getSouthWest()).canConnectRiver());
    }

    @Test
    public void dontRotateTileForEveryOtherRiverConfig() {
        mmRiverConfiguration mmRiverConfiguration = new mmRiverConfiguration(1, 3,5);
        mmTile t = new mmTile(mmTerrain.SEA, mmRiverConfiguration);
        assertTrue(t.getTileEdge(mmTileEdgeDirection.getNorth()).canConnectRiver());
        assertTrue(t.getTileEdge(mmTileEdgeDirection.getSouthEast()).canConnectRiver());
        assertTrue(t.getTileEdge(mmTileEdgeDirection.getSouthWest()).canConnectRiver());
    }

    @Test
    public void rotateTileForEveryOtherRiverConfig() {
        mmRiverConfiguration mmRiverConfiguration = new mmRiverConfiguration(2, 4,6);
        mmTile t = new mmTile(mmTerrain.SEA, mmRiverConfiguration);
        assertTrue(t.getTileEdge(mmTileEdgeDirection.getNorthEast()).canConnectRiver());
        assertTrue(t.getTileEdge(mmTileEdgeDirection.getSouth()).canConnectRiver());
        assertTrue(t.getTileEdge(mmTileEdgeDirection.getNorthWest()).canConnectRiver());
    }
}
