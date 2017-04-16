package game.model.transport;

import game.model.PlayerId;
import game.model.gameImportExport.Serializable;
import game.model.resources.Goose;
import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;

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

    public int getWealthPoints() {
        return resourceManager.getWealthPoints();
    }

    public boolean storeResource(ResourceType type, Integer numberToAdd) {
        if (canStoreResource(numberToAdd)) {
            lowerCarryCapacity(numberToAdd);
            resourceManager.addResource(type, numberToAdd);
            return true;
        }
        return false;
    }

    public boolean takeResource(ResourceType type, Integer numberToRemove) {
        if (resourceManager.removeResource(type, numberToRemove)) {
            raiseCarryCapacity(numberToRemove);
            return true;
        }
        return false;
    }

    public boolean hasResource(ResourceType wellDoesIt) {
        return resourceManager.hasResource(wellDoesIt);
    }

    public int getResourceCount(ResourceType desiredType) {
        return resourceManager.getResourceCount(desiredType);
    }

    private boolean canStoreResource(int number) {
        return (carryCapacity != 0)
                && (carryCapacity > number);
    }

    private void lowerCarryCapacity(int number) {
        carryCapacity = carryCapacity - number;
    }

    private void raiseCarryCapacity(int number) {
        carryCapacity = carryCapacity + number;
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
