package game.model.movement;

import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileEdgeDirection;
import game.model.tile.TileCompartment;
import game.model.transport.LandTransport;

public class Road {

    // TODO: Road Collection in TileCompartment *see UML*
    // TODO: RoadProducerManager, RoadProducerObserver, RoadProducerObservable, RoadInfoObservable *see UML*

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
