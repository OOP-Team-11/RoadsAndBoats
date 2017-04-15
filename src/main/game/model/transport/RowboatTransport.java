package game.model.transport;

import game.model.PlayerId;

public class RowboatTransport extends WaterTransport {

    private static final int MOVE_CAP = 4;
    private static final int CARRY_CAP = 5;

//    private Vector<Ability> abilities;

    public RowboatTransport(PlayerId playerId, TransportId transportId) {
        super(playerId, transportId, MOVE_CAP, CARRY_CAP);
    }

    public String getExportString() {
        return this.getPlayerId().getPlayerIdNumber() + " ROWBOAT " + getResourceManager().getExportString();
    }

}
