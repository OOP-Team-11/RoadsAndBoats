package game.model.transport;

import game.model.PlayerId;

public class SteamShipTransport extends WaterTransport {

//    private Vector<Ability> abilities;

    public SteamShipTransport(PlayerId playerId, TransportId transportId) {
        super(playerId, transportId, 6, 8);
    }

    public String getExportString() {
        return this.getPlayerId().getPlayerIdNumber() + " STEAMSHIP " + getResourceManager().getExportString();
    }

}
