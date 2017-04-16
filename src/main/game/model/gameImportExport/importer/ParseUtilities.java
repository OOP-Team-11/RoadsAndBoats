package game.model.gameImportExport.importer;

import game.model.direction.TileCompartmentDirection;
import game.model.resources.ResourceType;

import static game.model.resources.ResourceType.*;
import static game.model.resources.ResourceType.STOCKBOND;

public class ParseUtilities {

    public static ResourceType getResourceTypeByString(String name) {
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

    public static TileCompartmentDirection getTileCompartmentDirectionForTCDString(String tcdString) {
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
}
