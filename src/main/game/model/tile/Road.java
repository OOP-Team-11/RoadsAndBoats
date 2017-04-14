package game.model.tile;

import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileEdgeDirection;

public class Road
{
    private final Location myLocation;
    private final TileCompartment destination;
    private final TileEdgeDirection edgeDirection;
    private final TileCompartmentDirection compartmentDirection;

    public Road(Location myLocation, TileCompartment destination, TileEdgeDirection edgeDirection, TileCompartmentDirection compartmentDirection)
    {
        this.myLocation = myLocation;
        this.destination=destination;
        this.edgeDirection=edgeDirection;
        this.compartmentDirection=compartmentDirection;
    }

    public Location getLococation()
    {
        return myLocation;
    }

    public TileCompartment getDestination()
    {
        return destination;
    }

    public TileEdgeDirection getEdgeDirection()
    {
        return edgeDirection;
    }

    public TileCompartmentDirection getCompartmentDirection()
    {
        return compartmentDirection;
    }
}
