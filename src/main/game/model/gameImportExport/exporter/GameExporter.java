package game.model.gameImportExport.exporter;

import game.model.direction.*;
import game.model.managers.StructureManager;
import game.model.map.RBMap;
import game.model.resources.ResourceType;
import game.model.structures.Structure;
import game.model.structures.StructureType;
import game.model.structures.resourceProducer.ResourceHolder;
import game.model.structures.resourceProducer.primaryProducer.Mine;
import game.model.structures.resourceProducer.primaryProducer.OilRig;
import game.model.tile.Tile;
import game.model.tile.TileCompartment;
import game.model.tinyGame.Game;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class GameExporter {
    private Game game;
    private RBMap map;
    private Location[] locations;

    private HashMap<Angle,String> angleLetterMap;

    public GameExporter(Game game) {
        this.game = game;
        map = game.getMap();
        Set<Location> locSet = map.getAllLocations();
        locations = new Location[locSet.size()];
        locations = locSet.toArray(locations);
        angleLetterMap = makeAngleLetterMap();
    }

    public void exportGameToPath(String filePath) {
        File outputFile = new File(filePath);
        FileWriter fw;

        String mapSection = serializeMap() + "\n";
        String resourceSection = serializeResources() + "\n";
        String structureSections = serializeStructures(game.getStructureSet());
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

    private String serializeStructures(Map<TileCompartmentLocation, Structure> structures){
        String serializedStructures = "";
        serializedStructures += serializeMines(structures) + "\n";
        serializedStructures += serializeOilRigs(structures) + "\n";
        serializedStructures += serializeTheRest(structures) + "\n";

        return serializedStructures;
    }

    private String serializeOilRigs(Map<TileCompartmentLocation,Structure> allStructures){
        String oilRigString = "-----BEGIN " + StructureType.OIL_RIG.name() + "-----\n";

        TileCompartmentLocation[] tileCompartmentLocations = new TileCompartmentLocation[allStructures.size()];
        allStructures.keySet().toArray(tileCompartmentLocations);

        for(TileCompartmentLocation tileCompartmentLocation : tileCompartmentLocations){
            Structure currentStructure = allStructures.get(tileCompartmentLocation);
            if(currentStructure.getType().equals(StructureType.OIL_RIG)){
                ResourceHolder thisRig = ((OilRig) currentStructure);
                oilRigString += tileCompartmentLocation.getLocation().getExportString() + " ";
                oilRigString += angleLetterMap.get(tileCompartmentLocation.getTileCompartmentDirection().getAngle()) + " ";
                oilRigString += "[" ;
                HashMap<ResourceType,Integer> resourceCounts = thisRig.getResourceCounts();
                for(ResourceType type : resourceCounts.keySet()){
                    oilRigString += type.getName() + "=" + resourceCounts.get(type) + " ";
                }
                oilRigString += "]" ;

                oilRigString += "\n";
            }
        }

        oilRigString += "-----END " + StructureType.OIL_RIG.name() + "-----\n";
        return oilRigString;
    }

    private String serializeMines(Map<TileCompartmentLocation,Structure> allStructures){
        String minesList = "-----BEGIN MINES-----\n";

        TileCompartmentLocation[] tileCompartmentLocations = new TileCompartmentLocation[allStructures.size()];
        allStructures.keySet().toArray(tileCompartmentLocations);

        for(TileCompartmentLocation tileCompartmentLocation : tileCompartmentLocations){
            Structure currentStructure = allStructures.get(tileCompartmentLocation);
            if(currentStructure.getType().equals(StructureType.MINE)){
                minesList += tileCompartmentLocation.getLocation().getExportString() + " ";
                minesList += angleLetterMap.get(tileCompartmentLocation.getTileCompartmentDirection().getAngle()) + " ";
                Mine thisMine = ((Mine)currentStructure);
                int maxGoldCount = thisMine.getMaxGoldCount();
                int maxIronCount = thisMine.getMaxIronCount();
                int curGoldCount = thisMine.getCurrentGoldCount();
                int curIronCount = thisMine.getCurrentIronCount();
                minesList += "MAX=[GOLD=" + maxGoldCount + " IRON=" + maxIronCount + "] CURRENT=[GOLD=" + curGoldCount + " IRON=" + curIronCount + "]";
                minesList += "\n";
            }
        }

        minesList += "-----END MINES-----\n";
        return minesList;
    }

    private String serializeTheRest(Map<TileCompartmentLocation,Structure> allStructures){
        String wholeList = "";
        Collection<TileCompartmentLocation> structureLocations = allStructures.keySet();

        HashMap<StructureType, Boolean> simpleStructuresAndHasLimit = new HashMap<>();
        simpleStructuresAndHasLimit.put(StructureType.CLAYPIT, false);
        simpleStructuresAndHasLimit.put(StructureType.STONE_QUARRY, false);
        simpleStructuresAndHasLimit.put(StructureType.WOODCUTTER, false);
        simpleStructuresAndHasLimit.put(StructureType.MINT, false);
        simpleStructuresAndHasLimit.put(StructureType.PAPERMILL, false);
        simpleStructuresAndHasLimit.put(StructureType.COAL_BURNER, true);
        simpleStructuresAndHasLimit.put(StructureType.SAWMILL, true);
        simpleStructuresAndHasLimit.put(StructureType.STONE_FACTORY, true);
        simpleStructuresAndHasLimit.put(StructureType.STOCK_MARKET, true);

        /* Iterate through all the types */
        for(StructureType type : simpleStructuresAndHasLimit.keySet()){
            String subsection = "-----BEGIN " + type.name() + "-----\n";

            for(TileCompartmentLocation thisLocation : structureLocations){
                Structure thisStructure = allStructures.get(thisLocation);
                if(thisStructure.getType().toString().equals(type.toString())){
                    subsection += thisLocation.getLocation().getExportString() + " ";
                    subsection += angleLetterMap.get(thisLocation.getTileCompartmentDirection().getAngle()) + " ";
                    //If the StructureType has a limit
                    if(simpleStructuresAndHasLimit.get(type)){
                        subsection += thisStructure.getExportString();
                    }
                    subsection += "\n";
                }
            }

            subsection += "-----END " + type.name() + "-----\n";
            wholeList += subsection + "\n";
        }



        return wholeList;
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


    private HashMap<Angle,String> makeAngleLetterMap(){
        HashMap<Angle,String> angleMap = new HashMap<>();
        angleMap.put(CompassAngles.EAST.getAngle(),"E");
        angleMap.put(CompassAngles.NORTHEAST.getAngle(),"NE");
        angleMap.put(CompassAngles.NORTH_NORTHEAST.getAngle(),"NNE");
        angleMap.put(CompassAngles.NORTH.getAngle(),"N");
        angleMap.put(CompassAngles.NORTH_NORTHWEST.getAngle(),"NNW");
        angleMap.put(CompassAngles.NORTHWEST.getAngle(),"NW");
        angleMap.put(CompassAngles.WEST.getAngle(),"W");
        angleMap.put(CompassAngles.SOUTHWEST.getAngle(),"SW");
        angleMap.put(CompassAngles.SOUTH_SOUTHWEST.getAngle(),"SSW");
        angleMap.put(CompassAngles.SOUTH.getAngle(),"S");
        angleMap.put(CompassAngles.SOUTH_SOUTHEAST.getAngle(),"SSE");
        angleMap.put(CompassAngles.SOUTHEAST.getAngle(),"SE");
        return angleMap;
    }
}
