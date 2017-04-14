package game.model;

import game.model.managers.TransportAbilityManager;
import game.model.managers.TransportManager;
import game.model.managers.TransportProducerManager;

public class Player {

    private PlayerId playerId;
    private TransportManager transportManager;
    private TransportProducerManager transportProducerManager;
    private TransportAbilityManager transportAbilityManager;

    public Player(TransportAbilityManager transportAbilityManager) {
        this.playerId = new PlayerId();
        this.transportManager = new TransportManager(this, transportAbilityManager);
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
