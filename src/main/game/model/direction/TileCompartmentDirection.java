package game.model.direction;

public class TileCompartmentDirection {

    private Angle mmAngle;

    private TileCompartmentDirection(Angle mmAngle) {
        this.mmAngle = mmAngle;
    }

    public Angle getMmAngle() {
        return this.mmAngle;
    }

    public static TileCompartmentDirection getEast() {
        return new TileCompartmentDirection(CompassAngles.EAST.getMmAngle());
    }

    public static TileCompartmentDirection getNorthEast() {
        return new TileCompartmentDirection(CompassAngles.NORTHEAST.getMmAngle());
    }

    public static TileCompartmentDirection getNorthNorthEast() {
        return new TileCompartmentDirection(CompassAngles.NORTH_NORTHEAST.getMmAngle());
    }

    public static TileCompartmentDirection getNorth() {
        return new TileCompartmentDirection(CompassAngles.NORTH.getMmAngle());
    }

    public static TileCompartmentDirection getNorthNorthWest() {
        return new TileCompartmentDirection(CompassAngles.NORTH_NORTHWEST.getMmAngle());
    }

    public static TileCompartmentDirection getNorthWest() {
        return new TileCompartmentDirection(CompassAngles.NORTHWEST.getMmAngle());
    }

    public static TileCompartmentDirection getWest() {
        return new TileCompartmentDirection(CompassAngles.WEST.getMmAngle());
    }

    public static TileCompartmentDirection getSouthWest() {
        return new TileCompartmentDirection(CompassAngles.SOUTHWEST.getMmAngle());
    }

    public static TileCompartmentDirection getSouthSouthWest() {
        return new TileCompartmentDirection(CompassAngles.SOUTH_SOUTHWEST.getMmAngle());
    }

    public static TileCompartmentDirection getSouth() {
        return new TileCompartmentDirection(CompassAngles.SOUTH.getMmAngle());
    }

    public static TileCompartmentDirection getSouthEast() {
        return new TileCompartmentDirection(CompassAngles.SOUTHEAST.getMmAngle());
    }

    public static TileCompartmentDirection getSouthSouthEast() {
        return new TileCompartmentDirection(CompassAngles.SOUTH_SOUTHEAST.getMmAngle());
    }

    @Override
    public int hashCode() {
        return this.mmAngle.getDegrees();
    }

    @Override
    public boolean equals(Object object) {
        return (object instanceof TileCompartmentDirection) &&
                ((TileCompartmentDirection) object).getMmAngle() == this.mmAngle;
    }

}
