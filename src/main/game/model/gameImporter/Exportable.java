package game.model.gameImporter;

import game.model.direction.Location;

public class Exportable {
    private Location location;
    private String exportString;

    public Exportable(Location location, String exportString) {
        this.location = location;
        this.exportString = exportString;
    }

    public String getExportValue() {
        return "( " + location.getX() + " " + location.getY() + " " + location.getZ() + " ) " + exportString;
    }
}
