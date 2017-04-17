package game.model.tile;

import game.model.gameImportExport.exporter.Serializable;

public enum Terrain implements Serializable
{
    SEA(true, "SEA", false, false),
    PASTURE(false, "PASTURE", true, false),
    WOODS(false, "WOODS", false, false),
    ROCK(false, "ROCK", false, false),
    DESERT(false, "DESERT", false, true),
    MOUNTAIN(false, "MOUNTAIN", false, false);

    private final boolean canConnectRiver;
    private final String name;
    private final boolean canReproduce;
    private final boolean irrigatable;

    Terrain(boolean canConnectRiver, String name, boolean canReproduce, boolean irrigatable)
    {
        this.canConnectRiver=canConnectRiver;
        this.name = name;
        this.canReproduce = canReproduce;
        this.irrigatable = irrigatable;
    }

    public boolean canReproduce() { return this.canReproduce; }
    public boolean canConnectRiver() {
        return this.canConnectRiver;
    }
    public boolean isIrrigatable() {
        return this.irrigatable;
    }

    public String getName() { return this.name; }

    public String getExportString() {
        return getName();
    }
}
