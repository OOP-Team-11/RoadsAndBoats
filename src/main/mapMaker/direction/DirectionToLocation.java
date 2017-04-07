package mapMaker.direction;

import mapMaker.model.tile.InvalidLocationException;
import mapMaker.model.tile.Location;

public class DirectionToLocation {

    private static boolean angleIsInRange(Angle angle, int lowerDegrees, int higherDegrees) {
        return angle.getDegrees() > lowerDegrees && angle.getDegrees() < higherDegrees;
    }

    public static Location getLocation(Location startingLocation, TileEdgeDirection direction) {

        try {
            if (angleIsInRange(direction.getAngle(), 0, 60)) {
                return new Location(startingLocation.getX() + 1, startingLocation.getY(), startingLocation.getZ() - 1);

            } else if (angleIsInRange(direction.getAngle(), 60, 120)) {
                return new Location(startingLocation.getX(), startingLocation.getY() + 1, startingLocation.getZ() - 1);

            } else if (angleIsInRange(direction.getAngle(), 120, 180)) {
                return new Location(startingLocation.getX() - 1, startingLocation.getY() + 1, startingLocation.getZ());

            } else if (angleIsInRange(direction.getAngle(), 180, 240)) {
                return new Location(startingLocation.getX() - 1, startingLocation.getY(), startingLocation.getZ() + 1);

            } else if (angleIsInRange(direction.getAngle(), 240, 300)) {
                return new Location(startingLocation.getX(), startingLocation.getY() - 1, startingLocation.getZ() + 1);

            } else if (angleIsInRange(direction.getAngle(), 300, 360)) {
                return new Location(startingLocation.getX() + 1, startingLocation.getY() - 1, startingLocation.getZ());

            } else {
                throw new RuntimeException("Angle was not between 0 and 360");
            }

        } catch (InvalidLocationException e) {
            throw new RuntimeException("Direction to location calculated an invalid location.");
        }
    }
}
