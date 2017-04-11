package mapMaker.direction;

public class mmTileCompartmentDirection {

    private mmAngle mmAngle;

    private mmTileCompartmentDirection(mmAngle mmAngle) {
        this.mmAngle = mmAngle;
    }

    public mmAngle getMmAngle() {
        return this.mmAngle;
    }

    public static mmTileCompartmentDirection getEast() {
        return new mmTileCompartmentDirection(mmCompassAngles.EAST.getMmAngle());
    }

    public static mmTileCompartmentDirection getNorthEast() {
        return new mmTileCompartmentDirection(mmCompassAngles.NORTHEAST.getMmAngle());
    }

    public static mmTileCompartmentDirection getNorthNorthEast() {
        return new mmTileCompartmentDirection(mmCompassAngles.NORTH_NORTHEAST.getMmAngle());
    }

    public static mmTileCompartmentDirection getNorth() {
        return new mmTileCompartmentDirection(mmCompassAngles.NORTH.getMmAngle());
    }

    public static mmTileCompartmentDirection getNorthNorthWest() {
        return new mmTileCompartmentDirection(mmCompassAngles.NORTH_NORTHWEST.getMmAngle());
    }

    public static mmTileCompartmentDirection getNorthWest() {
        return new mmTileCompartmentDirection(mmCompassAngles.NORTHWEST.getMmAngle());
    }

    public static mmTileCompartmentDirection getWest() {
        return new mmTileCompartmentDirection(mmCompassAngles.WEST.getMmAngle());
    }

    public static mmTileCompartmentDirection getSouthWest() {
        return new mmTileCompartmentDirection(mmCompassAngles.SOUTHWEST.getMmAngle());
    }

    public static mmTileCompartmentDirection getSouthSouthWest() {
        return new mmTileCompartmentDirection(mmCompassAngles.SOUTH_SOUTHWEST.getMmAngle());
    }

    public static mmTileCompartmentDirection getSouth() {
        return new mmTileCompartmentDirection(mmCompassAngles.SOUTH.getMmAngle());
    }

    public static mmTileCompartmentDirection getSouthEast() {
        return new mmTileCompartmentDirection(mmCompassAngles.SOUTHEAST.getMmAngle());
    }

    public static mmTileCompartmentDirection getSouthSouthEast() {
        return new mmTileCompartmentDirection(mmCompassAngles.SOUTH_SOUTHEAST.getMmAngle());
    }

    @Override
    public int hashCode() {
        return this.mmAngle.getDegrees();
    }

    @Override
    public boolean equals(Object object) {
        return (object instanceof mmTileCompartmentDirection) &&
                ((mmTileCompartmentDirection) object).getMmAngle() == this.mmAngle;
    }

}
