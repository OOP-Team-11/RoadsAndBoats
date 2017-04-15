package game.model.transport;


import game.model.PlayerId;

public abstract class LandTransport extends Transport {

    public LandTransport(PlayerId playerId, TransportId transportId, int moveCapacity, int carryCapacity) {
        super(playerId, transportId, moveCapacity, carryCapacity);
    }

    public abstract String getExportString();
}
