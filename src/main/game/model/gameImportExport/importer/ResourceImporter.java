package game.model.gameImportExport.importer;

import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.map.RBMap;
import game.model.resources.ResourceType;
import game.model.tile.Tile;
import game.model.tile.TileCompartment;
import game.utilities.exceptions.MalformedMapFileException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static game.model.resources.ResourceType.*;
import static game.model.resources.ResourceType.STOCKBOND;

public class ResourceImporter {

    class Resource {
        ResourceType resourceType;
        int amount;
        Resource(ResourceType resourceType, int amount) {
            this.resourceType = resourceType;
            this.amount = amount;
        }
    }

    public void importResourcesFromFile(RBMap map, BufferedReader bufferedReader) throws MalformedMapFileException, IOException {
        if (!bufferedReader.readLine().equals("BEGIN RESOURCE"))  throw new MalformedMapFileException("BEGIN RESOURCE not found");

        boolean foundEOF = false;
        for(String line; (line = bufferedReader.readLine()) != null; ) {
            if (line.equals("END RESOURCE")) {
                foundEOF = true;
                break;
            }

            Location location = getLocation(line);
            TileCompartmentDirection tcd = getTileCompartmentDirection(line);
            Resource resource = getResource(line);
            Tile tile = map.getTile(location);
            TileCompartment tileCompartment = tile.getTileCompartment(tcd);

            // TODO: add resource to tile compartment once tony merges his branch
        }

        if (!foundEOF) throw new MalformedMapFileException("END RESOURCE not found");
    }

    private Location getLocation(String resourceString) throws MalformedMapFileException {
        Matcher m = getMatcherForPatternInString(resourceString, "(\\([^)]*\\))");
        if (m.find()) {
            String locationString = m.group(1);

            try {
                locationString = locationString.substring(2, locationString.length() - 2);
            } catch (IndexOutOfBoundsException e) {
                throw new MalformedMapFileException("Could not parse location for resource: " + resourceString);
            }
            String[] coordinatesString = locationString.split(" ");
            if (coordinatesString.length != 3)
                throw new MalformedMapFileException("Malformed resource string: " + resourceString);

            try {
                int x = Integer.parseInt(coordinatesString[0]);
                int y = Integer.parseInt(coordinatesString[1]);
                int z = Integer.parseInt(coordinatesString[2]);

                try {
                    return new Location(x, y, z);
                } catch (IllegalArgumentException e) {
                    throw new MalformedMapFileException("Invalid location for resource: " + resourceString);
                }

            } catch (NumberFormatException e) {
                throw new MalformedMapFileException("Malformed resource string: " + resourceString);
            }
        }

        throw new MalformedMapFileException("Could not find location on line: " + resourceString);
    }

    private TileCompartmentDirection getTileCompartmentDirectionForTCDString(String tcdString) {
        switch (tcdString) {
            case "E":
                return TileCompartmentDirection.getEast();
            case "NE":
                return TileCompartmentDirection.getNorthEast();
            case "NNE":
                return TileCompartmentDirection.getNorthNorthEast();
            case "N":
                return TileCompartmentDirection.getNorth();
            case "NNW":
                return TileCompartmentDirection.getNorthNorthWest();
            case "NW":
                return TileCompartmentDirection.getNorthWest();
            case "W":
                return TileCompartmentDirection.getWest();
            case "SW":
                return TileCompartmentDirection.getSouthWest();
            case "SSW":
                return TileCompartmentDirection.getSouthSouthWest();
            case "S":
                return TileCompartmentDirection.getSouth();
            case "SE":
                return TileCompartmentDirection.getSouthEast();
            case "SSE":
                return TileCompartmentDirection.getSouthSouthEast();
            default:
                return null;
        }
    }

    private TileCompartmentDirection getTileCompartmentDirection(String resourceString) throws MalformedMapFileException {
        Matcher m = getMatcherForPatternInString(resourceString, "\\([^)]*\\) ([A-Z])");
        if (m.find()) {
            String directionString = m.group(1);
            TileCompartmentDirection tcd = getTileCompartmentDirectionForTCDString(directionString);
            if (tcd == null) throw new MalformedMapFileException("Could not parse direction for resource: " + resourceString);
            return tcd;
        }

        throw new MalformedMapFileException("Malformed resource string: " + resourceString);
    }

    private ResourceType getResourceTypeByString(String name) {
        if (TRUNKS.getName().equals(name)) {
            return TRUNKS;
        } else if (BOARDS.getName().equals(name)) {
            return BOARDS;
        } else if (PAPER.getName().equals(name)) {
            return PAPER;
        } else if (GOOSE.getName().equals(name)) {
            return GOOSE;
        } else if (CLAY.getName().equals(name)) {
            return CLAY;
        } else if (FUEL.getName().equals(name)) {
            return FUEL;
        } else if (IRON.getName().equals(name)) {
            return IRON;
        } else if (GOLD.getName().equals(name)) {
            return GOLD;
        } else if (COINS.getName().equals(name)) {
            return COINS;
        } else if (STOCKBOND.getName().equals(name)) {
            return STOCKBOND;
        } else return null;
    }

    private Resource getResource(String resourceString) throws MalformedMapFileException {
        Matcher m = getMatcherForPatternInString(resourceString, "\\([^)]*\\) [A-Z] (.*)=([1-9][0-9]{0,2})");
        if (m.find()) {
            String resourceName = m.group(1);
            String resourceAmount = m.group(2);

            try {
                ResourceType type = getResourceTypeByString(resourceName);
                if (type == null) throw new MalformedMapFileException("Malformed resource string: " + resourceString);
                int amount = Integer.parseInt(resourceAmount);
                return new Resource(type, amount);
            } catch (NumberFormatException e) {
                throw new MalformedMapFileException("Malformed resource string: " + resourceString);
            }
        }

        throw new MalformedMapFileException("Could not find resource name and amount: " + resourceString);
    }

    private Matcher getMatcherForPatternInString(String searchString, String pattern) {
        Pattern locationPattern = Pattern.compile(pattern);
        return locationPattern.matcher(searchString);
    }
}
