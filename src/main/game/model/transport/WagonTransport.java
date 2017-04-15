package game.model.transport;

import game.model.PlayerId;

public class WagonTransport extends LandTransport {

    private static final int MOVE_CAP = 3;
    private static final int CARRY_CAP = 3;

//    private Vector<Ability> abilities;

    public WagonTransport(PlayerId playerId, TransportId transportId) {
        super(playerId, transportId, MOVE_CAP, CARRY_CAP);
    }

    public String getExportString() {
        return this.getPlayerId().getPlayerIdNumber() + " WAGON " + getResourceManager().getExportString();
    }

}
