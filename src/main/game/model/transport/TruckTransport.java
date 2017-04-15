package game.model.transport;

import game.model.PlayerId;

public class TruckTransport extends LandTransport {

//    private Vector<Ability> abilities;

    public TruckTransport(PlayerId playerId, TransportId transportId) {
        super(playerId, transportId, 4, 6);
    }

    public String getExportString() {
        return this.getPlayerId().getPlayerIdNumber() + " TRUCK " + getResourceManager().getExportString();
    }

}
