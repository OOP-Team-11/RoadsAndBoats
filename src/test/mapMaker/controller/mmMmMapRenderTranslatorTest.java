package mapMaker.controller;

import mapMaker.direction.mmTileEdgeDirection;
import mapMaker.model.mmMap;
import mapMaker.model.tile.*;
import mapMaker.model.tile.riverConfiguration.mmRiverConfiguration;
import mapMaker.view.render.mmTileRenderInformation;
import org.junit.Before;
import org.junit.Test;

public class mmMmMapRenderTranslatorTest {

    private mmMap mmMap;

    @Before
    public void setUp() throws mmInvalidLocationException {
        this.mmMap = new mmMap();
        mmRiverConfiguration riverConfig = mmRiverConfiguration.getNoRivers();
        mmTile mmTile1 = new mmTile(mmTerrain.PASTURE, riverConfig);
        mmTile mmTile2 = new mmTile(mmTerrain.PASTURE, riverConfig);
        mmTile mmTile3 = new mmTile(mmTerrain.PASTURE, riverConfig);
        mmTile mmTile4 = new mmTile(mmTerrain.PASTURE, riverConfig);
        mmTile mmTile5 = new mmTile(mmTerrain.PASTURE, riverConfig);
        mmTile mmTile6 = new mmTile(mmTerrain.PASTURE, riverConfig);
        mmTile mmTile7 = new mmTile(mmTerrain.PASTURE, riverConfig);

        mmLocation l1 = new mmLocation(0,0,0);
        mmLocation l2 = new mmLocation(1,0,-1);
        mmLocation l3 = new mmLocation(0,1,-1);
        mmLocation l4 = new mmLocation(-1,1,0);
        mmLocation l5 = new mmLocation(-1,0,1);
        mmLocation l6 = new mmLocation(0,-1,1);
        mmLocation l7 = new mmLocation(1,-1,0);

        this.mmMap.placeTile(l1, mmTile1);
        this.mmMap.placeTile(l2, mmTile2);
        this.mmMap.placeTile(l3, mmTile3);
        this.mmMap.placeTile(l4, mmTile4);
        this.mmMap.placeTile(l5, mmTile5);
        this.mmMap.placeTile(l6, mmTile6);
        this.mmMap.placeTile(l7, mmTile7);
    }

    private boolean tileEdgeCanConnectRiver(mmTileEdge mmTileEdge) {
        return mmTileEdge.canConnectRiver();
    }

    private boolean checkTileRenderInformatinMatchesTile(mmTile mmTile, mmTileRenderInformation mmTileRenderInformation) {
        mmTileEdge northEdge = mmTile.getTileEdge(mmTileEdgeDirection.getNorth());
        if (tileEdgeCanConnectRiver(northEdge) != mmTileRenderInformation.getNorth()) return false;

        mmTileEdge northEast = mmTile.getTileEdge(mmTileEdgeDirection.getNorthEast());
        if (tileEdgeCanConnectRiver(northEast) != tileEdgeCanConnectRiver(northEast)) return false;

        mmTileEdge southEast = mmTile.getTileEdge(mmTileEdgeDirection.getSouthEast());
        if (tileEdgeCanConnectRiver(southEast) != tileEdgeCanConnectRiver(southEast)) return false;

        mmTileEdge south = mmTile.getTileEdge(mmTileEdgeDirection.getSouth());
        if (tileEdgeCanConnectRiver(south) != tileEdgeCanConnectRiver(south)) return false;

        mmTileEdge southWest = mmTile.getTileEdge(mmTileEdgeDirection.getSouthWest());
        if (tileEdgeCanConnectRiver(southWest) != tileEdgeCanConnectRiver(southWest)) return false;

        mmTileEdge northWest = mmTile.getTileEdge(mmTileEdgeDirection.getNorthWest());
        if (tileEdgeCanConnectRiver(northWest) != tileEdgeCanConnectRiver(northWest)) return false;

        return true;
    }

    private boolean checkRenderMapMatchesRealMap(java.util.Map<mmLocation, mmTile> map, java.util.Map<mmLocation, mmTileRenderInformation> renderMap) {
        if (map.size() != renderMap.size()) return false;

        for (java.util.Map.Entry<mmLocation, mmTile> entry : map.entrySet()) {
            if (!checkTileRenderInformatinMatchesTile(entry.getValue(), renderMap.get(entry.getKey())))
                return false;

            if (entry.getValue().getMmTerrain() != renderMap.get(entry.getKey()).getMmTerrain()) return false;
        }

        return true;
    }

    @Test
    public void testTranslation() throws mmInvalidLocationException {
        java.util.Map<mmLocation, mmTileRenderInformation> renderMap = mmMapRenderTranslator.getRenderInformationForMap(this.mmMap);
        this.checkRenderMapMatchesRealMap(this.mmMap.getTiles(), renderMap);

    }
}
