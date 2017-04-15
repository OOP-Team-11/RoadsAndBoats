package game.model.transport;

import game.model.PlayerId;
import game.model.gameImporter.Serializable;
import game.model.resources.Goose;
import game.model.resources.ResourceManager;

import java.util.List;
import java.util.Objects;
import java.util.Vector;

public abstract class Transport implements Serializable {
    private Vector<Goose> followers;
    private PlayerId playerId;
    private TransportId transportId;
    private ResourceManager resourceManager;
    private int moveCapacity;
    private int carryCapacity;

    Transport() {
        this.followers = new Vector<Goose>();
    }

    public void addFollowers(Vector<Goose> geese) { this.followers = geese; }
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
    public abstract boolean canReproduce();

    public PlayerId getPlayerId() {
        return this.playerId;
    }
    public TransportId getTransportId() { return this.transportId; }

    public ResourceManager getResourceManager() {
        return this.resourceManager;
    }

    public int getMoveCapacity() {
        return this.moveCapacity;
    }

    public int getCarryCapacity() {
        return this.carryCapacity;
    }

    public List<Goose> getFollowers() {
        return this.followers;
    }

    // use for view drawing and import/export to file ONLY
    public abstract TransportType getType();

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
