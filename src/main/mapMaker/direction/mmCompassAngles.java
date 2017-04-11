package mapMaker.direction;

/**
 * Pre-defined set of Angles that relate to directions on a compass.
 */
public enum mmCompassAngles {
    EAST(0),
    NORTHEAST(30),
    NORTH_NORTHEAST(60),
    NORTH(90),
    NORTH_NORTHWEST(120),
    NORTHWEST(150),
    WEST(180),
    SOUTHWEST(210),
    SOUTH_SOUTHWEST(240),
    SOUTH(270),
    SOUTH_SOUTHEAST(300),
    SOUTHEAST(330);

    private mmAngle mmAngle;
    mmCompassAngles(int degrees) {
        this.mmAngle = new mmAngle(degrees);
    }

    public mmAngle getMmAngle() {
        return this.mmAngle;
    }
}
