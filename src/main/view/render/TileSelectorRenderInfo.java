package view.render;

import model.tile.Terrain;
import model.tile.Tile;

public class TileSelectorRenderInfo {
    private Terrain terrainTypeSelection;
    private Tile topTile, middleTile, lowerTile;

    public Terrain getTerrainTypeSelection() {
        return terrainTypeSelection;
    }
    public void setTerrainTypeSelection(Terrain newTerrain) {
        this.terrainTypeSelection = newTerrain;
    }
    public Tile getTopTile() {
        return topTile;
    }
    public void setTopTile(Tile newTile) {
        this.topTile = newTile;
    }
    public Tile getMiddleTile() {
        return middleTile;
    }
    public void setMiddleTile(Tile newTile) {
        this.middleTile = newTile;
    }
    public Tile getLowerTile() {
        return lowerTile;
    }
    public void setLowerTile(Tile newTile) {
        this.lowerTile = newTile;
    }
}
