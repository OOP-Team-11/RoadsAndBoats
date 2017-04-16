package game.model.movement;

import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileEdgeDirection;
import game.model.tile.TileCompartment;
import game.model.transport.WaterTransport;

public class River
{
    private TileCompartment destination;

    public void setDestination(TileCompartment destination)
    {
        this.destination=destination;
    }

    public boolean transport(WaterTransport waterTransport)
    {
        return false;
    }

    public TileCompartment getDestination()
    {
        return destination;
    }
}
