package game.model;

import game.model.direction.TileCompartmentLocation;
import game.model.managers.TransportAbilityManager;
import game.model.managers.TransportManager;
import game.model.managers.TransportProducerManager;
import game.model.transport.DonkeyTransport;
import game.model.transport.Transport;
import game.model.transport.TransportId;
import game.utilities.observable.MapTransportRenderInfoObservable;
import game.utilities.observer.MapTransportRenderInfoObserver;

public class Player implements MapTransportRenderInfoObservable{

    private PlayerId playerId;
    private TransportManager transportManager;
    private TransportProducerManager transportProducerManager;
    private String name;
    private TileCompartmentLocation startingLocation;

    public Player(TransportManager transportManager, PlayerId playerId, String name, TileCompartmentLocation startingLocation) {
        this.playerId = playerId;
        this.transportManager = transportManager;
        this.transportProducerManager = new TransportProducerManager(this);
        this.name = name;
        this.startingLocation = startingLocation;
        initializeTransports();
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

    private void initializeTransports() {
        Transport donkey1 = new DonkeyTransport(this.playerId, new TransportId());
        Transport donkey2 = new DonkeyTransport(this.playerId, new TransportId());
        Transport donkey3 = new DonkeyTransport(this.playerId, new TransportId());

        this.transportManager.addTransport(donkey1, this.startingLocation);
        this.transportManager.addTransport(donkey2, this.startingLocation);
        this.transportManager.addTransport(donkey3, this.startingLocation);
    }
}
