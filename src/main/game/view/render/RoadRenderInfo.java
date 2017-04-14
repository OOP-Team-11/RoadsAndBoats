package game.view.render;

import game.model.direction.Location;
import game.model.direction.TileCompartmentLocation;
import game.model.tile.Road;

import java.util.HashMap;

public class RoadRenderInfo
{
    public final HashMap<Location, Road> roads;

    public RoadRenderInfo(HashMap<Location, Road> roads)
    {
        this.roads = roads;
    }
}
