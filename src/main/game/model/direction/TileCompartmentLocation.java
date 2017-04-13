package game.model.direction;

import static java.util.Objects.hash;

public class TileCompartmentLocation {
    private Location location;
    private TileCompartmentDirection tileCompartmentDirection;

    public TileCompartmentLocation(Location loc, TileCompartmentDirection tcd) {
        this.location = loc;
        this.tileCompartmentDirection = tcd;
    }

    public Location getLocation() {
        return location;
    }
    public TileCompartmentDirection getTileCompartmentDirection() {
        return tileCompartmentDirection;
    }

    @Override
    public int hashCode() {
        return hash(this.location);
    }

    @Override
    public boolean equals(Object object) {
        return (object instanceof TileCompartmentLocation) &&
                ((TileCompartmentLocation) object).getLocation() == this.location &&
                ((TileCompartmentLocation) object).getTileCompartmentDirection() == this.getTileCompartmentDirection();
    }
}
