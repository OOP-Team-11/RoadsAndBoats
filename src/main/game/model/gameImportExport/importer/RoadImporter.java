package game.model.gameImportExport.importer;

import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileCompartmentLocation;
import game.model.direction.TileEdgeDirection;
import game.model.managers.StructureManager;
import game.model.map.RBMap;
import game.model.structures.resourceProducer.primaryProducer.Mine;
import game.model.tile.Tile;
import game.model.tile.TileCompartment;
import game.utilities.exceptions.MalformedMapFileException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;

import static game.model.gameImportExport.importer.ParseUtilities.getMatcherForPatternInString;

public class RoadImporter {

    static void importRoadsFromFile(RBMap map, BufferedReader bufferedReader) throws MalformedMapFileException, IOException {
        if (!bufferedReader.readLine().equals("-----BEGIN ROAD-----"))  throw new MalformedMapFileException("-----BEGIN ROAD----- not found");

        boolean foundEOF = false;
        for(String line; (line = bufferedReader.readLine()) != null; ) {
            if (line.equals("-----END ROAD-----")) {
                foundEOF = true;
                break;
            }

            Location location = getLocation(line);
            TileCompartmentDirection tcd = getTileCompartmentDirection(line);
            TileEdgeDirection ted = getTileEdgeDirection(line);
            map.buildRoad(location, ted, tcd);
        }

        if (!foundEOF) throw new MalformedMapFileException("-----END ROAD----- not found");
    }

    private static Location getLocation(String roadString) throws MalformedMapFileException {
        Matcher m = getMatcherForPatternInString(roadString, "(\\([^)]*\\))");
        if (m.find()) {
            String locationString = m.group(1);

            try {
                locationString = locationString.substring(2, locationString.length() - 2);
            } catch (IndexOutOfBoundsException e) {
                throw new MalformedMapFileException("Could not parse location for road: " + roadString);
            }
            String[] coordinatesString = locationString.split(" ");
            if (coordinatesString.length != 3)
                throw new MalformedMapFileException("Malformed location in road string: " + roadString);

            try {
                int x = Integer.parseInt(coordinatesString[0]);
                int y = Integer.parseInt(coordinatesString[1]);
                int z = Integer.parseInt(coordinatesString[2]);

                try {
                    return new Location(x, y, z);
                } catch (IllegalArgumentException e) {
                    throw new MalformedMapFileException("Invalid location for road: " + roadString);
                }

            } catch (NumberFormatException e) {
                throw new MalformedMapFileException("Malformed road string, could not parse location ints: " + roadString);
            }
        }

        throw new MalformedMapFileException("Could not find location on line: " + roadString);
    }

    private static TileCompartmentDirection getTileCompartmentDirection(String roadString) throws MalformedMapFileException {
        Matcher m = getMatcherForPatternInString(roadString, "\\([^)]*\\) ([A-Z]{1,3})");
        if (m.find()) {
            String directionString = m.group(1);
            TileCompartmentDirection tcd = ParseUtilities.getTileCompartmentDirectionForTCDString(directionString);
            if (tcd == null) throw new MalformedMapFileException("Could not parse direction for road: " + roadString);
            return tcd;
        }

        throw new MalformedMapFileException("Malformed mine string: " + roadString);
    }

    private static TileEdgeDirection getTileEdgeDirection(String roadString) throws MalformedMapFileException {
        Matcher m = getMatcherForPatternInString(roadString, "\\([^)]*\\) [A-Z]{1,3} ([A-Z]{1,3})");
        if (m.find()) {
            String directionString = m.group(1);
            TileEdgeDirection ted = ParseUtilities.getTileEdgeDirectionForString(directionString);
            if (ted == null) throw new MalformedMapFileException("Could not parse direction for road: " + roadString);
            return ted;
        }

        throw new MalformedMapFileException("Malformed mine string: " + roadString);
    }
}
