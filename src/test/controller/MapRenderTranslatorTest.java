package controller;

import direction.TileEdgeDirection;
import model.Map;
import model.tile.*;
import model.tile.riverConfiguration.RiverConfiguration;
import org.junit.Before;
import org.junit.Test;
import view.render.TileRenderInformation;

public class MapRenderTranslatorTest {

    private Map map;

    @Before
    public void setUp() throws InvalidLocationException {
        this.map = new Map();
        RiverConfiguration riverConfiguration = RiverConfiguration.getNoRivers();
        Tile tile1 = new Tile(Terrain.PASTURE, riverConfiguration);
        Tile tile2 = new Tile(Terrain.PASTURE, riverConfiguration);
        Tile tile3 = new Tile(Terrain.PASTURE, riverConfiguration);
        Tile tile4 = new Tile(Terrain.PASTURE, riverConfiguration);
        Tile tile5 = new Tile(Terrain.PASTURE, riverConfiguration);
        Tile tile6 = new Tile(Terrain.PASTURE, riverConfiguration);
        Tile tile7 = new Tile(Terrain.PASTURE, riverConfiguration);

        Location l1 = new Location(0,0,0);
        Location l2 = new Location(1,0,-1);
        Location l3 = new Location(0,1,-1);
        Location l4 = new Location(-1,1,0);
        Location l5 = new Location(-1,0,1);
        Location l6 = new Location(0,-1,1);
        Location l7 = new Location(1,-1,0);

        this.map.placeTile(l1, tile1);
        this.map.placeTile(l2, tile2);
        this.map.placeTile(l3, tile3);
        this.map.placeTile(l4, tile4);
        this.map.placeTile(l5, tile5);
        this.map.placeTile(l6, tile6);
        this.map.placeTile(l7, tile7);
    }

    private boolean tileEdgeCanConnectRiver(TileEdge tileEdge) {
        return tileEdge.canConnectRiver();
    }

    private boolean checkTileRenderInformatinMatchesTile(Tile tile, TileRenderInformation tileRenderInformation) {
        TileEdge northEdge = tile.getTileEdge(TileEdgeDirection.getNorth());
        if (tileEdgeCanConnectRiver(northEdge) != tileRenderInformation.getNorth()) return false;

        TileEdge northEast = tile.getTileEdge(TileEdgeDirection.getNorthEast());
        if (tileEdgeCanConnectRiver(northEast) != tileEdgeCanConnectRiver(northEast)) return false;

        TileEdge southEast = tile.getTileEdge(TileEdgeDirection.getSouthEast());
        if (tileEdgeCanConnectRiver(southEast) != tileEdgeCanConnectRiver(southEast)) return false;

        TileEdge south = tile.getTileEdge(TileEdgeDirection.getSouth());
        if (tileEdgeCanConnectRiver(south) != tileEdgeCanConnectRiver(south)) return false;

        TileEdge southWest = tile.getTileEdge(TileEdgeDirection.getSouthWest());
        if (tileEdgeCanConnectRiver(southWest) != tileEdgeCanConnectRiver(southWest)) return false;

        TileEdge northWest = tile.getTileEdge(TileEdgeDirection.getNorthWest());
        if (tileEdgeCanConnectRiver(northWest) != tileEdgeCanConnectRiver(northWest)) return false;

        return true;
    }

    private boolean checkRenderMapMatchesRealMap(java.util.Map<Location, Tile> map, java.util.Map<Location, TileRenderInformation> renderMap) {
        if (map.size() != renderMap.size()) return false;

        for (java.util.Map.Entry<Location, Tile> entry : map.entrySet()) {
            if (!checkTileRenderInformatinMatchesTile(entry.getValue(), renderMap.get(entry.getKey())))
                return false;

            if (entry.getValue().getTerrain() != renderMap.get(entry.getKey()).getTerrain()) return false;
        }

        return true;
    }

    @Test
    public void testTranslation() throws InvalidLocationException {
        java.util.Map<Location, TileRenderInformation> renderMap = MapRenderTranslator.getRenderInformationForMap(this.map);
        this.checkRenderMapMatchesRealMap(this.map.getTiles(), renderMap);

    }
}
