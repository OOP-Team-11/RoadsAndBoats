package game.model.transport;

import game.model.PlayerId;

public class RowboatTransport extends WaterTransport {

//    private Vector<Ability> abilities;

    public RowboatTransport(PlayerId playerId, TransportId transportId, int moveCapacity, int carryCapacity) {
        super(playerId, transportId, moveCapacity, carryCapacity);
    }

    public String getExportString() {
        return this.getPlayerId().getPlayerIdNumber() + " ROWBOAT " + getResourceManager().getExportString();
    }

    @Override
    public boolean canReproduce() {
        return false;
    }

}
