package model.tile;

public class TileEdge {
    private boolean canConnectRiver;
    public TileEdge(boolean canConnectRiver) {
        this.canConnectRiver = canConnectRiver;
    }

    public boolean canConnectRiver() {
        return canConnectRiver;
    }
    public void setCanConnectRiver(boolean canConnectRiver) {
        this.canConnectRiver = canConnectRiver;
    }
}
