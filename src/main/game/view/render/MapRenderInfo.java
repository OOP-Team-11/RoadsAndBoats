package game.view.render;

import game.model.direction.Location;
import game.model.map.RBMap;
import game.model.tile.RiverConfiguration;
import game.model.tile.Terrain;

import java.util.Map;

public class MapRenderInfo {

    private Map<Location, Terrain> terrainMap;
    private Map<Location, RiverConfiguration> riverConfigurationMap;
    private RBMap rbMap;
    public MapRenderInfo(Map<Location, Terrain> terrainMap, Map<Location, RiverConfiguration> riverConfigurationMap, RBMap rbMap){
        this.terrainMap = terrainMap;
        this.riverConfigurationMap = riverConfigurationMap;
        this.rbMap = rbMap;
    }

    public Map<Location, Terrain> getTerrainMap(){
        return this.terrainMap;
    }
    public Map<Location, RiverConfiguration> getRiverConfigurationMap() { return this.riverConfigurationMap;}

    public RBMap getRbMap() {return rbMap;}

    // TODO
}
