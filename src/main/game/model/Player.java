package game.model;

import game.model.managers.TransportManager;
import game.model.managers.TransportProducerManager;

public class Player {

    private PlayerId playerId;
    private TransportManager transportManager;
    private TransportProducerManager transportProducerManager;

    public Player() {
        this.playerId = new PlayerId();
        this.transportManager = new TransportManager(this);
        this.transportProducerManager = new TransportProducerManager(this);
    }

    public PlayerId getPlayerId() {
        return this.playerId;
    }

    public TransportManager getTransportManager() {
        return this.transportManager;
    }

    public TransportProducerManager getTransportProducerManager() {
        return this.transportProducerManager;
    }
}
