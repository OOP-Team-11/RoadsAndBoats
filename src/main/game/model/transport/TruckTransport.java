package game.model.transport;

import game.model.PlayerId;

public class TruckTransport extends LandTransport {

    private static final int MOVE_CAP = 4;
    private static final int CARRY_CAP = 6;

//    private Vector<Ability> abilities;

    public TruckTransport(PlayerId playerId, TransportId transportId) {
        super(playerId, transportId, MOVE_CAP, CARRY_CAP);
    }

    public String getExportString() {
        return this.getPlayerId().getPlayerIdNumber() + " " + this.getType().getName() + " " + getResourceManager().getExportString();
    }

    @Override
    public boolean canReproduce() {
        return false;
    }

    public TransportType getType() {
        return TransportType.TRUCK;
    }
}
