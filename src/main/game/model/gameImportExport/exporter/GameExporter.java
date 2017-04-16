package game.model.gameImportExport.exporter;

import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.map.RBMap;
import game.model.resources.ResourceType;
import game.model.tile.Tile;
import game.model.tile.TileCompartment;
import game.model.tinyGame.Game;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class GameExporter {

    private File outputFile;
    private FileWriter fw;

    private RBMap map;
    private Location[] locations;

    public GameExporter(Game game) {
        map = game.getMap();
        Set<Location> locSet = map.getAllLocations();
        locations = new Location[locSet.size()];
        locations = locSet.toArray(locations);
    }

    public void exportGameToPath(String filePath) {
        outputFile = new File(filePath);

        String mapSection = serializeMap();
        String resourceSection = serializeResources();
        System.out.println(resourceSection);
        try {
            fw = new FileWriter(outputFile);
            fw.write(mapSection);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String serializeMap(){
        String serializedMap = "-----BEGIN MAP-----\n";


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
        String serializedResources = "-----BEGIN RESOURCES-----\n";


        for (Location location : locations) {
            Tile thisTile = map.getTile(location);
            boolean hasResources = false;   //Will be used to determine whether to actually add a line or not.
            serializedResources += location.getExportString() + " ";  /* Coordinates */

            HashMap<String, TileCompartment> tileCompartments = new HashMap<>();
            tileCompartments.put("N",thisTile.getTileCompartment(TileCompartmentDirection.getNorth()));
            tileCompartments.put("NNE",thisTile.getTileCompartment(TileCompartmentDirection.getNorthNorthEast()));
            tileCompartments.put("NE",thisTile.getTileCompartment(TileCompartmentDirection.getNorthEast()));
            tileCompartments.put("E",thisTile.getTileCompartment(TileCompartmentDirection.getEast()));
            tileCompartments.put("SE",thisTile.getTileCompartment(TileCompartmentDirection.getSouthEast()));
            tileCompartments.put("SSE",thisTile.getTileCompartment(TileCompartmentDirection.getSouthSouthEast()));
            tileCompartments.put("S",thisTile.getTileCompartment(TileCompartmentDirection.getSouth()));
            tileCompartments.put("SSW",thisTile.getTileCompartment(TileCompartmentDirection.getSouthSouthWest()));
            tileCompartments.put("SW",thisTile.getTileCompartment(TileCompartmentDirection.getSouthWest()));
            tileCompartments.put("W",thisTile.getTileCompartment(TileCompartmentDirection.getWest()));
            tileCompartments.put("NW",thisTile.getTileCompartment(TileCompartmentDirection.getNorthWest()));
            tileCompartments.put("NNW",thisTile.getTileCompartment(TileCompartmentDirection.getNorthNorthWest()));

            Set<String> dirs = tileCompartments.keySet();
            String[] sDirs = new String[dirs.size()];
            sDirs = dirs.toArray(sDirs);

            for(int j = 0; j < sDirs.length; j++){
                String directionName = sDirs[j];    //For readability

                HashMap<String,Integer> resourceCount = new HashMap<>();
                resourceCount.put("BOARDS",tileCompartments.get(sDirs[j]).getResourceCount(ResourceType.BOARDS));
                resourceCount.put("CLAY",tileCompartments.get(sDirs[j]).getResourceCount(ResourceType.CLAY));
                resourceCount.put("GOLD",tileCompartments.get(sDirs[j]).getResourceCount(ResourceType.GOLD));
                resourceCount.put("COINS",tileCompartments.get(sDirs[j]).getResourceCount(ResourceType.COINS));
                resourceCount.put("FUEL",tileCompartments.get(sDirs[j]).getResourceCount(ResourceType.FUEL));
                resourceCount.put("GOOSE",tileCompartments.get(sDirs[j]).getResourceCount(ResourceType.GOOSE));
                resourceCount.put("IRON",tileCompartments.get(sDirs[j]).getResourceCount(ResourceType.IRON));
                resourceCount.put("PAPER",tileCompartments.get(sDirs[j]).getResourceCount(ResourceType.PAPER));
                resourceCount.put("STOCKBOND",tileCompartments.get(sDirs[j]).getResourceCount(ResourceType.STOCKBOND));
                resourceCount.put("STONE",tileCompartments.get(sDirs[j]).getResourceCount(ResourceType.STONE));
                resourceCount.put("TRUNKS",tileCompartments.get(sDirs[j]).getResourceCount(ResourceType.TRUNKS));


            }


            serializedResources += "\n";
        }

        serializedResources += "-----END RESOURCES-----";
        return serializedResources;

    }
}
