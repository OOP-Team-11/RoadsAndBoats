package view.render;

import direction.TileEdgeDirection;
import model.tile.Terrain;
import model.tile.Tile;

public class TileSelectorRenderInfo {
    private Tile topTile, middleTile, lowerTile;

    public TileSelectorRenderInfo(Tile prev, Tile cur, Tile next) {
        topTile = (Tile) prev.makeClone();
        middleTile = (Tile) cur.makeClone();
        lowerTile = (Tile) next.makeClone();

    }

    public Terrain getTerrainTypeSelection() {
        return middleTile.getTerrain();
    }
    public Tile getTopTile() {
        return topTile;
    }
    public Tile getMiddleTile() {
        return middleTile;
    }
    public Tile getLowerTile() {
        return lowerTile;
    }
}
