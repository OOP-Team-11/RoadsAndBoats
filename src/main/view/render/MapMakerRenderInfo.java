package view.render;

import model.tile.Location;
import model.tile.Tile;
import java.util.Collection;
import java.util.Map;

public class MapMakerRenderInfo {
    private Map<Location, Tile> tileInformation;

    public MapMakerRenderInfo(Map<Location, Tile> tileInfo) {
        this.tileInformation = tileInfo;
    }

    public Map<Location, Tile> getTileInformation() {
        return this.tileInformation;
    }
    public void setTileInformation(Map<Location, Tile> tileInfo) {
        this.tileInformation = tileInfo;
    }
}
