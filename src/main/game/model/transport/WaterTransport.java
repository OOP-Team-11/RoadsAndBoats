package game.model.transport;

import game.model.PlayerId;

public abstract class WaterTransport extends Transport {

    public WaterTransport(PlayerId playerId, TransportId transportId, int moveCapacity, int carryCapacity) {
        super(playerId, transportId, moveCapacity, carryCapacity);
    }

    @Override
    public boolean canMoveOnLand()
    {
        return false;
    }

    @Override
    public boolean canMoveOnRoad()
    {
        return false;
    }

    @Override
    public boolean canMoveOnWater()
    {
        return true;
    }

    public abstract String getExportString();
}
