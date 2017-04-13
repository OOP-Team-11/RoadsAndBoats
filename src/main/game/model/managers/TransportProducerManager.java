package game.model.managers;

import game.model.Player;
import game.model.PlayerId;
import game.model.structures.transportProducer.TransportProducer;
import game.model.direction.Location;

import java.util.List;

public class TransportProducerManager {

    private Player player;
    private List<TransportProducer> transportProducers; // might want to do hashmap of location to transportproducer?
    public TransportProducerManager(Player player) {
        this.player = player;
    }

    public PlayerId getPlayerId() {
        return this.player.getPlayerId();
    }

    public TransportProducer getTransportProducer(Location location) {
        // TODO
        return null;
    }

    public void addTransportProducer(Location location, TransportProducer transportProducer) {
        // TODO
    }

    public List<TransportProducer> getTransportProducers() {
        return transportProducers;
    }
}
