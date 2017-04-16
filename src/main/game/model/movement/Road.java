package game.model.movement;

import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileEdgeDirection;
import game.model.tile.TileCompartment;
import game.model.transport.LandTransport;

public class Road {

    // TODO: Road Collection in TileCompartment *see UML*
    // TODO: RoadProducerManager, RoadProducerObserver, RoadProducerObservable, RoadInfoObservable *see UML*

    private Location myLocation;
    private TileCompartmentDirection tcd;
    private TileEdgeDirection ted;
    private TileCompartment destination;

    public Road() {

    }

    public boolean transport(LandTransport landTransport) {
        return false;
    }
}
