package game.view.render;

import game.model.direction.Location;
import game.model.map.RBMap;
import game.model.tile.RiverConfiguration;
import game.model.tile.RoadConfiguration;
import game.model.tile.Terrain;

import java.util.Map;

public class MapRenderInfo
{
    private Map<Location, Terrain> terrainMap;
    private Map<Location, RiverConfiguration> riverConfigurationMap;
    private final Map<Location, RoadConfiguration> roadConfigurationMap;
    private RBMap rbMap;

    public MapRenderInfo(Map<Location, Terrain> terrainMap, Map<Location, RiverConfiguration> riverConfigurationMap, Map<Location, RoadConfiguration> roadConfigurationMap, RBMap rbMap)
    {
        this.terrainMap = terrainMap;
        this.riverConfigurationMap = riverConfigurationMap;
        this.roadConfigurationMap = roadConfigurationMap;
        this.rbMap = rbMap;
    }

    public Map<Location, Terrain> getTerrainMap()
    {
        return this.terrainMap;
    }

    public Map<Location, RiverConfiguration> getRiverConfigurationMap()
    {
        return this.riverConfigurationMap;
    }

    public RBMap getRbMap()
    {
        return rbMap;
    }

    public Map<Location, RoadConfiguration> getRoadConfigurationMap()
    {
        return roadConfigurationMap;
    }

    // TODO
}
