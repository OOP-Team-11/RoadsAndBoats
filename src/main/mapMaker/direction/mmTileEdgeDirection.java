package mapMaker.direction;

import java.util.ArrayList;

public class mmTileEdgeDirection implements Cloneable{
    private mmAngle mmAngle;

    private mmTileEdgeDirection(mmAngle mmAngle) {
        this.mmAngle = mmAngle;
    }

    public mmAngle getMmAngle() {
        return this.mmAngle;
    }

	public mmTileEdgeDirection reverse() {
        int newDegrees = (getMmAngle().getDegrees()+180) % 360;
        return new mmTileEdgeDirection(new mmAngle(newDegrees));

	}
	public Object clone() throws CloneNotSupportedException{
        Object clone = super.clone();
        ((mmTileEdgeDirection)clone).mmAngle = (mmAngle)this.mmAngle.clone();
        return super.clone();
    }
	
    public static mmTileEdgeDirection getNorthEast() {
        return new mmTileEdgeDirection(mmCompassAngles.NORTHEAST.getMmAngle());
    }

    public static mmTileEdgeDirection getNorth() {
        return new mmTileEdgeDirection(mmCompassAngles.NORTH.getMmAngle());
    }

    public static mmTileEdgeDirection getNorthWest() {
        return new mmTileEdgeDirection(mmCompassAngles.NORTHWEST.getMmAngle());
    }

    public static mmTileEdgeDirection getSouthWest() {
        return new mmTileEdgeDirection(mmCompassAngles.SOUTHWEST.getMmAngle());
    }

    public static mmTileEdgeDirection getSouth() {
        return new mmTileEdgeDirection(mmCompassAngles.SOUTH.getMmAngle());
    }

    public static mmTileEdgeDirection getSouthEast() {
        return new mmTileEdgeDirection(mmCompassAngles.SOUTHEAST.getMmAngle());
    }

	public static ArrayList<mmTileEdgeDirection> getAllDirections() {
        ArrayList<mmTileEdgeDirection> directions=new ArrayList<mmTileEdgeDirection>();
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
        return (object instanceof mmTileEdgeDirection) &&
                ((mmTileEdgeDirection) object).getMmAngle().equals(this.mmAngle);
    }
}
