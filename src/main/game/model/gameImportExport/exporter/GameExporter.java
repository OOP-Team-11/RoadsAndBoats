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

        //Loop through all locations
        for (Location location : locations) {
            Tile thisTile = map.getTile(location);  //Get the tile at that location
            String locationCoords = location.getExportString() + " ";  /* To put the Coordinates on return strings */

            //Map direction names to the tile's actual TileCompartments
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

            //Get a key set for the map to allow iterating through it
            Set<String> dirs = tileCompartments.keySet();
            String[] sDirs = new String[dirs.size()];
            sDirs = dirs.toArray(sDirs);

            //Create matching arrays of resource names to ResourceTypes. Will be used in a sec.
            String[] resourceTypeNames = {"BOARDS", "CLAY", "GOLD", "COINS", "FUEL", "GOOSE", "IRON", "PAPER", "STOCKBOND", "STONE", "TRUNKS"};
            ResourceType[] actualResourceTypes = {
                    ResourceType.BOARDS, ResourceType.CLAY, ResourceType.GOLD, ResourceType.COINS,
                    ResourceType.FUEL, ResourceType.GOOSE, ResourceType.IRON, ResourceType.PAPER,
                    ResourceType.STOCKBOND, ResourceType.STONE, ResourceType.TRUNKS
            };

            //Loop through all the directions on the tile.
            for (String directionName : sDirs) {
                //Loop through all the resource types
                for (int k = 0; k < resourceTypeNames.length; k++) {
                    //Get the count of a specific resource at the tilecompartment given by the direction
                    int count = tileCompartments.get(directionName).getResourceCount(actualResourceTypes[k]);
                    //If the count for that resource is not zero
                    if (count > 0) {
                        serializedResources += locationCoords + " " + directionName + " " + resourceTypeNames[k] + " " + count + "\n";
                    }
                }
            }

        }

        serializedResources += "-----END RESOURCES-----";
        return serializedResources;

    }
}
