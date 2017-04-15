package game.model.transport;

import game.model.PlayerId;

public class RaftTransport extends WaterTransport {

//    private Vector<Ability> abilities;

    public RaftTransport(PlayerId playerId, TransportId transportId) {
        super(playerId, transportId, 3, 3);
    }

    public String getExportString() {
        return this.getPlayerId().getPlayerIdNumber() + " " + this.getType().getName() + " " + getResourceManager().getExportString();
    }

    @Override
    public TransportType getType() {
        return TransportType.RAFT;
    }

}
