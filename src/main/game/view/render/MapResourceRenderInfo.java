package game.view.render;

import game.model.direction.TileCompartmentLocation;
import game.model.resources.ResourceType;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MapResourceRenderInfo
{
    private final Map<TileCompartmentLocation, ResourceManagerRenderInfo> resources;

    public MapResourceRenderInfo(Map<TileCompartmentLocation, ResourceManagerRenderInfo> resources)
    {
        this.resources = resources;
    }

    public Map<TileCompartmentLocation, Map<ResourceType, Integer>> getResourceMap() {
        Map<TileCompartmentLocation, Map<ResourceType, Integer>> resourceMap = new HashMap<>();
        Iterator it = resources.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry)it.next();
            TileCompartmentLocation tcl = (TileCompartmentLocation) pair.getKey();
            ResourceManagerRenderInfo renderInfo = (ResourceManagerRenderInfo) pair.getValue();
            resourceMap.put(tcl, renderInfo.getResourceMap());
        }
        return resourceMap;
    }
}
