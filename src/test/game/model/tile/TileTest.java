package game.model.tile;

import game.model.direction.TileCompartmentDirection;
import org.junit.Test;

import java.util.HashSet;

import static org.junit.Assert.assertEquals;

public class TileTest
{
    @Test
    public void constructor_SpringheadRiverConfiguration_TwoCompartments()
    {
        Tile tile=new Tile(Terrain.DESERT, RiverConfiguration.getSpringHead());
        assertEquals(2, countCompartments(tile));
    }

    @Test
    public void constructor_AdjacentFacesRiverConfiguration_ThreeCompartments()
    {
        Tile tile=new Tile(Terrain.DESERT, RiverConfiguration.getAdjacentFaces());
        assertEquals(3, countCompartments(tile));
    }

    @Test
    public void constructor_NoRiverConfiguration_OneCompartments()
    {
        Tile tile=new Tile(Terrain.DESERT, RiverConfiguration.getNoRivers());
        assertEquals(1, countCompartments(tile));
    }

    @Test
    public void constructor_OppositeFacesRiverConfiguration_ThreeCompartments()
    {
        Tile tile=new Tile(Terrain.DESERT, RiverConfiguration.getOppositeFaces());
        assertEquals(3, countCompartments(tile));
    }

    @Test
    public void constructor_EveryOtherFaceRiverConfiguration_FourCompartments()
    {
        Tile tile=new Tile(Terrain.DESERT, RiverConfiguration.getEveryOtherFace());
        assertEquals(4, countCompartments(tile));
    }

    @Test
    public void constructor_SkipAFaceFaceRiverConfiguration_ThreeCompartments()
    {
        Tile tile=new Tile(Terrain.DESERT, RiverConfiguration.getSkipAFace());
        assertEquals(3, countCompartments(tile));
    }

    @Test
    public void constructor_SeaTerrain_OneCompartment()
    {
        Tile tile=new Tile(Terrain.SEA, RiverConfiguration.getNoRivers());
        assertEquals(1, countCompartments(tile));
    }

    private static int countCompartments(Tile tile)
    {
        HashSet<TileCompartment> comps=new HashSet<>();
        for(TileCompartmentDirection tcd: TileCompartmentDirection.getAllDirections())
        {
            comps.add(tile.getTileCompartment(tcd));
        }

        return comps.size();
    }

}
