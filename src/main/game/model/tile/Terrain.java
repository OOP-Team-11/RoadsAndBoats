package game.model.tile;

import game.model.gameImporter.Serializable;

public enum Terrain implements Serializable
{
    SEA(true, "SEA"),
    PASTURE(false, "PASTURE"),
    WOODS(false, "WOODS"),
    ROCK(false, "ROCK"),
    DESERT(false, "DESERT"),
    MOUNTAIN(false, "MOUNTAIN");

    private final boolean canConnectRiver;
    private final String name;

    Terrain(boolean canConnectRiver, String name)
    {
        this.canConnectRiver=canConnectRiver;
        this.name = name;
    }

    public boolean canConnectRiver() {
        return canConnectRiver;
    }

    public String getName() { return this.name; }

    public String getExportString() {
        return getName();
    }
}
