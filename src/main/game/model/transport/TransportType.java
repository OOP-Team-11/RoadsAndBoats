package game.model.transport;

public enum TransportType {

    DONKEY("DONKEY"),
    RAFT("RAFT"),
    ROWBOAT("ROWBOAT"),
    STEAMSHIP("STEAMSHIP"),
    TRUCK("TRUCK"),
    WAGON("WAGON");

    private String name;
    TransportType(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
