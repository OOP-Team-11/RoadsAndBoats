package game.model.gameImportExport.importer;

import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.StructureManager;
import game.model.structures.resourceProducer.primaryProducer.ClayPit;
import game.model.structures.resourceProducer.primaryProducer.Mine;
import game.model.structures.resourceProducer.primaryProducer.StoneQuarry;
import game.model.structures.resourceProducer.primaryProducer.Woodcutter;
import game.model.structures.resourceProducer.secondaryProducer.Mint;
import game.model.structures.resourceProducer.secondaryProducer.Papermill;
import game.utilities.exceptions.MalformedMapFileException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;

import static game.model.gameImportExport.importer.ParseUtilities.getMatcherForPatternInString;

public class NonSpecialLimitlessStructures {

    static void importNonSpecialLimitlessStructures(StructureManager structureManager, BufferedReader bufferedReader) throws MalformedMapFileException, IOException {
        importClaypitsFromFile(structureManager, bufferedReader);
        importStoneQuarryFromFile(structureManager, bufferedReader);
        importWoodcutterFromFile(structureManager, bufferedReader);
        importMintFromFile(structureManager, bufferedReader);
        importPapermillFromFile(structureManager, bufferedReader);
    }

    private static void importClaypitsFromFile(StructureManager structureManager, BufferedReader bufferedReader) throws MalformedMapFileException, IOException {
        if (!bufferedReader.readLine().equals("-----BEGIN CLAYPIT-----"))  throw new MalformedMapFileException("-----BEGIN CLAYPIT----- not found");

        boolean foundEOF = false;
        for(String line; (line = bufferedReader.readLine()) != null; ) {
            if (line.equals("-----END CLAYPIT-----")) {
                foundEOF = true;
                break;
            }

            Location location = getLocation(line);
            TileCompartmentDirection tcd = getTileCompartmentDirection(line);
            TileCompartmentLocation tcl = new TileCompartmentLocation(location, tcd);
            ClayPit clayPit = new ClayPit();
            structureManager.addStructure(tcl, clayPit);
        }

        if (!foundEOF) throw new MalformedMapFileException("-----END CLAYPIT----- not found");
    }

    private static void importStoneQuarryFromFile(StructureManager structureManager, BufferedReader bufferedReader) throws MalformedMapFileException, IOException {
        if (!bufferedReader.readLine().equals("-----BEGIN STONEQUARRY-----"))  throw new MalformedMapFileException("-----BEGIN STONEQUARRY----- not found");

        boolean foundEOF = false;
        for(String line; (line = bufferedReader.readLine()) != null; ) {
            if (line.equals("-----END STONEQUARRY-----")) {
                foundEOF = true;
                break;
            }

            Location location = getLocation(line);
            TileCompartmentDirection tcd = getTileCompartmentDirection(line);
            TileCompartmentLocation tcl = new TileCompartmentLocation(location, tcd);
            StoneQuarry stoneQuarry = new StoneQuarry();
            structureManager.addStructure(tcl, stoneQuarry);
        }

        if (!foundEOF) throw new MalformedMapFileException("-----END STONEQUARRY----- not found");
    }

    private static void importWoodcutterFromFile(StructureManager structureManager, BufferedReader bufferedReader) throws MalformedMapFileException, IOException {
        if (!bufferedReader.readLine().equals("-----BEGIN WOODCUTTER-----"))  throw new MalformedMapFileException("-----BEGIN WOODCUTTER----- not found");

        boolean foundEOF = false;
        for(String line; (line = bufferedReader.readLine()) != null; ) {
            if (line.equals("-----END WOODCUTTER-----")) {
                foundEOF = true;
                break;
            }

            Location location = getLocation(line);
            TileCompartmentDirection tcd = getTileCompartmentDirection(line);
            TileCompartmentLocation tcl = new TileCompartmentLocation(location, tcd);
            Woodcutter woodcutter = new Woodcutter();
            structureManager.addStructure(tcl, woodcutter);
        }

        if (!foundEOF) throw new MalformedMapFileException("-----END WOODCUTTER----- not found");
    }

    private static void importMintFromFile(StructureManager structureManager, BufferedReader bufferedReader) throws MalformedMapFileException, IOException {
        if (!bufferedReader.readLine().equals("-----BEGIN MINT-----"))  throw new MalformedMapFileException("-----BEGIN MINT----- not found");

        boolean foundEOF = false;
        for(String line; (line = bufferedReader.readLine()) != null; ) {
            if (line.equals("-----END MINT-----")) {
                foundEOF = true;
                break;
            }

            Location location = getLocation(line);
            TileCompartmentDirection tcd = getTileCompartmentDirection(line);
            TileCompartmentLocation tcl = new TileCompartmentLocation(location, tcd);
            Mint mint = new Mint();
            structureManager.addStructure(tcl, mint);
        }

        if (!foundEOF) throw new MalformedMapFileException("-----END MINT----- not found");
    }

    private static void importPapermillFromFile(StructureManager structureManager, BufferedReader bufferedReader) throws MalformedMapFileException, IOException {
        if (!bufferedReader.readLine().equals("-----BEGIN PAPERMILL-----"))  throw new MalformedMapFileException("-----BEGIN PAPERMILL----- not found");

        boolean foundEOF = false;
        for(String line; (line = bufferedReader.readLine()) != null; ) {
            if (line.equals("-----END PAPERMILL-----")) {
                foundEOF = true;
                break;
            }

            Location location = getLocation(line);
            TileCompartmentDirection tcd = getTileCompartmentDirection(line);
            TileCompartmentLocation tcl = new TileCompartmentLocation(location, tcd);
            Papermill papermill = new Papermill();
            structureManager.addStructure(tcl, papermill);
        }

        if (!foundEOF) throw new MalformedMapFileException("-----END PAPERMILL----- not found");
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
