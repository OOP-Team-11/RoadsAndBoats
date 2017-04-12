package game.model.direction;

import java.util.ArrayList;

public class TileEdgeDirection implements Cloneable{
    private Angle mmAngle;

    private TileEdgeDirection(Angle mmAngle) {
        this.mmAngle = mmAngle;
    }

    public Angle getMmAngle() {
        return this.mmAngle;
    }

	public TileEdgeDirection reverse() {
        int newDegrees = (getMmAngle().getDegrees()+180) % 360;
        return new TileEdgeDirection(new Angle(newDegrees));

	}
	public Object clone() throws CloneNotSupportedException{
        Object clone = super.clone();
        ((TileEdgeDirection)clone).mmAngle = (Angle)this.mmAngle.clone();
        return super.clone();
    }

    public static TileEdgeDirection getNorthEast() {
        return new TileEdgeDirection(CompassAngles.NORTHEAST.getMmAngle());
    }

    public static TileEdgeDirection getNorth() {
        return new TileEdgeDirection(CompassAngles.NORTH.getMmAngle());
    }

    public static TileEdgeDirection getNorthWest() {
        return new TileEdgeDirection(CompassAngles.NORTHWEST.getMmAngle());
    }

    public static TileEdgeDirection getSouthWest() {
        return new TileEdgeDirection(CompassAngles.SOUTHWEST.getMmAngle());
    }

    public static TileEdgeDirection getSouth() {
        return new TileEdgeDirection(CompassAngles.SOUTH.getMmAngle());
    }

    public static TileEdgeDirection getSouthEast() {
        return new TileEdgeDirection(CompassAngles.SOUTHEAST.getMmAngle());
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
        return this.mmAngle.getDegrees();
    }

    @Override
    public boolean equals(Object object) {
        return (object instanceof TileEdgeDirection) &&
                ((TileEdgeDirection) object).getMmAngle().equals(this.mmAngle);
    }
}
