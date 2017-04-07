package mapMaker.model.tile;

public enum Terrain
{
    SEA(true),
    PASTURE(false),
    WOODS(false),
    ROCK(false),
    DESERT(false),
    MOUNTAIN(false);

    private final boolean canConnectRiver;

    Terrain(boolean canConnectRiver)
    {
        this.canConnectRiver=canConnectRiver;
    }

    public boolean canConnectRiver() {
        return canConnectRiver;
    }
}
