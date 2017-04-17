package game.model.gameImportExport.importer;

import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.GooseManager;
import game.model.managers.StructureManager;
import game.model.resources.Goose;
import game.model.resources.GooseId;
import game.model.structures.resourceProducer.primaryProducer.ClayPit;
import game.model.structures.resourceProducer.primaryProducer.StoneQuarry;
import game.model.structures.resourceProducer.primaryProducer.Woodcutter;
import game.model.structures.resourceProducer.secondaryProducer.Mint;
import game.model.structures.resourceProducer.secondaryProducer.Papermill;
import game.utilities.exceptions.MalformedMapFileException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;

import static game.model.gameImportExport.importer.ParseUtilities.getMatcherForPatternInString;

public class GooseImporter {

    static void importGoose(GooseManager gooseManager, BufferedReader bufferedReader) throws MalformedMapFileException, IOException {
        importGeese(gooseManager, bufferedReader);
    }

    private static void importGeese(GooseManager gooseManager, BufferedReader bufferedReader) throws MalformedMapFileException, IOException {
        if (!bufferedReader.readLine().equals("-----BEGIN GOOSE-----"))  throw new MalformedMapFileException("-----BEGIN GOOSE----- not found");

        boolean foundEOF = false;
        for(String line; (line = bufferedReader.readLine()) != null; ) {
            if (line.equals("-----END GOOSE-----")) {
                foundEOF = true;
                break;
            }

            line = stripIdentifier(line, "GOOSE");
            Location location = getLocation(line);
            TileCompartmentDirection tcd = getTileCompartmentDirection(line);
            TileCompartmentLocation tcl = new TileCompartmentLocation(location, tcd);
            Goose goose = new Goose(new GooseId());
            gooseManager.addGoose(tcl, goose);
        }

        if (!foundEOF) throw new MalformedMapFileException("-----END GOOSE----- not found");
    }

    private static String stripIdentifier(String structureString, String structureName) {
        return structureString.replace(structureName + " ", "");
    }

    private static Location getLocation(String structureString) throws MalformedMapFileException {
        Matcher m = getMatcherForPatternInString(structureString, "(\\([^)]*\\))");
        if (m.find()) {
            String locationString = m.group(1);

            try {
                locationString = locationString.substring(2, locationString.length() - 2);
            } catch (IndexOutOfBoundsException e) {
                throw new MalformedMapFileException("Could not parse location for structure: " + structureString);
            }
            String[] coordinatesString = locationString.split(" ");
            if (coordinatesString.length != 3)
                throw new MalformedMapFileException("Malformed location in structure string: " + structureString);

            try {
                int x = Integer.parseInt(coordinatesString[0]);
                int y = Integer.parseInt(coordinatesString[1]);
                int z = Integer.parseInt(coordinatesString[2]);

                try {
                    return new Location(x, y, z);
                } catch (IllegalArgumentException e) {
                    throw new MalformedMapFileException("Invalid location for structure: " + structureString);
                }

            } catch (NumberFormatException e) {
                throw new MalformedMapFileException("Malformed structure string, could not parse location ints: " + structureString);
            }
        }

        throw new MalformedMapFileException("Could not find location on line: " + structureString);
    }

    private static TileCompartmentDirection getTileCompartmentDirection(String structureString) throws MalformedMapFileException {
        Matcher m = getMatcherForPatternInString(structureString, "\\([^)]*\\) ([A-Z])");
        if (m.find()) {
            String directionString = m.group(1);
            TileCompartmentDirection tcd = ParseUtilities.getTileCompartmentDirectionForTCDString(directionString);
            if (tcd == null) throw new MalformedMapFileException("Could not parse direction for structure: " + structureString);
            return tcd;
        }

        throw new MalformedMapFileException("Malformed mine string: " + structureString);
    }
}
