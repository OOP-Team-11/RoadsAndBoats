package game.model.transport;


import game.model.PlayerId;

public abstract class LandTransport extends Transport {

    public LandTransport(PlayerId playerId, TransportId transportId, int moveCapacity, int carryCapacity) {
        super(playerId, transportId, moveCapacity, carryCapacity);
    }

    @Override
    public boolean canMoveOnLand()
    {
        return true;
    }

    @Override
    public boolean canMoveOnRoad()
    {
        return false;
    }

    @Override
    public boolean canMoveOnWater()
    {
        return false;
    }

    public abstract String getExportString();
}
