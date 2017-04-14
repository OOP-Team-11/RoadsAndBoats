package game.view.render;

import game.model.direction.TileCompartmentLocation;
import game.model.resources.ResourceType;

import java.util.HashMap;

public class ResourceRenderInfo
{
    public final HashMap<TileCompartmentLocation, HashMap<ResourceType, Integer>> resources;

    public ResourceRenderInfo(HashMap<TileCompartmentLocation, HashMap<ResourceType, Integer>> resources)
    {
        this.resources = resources;
    }
}
