package game.model.direction;

import java.util.ArrayList;
import java.util.List;

public class TileCompartmentDirection {

    private Angle angle;

    public TileCompartmentDirection(Angle angle) {
        if(validAngle(angle))
        {
            this.angle = angle;
        }
        else
        {
            throw new IllegalArgumentException("Invalid Angle: " + angle);
        }
    }

    private boolean validAngle(Angle angle)
    {
        return angle.equals(CompassAngles.NORTH.getAngle())
                || angle.equals(CompassAngles.NORTHEAST.getAngle())
                || angle.equals(CompassAngles.SOUTHEAST.getAngle())
                || angle.equals(CompassAngles.SOUTH.getAngle())
                || angle.equals(CompassAngles.SOUTHWEST.getAngle())
                || angle.equals(CompassAngles.NORTHWEST.getAngle())
                || angle.equals(CompassAngles.EAST.getAngle())
                || angle.equals(CompassAngles.NORTH_NORTHEAST.getAngle())
                || angle.equals(CompassAngles.SOUTH_SOUTHEAST.getAngle())
                || angle.equals(CompassAngles.WEST.getAngle())
                || angle.equals(CompassAngles.SOUTH_SOUTHWEST.getAngle())
                || angle.equals(CompassAngles.NORTH_NORTHWEST.getAngle());
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

    public static  List<TileCompartmentDirection> getAllDirections() {
        List<TileCompartmentDirection> compartments = new ArrayList<TileCompartmentDirection>();
        compartments.add(getEast());
        compartments.add(getNorthEast());
        compartments.add(getNorthNorthEast());
        compartments.add(getNorth());
        compartments.add(getNorthNorthWest());
        compartments.add(getNorthWest());
        compartments.add(getWest());
        compartments.add(getSouthWest());
        compartments.add(getSouthSouthWest());
        compartments.add(getSouth());
        compartments.add(getSouthEast());
        compartments.add(getSouthSouthEast());

        return compartments;
    }

    @Override
    public int hashCode() {
        return this.angle.getDegrees();
    }

    @Override
    public boolean equals(Object object) {
        return (object instanceof TileCompartmentDirection) &&
                ((TileCompartmentDirection) object).getAngle().equals(this.angle);
    }

    public TileCompartmentDirection reverse()
    {
        int newDegrees = (getAngle().getDegrees()+180) % 360;
        return new TileCompartmentDirection(new Angle(newDegrees));
    }
}
