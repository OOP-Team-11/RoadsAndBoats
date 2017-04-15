package game.model.gameImporter.importer;

import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.map.RBMap;
import game.model.resources.ResourceType;
import game.model.tile.Tile;
import game.model.tile.TileCompartment;
import game.utilities.exceptions.MalformedMapFileException;

import java.io.BufferedReader;
import java.io.IOException;

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

    public RBMap importResourcesFromFile(RBMap map, BufferedReader bufferedReader) throws MalformedMapFileException, IOException {
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
        }

        if (!foundEOF) throw new MalformedMapFileException("END RESOURCE not found");
        return map;
    }

    private Location getLocation(String resourceString) throws MalformedMapFileException {
        String[] sections = resourceString.split(" ");
        if (sections.length != 3) throw new MalformedMapFileException("Malformed resource string: " + resourceString);

        String locationString = sections[0];
        locationString = locationString.substring(1,locationString.length()-1);
        String[] coordinatesString = locationString.split(" ");
        if (coordinatesString.length != 3)  throw new MalformedMapFileException("Malformed resource string: " + resourceString);

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
        String[] sections = resourceString.split(" ");
        if (sections.length != 3) throw new MalformedMapFileException("Malformed resource string: " + resourceString);

        String directionString = sections[1];
        TileCompartmentDirection tcd = getTileCompartmentDirectionForTCDString(directionString);
        if (tcd == null) throw new MalformedMapFileException("Could not parse direction for resource: " + resourceString);
        return tcd;
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
        String[] sections = resourceString.split(" ");
        if (sections.length != 3) throw new MalformedMapFileException("Malformed resource string: " + resourceString);

        String rsrcString = sections[2];
        String[] resourceArr = rsrcString.split("=");
        if (resourceArr.length != 3) throw new MalformedMapFileException("Malformed resource string: " + resourceString);

        try {
            ResourceType type = getResourceTypeByString(resourceArr[0]);
            if (type == null) throw new MalformedMapFileException("Malformed resource string: " + resourceString);
            int amount = Integer.parseInt(resourceArr[2]);
            return new Resource(type, amount);
        } catch (NumberFormatException e) {
            throw new MalformedMapFileException("Malformed resource string: " + resourceString);
        }
    }
}
