package game.model.managers;

import game.model.Player;
import game.model.PlayerId;
import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.transport.Transport;
import game.model.transport.TransportId;

import java.util.ArrayList;
import java.util.List;

public class TransportManager {

    private Player player;
    private List<Transport> transports; // might want to do hashmap of location to transport?
    public TransportManager(Player player) {
        this.transports = new ArrayList<>();
    }

    public PlayerId getPlayerId() {
        return this.player.getPlayerId();
    }

    public void addTransport(Transport transport, Location location, TileCompartmentDirection tileCompartmentDirection) {
        // TODO
    }

    public void moveTransport(Transport transport, Location location, TileCompartmentDirection tileCompartmentDirection) {
        // TODO
    }

    public Transport getTransport(TransportId transportId) {
        // TODO
        return null;
    }

    public List<Transport> getTransports() {
        return this.transports;
    }
}
