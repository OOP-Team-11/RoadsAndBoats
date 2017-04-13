package game.model.transport;

import game.model.PlayerId;

public class RaftTransport extends WaterTransport {

//    private Vector<Ability> abilities;

    public RaftTransport(PlayerId playerId, TransportId transportId, int moveCapacity, int carryCapacity) {
        super(playerId, transportId, moveCapacity, carryCapacity);
    }

    public String getExportString() {
        return this.getPlayerId().getPlayerIdNumber() + " RAFT " + getResourceManager().getExportString();
    }

}
