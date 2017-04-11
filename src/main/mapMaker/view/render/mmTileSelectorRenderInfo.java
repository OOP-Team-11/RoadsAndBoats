package mapMaker.view.render;

public class mmTileSelectorRenderInfo {
    private mmTileRenderInformation topTile;
    private mmTileRenderInformation middleTile;
    private mmTileRenderInformation lowerTile;
    private Boolean validMap;


    public mmTileSelectorRenderInfo(mmTileRenderInformation topTile, mmTileRenderInformation middleTile, mmTileRenderInformation lowerTile, boolean validMap ) {
        this.topTile = topTile;
        this.middleTile = middleTile;
        this.lowerTile = lowerTile;
        this.validMap = validMap;
    }

    public mmTileRenderInformation getTopTile() {return topTile; }
    public mmTileRenderInformation getMiddleTile() {
        return middleTile;
    }
    public mmTileRenderInformation getLowerTile() {
        return lowerTile;
    }
    public Boolean getMapValidation() {return validMap; }
}
