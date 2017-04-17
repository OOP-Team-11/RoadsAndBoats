package game.model.direction;

import java.util.ArrayList;

public class TileEdgeDirection implements Cloneable{
    private Angle angle;

    public TileEdgeDirection(Angle angle) {
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
                || angle.equals(CompassAngles.NORTHWEST.getAngle());
    }

    public Angle getAngle() {
        return this.angle;
    }

	public TileEdgeDirection reverse() {
        int newDegrees = (getAngle().getDegrees()+180) % 360;
        return new TileEdgeDirection(new Angle(newDegrees));
	}

	public Object clone() throws CloneNotSupportedException{
        Object clone = super.clone();
        ((TileEdgeDirection)clone).angle = (Angle)this.angle.clone();
        return super.clone();
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

	public static ArrayList<TileEdgeDirection> getAllDirections() {
        ArrayList<TileEdgeDirection> directions=new ArrayList<TileEdgeDirection>();
		directions.add(getNorth());
        directions.add(getNorthEast());
		directions.add(getSouthEast());
        directions.add(getSouth());
		directions.add(getSouthWest());
        directions.add(getNorthWest());
        return directions;
	}

    public String getString() {
        if(this.angle == CompassAngles.NORTHEAST.getAngle())
            return "North East";
        else if(this.angle == CompassAngles.NORTH.getAngle())
            return "North";
        else if(this.angle == CompassAngles.NORTHWEST.getAngle())
            return "Northwest";
        else if(this.angle == CompassAngles.SOUTHWEST.getAngle())
            return "Southwest";
        else if(this.angle == CompassAngles.SOUTH.getAngle())
            return "South";
        else
            return "Southeast";
    }

    @Override
    public int hashCode() {
        return this.angle.getDegrees();
    }

    @Override
    public boolean equals(Object object) {
        return (object instanceof TileEdgeDirection) &&
                ((TileEdgeDirection) object).getAngle().equals(this.angle);
    }
}
