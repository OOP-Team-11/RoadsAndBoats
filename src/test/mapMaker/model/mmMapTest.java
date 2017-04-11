package mapMaker.model;

import mapMaker.direction.mmAngle;
import mapMaker.model.tile.mmInvalidLocationException;
import mapMaker.model.tile.mmLocation;
import mapMaker.model.tile.mmTerrain;
import mapMaker.model.tile.mmTile;
import mapMaker.model.tile.riverConfiguration.mmRiverConfiguration;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class mmMapTest
{
    private mmMap mmMap;
    private mmRiverConfiguration mmRiverConfiguration;

    @Before
    public void setUp() throws mmInvalidLocationException
    {
        mmMap = new mmMap();
        mmRiverConfiguration = mmRiverConfiguration.getNoRivers();

        setUpTiles();
    }

    private void setUpTiles()
    {
        try
        {
            mmMap.placeTile(new mmLocation(-1, 3, -2), new mmTile(mmTerrain.WOODS, mmRiverConfiguration));
            mmMap.placeTile(new mmLocation(-1, 2, -1), new mmTile(mmTerrain.SEA, mmRiverConfiguration));
            mmMap.placeTile(new mmLocation(-1, 1, 0), new mmTile(mmTerrain.MOUNTAIN, mmRiverConfiguration));
            mmMap.placeTile(new mmLocation(-1, 0, 1), new mmTile(mmTerrain.SEA, mmRiverConfiguration));
            mmMap.placeTile(new mmLocation(-1, -1, 2), new mmTile(mmTerrain.ROCK, mmRiverConfiguration));
            mmMap.placeTile(new mmLocation(-1, -2, 3), new mmTile(mmTerrain.DESERT, mmRiverConfiguration));
            mmMap.placeTile(new mmLocation(-2, 3, -1), new mmTile(mmTerrain.WOODS, mmRiverConfiguration));
            mmMap.placeTile(new mmLocation(-2, 2, 0), new mmTile(mmTerrain.ROCK, mmRiverConfiguration));
            mmMap.placeTile(new mmLocation(-2, 1, 1), new mmTile(mmTerrain.SEA, mmRiverConfiguration));
            mmMap.placeTile(new mmLocation(-2, 0, 2), new mmTile(mmTerrain.ROCK, mmRiverConfiguration));
            mmMap.placeTile(new mmLocation(-2, -1, 3), new mmTile(mmTerrain.MOUNTAIN, mmRiverConfiguration.getSpringHead()));
            mmMap.placeTile(new mmLocation(-3, 3, 0), new mmTile(mmTerrain.SEA, mmRiverConfiguration));
            mmMap.placeTile(new mmLocation(-3, 2, 1), new mmTile(mmTerrain.PASTURE, mmRiverConfiguration));
            mmMap.placeTile(new mmLocation(-3, 1, 2), new mmTile(mmTerrain.WOODS, mmRiverConfiguration));
            mmMap.placeTile(new mmLocation(-3, 0, 3), new mmTile(mmTerrain.PASTURE, mmRiverConfiguration.getSpringHead()));
        } catch (mmInvalidLocationException e)
        {
            fail("Invalid mmLocation created while setting up tiles");
        }
    }

    @Test

    public void getTile_invalidLocation_returnsNull() throws mmInvalidLocationException
    {
        mmTile mmTile = mmMap.getTile(new mmLocation(1, 0, -1));
        assertNull(mmTile);
    }

    @Test
    public void getTile_validLocation_returnsCorrectTerrain() throws mmInvalidLocationException
    {
        mmTile mmTile = mmMap.getTile(new mmLocation(-1, -2, 3));
        assertEquals(mmTerrain.DESERT, mmTile.getMmTerrain());
    }

    @Test
    public void isValidPlacement_validWithoutRivers_returnsTrue() throws mmInvalidLocationException
    {
        mmLocation loc = new mmLocation(0, -3, 3);
        mmTile mmTile = new mmTile(mmTerrain.MOUNTAIN, mmRiverConfiguration);
        boolean isValid = mmMap.isValidPlacement(loc, mmTile);
        assertTrue(isValid);
    }

    @Test
    public void isValidPlacement_invalidUnattatchedTile_returnsFalse() throws mmInvalidLocationException
    {
        mmLocation loc = new mmLocation(10, -3, -7);
        mmTile mmTile = new mmTile(mmTerrain.MOUNTAIN, mmRiverConfiguration);
        boolean isValid = mmMap.isValidPlacement(loc, mmTile);
        assertFalse(isValid);
    }

    //0 -1 1 and 1 -1 0
    @Test
    public void isValidPlacement_validSecondTile_returnsTrue() throws mmInvalidLocationException
    {
        mmMap mmMap = new mmMap();
        mmMap.placeTile(new mmLocation(0, -1, 1), new mmTile(mmTerrain.MOUNTAIN, mmRiverConfiguration));
        mmLocation loc = new mmLocation(1, -1, 0);
        mmTile mmTile = new mmTile(mmTerrain.DESERT, mmRiverConfiguration.getNoRivers());
        boolean isValid = mmMap.isValidPlacement(loc, mmTile);

        assertTrue(isValid);
    }

    @Test
    public void isValidPlacement_validWithTwoRiversConnectingToSeas_returnsTrue() throws mmInvalidLocationException
    {
        mmLocation loc = new mmLocation(-1, 0, 1);
        mmTile mmTile = new mmTile(mmTerrain.MOUNTAIN, mmRiverConfiguration.getAdjacentFaces());
        mmTile.rotate(new mmAngle(60));
        mmMap.removeTileAtLocation(loc);
        boolean isValid = mmMap.isValidPlacement(loc, mmTile);
        assertTrue(isValid);
    }

    @Test
    public void isValidPlacement_validWithThreeRiversConnectingToSeas_returnsTrue() throws mmInvalidLocationException
    {
        mmLocation loc = new mmLocation(-2, 2, 0);
        mmTile mmTile = new mmTile(mmTerrain.MOUNTAIN, mmRiverConfiguration.getEveryOtherFace());
        mmTile.rotate(new mmAngle(60));
        mmMap.removeTileAtLocation(loc);
        boolean isValid = mmMap.isValidPlacement(loc, mmTile);
        assertTrue(isValid);
    }

    @Test
    public void isValidPlacement_validWithRiverConnectingToSeaAndRiver_returnsTrue() throws mmInvalidLocationException
    {
        mmLocation loc = new mmLocation(-2, 0, 2);
        mmTile mmTile = new mmTile(mmTerrain.MOUNTAIN, mmRiverConfiguration.getOppositeFaces());
        mmMap.removeTileAtLocation(loc);
        boolean isValid = mmMap.isValidPlacement(loc, mmTile);
        assertTrue(isValid);
    }

    @Test
    public void isValidPlacement_validWithRiverConnectingToRiverAndOffMap_returnsTrue() throws mmInvalidLocationException
    {
        mmLocation loc = new mmLocation(-3, 1, 2);
        mmTile mmTile = new mmTile(mmTerrain.MOUNTAIN, mmRiverConfiguration.getAdjacentFaces());
        mmTile.rotate(new mmAngle(180));
        mmMap.removeTileAtLocation(loc);
        boolean isValid = mmMap.isValidPlacement(loc, mmTile);
        assertTrue(isValid);
    }

    @Test
    public void isValidPlacement_unconnectedRivers_returnsFalse() throws mmInvalidLocationException
    {
        mmLocation loc = new mmLocation(0, -2, 2);
        mmTile mmTile = new mmTile(mmTerrain.MOUNTAIN, mmRiverConfiguration.getAdjacentFaces());
        mmTile.rotate(new mmAngle(180));
        boolean isValid = mmMap.isValidPlacement(loc, mmTile);
        assertFalse(isValid);
    }

    @Test
    public void isValidPlacement_doesntConnectExistingRiver_returnsFalse() throws mmInvalidLocationException
    {
        mmLocation loc = new mmLocation(-3, 1, 2);
        mmTile mmTile = new mmTile(mmTerrain.MOUNTAIN, mmRiverConfiguration);
        mmTile.rotate(new mmAngle(180));
        boolean isValid = mmMap.isValidPlacement(loc, mmTile);
        assertFalse(isValid);
    }

    @Test
    public void recenter_preConstructedMap_properCenterTerrain() throws mmInvalidLocationException
    {
        mmMap.recenter();
        assertEquals(mmTerrain.ROCK, mmMap.getTile(new mmLocation(0, 0, 0)).getMmTerrain());
    }

    @Test
    public void recenter_preConstructedMap_properDesertLocation() throws mmInvalidLocationException
    {
        mmMap.recenter();
        assertEquals(mmTerrain.DESERT, mmMap.getTile(new mmLocation(1, -2, 1)).getMmTerrain());
    }

    @Test
    public void recenter_lineOfTiles_correctCenter() throws mmInvalidLocationException
    {
//        Build line of tiles to the right starting at 0,0,0
        mmMap mmMap = new mmMap();
        mmMap.placeTile(new mmLocation(0, 0, 0), new mmTile(mmTerrain.SEA, mmRiverConfiguration));
        mmMap.placeTile(new mmLocation(1, -1, 0), new mmTile(mmTerrain.SEA, mmRiverConfiguration));
        mmMap.placeTile(new mmLocation(2, -2, 0), new mmTile(mmTerrain.DESERT, mmRiverConfiguration));
        mmMap.placeTile(new mmLocation(3, -3, 0), new mmTile(mmTerrain.SEA, mmRiverConfiguration));
        mmMap.placeTile(new mmLocation(4, -4, 0), new mmTile(mmTerrain.SEA, mmRiverConfiguration));
        mmMap.recenter();
//        New center should be Desert terrain tile
        assertEquals(mmMap.getTile(new mmLocation(0, 0, 0)).getMmTerrain(), mmTerrain.DESERT);
    }

    @Test
    public void recenter_emptyMap_nullTile() throws mmInvalidLocationException
    {
        mmMap mmMap = new mmMap();
        mmMap.recenter();
        assertEquals(null, mmMap.getTile(new mmLocation(0, 0, 0)));
    }
}
