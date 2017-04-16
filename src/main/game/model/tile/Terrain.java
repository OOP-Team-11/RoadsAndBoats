package game.model.tile;

import game.model.gameImportExport.exporter.Serializable;

public enum Terrain implements Serializable
{
    SEA(true, "SEA", false),
    PASTURE(false, "PASTURE", true),
    WOODS(false, "WOODS", false),
    ROCK(false, "ROCK", false),
    DESERT(false, "DESERT", false),
    MOUNTAIN(false, "MOUNTAIN", false);

    private final boolean canConnectRiver;
    private final String name;
    private final boolean canReproduce;

    Terrain(boolean canConnectRiver, String name, boolean canReproduce)
    {
        this.canConnectRiver=canConnectRiver;
        this.name = name;
        this.canReproduce = canReproduce;
    }

    public boolean canReproduce() { return this.canReproduce; }
    public boolean canConnectRiver() {
        return canConnectRiver;
    }

    public String getName() { return this.name; }

    public String getExportString() {
        return getName();
    }
}
