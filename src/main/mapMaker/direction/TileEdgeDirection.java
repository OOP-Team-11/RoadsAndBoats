package mapMaker.direction;

import java.util.ArrayList;

public class TileEdgeDirection implements Cloneable{
    private Angle angle;

    private TileEdgeDirection(Angle angle) {
        this.angle = angle;
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
