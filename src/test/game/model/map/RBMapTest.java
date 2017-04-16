package game.model.map;

import game.model.direction.Angle;
import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.tile.RiverConfiguration;
import game.model.tile.Terrain;
import game.model.tile.Tile;
import game.model.tile.TileCompartment;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RBMapTest
{
    private RBMap map;
    private Tile tileWithUpRiver;
    private Tile tileWithDownRiver;
    private Tile seaTile;
    private Tile seaTile2;

    @Before
    public void setUp()
    {
        map=new RBMap();

        tileWithUpRiver=new Tile(Terrain.PASTURE, RiverConfiguration.getSpringHead());

        tileWithDownRiver=new Tile(Terrain.MOUNTAIN, RiverConfiguration.getSpringHead());
        tileWithDownRiver.rotate(new Angle(180));

        seaTile=new Tile(Terrain.SEA, RiverConfiguration.getNoRivers());
        seaTile2=new Tile(Terrain.SEA, RiverConfiguration.getNoRivers());
    }

    @Test
    public void placeTile_2SpringHeads_RiversAttach()
    {
        map.placeTile(new Location(0,0,0), tileWithDownRiver);
        map.placeTile(new Location(0,-1,1), tileWithUpRiver);

        map.finalizeMap();

        TileCompartment topTileBottomComponent = tileWithDownRiver.getTileCompartment(TileCompartmentDirection.getSouth());
        TileCompartment bottomTileTopComponent = tileWithUpRiver.getTileCompartment(TileCompartmentDirection.getNorth());

        assertEquals(topTileBottomComponent, bottomTileTopComponent.getRivers().get(TileCompartmentDirection.getNorth()).getDestination());
        assertEquals(bottomTileTopComponent, topTileBottomComponent.getRivers().get(TileCompartmentDirection.getSouth()).getDestination());

        assertEquals(1, topTileBottomComponent.getRivers().size());
        assertEquals(1, bottomTileTopComponent.getRivers().size());
    }

    @Test
    public void placeTile_SpringHeadAndSea_RiversAttach()
    {
        map.placeTile(new Location(0,0,0), tileWithDownRiver);
        map.placeTile(new Location(0,-1,1), seaTile);

        map.finalizeMap();

        TileCompartment topTileBottomComponent = tileWithDownRiver.getTileCompartment(TileCompartmentDirection.getSouth());
        TileCompartment seaTileTopComponent = seaTile.getTileCompartment(TileCompartmentDirection.getNorth());

        assertEquals(topTileBottomComponent, seaTileTopComponent.getRivers().get(TileCompartmentDirection.getNorth()).getDestination());
        assertEquals(seaTileTopComponent, topTileBottomComponent.getRivers().get(TileCompartmentDirection.getSouth()).getDestination());

        assertEquals(1, topTileBottomComponent.getRivers().size());
        assertEquals(1, seaTileTopComponent.getRivers().size());
    }

    @Test
    public void placeTile_2Sea_RiversAttach()
    {
        map.placeTile(new Location(0,0,0), seaTile2);
        map.placeTile(new Location(0,-1,1), seaTile);

        map.finalizeMap();

        TileCompartment seaTile2BottomComponent = seaTile2.getTileCompartment(TileCompartmentDirection.getSouth());
        TileCompartment seaTileTopComponent = seaTile.getTileCompartment(TileCompartmentDirection.getNorth());

        assertEquals(seaTile2BottomComponent, seaTileTopComponent.getRivers().get(TileCompartmentDirection.getNorth()).getDestination());
        assertEquals(seaTileTopComponent, seaTile2BottomComponent.getRivers().get(TileCompartmentDirection.getSouth()).getDestination());

        assertEquals(1, seaTile2BottomComponent.getRivers().size());
        assertEquals(1, seaTileTopComponent.getRivers().size());
    }

    @Test
    public void placeTile_SeaToNonriver_NoRiverAttaches()
    {
        Tile desert=new Tile(Terrain.DESERT, RiverConfiguration.getNoRivers());

        map.placeTile(new Location(0,0,0), desert);
        map.placeTile(new Location(0,-1,1), seaTile);

        map.finalizeMap();

        TileCompartment desertBottomComponent = desert.getTileCompartment(TileCompartmentDirection.getSouth());
        TileCompartment seaTileTopComponent = seaTile.getTileCompartment(TileCompartmentDirection.getNorth());

        assertEquals(0, seaTileTopComponent.getRivers().size());
        assertEquals(0, desertBottomComponent.getRivers().size());
    }
}
