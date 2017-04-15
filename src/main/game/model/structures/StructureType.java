package game.model.structures;

public enum StructureType {
    CLAYPIT("CLAYPIT"),
    MINE("MINE"),
    OIL_RIG("OIL_RIG"),
    STONE_QUARRY("STONE_QUARRY"),
    WOODCUTTER("WOODCUTTER"),
    COAL_BURNER("COAL_BURNER"),
    MINT("MINT"),
    PAPERMILL("PAPERMILL"),
    SAWMILL("SAWMILL"),
    STOCK_MARKET("STOCK_MARKET"),
    STONE_FACTORY("STONE_FACTORY"),
    RAFT_FACTORY("RAFT_FACTORY"),
    ROWBOAT_FACTORY("ROWBOAT_FACTORY"),
    STEAMER_FACTORY("STEAMER_FACTORY"),
    TRANSPORT_PRODUCER("TRANSPORT_PRODUCER"),
    TRUCK_FACTORY("TRUCK_FACTORY"),
    WAGON_FACTORY("WAGON_FACTORY");

    private String name;
    StructureType(String name) {
        this.name = name;
    }

    public String toString() {
        return name;
    }
}
