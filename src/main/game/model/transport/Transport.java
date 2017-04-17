package game.model.transport;

import game.model.PlayerId;
import game.model.gameImportExport.exporter.Serializable;
import game.model.resources.Goose;
import game.model.managers.ResourceManager;
import game.model.resources.ResourceType;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Vector;

public abstract class Transport implements Serializable {
    private List<Goose> followers;
    private PlayerId playerId;
    private TransportId transportId;
    private ResourceManager resourceManager;
    private int moveCapacity;
    private int currentCarryCapacity;
    private int maxCarryCapacity;
    private Transport transport;

    Transport() {
        this.followers = new Vector<Goose>();
    }

    public void addFollowers(List<Goose> geese) { this.followers = geese; }
    public void removeFollowers() {
        for(Goose g : followers)
            followers.remove(g);
    }

    public Transport(PlayerId playerId, TransportId transportId, int moveCapacity, int carryCapacity) {
        this.playerId = playerId;
        this.transportId = transportId;
        this.moveCapacity = moveCapacity;
        this.currentCarryCapacity = carryCapacity;
        this.maxCarryCapacity = carryCapacity;
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

    public boolean storeTransport(Transport transport) {
        if (!canStoreTransport(transport)) return false;

        this.transport = transport;
        this.currentCarryCapacity = 0;
        return true;
    }

    public boolean canStoreTransport(Transport transport) {
        return hasNoTransport() &&
                isCarryingNothing() &&
                transport.isCarryingNothing() &&
                transportBelongsToSamePlayer(transport);
    }

    public boolean canRemoveTransport() {
        return transport != null;
    }

    public Transport removeTransport() {
        Transport transport = this.transport;
        this.transport = null;
        return transport;
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

    public boolean canStoreResource(int number) {
        return (currentCarryCapacity != 0)
                && (currentCarryCapacity >= number);
    }

    private void lowerCarryCapacity(int number) {
        currentCarryCapacity = currentCarryCapacity - number;
    }

    private void raiseCarryCapacity(int number) {
        currentCarryCapacity = currentCarryCapacity + number;
    }

    public int getMoveCapacity() {
        return this.moveCapacity;
    }

    public int getCarryCapacity() {
        return this.currentCarryCapacity;
    }

    public List<Goose> getFollowers() {
        return this.followers;
    }

    // use for view drawing and import/export to file ONLY
    public abstract TransportType getType();

    public abstract boolean canMoveOnLand();

    public abstract boolean canMoveOnRoad();

    public abstract boolean canMoveOnWater();

    @Override
    public int hashCode() {
        return Objects.hash(playerId, transportId, moveCapacity, currentCarryCapacity);
    }

    @Override
    public boolean equals(Object object) {
        if (!(object instanceof Transport))
            return false;
        if (object == this)
            return true;

        Transport tr = (Transport) object;
        return tr.transportId.equals(transportId) && tr.playerId.equals(playerId)
                && tr.moveCapacity == moveCapacity && tr.currentCarryCapacity == currentCarryCapacity;
    }

    private boolean hasNoTransport() {
        return transport == null;
    }

    private boolean isCarryingNothing() {
        return this.currentCarryCapacity == this.maxCarryCapacity;
    }

    private boolean transportBelongsToSamePlayer(Transport transport) {
        return this.playerId.equals(transport.playerId);
    }


}
