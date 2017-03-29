package view.render;

import model.tile.Tile;
import java.util.Collection;

public class MapMakerRenderInfo {
    private Collection<Tile> tileInformation;

    public MapMakerRenderInfo() {
    }

    public Collection<Tile> getTileInformation() {
        return this.tileInformation;
    }
    public void setTileInformation(Collection<Tile> tileInfo) {
        this.tileInformation = tileInfo;
    }
}
