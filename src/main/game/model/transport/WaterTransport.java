package game.model.transport;

import game.model.PlayerId;

public abstract class WaterTransport extends Transport {

    public WaterTransport(PlayerId playerId, TransportId transportId, int moveCapacity, int carryCapacity) {
        super(playerId, transportId, moveCapacity, carryCapacity);
    }

    public abstract String getExportString();
}
