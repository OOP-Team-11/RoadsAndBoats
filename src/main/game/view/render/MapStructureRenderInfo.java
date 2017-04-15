package game.view.render;

import game.model.direction.Location;
import game.model.direction.TileCompartmentLocation;

import java.util.HashMap;
import java.util.Map;

public class MapStructureRenderInfo {

    private final Map<TileCompartmentLocation, StructureRenderInfo> structures;

    public MapStructureRenderInfo(Map<TileCompartmentLocation, StructureRenderInfo> structureRenderInfoMap)
    {
        this.structures = structureRenderInfoMap;
    }

    public Map<TileCompartmentLocation, StructureRenderInfo> getStructures() {
        return this.structures;
    }
}
