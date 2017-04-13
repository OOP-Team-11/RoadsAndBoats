package game.model.transport;

import game.model.PlayerId;

public class SteamShipTransport extends WaterTransport {

//    private Vector<Ability> abilities;

    public SteamShipTransport(PlayerId playerId, TransportId transportId, int moveCapacity, int carryCapacity) {
        super(playerId, transportId, moveCapacity, carryCapacity);
    }

    public String getExportString() {
        return this.getPlayerId().getPlayerIdNumber() + " STEAMSHIP" + getResourceManager().getExportString();
    }

}
