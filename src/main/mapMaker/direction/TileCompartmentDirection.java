package mapMaker.direction;

public class TileCompartmentDirection {

    private Angle angle;

    private TileCompartmentDirection(Angle angle) {
        this.angle = angle;
    }

    public Angle getAngle() {
        return this.angle;
    }

    public static TileCompartmentDirection getEast() {
        return new TileCompartmentDirection(CompassAngles.EAST.getAngle());
    }

    public static TileCompartmentDirection getNorthEast() {
        return new TileCompartmentDirection(CompassAngles.NORTHEAST.getAngle());
    }

    public static TileCompartmentDirection getNorthNorthEast() {
        return new TileCompartmentDirection(CompassAngles.NORTH_NORTHEAST.getAngle());
    }

    public static TileCompartmentDirection getNorth() {
        return new TileCompartmentDirection(CompassAngles.NORTH.getAngle());
    }

    public static TileCompartmentDirection getNorthNorthWest() {
        return new TileCompartmentDirection(CompassAngles.NORTH_NORTHWEST.getAngle());
    }

    public static TileCompartmentDirection getNorthWest() {
        return new TileCompartmentDirection(CompassAngles.NORTHWEST.getAngle());
    }

    public static TileCompartmentDirection getWest() {
        return new TileCompartmentDirection(CompassAngles.WEST.getAngle());
    }

    public static TileCompartmentDirection getSouthWest() {
        return new TileCompartmentDirection(CompassAngles.SOUTHWEST.getAngle());
    }

    public static TileCompartmentDirection getSouthSouthWest() {
        return new TileCompartmentDirection(CompassAngles.SOUTH_SOUTHWEST.getAngle());
    }

    public static TileCompartmentDirection getSouth() {
        return new TileCompartmentDirection(CompassAngles.SOUTH.getAngle());
    }

    public static TileCompartmentDirection getSouthEast() {
        return new TileCompartmentDirection(CompassAngles.SOUTHEAST.getAngle());
    }

    public static TileCompartmentDirection getSouthSouthEast() {
        return new TileCompartmentDirection(CompassAngles.SOUTH_SOUTHEAST.getAngle());
    }

    @Override
    public int hashCode() {
        return this.angle.getDegrees();
    }

    @Override
    public boolean equals(Object object) {
        return (object instanceof TileCompartmentDirection) &&
                ((TileCompartmentDirection) object).getAngle() == this.angle;
    }

}
