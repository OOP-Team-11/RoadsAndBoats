package game.model.gameImportExport.importer;

import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.StructureManager;
import game.model.structures.resourceProducer.primaryProducer.Mine;
import game.utilities.exceptions.MalformedMapFileException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static game.model.gameImportExport.importer.ParseUtilities.getMatcherForPatternInString;

class MineImporter {

    static void importMinesFromFile(StructureManager structureManager, BufferedReader bufferedReader) throws MalformedMapFileException, IOException {
        if (!bufferedReader.readLine().equals("-----BEGIN MINE-----"))  throw new MalformedMapFileException("-----BEGIN MINE----- not found");

        boolean foundEOF = false;
        for(String line; (line = bufferedReader.readLine()) != null; ) {
            if (line.equals("-----END MINE-----")) {
                foundEOF = true;
                break;
            }

            line = stripIdentifier(line);
            Location location = getLocation(line);
            TileCompartmentDirection tcd = getTileCompartmentDirection(line);
            Mine mine = createMineWithCapacity(line);
            TileCompartmentLocation tcl = new TileCompartmentLocation(location, tcd);
            structureManager.addStructure(tcl, mine);
        }

        if (!foundEOF) throw new MalformedMapFileException("-----END MINE----- not found");
    }

    private static String stripIdentifier(String mineString) {
        return mineString.replace("MINE ", "");
    }

    private static Location getLocation(String mineString) throws MalformedMapFileException {
        Matcher m = getMatcherForPatternInString(mineString, "(\\([^)]*\\))");
        if (m.find()) {
            String locationString = m.group(1);

            try {
                locationString = locationString.substring(2, locationString.length() - 2);
            } catch (IndexOutOfBoundsException e) {
                throw new MalformedMapFileException("Could not parse location for mine: " + mineString);
            }
            String[] coordinatesString = locationString.split(" ");
            if (coordinatesString.length != 3)
                throw new MalformedMapFileException("Malformed location in mine string: " + mineString);

            try {
                int x = Integer.parseInt(coordinatesString[0]);
                int y = Integer.parseInt(coordinatesString[1]);
                int z = Integer.parseInt(coordinatesString[2]);

                try {
                    return new Location(x, y, z);
                } catch (IllegalArgumentException e) {
                    throw new MalformedMapFileException("Invalid location for mine: " + mineString);
                }

            } catch (NumberFormatException e) {
                throw new MalformedMapFileException("Malformed mine string, could not parse location ints: " + mineString);
            }
        }

        throw new MalformedMapFileException("Could not find location on line: " + mineString);
    }

    private static TileCompartmentDirection getTileCompartmentDirection(String mineString) throws MalformedMapFileException {
        Matcher m = getMatcherForPatternInString(mineString, "\\([^)]*\\) ([A-Z])");
        if (m.find()) {
            String directionString = m.group(1);
            TileCompartmentDirection tcd = ParseUtilities.getTileCompartmentDirectionForTCDString(directionString);
            if (tcd == null) throw new MalformedMapFileException("Could not parse direction for mine: " + mineString);
            return tcd;
        }

        throw new MalformedMapFileException("Malformed mine string: " + mineString);
    }

    private static Mine createMineWithCapacity(String mineString) throws MalformedMapFileException {
        int maxGoldCount = -1;
        int maxIronCount = -1;
        int currentGoldCount = -1;
        int currentIronCount = -1;

        Matcher m = getMatcherForPatternInString(mineString, "MAX=\\[GOLD=([0-9]{1,2}) IRON=([0-9]{1,2})\\]");
        if (m.find()) {
            String goldCount = m.group(1);
            String ironCount = m.group(2);
            try {
                maxGoldCount = Integer.parseInt(goldCount);
                maxIronCount = Integer.parseInt(ironCount);
            } catch (NumberFormatException e) {
                throw new MalformedMapFileException("Could not parse max resource amounts for mine: " + mineString);
            }
        }

        Matcher m2 = getMatcherForPatternInString(mineString, "CURRENT=\\[GOLD=([0-9]{1,2}) IRON=([0-9]{1,2})\\]");
        if (m2.find()) {
            String goldCount = m2.group(1);
            String ironCount = m2.group(2);
            try {
                currentGoldCount = Integer.parseInt(goldCount);
                currentIronCount = Integer.parseInt(ironCount);
            } catch (NumberFormatException e) {
                throw new MalformedMapFileException("Could not parse current resource amounts for mine: " + mineString);
            }
        }

        if (maxGoldCount == -1 || maxIronCount == -1 || currentGoldCount == -1 || currentIronCount == -1)
            throw new MalformedMapFileException("Could not match mine resource string: " + mineString);

        return new Mine(currentGoldCount, maxGoldCount, currentIronCount, maxIronCount);
    }
}
