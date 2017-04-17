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
import java.util.LinkedHashMap;
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
        String structureSections = serializeStructures();
        try {
            fw = new FileWriter(outputFile);
            fw.write(mapSection);
            fw.write(resourceSection);
            fw.write(structureSections);
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String serializeStructures(){
        String serializedStructures = "";



        return serializedStructures;
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


        serializedMap += "-----END MAP-----\n";
        return serializedMap;
    }

    public String serializeResources(){
        String serializedResources = "-----BEGIN RESOURCES-----\n";

        //Loop through all locations
        for (Location location : locations) {
            Tile thisTile = map.getTile(location);  //Get the tile at that location
            String locationCoords = location.getExportString() + " ";  /* To put the Coordinates on return strings */

            //Map TileCompartments to direction names
            HashMap<TileCompartment, String> tileCompartments = new HashMap<>();
            tileCompartments.put(thisTile.getTileCompartment(TileCompartmentDirection.getNorthNorthEast()),"NNE");
            tileCompartments.put(thisTile.getTileCompartment(TileCompartmentDirection.getSouthSouthEast()),"SSE");
            tileCompartments.put(thisTile.getTileCompartment(TileCompartmentDirection.getSouthSouthWest()),"SSW");
            tileCompartments.put(thisTile.getTileCompartment(TileCompartmentDirection.getNorthNorthWest()),"NNW");
            tileCompartments.put(thisTile.getTileCompartment(TileCompartmentDirection.getSouthWest()),"SW");
            tileCompartments.put(thisTile.getTileCompartment(TileCompartmentDirection.getSouthEast()),"SE");
            tileCompartments.put(thisTile.getTileCompartment(TileCompartmentDirection.getNorthWest()),"NW");
            tileCompartments.put(thisTile.getTileCompartment(TileCompartmentDirection.getNorthEast()),"NE");
            tileCompartments.put(thisTile.getTileCompartment(TileCompartmentDirection.getWest()),"W");
            tileCompartments.put(thisTile.getTileCompartment(TileCompartmentDirection.getEast()),"E");
            tileCompartments.put(thisTile.getTileCompartment(TileCompartmentDirection.getSouth()),"S");
            tileCompartments.replace(thisTile.getTileCompartment(TileCompartmentDirection.getNorth()),"N");


            //Create matching arrays of resource names to ResourceTypes. Will be used in a sec.
            String[] resourceTypeNames = {"BOARDS", "CLAY", "GOLD", "COINS", "FUEL", "GOOSE", "IRON", "PAPER", "STOCKBOND", "STONE", "TRUNKS"};
            ResourceType[] actualResourceTypes = {
                    ResourceType.BOARDS, ResourceType.CLAY, ResourceType.GOLD, ResourceType.COINS,
                    ResourceType.FUEL, ResourceType.GOOSE, ResourceType.IRON, ResourceType.PAPER,
                    ResourceType.STOCKBOND, ResourceType.STONE, ResourceType.TRUNKS
            };

            //Maps each direction name to a HashMap of String resource names to counts
            LinkedHashMap<String, HashMap<String,Integer>> counts = new LinkedHashMap<>();

            //Loop through all the TileCompartments on the tile.
            for (TileCompartment tc : tileCompartments.keySet()) {

                //Loop through all the resource types
                for (int k = 0; k < resourceTypeNames.length; k++) {
                    //Get the count of a specific resource at the tilecompartment given by the direction
                    int count = tc.getResourceCount(actualResourceTypes[k]);

                    //If the count for that resource is not zero
                    if (count > 0) {
                        HashMap<String,Integer> tuples = counts.get(tileCompartments.get(tc));    //Maps resource name to count
                        if(null == tuples) tuples = new HashMap<>();
                        tuples.put(resourceTypeNames[k],count);
                        counts.put(tileCompartments.get(tc),tuples);
                    }
                }
            }

            for(String directionName : counts.keySet()){
                HashMap<String,Integer> resourceCounts = counts.get(directionName);
                for(String resourceName : resourceCounts.keySet()){
                    serializedResources += locationCoords + " " + directionName + " " + resourceName + " " + resourceCounts.get(resourceName) + "\n";
                }
            }


        }

        serializedResources += "-----END RESOURCES-----\n";
        return serializedResources;

    }
}
