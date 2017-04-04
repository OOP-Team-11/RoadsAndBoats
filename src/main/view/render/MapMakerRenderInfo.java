package view.render;

import model.tile.Location;
import model.tile.Tile;
import java.util.Collection;
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
