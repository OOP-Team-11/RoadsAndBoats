package mapMaker.direction;

import mapMaker.model.tile.mmInvalidLocationException;
import mapMaker.model.tile.mmLocation;

public class mmDirectionToLocation {

    private static boolean angleIsInRange(mmAngle mmAngle, int lowerDegrees, int higherDegrees) {
        return mmAngle.getDegrees() > lowerDegrees && mmAngle.getDegrees() < higherDegrees;
    }

    public static mmLocation getLocation(mmLocation startingMmLocation, mmTileEdgeDirection direction) {

        try {
            if (angleIsInRange(direction.getMmAngle(), 0, 60)) {
                return new mmLocation(startingMmLocation.getX() + 1, startingMmLocation.getY(), startingMmLocation.getZ() - 1);

            } else if (angleIsInRange(direction.getMmAngle(), 60, 120)) {
                return new mmLocation(startingMmLocation.getX(), startingMmLocation.getY() + 1, startingMmLocation.getZ() - 1);

            } else if (angleIsInRange(direction.getMmAngle(), 120, 180)) {
                return new mmLocation(startingMmLocation.getX() - 1, startingMmLocation.getY() + 1, startingMmLocation.getZ());

            } else if (angleIsInRange(direction.getMmAngle(), 180, 240)) {
                return new mmLocation(startingMmLocation.getX() - 1, startingMmLocation.getY(), startingMmLocation.getZ() + 1);

            } else if (angleIsInRange(direction.getMmAngle(), 240, 300)) {
                return new mmLocation(startingMmLocation.getX(), startingMmLocation.getY() - 1, startingMmLocation.getZ() + 1);

            } else if (angleIsInRange(direction.getMmAngle(), 300, 360)) {
                return new mmLocation(startingMmLocation.getX() + 1, startingMmLocation.getY() - 1, startingMmLocation.getZ());

            } else {
                throw new RuntimeException("mmAngle was not between 0 and 360");
            }

        } catch (mmInvalidLocationException e) {
            throw new RuntimeException("Direction to location calculated an invalid location.");
        }
    }
}
