package mapMaker.view.render;

import mapMaker.model.tile.Location;

import java.util.Map;

public class MapMakerRenderInfo {
    private Map<Location, TileRenderInformation> tileInformation;

    public MapMakerRenderInfo(Map<Location, TileRenderInformation> tileInfo) {
        this.tileInformation = tileInfo;
    }
    public Map<Location, TileRenderInformation> getTileInformation()
    {
        return this.tileInformation;
    }
    public void setTileInformation(Map<Location, TileRenderInformation> tileInfo) {
        this.tileInformation = tileInfo;
    }
}
