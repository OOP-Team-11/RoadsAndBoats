package game.view.render;

import game.model.direction.Location;
import game.model.direction.TileCompartmentLocation;
import game.model.structures.Structure;
import game.model.structures.StructureType;

import java.util.HashMap;
import java.util.List;

public class StructureRenderInfo
{
    private StructureType structureType;
    public StructureRenderInfo(Structure structure)
    {
        this.structureType = structure.getType();
    }

    public StructureType getStructureType() {
        return this.structureType;
    }
}
