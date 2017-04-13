package game.model.transport;

import game.model.PlayerId;
import game.model.resources.Goose;
import game.model.resources.ResourceManager;

import java.util.Objects;
import java.util.Vector;

public abstract class Transport {
    private Vector<Goose> followers;
    private PlayerId playerId;
    private TransportId transportId;
    private ResourceManager resourceManager;
    private int moveCapacity;
    private int carryCapacity;

    Transport() {
        this.followers = new Vector<Goose>();
    }

    public void addFollower(Goose goose) { followers.add(goose); }
    public void removeFollowers() {
        for(Goose g : followers)
            followers.remove(g);
    }

    public Transport(PlayerId playerId, TransportId transportId, int moveCapacity, int carryCapacity) {
        this.playerId = playerId;
        this.transportId = transportId;
        this.moveCapacity = moveCapacity;
        this.carryCapacity = carryCapacity;
        this.resourceManager = new ResourceManager();
    }

    public abstract String getExportString();

    public PlayerId getPlayerId() {
        return this.playerId;
    }

    public ResourceManager getResourceManager() {
        return this.resourceManager;
    }

    @Override
    public int hashCode() {
        return Objects.hash(playerId, transportId, moveCapacity, carryCapacity);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Transport))
            return false;
        if (object == this)
            return true;

        Transport tr = (Transport) object;
        return tr.transportId.equals(transportId) && tr.playerId.equals(playerId)
                && tr.moveCapacity == moveCapacity && tr.carryCapacity == carryCapacity;
    }
}
