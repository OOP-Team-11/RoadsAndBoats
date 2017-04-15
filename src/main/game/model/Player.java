package game.model;

import game.model.managers.TransportAbilityManager;
import game.model.managers.TransportManager;
import game.model.managers.TransportProducerManager;
import game.utilities.observable.MapTransportRenderInfoObservable;
import game.utilities.observer.MapTransportRenderInfoObserver;

public class Player implements MapTransportRenderInfoObservable{

    private PlayerId playerId;
    private TransportManager transportManager;
    private TransportProducerManager transportProducerManager;
    private String name;

    public Player(TransportAbilityManager transportAbilityManager, PlayerId playerId, String name) {
        this.playerId = playerId;
        this.transportManager = new TransportManager(this, transportAbilityManager);
        this.transportProducerManager = new TransportProducerManager(this);
        this.name = name;
        transportAbilityManager.getGooseManager().addTransportManager(this.transportManager);
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

    @Override
    public void attach(MapTransportRenderInfoObserver observer) {
        this.transportManager.attach(observer);
    }

    @Override
    public void detach(MapTransportRenderInfoObserver observer) {
        this.transportManager.detach(observer);
    }
}
