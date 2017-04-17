package game.model.gameImportExport.importer;

import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.ResourceManager;
import game.model.managers.StructureManager;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.ResourceHolder;
import game.model.structures.resourceProducer.primaryProducer.Mine;
import game.model.structures.resourceProducer.primaryProducer.OilRig;
import game.model.tile.TileCompartment;
import game.utilities.exceptions.MalformedMapFileException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;

import static game.model.gameImportExport.importer.ParseUtilities.getMatcherForPatternInString;

class OilRigImporter {

    static void importOilRigsFromFile(StructureManager structureManager, BufferedReader bufferedReader) throws MalformedMapFileException, IOException {
        if (!bufferedReader.readLine().equals("-----BEGIN OILRIG-----"))  throw new MalformedMapFileException("-----BEGIN OILRIG----- not found");

        boolean foundEOF = false;
        for(String line; (line = bufferedReader.readLine()) != null; ) {
            if (line.equals("-----END OILRIG-----")) {
                foundEOF = true;
                break;
            }

            line = stripIdentifier(line);
            Location location = getLocation(line);
            TileCompartmentDirection tcd = getTileCompartmentDirection(line);
            TileCompartmentLocation tcl = new TileCompartmentLocation(location, tcd);
            OilRig oilRig = createOilRigWithResources(line);
            structureManager.addStructure(tcl, oilRig);
        }

        if (!foundEOF) throw new MalformedMapFileException("-----END OILRIG----- not found");
    }

    private static String stripIdentifier(String oilRigString) {
        return oilRigString.replace("OILRIG ", "");
    }

    private static Location getLocation(String mineString) throws MalformedMapFileException {
        Matcher m = getMatcherForPatternInString(mineString, "(\\([^)]*\\))");
        if (m.find()) {
            String locationString = m.group(1);

            try {
                locationString = locationString.substring(2, locationString.length() - 2);
            } catch (IndexOutOfBoundsException e) {
                throw new MalformedMapFileException("Could not parse location for oil rig: " + mineString);
            }
            String[] coordinatesString = locationString.split(" ");
            if (coordinatesString.length != 3)
                throw new MalformedMapFileException("Malformed location in oil rig string: " + mineString);

            try {
                int x = Integer.parseInt(coordinatesString[0]);
                int y = Integer.parseInt(coordinatesString[1]);
                int z = Integer.parseInt(coordinatesString[2]);

                try {
                    return new Location(x, y, z);
                } catch (IllegalArgumentException e) {
                    throw new MalformedMapFileException("Invalid location for oil rig: " + mineString);
                }

            } catch (NumberFormatException e) {
                throw new MalformedMapFileException("Malformed oil rig string, could not parse location ints: " + mineString);
            }
        }

        throw new MalformedMapFileException("Could not find location on line: " + mineString);
    }

    private static TileCompartmentDirection getTileCompartmentDirection(String mineString) throws MalformedMapFileException {
        Matcher m = getMatcherForPatternInString(mineString, "\\([^)]*\\) ([A-Z])");
        if (m.find()) {
            String directionString = m.group(1);
            TileCompartmentDirection tcd = ParseUtilities.getTileCompartmentDirectionForTCDString(directionString);
            if (tcd == null) throw new MalformedMapFileException("Could not parse direction for oil rig: " + mineString);
            return tcd;
        }

        throw new MalformedMapFileException("Malformed mine string: " + mineString);
    }

    private static OilRig createOilRigWithResources(String oilRigString) throws MalformedMapFileException {
        Matcher m = getMatcherForPatternInString(oilRigString, "(\\[.*\\])");
        if (m.find()) {
            String resourcesString = m.group(1);
            Matcher resourceMatcher = getMatcherForPatternInString(resourcesString, "([A-Z][^=]*)=([0-9]{1,2})");
            boolean foundResources = false;
            OilRig resourceHolder = new OilRig();
            while (resourceMatcher.find()) {
                foundResources = true;
                String resourceName = resourceMatcher.group(1);
                String stringAmount = resourceMatcher.group(2);
                int amount;
                ResourceType resourceType = ParseUtilities.getResourceTypeByString(resourceName);
                if (resourceType == null) throw new MalformedMapFileException("Could not match resource string to resource type: " + oilRigString);

                try {
                    amount = Integer.parseInt(stringAmount);
                } catch (NumberFormatException e) {
                    throw new MalformedMapFileException("Could not parse resource amount: " + oilRigString);
                }

                resourceHolder.storeResource(resourceType, amount);
            }

            if (!foundResources) throw new MalformedMapFileException("Found brackets with no resources for oil rig: " + oilRigString);
            return resourceHolder;
        }

        return new OilRig();
    }
}
