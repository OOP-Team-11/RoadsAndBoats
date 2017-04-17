package game.view.render;

import game.model.direction.TileCompartmentDirection;
import game.model.tile.Tile;

import java.util.Map;

public class TileResourceRenderInfo {

    private Map<TileCompartmentDirection, ResourceManagerRenderInfo> tileResourceMap;
    private Tile tile;
    public TileResourceRenderInfo(Tile tile, Map<TileCompartmentDirection, ResourceManagerRenderInfo> tileResourceMap) {
        this.tileResourceMap = tileResourceMap;
        this.tile = tile;
    }

    public Map<TileCompartmentDirection, ResourceManagerRenderInfo> getTileResourceMap() {
        return this.tileResourceMap;
    }

    public Tile getTile() {
        return tile;
    }
}
