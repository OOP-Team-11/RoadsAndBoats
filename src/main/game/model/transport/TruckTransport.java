package game.model.transport;

import game.model.PlayerId;

public class TruckTransport extends LandTransport {

//    private Vector<Ability> abilities;

    public TruckTransport(PlayerId playerId, TransportId transportId) {
        super(playerId, transportId, 4, 6);
    }

    public String getExportString() {
        return this.getPlayerId().getPlayerIdNumber() + " " + this.getType().getName() + " " + getResourceManager().getExportString();
    }

    @Override
    public TransportType getType() {
        return TransportType.TRUCK;
    }
}
