package game.view.render;

import game.model.direction.Location;

import java.util.HashMap;
import java.util.Map;

public class MapStructureRenderInfo {

    public final Map<Location, StructureRenderInfo> structures;

    public MapStructureRenderInfo(Map<Location, StructureRenderInfo> structureRenderInfoMap)
    {
        this.structures = structureRenderInfoMap;
    }
}
