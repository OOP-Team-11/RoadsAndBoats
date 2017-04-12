package game;

import game.model.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Arrays;
import java.util.List;
import java.util.Vector;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

import static game.model.Terrain.*;

public class MapImporter {

    private RBMap map;
    public MapImporter(RBMap map) {
        this.map = map;
    }

    public void importMapFromFile(BufferedReader bufferedReader) throws MalformedMapFileException, IOException {
        if (!bufferedReader.readLine().equals("BEGIN MAP"))  throw new MalformedMapFileException("BEGIN MAP not found");

        boolean foundEOF = false;
        for(String line; (line = bufferedReader.readLine()) != null; ) {
            if (line.equals("END MAP")) {
                foundEOF = true;
                break;
            }

            Tile tile = getTileForTileString(line);
            Location location = getLocationForTileString(line);
            this.map.placeTile(location, tile);
        }

        if (!foundEOF) throw new MalformedMapFileException("END MAP not found");

        System.out.println(this.map);
    }

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
            } catch (NumberFormatException e) {
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

    private Tile getTileForTileString(String tileString) throws MalformedMapFileException {
        RiverConfiguration tileRiverConfiguration = getRiverConfigurationForTile(tileString);
        Terrain tileTerrain = getTerrainForTileString(tileString);
        return new Tile(tileTerrain, tileRiverConfiguration);
    }
}
