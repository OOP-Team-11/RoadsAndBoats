package game.model.direction;

import game.model.Location;

public class DirectionToLocation {

    private static boolean angleIsInRange(Angle mmAngle, int lowerDegrees, int higherDegrees) {
        return mmAngle.getDegrees() > lowerDegrees && mmAngle.getDegrees() < higherDegrees;
    }

    public static Location getLocation(Location startingLocation, TileEdgeDirection direction) {
        if (angleIsInRange(direction.getMmAngle(), 0, 60)) {
            return new Location(startingLocation.getX() + 1, startingLocation.getY(), startingLocation.getZ() - 1);

        } else if (angleIsInRange(direction.getMmAngle(), 60, 120)) {
            return new Location(startingLocation.getX(), startingLocation.getY() + 1, startingLocation.getZ() - 1);

        } else if (angleIsInRange(direction.getMmAngle(), 120, 180)) {
            return new Location(startingLocation.getX() - 1, startingLocation.getY() + 1, startingLocation.getZ());

        } else if (angleIsInRange(direction.getMmAngle(), 180, 240)) {
            return new Location(startingLocation.getX() - 1, startingLocation.getY(), startingLocation.getZ() + 1);

        } else if (angleIsInRange(direction.getMmAngle(), 240, 300)) {
            return new Location(startingLocation.getX(), startingLocation.getY() - 1, startingLocation.getZ() + 1);

        } else if (angleIsInRange(direction.getMmAngle(), 300, 360)) {
            return new Location(startingLocation.getX() + 1, startingLocation.getY() - 1, startingLocation.getZ());

        } else {
            throw new RuntimeException("Angle was not between 0 and 360");
        }
    }
}
