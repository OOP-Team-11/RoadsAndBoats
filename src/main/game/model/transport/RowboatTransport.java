package game.model.transport;

import game.model.PlayerId;

public class RowboatTransport extends WaterTransport {

//    private Vector<Ability> abilities;

    public RowboatTransport(PlayerId playerId, TransportId transportId) {
        super(playerId, transportId, 4, 5);
    }

    public String getExportString() {
        return this.getPlayerId().getPlayerIdNumber() + " ROWBOAT " + getResourceManager().getExportString();
    }

}
