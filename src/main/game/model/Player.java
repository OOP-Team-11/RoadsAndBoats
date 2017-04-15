package game.model;

import game.model.managers.TransportAbilityManager;
import game.model.managers.TransportManager;
import game.model.managers.TransportProducerManager;

public class Player {

    private PlayerId playerId;
    private TransportManager transportManager;
    private TransportProducerManager transportProducerManager;
    private String name;

    public Player(TransportAbilityManager transportAbilityManager, PlayerId playerId, String name) {
        this.playerId = playerId;
        this.transportManager = new TransportManager(this, transportAbilityManager);
        this.transportProducerManager = new TransportProducerManager(this);
        this.name = name;
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

    public String getName() {
        return this.name;
    }
}
