package game.model.transport;

import game.model.PlayerId;

public class TruckTransport extends LandTransport {

//    private Vector<Ability> abilities;

    public TruckTransport(PlayerId playerId, TransportId transportId, int moveCapacity, int carryCapacity) {
        super(playerId, transportId, moveCapacity, carryCapacity);
    }

    public String getExportString() {
        return this.getPlayerId().getPlayerIdNumber() + " TRUCK " + getResourceManager().getExportString();
    }

    @Override
    public boolean canReproduce() {
        return false;
    }

}
