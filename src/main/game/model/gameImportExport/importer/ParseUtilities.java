package game.model.gameImportExport.importer;

import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileEdgeDirection;
import game.model.resources.ResourceType;
import game.model.tile.Terrain;
import game.model.transport.TransportType;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static game.model.resources.ResourceType.*;
import static game.model.resources.ResourceType.STOCKBOND;
import static game.model.tile.Terrain.*;
import static game.model.tile.Terrain.DESERT;
import static game.model.tile.Terrain.MOUNTAIN;

class ParseUtilities {

    static ResourceType getResourceTypeByString(String name) {
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

    static TileCompartmentDirection getTileCompartmentDirectionForTCDString(String tcdString) {
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

    static TileEdgeDirection getTileEdgeDirectionForString(String tedString) {
        switch (tedString) {
            case "N":
                return TileEdgeDirection.getNorth();
            case "NE":
                return TileEdgeDirection.getNorthEast();
            case "NW":
                return TileEdgeDirection.getNorthWest();
            case "S":
                return TileEdgeDirection.getSouth();
            case "SE":
                return TileEdgeDirection.getSouthEast();
            case "SW":
                return TileEdgeDirection.getSouthWest();
            default:
                return null;
        }
    }

    static Terrain getTerrainForString(String terrainString) {
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

    static Matcher getMatcherForPatternInString(String searchString, String pattern) {
        Pattern locationPattern = Pattern.compile(pattern);
        return locationPattern.matcher(searchString);
    }

    static TransportType getTransportTypeForString(String transportString) {
        switch (transportString) {
            case "DONKEY":
                return TransportType.DONKEY;
            case "RAFT":
                return TransportType.RAFT;
            case "ROWBOAT":
                return TransportType.ROWBOAT;
            case "STEAMSHIP":
                return TransportType.STEAMSHIP;
            case "TRUCK":
                return TransportType.TRUCK;
            case "WAGON":
                return TransportType.WAGON;
            default:
                return null;
        }
    }
}
