package game.model.resources;

import game.model.gameImporter.Serializable;

public enum ResourceType implements Serializable {

    TRUNKS("TRUNKS", 0),
    BOARDS("BOARDS", 0),
    PAPER("PAPER", 0),
    GOOSE("GOOSE", 0),
    CLAY("CLAY", 0),
    STONE("STONE", 0),
    FUEL("FUEL", 0),
    IRON("IRON", 0),
    GOLD("GOLD", 10),
    COINS("COINS", 40),
    STOCKBOND("STOCKBOND", 120);

    private String name;
    private int wealthPoints;

    ResourceType(String name, int wealthPoints) {
        this.name = name;
        this.wealthPoints = wealthPoints;
    }

    public int getWealthPoints() {
        return wealthPoints;
    }

    public String getName() {
        return name;
    }

    public String getExportString() {
        return getName();
    }

}
