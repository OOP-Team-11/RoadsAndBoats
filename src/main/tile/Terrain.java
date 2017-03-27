package tile;

public enum Terrain
{
    Sea(true),
    Pasture(false),
    Woods(false),
    Rock(false),
    Desert(false),
    Mountain(false);

    private final boolean canConnectRiver;

    Terrain(boolean canConnectRiver)
    {
        this.canConnectRiver=canConnectRiver;
    }

    public boolean canConnectRiver() {
        return canConnectRiver;
    }
}
