package game.model.gameImportExport;

import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.map.RBMap;
import game.model.resources.ResourceType;
import game.model.tile.Tile;
import game.model.tile.TileCompartment;
import game.model.tinyGame.Game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class GameExporter {

    private Game game;
    Location[] locations;

    public GameExporter(Game game) {
        this.game = game;
    }

    public void exportGameToPath(String filePath) {
        String mapSection = serializeMap();
        System.out.println(mapSection);
    }

    private String serializeMap(){
        String serializedMap = "-----BEGIN MAP-----\n";
        RBMap map = game.getMap();
        Set<Location> locSet = map.getAllLocations();
        Location[] locations = new Location[locSet.size()];
        locations = locSet.toArray(locations);

        for (Location location : locations) {
            Tile thisTile = map.getTile(location);
            serializedMap += location.getExportString() + " ";  /* Coordinates */
            serializedMap += thisTile.getTerrain() + " ";       /* Terrain */
            serializedMap += thisTile.getRiverConfiguration().getExportString(); /* RiverConfig */

            serializedMap += "\n";
        }


        serializedMap += "-----END MAP-----";
        return serializedMap;
    }


    public String serializeResources(){

        //In loop:
//        HashMap<String, TileCompartment> tileCompartments = new HashMap<>();
//        tileCompartments.put("N",thisTile.getTileCompartment(TileCompartmentDirection.getNorth()));
//        tileCompartments.put("NNE",thisTile.getTileCompartment(TileCompartmentDirection.getNorthNorthEast()));
//        tileCompartments.put("NE",thisTile.getTileCompartment(TileCompartmentDirection.getNorthEast()));
//        tileCompartments.put("E",thisTile.getTileCompartment(TileCompartmentDirection.getEast()));
//        tileCompartments.put("SE",thisTile.getTileCompartment(TileCompartmentDirection.getSouthEast()));
//        tileCompartments.put("SSE",thisTile.getTileCompartment(TileCompartmentDirection.getSouthSouthEast()));
//        tileCompartments.put("S",thisTile.getTileCompartment(TileCompartmentDirection.getSouth()));
//        tileCompartments.put("SSW",thisTile.getTileCompartment(TileCompartmentDirection.getSouthSouthWest()));
//        tileCompartments.put("SW",thisTile.getTileCompartment(TileCompartmentDirection.getSouthWest()));
//        tileCompartments.put("W",thisTile.getTileCompartment(TileCompartmentDirection.getWest()));
//        tileCompartments.put("NW",thisTile.getTileCompartment(TileCompartmentDirection.getNorthWest()));
//        tileCompartments.put("NNW",thisTile.getTileCompartment(TileCompartmentDirection.getNorthNorthWest()));
//
//        Set<String> dirs = tileCompartments.keySet();
//        String[] sDirs = new String[dirs.size()];
//        sDirs = dirs.toArray(sDirs);
//        for(int j = 0; j < sDirs.length; j++){
//            HashMap<String,Integer> resourceCount = new HashMap<>();
//            //If the TileCompartment has resources
//            int boardCount = tileCompartments.get(sDirs[j]).getResourceCount(ResourceType.BOARDS);
//            int clayCount = tileCompartments.get(sDirs[j]).getResourceCount(ResourceType.CLAY);
//            int goldCount = tileCompartments.get(sDirs[j]).getResourceCount(ResourceType.GOLD);
//            int coinsCount = tileCompartments.get(sDirs[j]).getResourceCount(ResourceType.COINS);
//            int fuelCount = tileCompartments.get(sDirs[j]).getResourceCount(ResourceType.FUEL);
//            int gooseCount = tileCompartments.get(sDirs[j]).getResourceCount(ResourceType.GOOSE);
//            int ironCount = tileCompartments.get(sDirs[j]).getResourceCount(ResourceType.IRON);
//            int paperCount = tileCompartments.get(sDirs[j]).getResourceCount(ResourceType.PAPER);
//            int stockbondCount = tileCompartments.get(sDirs[j]).getResourceCount(ResourceType.STOCKBOND);
//            int stoneCount = tileCompartments.get(sDirs[j]).getResourceCount(ResourceType.STONE);
//            int trunksCount = tileCompartments.get(sDirs[j]).getResourceCount(ResourceType.TRUNKS);
//
//            if(boardCount > 0) serializedMap += "";
//        }
//
//        serializedMap += "\n";
        return null;
    }
}
