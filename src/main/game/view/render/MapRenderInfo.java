package game.view.render;

import game.model.direction.Location;
import game.model.tile.RiverConfiguration;
import game.model.tile.Terrain;

import java.util.Map;

public class MapRenderInfo {

    private Map<Location, Terrain> terrainMap;
    private Map<Location, RiverConfiguration> riverConfigurationMap;
    public MapRenderInfo(Map<Location, Terrain> terrainMap, Map<Location, RiverConfiguration> riverConfigurationMap){
        this.terrainMap = terrainMap;
        this.riverConfigurationMap = riverConfigurationMap;
    }

    // TODO
}
