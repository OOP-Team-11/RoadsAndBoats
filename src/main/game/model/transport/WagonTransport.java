package game.model.transport;

import game.model.PlayerId;

public class WagonTransport extends LandTransport {

//    private Vector<Ability> abilities;

    public WagonTransport(PlayerId playerId, TransportId transportId) {
        super(playerId, transportId, 3, 3);
    }

    public String getExportString() {
        return this.getPlayerId().getPlayerIdNumber() + " WAGON " + getResourceManager().getExportString();
    }

}
