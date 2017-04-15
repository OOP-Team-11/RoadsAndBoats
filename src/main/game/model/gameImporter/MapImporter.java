package game.model.gameImporter;

import game.model.direction.Location;
import game.model.map.RBMap;
import game.model.resources.ResourceManager;
import game.model.resources.ResourceType;
import game.model.tile.RiverConfiguration;
import game.model.tile.Terrain;
import game.model.tile.Tile;
import game.utilities.exceptions.MalformedMapFileException;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static game.model.resources.ResourceType.*;
import static game.model.tile.Terrain.*;

public class MapImporter {

//    public RBMap importMapFromFile(RBMap map, BufferedReader bufferedReader) throws MalformedMapFileException, IOException {
//        if (!bufferedReader.readLine().equals("BEGIN MAP"))  throw new MalformedMapFileException("BEGIN MAP not found");
//
//        boolean foundEOF = false;
//        for(String line; (line = bufferedReader.readLine()) != null; ) {
//            if (line.equals("END MAP")) {
//                foundEOF = true;
//                break;
//            }
//
//            Tile tile = getTileForTileString(line);
//            addResourcesToTileForTileString(tile, line);
//            Location location = getLocationForTileString(line);
//            map.placeTile(location, tile);
//        }
//
//        if (!foundEOF) throw new MalformedMapFileException("END MAP not found");
//        return map;
//    }

    private Location getLocationForTileString(String tileString) throws MalformedMapFileException {
        Pattern locationPattern = Pattern.compile("(\\([^)]*\\))");
        Matcher m = locationPattern.matcher(tileString);
        if (m.find()) {
            String groupString = m.group(1);
            if (groupString.length() < 3)  throw new MalformedMapFileException("Could not parse line: " + tileString);
            String locationString = groupString.substring(2, groupString.length()-2);
            String[] coordinates = locationString.split(" ");
            if (coordinates.length != 3) throw new MalformedMapFileException("Could not parse line: " + tileString);

            try {
                int x = Integer.parseInt(coordinates[0]);
                int y = Integer.parseInt(coordinates[1]);
                int z = Integer.parseInt(coordinates[2]);
                return new Location(x, y, z);
            } catch (IllegalArgumentException e) {
                throw new MalformedMapFileException("Could not parse line. Invalid coordinates: " + tileString);
            }
        }

        throw new MalformedMapFileException("Could not parse line. Could not find location: " + tileString);
    }

    private Terrain getTerrainForString(String terrainString) {
        switch (terrainString) {
            case "SEA":
                return SEA;
            case "PASTURE":
                return PASTURE;
            case "WOODS":
                return WOODS;
            case "ROCK":
                return ROCK;
            case "DESERT":
                return DESERT;
            case "MOUNTAIN":
                return MOUNTAIN;
            default:
                throw new IllegalArgumentException();
        }
    }

    private Terrain getTerrainForTileString(String tileString) throws MalformedMapFileException {
        Pattern locationPattern = Pattern.compile("\\([^)]*\\) ([^\\s]+)");
        Matcher m = locationPattern.matcher(tileString);
        if (m.find()) {
            String terrainString = m.group(1);
            try {
                return getTerrainForString(terrainString);
            } catch (IllegalArgumentException e) {
                throw new MalformedMapFileException("Could not parse terrain on line: " + tileString);
            }
        }

        throw new MalformedMapFileException("Terrain not found on line: " + tileString);
    }

    private RiverConfiguration getRiverConfigurationForTile(String tileString) throws MalformedMapFileException {
        Pattern locationPattern = Pattern.compile("\\([^)]*\\).*(\\([^)]*\\))");
        Matcher m = locationPattern.matcher(tileString);
        if (m.find()) {
            String groupString = m.group(1);
            if (groupString.length() < 3) throw new MalformedMapFileException("Could not parse file on line: " + tileString);

            String riverString = groupString.substring(2, groupString.length()-2);
            String[] riverSides = riverString.split(" ");
            Vector<Integer> riverSideInts = new Vector<>();

            try {
                for (String riverSide : riverSides) {
                    riverSideInts.add(Integer.parseInt(riverSide));
                }
            } catch (NumberFormatException e) {
                throw new MalformedMapFileException("River side is invalid: " + tileString);
            }

            if (riverSideInts.size() == 1) {
                return new RiverConfiguration(riverSideInts.get(0));
            } else if (riverSideInts.size() == 2) {
                return new RiverConfiguration(riverSideInts.get(0), riverSideInts.get(1));
            } else if (riverSideInts.size() == 3) {
                return new RiverConfiguration(riverSideInts.get(0), riverSideInts.get(1), riverSideInts.get(2));
            } else {
                throw new MalformedMapFileException("River configuration invalid: " + tileString);
            }
        }

        return RiverConfiguration.getNoRivers();
    }

    private ResourceType getResourceByString(String name) {
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

//    private void addResourcesToTileForTileString(Tile tile, String tileString) throws MalformedMapFileException {
//        // ([A-Z]*):([0-9]*)
//        // each match: group 1 is resource string, group 2 is amount
//        Pattern locationPattern = Pattern.compile("([A-Z]*):([0-9]*)");
//        Matcher m = locationPattern.matcher(tileString);
//        while (m.find()) {
//            String resourceString = m.group(1);
//            String amountString = m.group(2);
//
//            try {
//                ResourceType resourceType = getResourceByString(resourceString);
//                if (resourceType == null) {
//                    throw new MalformedMapFileException("Could not parse resource on line: " + tileString);
//                }
//                Integer amount = Integer.parseInt(amountString);
//                tile.addResource(resourceType, amount);
//            } catch (NumberFormatException e) {
//                throw new MalformedMapFileException("Could not parse amount on line: " + tileString);
//            }
//
//        }
//    }

    private Tile getTileForTileString(String tileString) throws MalformedMapFileException {
        RiverConfiguration tileRiverConfiguration = getRiverConfigurationForTile(tileString);
        Terrain tileTerrain = getTerrainForTileString(tileString);
        return new Tile(tileTerrain, tileRiverConfiguration);
    }
}
