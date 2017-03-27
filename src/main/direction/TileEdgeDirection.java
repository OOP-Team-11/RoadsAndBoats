package direction;

public class TileEdgeDirection {
    private Angle angle;

    private TileEdgeDirection(Angle angle) {
        this.angle = angle;
    }

    public Angle getAngle() {
        return this.angle;
    }

    public static TileEdgeDirection getNorthEast() {
        return new TileEdgeDirection(CompassAngles.NORTHEAST.getAngle());
    }

    public static TileEdgeDirection getNorth() {
        return new TileEdgeDirection(CompassAngles.NORTH.getAngle());
    }

    public static TileEdgeDirection getNorthWest() {
        return new TileEdgeDirection(CompassAngles.NORTHWEST.getAngle());
    }

    public static TileEdgeDirection getSouthWest() {
        return new TileEdgeDirection(CompassAngles.SOUTHWEST.getAngle());
    }

    public static TileEdgeDirection getSouth() {
        return new TileEdgeDirection(CompassAngles.SOUTH.getAngle());
    }

    public static TileEdgeDirection getSouthEast() {
        return new TileEdgeDirection(CompassAngles.SOUTHEAST.getAngle());
    }

    @Override
    public int hashCode() {
        return this.angle.getDegrees();
    }

    @Override
    public boolean equals(Object object) {
        return (object instanceof TileEdgeDirection) &&
                ((TileEdgeDirection) object).getAngle() == this.angle;
    }
}
