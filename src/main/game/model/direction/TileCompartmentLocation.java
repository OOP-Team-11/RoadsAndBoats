package game.model.direction;

public class TileCompartmentLocation {
    private Location location;
    private TileCompartmentDirection tcd;

    public TileCompartmentLocation(Location loc, TileCompartmentDirection tcd) {
        this.location = loc;
        this.tcd = tcd;
    }

    public Location getLocation() {
        return location;
    }
}
