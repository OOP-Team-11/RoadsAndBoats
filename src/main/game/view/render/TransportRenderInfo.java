package game.view.render;

import game.model.PlayerId;
import game.model.direction.TileCompartmentLocation;
import game.model.resources.Goose;
import game.model.transport.Transport;
import game.model.transport.TransportType;

import java.util.HashMap;
import java.util.List;

public class TransportRenderInfo
{
    private Transport transport;
    
    public TransportRenderInfo(Transport transport)
    {
        this.transport = transport;
    }

    public TransportType getTransportType() {
        return this.transport.getType();
    }

    public int getMoveCapacity() {
        return this.transport.getMoveCapacity();
    }

    public int getCarryCapacity() {
        return this.transport.getCarryCapacity();
    }

    public List<Goose> getFollowers() {
        return this.transport.getFollowers();
    }

    public PlayerId getOwner() {
        return transport.getPlayerId();
    }
}
