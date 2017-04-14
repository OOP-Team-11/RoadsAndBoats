package game.view.render;

import game.model.direction.TileCompartmentLocation;
import game.model.structures.Structure;

import java.util.HashMap;
import java.util.List;

public class StructureRenderInfo
{
    public final HashMap<TileCompartmentLocation, List<Structure>> structures;

    public StructureRenderInfo(HashMap<TileCompartmentLocation, List<Structure>> structures)
    {
        this.structures = structures;
    }
}
