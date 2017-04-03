package view.render;

import direction.TileEdgeDirection;
import model.tile.Location;
import model.tile.Terrain;
import model.tile.Tile;

public class TileSelectorRenderInfo {
    private TileRenderInformation topTile;
    private TileRenderInformation middleTile;
    private TileRenderInformation lowerTile;
    private Boolean validMap;


    public TileSelectorRenderInfo(TileRenderInformation topTile, TileRenderInformation middleTile, TileRenderInformation lowerTile, boolean validMap ) {
        this.topTile = topTile;
        this.middleTile = middleTile;
        this.lowerTile = lowerTile;
        this.validMap = validMap;
    }

    public TileRenderInformation getTopTile() {return topTile; }
    public TileRenderInformation getMiddleTile() {
        return middleTile;
    }
    public TileRenderInformation getLowerTile() {
        return lowerTile;
    }
    public Boolean getMapValidation() {return validMap; }
}
