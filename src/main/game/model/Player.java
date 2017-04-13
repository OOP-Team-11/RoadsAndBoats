package game.model;

import game.model.managers.AbilityManager;
import game.model.managers.TransportManager;
import game.model.managers.TransportProducerManager;

public class Player {

    private PlayerId playerId;
    private TransportManager transportManager;
    private TransportProducerManager transportProducerManager;
    private AbilityManager abilityManager;

    public Player(AbilityManager abilityManager) {
        this.playerId = new PlayerId();
        this.transportManager = new TransportManager(this);
        this.transportProducerManager = new TransportProducerManager(this);
        this.abilityManager = abilityManager;
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
