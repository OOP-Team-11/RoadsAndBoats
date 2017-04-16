package game.model.transport;

import game.model.PlayerId;

public class RaftTransport extends WaterTransport {

    private static final int MOVE_CAP = 3;
    private static final int CARRY_CAP = 3;

//    private Vector<Ability> abilities;

    public RaftTransport(PlayerId playerId, TransportId transportId) {
        super(playerId, transportId, MOVE_CAP, CARRY_CAP);
    }

    public String getExportString() {
        return this.getPlayerId().getPlayerIdNumber() + " " + this.getType().getName() + " " + getResourceManager().getExportString();
    }

    @Override
    public TransportType getType() {
        return TransportType.RAFT;
    }

    @Override
    public boolean canReproduce() {
        return false;
    }

}
