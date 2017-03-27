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


    // Putting more than 12 TileCompartment into Tile
    @Test
    public void putMaxTileCompartmentTest() {
        Tile t = new Tile(Terrain.Sea);
        try {
            t.putTileCompartment(TileCompartmentDirection.getNorth(), new TileCompartment(false));  //1
            t.putTileCompartment(TileCompartmentDirection.getNorth(), new TileCompartment(false));  //2
            t.putTileCompartment(TileCompartmentDirection.getNorth(), new TileCompartment(false));  //3
            t.putTileCompartment(TileCompartmentDirection.getNorth(), new TileCompartment(false));  //4
            t.putTileCompartment(TileCompartmentDirection.getNorth(), new TileCompartment(false));  //5
            t.putTileCompartment(TileCompartmentDirection.getNorth(), new TileCompartment(false));  //6
            t.putTileCompartment(TileCompartmentDirection.getNorth(), new TileCompartment(false));  //7
            t.putTileCompartment(TileCompartmentDirection.getNorth(), new TileCompartment(false));  //8
            t.putTileCompartment(TileCompartmentDirection.getNorth(), new TileCompartment(false));  //9
            t.putTileCompartment(TileCompartmentDirection.getNorth(), new TileCompartment(false));  //10
            t.putTileCompartment(TileCompartmentDirection.getNorth(), new TileCompartment(false));  //11
            t.putTileCompartment(TileCompartmentDirection.getNorth(), new TileCompartment(false));  //12
            t.putTileCompartment(TileCompartmentDirection.getNorth(), new TileCompartment(false));  //13
        } catch (TileCompartmentLimitException e) {
            assertEquals(t.getAllCompartments().size(), Tile.MAX_COMPARTMENTS);
        }
    }
    // ----------------------------------------------------------------------------------------------------

    // Getting TileCompartment from Tile
    @Test
    public void getTileCompartmentTest() {
        Tile t = new Tile(Terrain.Sea);
        TileCompartment compartment = new TileCompartment(false);
        TileCompartmentDirection direction = TileCompartmentDirection.getNorth();
        try {
            t.putTileCompartment(direction, compartment);
            assertEquals(t.getTileCompartment(direction), compartment);
        } catch (TileCompartmentLimitException e) {
            Assert.fail();
        }
    }
    // ----------------------------------------------------------------------------------------------------


    // Putting more than 6 TileEdges into Tile
    @Test
    public void putMaxTileEdgeTest() {
        Tile t = new Tile(Terrain.Sea);
        try {
            t.putTileEdge(TileEdgeDirection.getNorth(), new TileEdge(false));  //1
            t.putTileEdge(TileEdgeDirection.getNorth(), new TileEdge(false));  //2
            t.putTileEdge(TileEdgeDirection.getNorth(), new TileEdge(false));  //3
            t.putTileEdge(TileEdgeDirection.getNorth(), new TileEdge(false));  //4
            t.putTileEdge(TileEdgeDirection.getNorth(), new TileEdge(false));  //5
            t.putTileEdge(TileEdgeDirection.getNorth(), new TileEdge(false));  //6
            t.putTileEdge(TileEdgeDirection.getNorth(), new TileEdge(false));  //7
        } catch (TileEdgeLimitException e) {
            assertEquals(t.getAllEdges().size(), Tile.MAX_EDGES);
        }
    }
    // ----------------------------------------------------------------------------------------------------


    // Getting TileEdge from Tile
    @Test
    public void getTileEdgeTest() {
        Tile t = new Tile(Terrain.Sea);
        TileEdge edge = new TileEdge(false);
        TileEdgeDirection direction = TileEdgeDirection.getNorth();
        try {
            t.putTileEdge(direction, edge);
            assertEquals(t.getTileEdge(direction), edge);
        } catch (TileEdgeLimitException e) {
            Assert.fail();
        }
    }
    // ----------------------------------------------------------------------------------------------------


}
