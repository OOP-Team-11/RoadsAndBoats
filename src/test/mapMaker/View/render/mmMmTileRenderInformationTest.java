package mapMaker.view.render;

import mapMaker.direction.mmAngle;
import mapMaker.model.tile.mmTerrain;
import mapMaker.model.tile.mmTile;
import mapMaker.model.tile.riverConfiguration.mmRiverConfiguration;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class mmMmTileRenderInformationTest {

    @Test
    public void noRivers() {
        mmTile t = new mmTile(mmTerrain.PASTURE, mmRiverConfiguration.getNoRivers());
        mmTileRenderInformation mmTileRenderInformation = new mmTileRenderInformation(t);
        assertEquals(mmTileRenderInformation.getMmTerrain(), mmTerrain.PASTURE);
        assertFalse(mmTileRenderInformation.getNorth());
        assertFalse(mmTileRenderInformation.getNorthEast());
        assertFalse(mmTileRenderInformation.getSouthEast());
        assertFalse(mmTileRenderInformation.getSouth());
        assertFalse(mmTileRenderInformation.getSouthWest());
        assertFalse(mmTileRenderInformation.getNorthWest());
    }

    @Test
    public void spring() {
        mmTile t = new mmTile(mmTerrain.PASTURE, mmRiverConfiguration.getSpringHead());
        mmTileRenderInformation mmTileRenderInformation = new mmTileRenderInformation(t);
        assertEquals(mmTileRenderInformation.getMmTerrain(), mmTerrain.PASTURE);
        assertTrue(mmTileRenderInformation.getNorth());
        assertFalse(mmTileRenderInformation.getNorthEast());
        assertFalse(mmTileRenderInformation.getSouthEast());
        assertFalse(mmTileRenderInformation.getSouth());
        assertFalse(mmTileRenderInformation.getSouthWest());
        assertFalse(mmTileRenderInformation.getNorthWest());
    }

    @Test
    public void adjacent() {
        mmTile t = new mmTile(mmTerrain.PASTURE, mmRiverConfiguration.getAdjacentFaces());
        mmTileRenderInformation mmTileRenderInformation = new mmTileRenderInformation(t);
        assertEquals(mmTileRenderInformation.getMmTerrain(), mmTerrain.PASTURE);
        assertTrue(mmTileRenderInformation.getNorth());
        assertTrue(mmTileRenderInformation.getNorthEast());
        assertFalse(mmTileRenderInformation.getSouthEast());
        assertFalse(mmTileRenderInformation.getSouth());
        assertFalse(mmTileRenderInformation.getSouthWest());
        assertFalse(mmTileRenderInformation.getNorthWest());
    }

    @Test
    public void skipAFace() {
        mmTile t = new mmTile(mmTerrain.PASTURE, mmRiverConfiguration.getSkipAFace());
        mmTileRenderInformation mmTileRenderInformation = new mmTileRenderInformation(t);
        assertEquals(mmTileRenderInformation.getMmTerrain(), mmTerrain.PASTURE);
        assertTrue(mmTileRenderInformation.getNorth());
        assertFalse(mmTileRenderInformation.getNorthEast());
        assertTrue(mmTileRenderInformation.getSouthEast());
        assertFalse(mmTileRenderInformation.getSouth());
        assertFalse(mmTileRenderInformation.getSouthWest());
        assertFalse(mmTileRenderInformation.getNorthWest());
    }

    @Test
    public void opposite() {
        mmTile t = new mmTile(mmTerrain.PASTURE, mmRiverConfiguration.getOppositeFaces());
        mmTileRenderInformation mmTileRenderInformation = new mmTileRenderInformation(t);
        assertEquals(mmTileRenderInformation.getMmTerrain(), mmTerrain.PASTURE);
        assertTrue(mmTileRenderInformation.getNorth());
        assertFalse(mmTileRenderInformation.getNorthEast());
        assertFalse(mmTileRenderInformation.getSouthEast());
        assertTrue(mmTileRenderInformation.getSouth());
        assertFalse(mmTileRenderInformation.getSouthWest());
        assertFalse(mmTileRenderInformation.getNorthWest());
    }

    @Test
    public void everyOther() {
        mmTile t = new mmTile(mmTerrain.PASTURE, mmRiverConfiguration.getEveryOtherFace());
        mmTileRenderInformation mmTileRenderInformation = new mmTileRenderInformation(t);
        assertEquals(mmTileRenderInformation.getMmTerrain(), mmTerrain.PASTURE);
        assertTrue(mmTileRenderInformation.getNorth());
        assertFalse(mmTileRenderInformation.getNorthEast());
        assertTrue(mmTileRenderInformation.getSouthEast());
        assertFalse(mmTileRenderInformation.getSouth());
        assertTrue(mmTileRenderInformation.getSouthWest());
        assertFalse(mmTileRenderInformation.getNorthWest());
    }

    @Test
    public void rotateOne() {
        mmTile t = new mmTile(mmTerrain.PASTURE, mmRiverConfiguration.getSpringHead());
        t.rotate(new mmAngle(60));
        mmTileRenderInformation mmTileRenderInformation = new mmTileRenderInformation(t);
        assertEquals(mmTileRenderInformation.getMmTerrain(), mmTerrain.PASTURE);
        assertFalse(mmTileRenderInformation.getNorth());
        assertTrue(mmTileRenderInformation.getNorthEast());
        assertFalse(mmTileRenderInformation.getSouthEast());
        assertFalse(mmTileRenderInformation.getSouth());
        assertFalse(mmTileRenderInformation.getSouthWest());
        assertFalse(mmTileRenderInformation.getNorthWest());
    }

    @Test
    public void rotateTwo() {
        mmTile t = new mmTile(mmTerrain.PASTURE, mmRiverConfiguration.getSpringHead());
        t.rotate(new mmAngle(120));
        mmTileRenderInformation mmTileRenderInformation = new mmTileRenderInformation(t);
        assertEquals(mmTileRenderInformation.getMmTerrain(), mmTerrain.PASTURE);
        assertFalse(mmTileRenderInformation.getNorth());
        assertFalse(mmTileRenderInformation.getNorthEast());
        assertTrue(mmTileRenderInformation.getSouthEast());
        assertFalse(mmTileRenderInformation.getSouth());
        assertFalse(mmTileRenderInformation.getSouthWest());
        assertFalse(mmTileRenderInformation.getNorthWest());
    }

    @Test
    public void rotateThree() {
        mmTile t = new mmTile(mmTerrain.PASTURE, mmRiverConfiguration.getSpringHead());
        t.rotate(new mmAngle(180));
        mmTileRenderInformation mmTileRenderInformation = new mmTileRenderInformation(t);
        assertEquals(mmTileRenderInformation.getMmTerrain(), mmTerrain.PASTURE);
        assertFalse(mmTileRenderInformation.getNorth());
        assertFalse(mmTileRenderInformation.getNorthEast());
        assertFalse(mmTileRenderInformation.getSouthEast());
        assertTrue(mmTileRenderInformation.getSouth());
        assertFalse(mmTileRenderInformation.getSouthWest());
        assertFalse(mmTileRenderInformation.getNorthWest());
    }

    @Test
    public void rotateFour() {
        mmTile t = new mmTile(mmTerrain.PASTURE, mmRiverConfiguration.getSpringHead());
        t.rotate(new mmAngle(240));
        mmTileRenderInformation mmTileRenderInformation = new mmTileRenderInformation(t);
        assertEquals(mmTileRenderInformation.getMmTerrain(), mmTerrain.PASTURE);
        assertFalse(mmTileRenderInformation.getNorth());
        assertFalse(mmTileRenderInformation.getNorthEast());
        assertFalse(mmTileRenderInformation.getSouthEast());
        assertFalse(mmTileRenderInformation.getSouth());
        assertTrue(mmTileRenderInformation.getSouthWest());
        assertFalse(mmTileRenderInformation.getNorthWest());
    }

    @Test
    public void rotateFive() {
        mmTile t = new mmTile(mmTerrain.PASTURE, mmRiverConfiguration.getSpringHead());
        t.rotate(new mmAngle(300));
        mmTileRenderInformation mmTileRenderInformation = new mmTileRenderInformation(t);
        assertEquals(mmTileRenderInformation.getMmTerrain(), mmTerrain.PASTURE);
        assertFalse(mmTileRenderInformation.getNorth());
        assertFalse(mmTileRenderInformation.getNorthEast());
        assertFalse(mmTileRenderInformation.getSouthEast());
        assertFalse(mmTileRenderInformation.getSouth());
        assertFalse(mmTileRenderInformation.getSouthWest());
        assertTrue(mmTileRenderInformation.getNorthWest());
    }
}
