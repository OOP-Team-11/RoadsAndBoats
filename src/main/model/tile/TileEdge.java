package model.tile;

import java.util.Objects;

public class TileEdge {
    private boolean canConnectRiver;
    private boolean hasRiver;

    public TileEdge(boolean canConnectRiver, boolean hasRiver) {
        this.canConnectRiver = canConnectRiver;
        this.hasRiver = hasRiver;
    }

    public boolean canConnectRiver() {
        return canConnectRiver;
    }

    public void setCanConnectRiver(boolean canConnectRiver) {
        this.canConnectRiver = canConnectRiver;
    }

    public boolean hasRiver() {
        return hasRiver;
    }

    public void setHasRiver(boolean hasRiver) {
        this.hasRiver = hasRiver;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(canConnectRiver, hasRiver);
    }

    @Override
    public boolean equals(Object o)
    {
        if(!(o instanceof TileEdge))
        {
            return false;
        }

        TileEdge other=((TileEdge)o);

        return other.canConnectRiver() == this.canConnectRiver()
                && other.hasRiver() == this.hasRiver();
    }
}
