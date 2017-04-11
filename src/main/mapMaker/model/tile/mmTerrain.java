package mapMaker.model.tile;

public enum mmTerrain
{
    SEA(true),
    PASTURE(false),
    WOODS(false),
    ROCK(false),
    DESERT(false),
    MOUNTAIN(false);

    private final boolean canConnectRiver;

    mmTerrain(boolean canConnectRiver)
    {
        this.canConnectRiver=canConnectRiver;
    }

    public boolean canConnectRiver() {
        return canConnectRiver;
    }
}
