package game.model.resources;

public enum ResourceType {

    TRUNKS("Trunks", 0),
    BOARDS("Boards", 0),
    PAPER("Paper", 0),
    GOOSE("Goose", 0),
    CLAY("Clay", 0),
    STONE("Stone", 0),
    FUEL("Fuel", 0),
    IRON("Iron", 0),
    GOLD("Gold", 10),
    COINS("Coins", 40),
    STOCKBOND("Stockbond", 120);

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

}
