package view.render;

import direction.Angle;
import model.tile.Terrain;
import model.tile.Tile;
import model.tile.riverConfiguration.RiverConfiguration;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TileRenderInformationTest {

    @Test
    public void noRivers() {
        Tile t = new Tile(Terrain.PASTURE, RiverConfiguration.getNoRivers());
        TileRenderInformation tileRenderInformation = new TileRenderInformation(t);
        assertEquals(tileRenderInformation.getTerrain(), Terrain.PASTURE);
        assertFalse(tileRenderInformation.getNorth());
        assertFalse(tileRenderInformation.getNorthEast());
        assertFalse(tileRenderInformation.getSouthEast());
        assertFalse(tileRenderInformation.getSouth());
        assertFalse(tileRenderInformation.getSouthWest());
        assertFalse(tileRenderInformation.getNorthWest());
    }

    @Test
    public void spring() {
        Tile t = new Tile(Terrain.PASTURE, RiverConfiguration.getSpringHead());
        TileRenderInformation tileRenderInformation = new TileRenderInformation(t);
        assertEquals(tileRenderInformation.getTerrain(), Terrain.PASTURE);
        assertTrue(tileRenderInformation.getNorth());
        assertFalse(tileRenderInformation.getNorthEast());
        assertFalse(tileRenderInformation.getSouthEast());
        assertFalse(tileRenderInformation.getSouth());
        assertFalse(tileRenderInformation.getSouthWest());
        assertFalse(tileRenderInformation.getNorthWest());
    }

    @Test
    public void adjacent() {
        Tile t = new Tile(Terrain.PASTURE, RiverConfiguration.getAdjacentFaces());
        TileRenderInformation tileRenderInformation = new TileRenderInformation(t);
        assertEquals(tileRenderInformation.getTerrain(), Terrain.PASTURE);
        assertTrue(tileRenderInformation.getNorth());
        assertTrue(tileRenderInformation.getNorthEast());
        assertFalse(tileRenderInformation.getSouthEast());
        assertFalse(tileRenderInformation.getSouth());
        assertFalse(tileRenderInformation.getSouthWest());
        assertFalse(tileRenderInformation.getNorthWest());
    }

    @Test
    public void skipAFace() {
        Tile t = new Tile(Terrain.PASTURE, RiverConfiguration.getSkipAFace());
        TileRenderInformation tileRenderInformation = new TileRenderInformation(t);
        assertEquals(tileRenderInformation.getTerrain(), Terrain.PASTURE);
        assertTrue(tileRenderInformation.getNorth());
        assertFalse(tileRenderInformation.getNorthEast());
        assertTrue(tileRenderInformation.getSouthEast());
        assertFalse(tileRenderInformation.getSouth());
        assertFalse(tileRenderInformation.getSouthWest());
        assertFalse(tileRenderInformation.getNorthWest());
    }

    @Test
    public void opposite() {
        Tile t = new Tile(Terrain.PASTURE, RiverConfiguration.getOppositeFaces());
        TileRenderInformation tileRenderInformation = new TileRenderInformation(t);
        assertEquals(tileRenderInformation.getTerrain(), Terrain.PASTURE);
        assertTrue(tileRenderInformation.getNorth());
        assertFalse(tileRenderInformation.getNorthEast());
        assertFalse(tileRenderInformation.getSouthEast());
        assertTrue(tileRenderInformation.getSouth());
        assertFalse(tileRenderInformation.getSouthWest());
        assertFalse(tileRenderInformation.getNorthWest());
    }

    @Test
    public void everyOther() {
        Tile t = new Tile(Terrain.PASTURE, RiverConfiguration.getEveryOtherFace());
        TileRenderInformation tileRenderInformation = new TileRenderInformation(t);
        assertEquals(tileRenderInformation.getTerrain(), Terrain.PASTURE);
        assertTrue(tileRenderInformation.getNorth());
        assertFalse(tileRenderInformation.getNorthEast());
        assertTrue(tileRenderInformation.getSouthEast());
        assertFalse(tileRenderInformation.getSouth());
        assertTrue(tileRenderInformation.getSouthWest());
        assertFalse(tileRenderInformation.getNorthWest());
    }

    @Test
    public void rotateOne() {
        Tile t = new Tile(Terrain.PASTURE, RiverConfiguration.getSpringHead());
        t.rotate(new Angle(60));
        TileRenderInformation tileRenderInformation = new TileRenderInformation(t);
        assertEquals(tileRenderInformation.getTerrain(), Terrain.PASTURE);
        assertFalse(tileRenderInformation.getNorth());
        assertTrue(tileRenderInformation.getNorthEast());
        assertFalse(tileRenderInformation.getSouthEast());
        assertFalse(tileRenderInformation.getSouth());
        assertFalse(tileRenderInformation.getSouthWest());
        assertFalse(tileRenderInformation.getNorthWest());
    }

    @Test
    public void rotateTwo() {
        Tile t = new Tile(Terrain.PASTURE, RiverConfiguration.getSpringHead());
        t.rotate(new Angle(120));
        TileRenderInformation tileRenderInformation = new TileRenderInformation(t);
        assertEquals(tileRenderInformation.getTerrain(), Terrain.PASTURE);
        assertFalse(tileRenderInformation.getNorth());
        assertFalse(tileRenderInformation.getNorthEast());
        assertTrue(tileRenderInformation.getSouthEast());
        assertFalse(tileRenderInformation.getSouth());
        assertFalse(tileRenderInformation.getSouthWest());
        assertFalse(tileRenderInformation.getNorthWest());
    }

    @Test
    public void rotateThree() {
        Tile t = new Tile(Terrain.PASTURE, RiverConfiguration.getSpringHead());
        t.rotate(new Angle(180));
        TileRenderInformation tileRenderInformation = new TileRenderInformation(t);
        assertEquals(tileRenderInformation.getTerrain(), Terrain.PASTURE);
        assertFalse(tileRenderInformation.getNorth());
        assertFalse(tileRenderInformation.getNorthEast());
        assertFalse(tileRenderInformation.getSouthEast());
        assertTrue(tileRenderInformation.getSouth());
        assertFalse(tileRenderInformation.getSouthWest());
        assertFalse(tileRenderInformation.getNorthWest());
    }

    @Test
    public void rotateFour() {
        Tile t = new Tile(Terrain.PASTURE, RiverConfiguration.getSpringHead());
        t.rotate(new Angle(240));
        TileRenderInformation tileRenderInformation = new TileRenderInformation(t);
        assertEquals(tileRenderInformation.getTerrain(), Terrain.PASTURE);
        assertFalse(tileRenderInformation.getNorth());
        assertFalse(tileRenderInformation.getNorthEast());
        assertFalse(tileRenderInformation.getSouthEast());
        assertFalse(tileRenderInformation.getSouth());
        assertTrue(tileRenderInformation.getSouthWest());
        assertFalse(tileRenderInformation.getNorthWest());
    }

    @Test
    public void rotateFive() {
        Tile t = new Tile(Terrain.PASTURE, RiverConfiguration.getSpringHead());
        t.rotate(new Angle(300));
        TileRenderInformation tileRenderInformation = new TileRenderInformation(t);
        assertEquals(tileRenderInformation.getTerrain(), Terrain.PASTURE);
        assertFalse(tileRenderInformation.getNorth());
        assertFalse(tileRenderInformation.getNorthEast());
        assertFalse(tileRenderInformation.getSouthEast());
        assertFalse(tileRenderInformation.getSouth());
        assertFalse(tileRenderInformation.getSouthWest());
        assertTrue(tileRenderInformation.getNorthWest());
    }
}
