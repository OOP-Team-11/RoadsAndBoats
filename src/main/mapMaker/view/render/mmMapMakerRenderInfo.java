package mapMaker.view.render;

import mapMaker.model.tile.mmLocation;

import java.util.Map;

public class mmMapMakerRenderInfo {
    private Map<mmLocation, mmTileRenderInformation> tileInformation;

    public mmMapMakerRenderInfo(Map<mmLocation, mmTileRenderInformation> tileInfo) {
        this.tileInformation = tileInfo;
    }
    public Map<mmLocation, mmTileRenderInformation> getTileInformation()
    {
        return this.tileInformation;
    }
    public void setTileInformation(Map<mmLocation, mmTileRenderInformation> tileInfo) {
        this.tileInformation = tileInfo;
    }
}
