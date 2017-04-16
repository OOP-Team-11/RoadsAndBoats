package game.model.movement;

import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileEdgeDirection;
import game.model.tile.TileCompartment;
import game.model.transport.WaterTransport;

public class River {

    private Location myLocation;
    private TileCompartmentDirection tcd;
    private TileEdgeDirection ted;
    private TileCompartment destination;

    public River() {

    }

    public boolean transport(WaterTransport waterTransport) {
        return false;
    }

}
