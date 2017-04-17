package game.model.gameImportExport.importer;

import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.StructureManager;
import game.model.structures.resourceProducer.primaryProducer.ClayPit;
import game.model.structures.resourceProducer.primaryProducer.StoneQuarry;
import game.model.structures.resourceProducer.primaryProducer.Woodcutter;
import game.model.structures.resourceProducer.secondaryProducer.*;
import game.utilities.exceptions.MalformedMapFileException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;

import static game.model.gameImportExport.importer.ParseUtilities.getMatcherForPatternInString;

public class NonSpecialStructuresWithLimits {

    static void importNonSpecialStructuresWithLimits(StructureManager structureManager, BufferedReader bufferedReader) throws MalformedMapFileException, IOException {
        importCoalburnersFromFile(structureManager, bufferedReader);
        importSawmillsFromFile(structureManager, bufferedReader);
        importStoneFactoriesFromFile(structureManager, bufferedReader);
        importStockMarketsFromFile(structureManager, bufferedReader);
    }

    private static void importCoalburnersFromFile(StructureManager structureManager, BufferedReader bufferedReader) throws MalformedMapFileException, IOException {
        if (!bufferedReader.readLine().equals("-----BEGIN COALBURNER-----"))  throw new MalformedMapFileException("-----BEGIN COALBURNER----- not found");

        boolean foundEOF = false;
        for(String line; (line = bufferedReader.readLine()) != null; ) {
            if (line.equals("-----END COALBURNER-----")) {
                foundEOF = true;
                break;
            }

            line = stripIdentifier(line, "COALBURNER");
            Location location = getLocation(line);
            TileCompartmentDirection tcd = getTileCompartmentDirection(line);
            TileCompartmentLocation tcl = new TileCompartmentLocation(location, tcd);
            int productionLimit = getLimit(line);
            CoalBurner coalBurner = new CoalBurner(productionLimit);
            structureManager.addStructure(tcl, coalBurner);
        }

        if (!foundEOF) throw new MalformedMapFileException("-----END COALBURNER----- not found");
    }

    private static void importSawmillsFromFile(StructureManager structureManager, BufferedReader bufferedReader) throws MalformedMapFileException, IOException {
        if (!bufferedReader.readLine().equals("-----BEGIN SAWMILL-----"))  throw new MalformedMapFileException("-----BEGIN SAWMILL----- not found");

        boolean foundEOF = false;
        for(String line; (line = bufferedReader.readLine()) != null; ) {
            if (line.equals("-----END SAWMILL-----")) {
                foundEOF = true;
                break;
            }

            line = stripIdentifier(line, "SAWMILL");
            Location location = getLocation(line);
            TileCompartmentDirection tcd = getTileCompartmentDirection(line);
            TileCompartmentLocation tcl = new TileCompartmentLocation(location, tcd);
            int productionLimit = getLimit(line);
            Sawmill sawmill = new Sawmill(productionLimit);
            structureManager.addStructure(tcl, sawmill);
        }

        if (!foundEOF) throw new MalformedMapFileException("-----END SAWMILL----- not found");
    }

    private static void importStoneFactoriesFromFile(StructureManager structureManager, BufferedReader bufferedReader) throws MalformedMapFileException, IOException {
        if (!bufferedReader.readLine().equals("-----BEGIN STONEFACTORY-----"))  throw new MalformedMapFileException("-----BEGIN STONEFACTORY----- not found");

        boolean foundEOF = false;
        for(String line; (line = bufferedReader.readLine()) != null; ) {
            if (line.equals("-----END STONEFACTORY-----")) {
                foundEOF = true;
                break;
            }

            line = stripIdentifier(line, "STONEFACTORY");
            Location location = getLocation(line);
            TileCompartmentDirection tcd = getTileCompartmentDirection(line);
            TileCompartmentLocation tcl = new TileCompartmentLocation(location, tcd);
            int productionLimit = getLimit(line);
            StoneFactory stonefactory = new StoneFactory(productionLimit);
            structureManager.addStructure(tcl, stonefactory);
        }

        if (!foundEOF) throw new MalformedMapFileException("-----END STONEFACTORY----- not found");
    }

    private static void importStockMarketsFromFile(StructureManager structureManager, BufferedReader bufferedReader) throws MalformedMapFileException, IOException {
        if (!bufferedReader.readLine().equals("-----BEGIN STOCKMARKET-----"))  throw new MalformedMapFileException("-----BEGIN STOCKMARKET----- not found");

        boolean foundEOF = false;
        for(String line; (line = bufferedReader.readLine()) != null; ) {
            if (line.equals("-----END STOCKMARKET-----")) {
                foundEOF = true;
                break;
            }

            line = stripIdentifier(line, "STOCKMARKET");
            Location location = getLocation(line);
            TileCompartmentDirection tcd = getTileCompartmentDirection(line);
            TileCompartmentLocation tcl = new TileCompartmentLocation(location, tcd);
            int productionLimit = getLimit(line);
            StockMarket stockMarket = new StockMarket(productionLimit);
            structureManager.addStructure(tcl, stockMarket);
        }

        if (!foundEOF) throw new MalformedMapFileException("-----END STOCKMARKET----- not found");
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
                throw new MalformedMapFileException("Could not parse location for limit structure: " + structureString);
            }
            String[] coordinatesString = locationString.split(" ");
            if (coordinatesString.length != 3)
                throw new MalformedMapFileException("Malformed location in limit structure string: " + structureString);

            try {
                int x = Integer.parseInt(coordinatesString[0]);
                int y = Integer.parseInt(coordinatesString[1]);
                int z = Integer.parseInt(coordinatesString[2]);

                try {
                    return new Location(x, y, z);
                } catch (IllegalArgumentException e) {
                    throw new MalformedMapFileException("Invalid location for limit structure: " + structureString);
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
            if (tcd == null) throw new MalformedMapFileException("Could not parse direction for limit structure: " + structureString);
            return tcd;
        }

        throw new MalformedMapFileException("Malformed mine string: " + structureString);
    }

    private static int getLimit(String structureString) throws MalformedMapFileException {
        Matcher m = getMatcherForPatternInString(structureString, "LIMIT=([0-9]{1,2})");
        if (m.find()) {
            String limitString = m.group(1);
            try {
                return Integer.parseInt(limitString);
            } catch (NumberFormatException e) {
                throw new MalformedMapFileException("Could not parse production limit: " + structureString);
            }
        }

        throw new MalformedMapFileException("Could not find production limit for limit structure: " + structureString);
    }
}
