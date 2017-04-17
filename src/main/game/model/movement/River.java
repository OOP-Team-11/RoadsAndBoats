package game.model.movement;

import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileEdgeDirection;
import game.model.tile.TileCompartment;

public class River
{
    private TileCompartment destination;
    private TileCompartmentDirection compartmentDirection;

    public River(TileCompartmentDirection compartmentDirection)
    {
        this.compartmentDirection = compartmentDirection;
    }

    public void setDestination(TileCompartment destination)
    {
        this.destination=destination;
    }

    public TileCompartment getDestination()
    {
        return destination;
    }

    public TileCompartmentDirection getCompartmentDirection()
    {
        return compartmentDirection;
    }

    public TileEdgeDirection getEdgeDirection()
    {
        return new TileEdgeDirection(compartmentDirection.getAngle());
    }
}
