package game.model.resources;

public enum ResourceType {

    BOARD(0),
    PAPER(0),
    TRUNK(0),
    CLAY(0),
    STONE(0),
    IRON(0),
    FUEL(0),
    GOLD(10),
    COINS(40),
    STOCKBOND(120);

    private int wealthPoints;

    ResourceType(int wealthPoints) {
        this.wealthPoints = wealthPoints;
    }

    public int getWealthPoints() {
        return wealthPoints;
    }

}
