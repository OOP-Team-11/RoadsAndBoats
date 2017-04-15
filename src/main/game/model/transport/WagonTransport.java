package game.model.transport;

import game.model.PlayerId;

public class WagonTransport extends LandTransport {

//    private Vector<Ability> abilities;

    public WagonTransport(PlayerId playerId, TransportId transportId, int moveCapacity, int carryCapacity) {
        super(playerId, transportId, moveCapacity, carryCapacity);
    }

    public String getExportString() {
        return this.getPlayerId().getPlayerIdNumber() + " WAGON " + getResourceManager().getExportString();
    }

    @Override
    public boolean canReproduce() {
        return false;
    }

}
