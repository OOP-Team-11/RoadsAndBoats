package game.model.movement;

import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileEdgeDirection;
import game.model.tile.TileCompartment;

import java.util.Objects;

public class Move
{
    public TileCompartment getDestination() {
        return destination;
    }

    public final TileCompartment destination;
    public final TileCompartmentDirection tileCompartmentDirection;
    public final TileEdgeDirection tileEdgeDirection;

    public Move(TileCompartment destination, TileCompartmentDirection tileCompartmentDirection, TileEdgeDirection tileEdgeDirection)
    {
        this.destination = destination;
        this.tileCompartmentDirection = tileCompartmentDirection;
        this.tileEdgeDirection = tileEdgeDirection;
    }

    public TileCompartmentDirection getTileCompartmentDirection() {
        return tileCompartmentDirection;
    }

    public TileEdgeDirection getTileEdgeDirection() {
        return tileEdgeDirection;
    }

    public int hashCode()
    {
        return Objects.hash(destination);
    }

    public boolean equals(Object o)
    {
        if(o instanceof Move)
        {
            Move other = (Move)o;

            return this.destination.equals(other.destination);
        }
        else
        {
            return false;
        }
    }
}
