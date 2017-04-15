package game.model.transport;

import game.model.PlayerId;

public class SteamShipTransport extends WaterTransport {

    private static final int MOVE_CAP = 6;
    private static final int CARRY_CAP = 8;

//    private Vector<Ability> abilities;

    public SteamShipTransport(PlayerId playerId, TransportId transportId) {
        super(playerId, transportId, MOVE_CAP, CARRY_CAP);
    }

    public String getExportString() {
        return this.getPlayerId().getPlayerIdNumber() + " STEAMSHIP " + getResourceManager().getExportString();
    }

}
