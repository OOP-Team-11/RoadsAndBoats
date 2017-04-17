package game.model.gameImportExport.importer;

import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileCompartmentLocation;
import game.model.managers.StructureManager;
import game.model.managers.WallManager;
import game.model.structures.resourceProducer.primaryProducer.Mine;
import game.utilities.exceptions.MalformedMapFileException;
import game.view.render.WallInfo;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;

import static game.model.gameImportExport.importer.ParseUtilities.getMatcherForPatternInString;

public class WallImporter {


    static void importWallsFromFile(WallManager wallManager , BufferedReader bufferedReader) throws MalformedMapFileException, IOException {
        if (!bufferedReader.readLine().equals("-----BEGIN WALL-----"))  throw new MalformedMapFileException("-----BEGIN WALL----- not found");

        boolean foundEOF = false;

        HashMap<Location,ArrayList<WallInfo>> information = new HashMap<>();

        for(String line; (line = bufferedReader.readLine()) != null; ) {
            if (line.equals("-----END WALL-----")) {
                foundEOF = true;
                break;
            }
            Location location = getLocation(line);
            int type = 0;
            int compartment = 0;


            Matcher m2 = getMatcherForPatternInString(line, "PLAYERID=([0-9]{1,2}) COMPARTMENT=([0-9])");
            if (m2.find()) {
                String goldCount = m2.group(1);
                String ironCount = m2.group(2);
                try {
                    type = Integer.parseInt(goldCount);
                    compartment = Integer.parseInt(ironCount);
                    if(information.get(location) == null){
                        ArrayList<WallInfo> info = new ArrayList<>();
                        information.put(location,info);
                        info.add(new WallInfo(type,compartment));
                        wallManager.addNewWall(location,info);
                    } else {
                        information.get(location).add(new WallInfo(type,compartment));
                        wallManager.addNewWall(location, information.get(location));
                    }


                } catch (NumberFormatException e) {
                    throw new MalformedMapFileException("Could not parse current resource amounts for mine: " + line);
                }
            }


        }

        if (!foundEOF) throw new MalformedMapFileException("-----END WALL----- not found");

        /*
        Matcher m2 = getMatcherForPatternInString(mineString, "CURRENT=\\[GOLD=([0-9]{1,2}) IRON=([0-9]{1,2})\\]");
        if (m2.find()) {
            String goldCount = m2.group(1);
            String ironCount = m2.group(2);
            try {
                currentGoldCount = Integer.parseInt(goldCount);
                currentIronCount = Integer.parseInt(ironCount);
            } catch (NumberFormatException e) {
                throw new MalformedMapFileException("Could not parse current resource amounts for mine: " + mineString);
            }
        }
        // PLAYERID=([0-9]{1,2})
        if (!foundEOF) throw new MalformedMapFileException("-----END WALL----- not found");
        */
    }



    private static Location getLocation(String wallString) throws MalformedMapFileException {
        Matcher m = getMatcherForPatternInString(wallString, "(\\([^)]*\\))");
        if (m.find()) {
            String locationString = m.group(1);

            try {
                locationString = locationString.substring(2, locationString.length() - 2);
            } catch (IndexOutOfBoundsException e) {
                throw new MalformedMapFileException("Could not parse location for wall: " + wallString);
            }
            String[] coordinatesString = locationString.split(" ");
            if (coordinatesString.length != 3)
                throw new MalformedMapFileException("Malformed location in wall string: " + wallString);

            try {
                int x = Integer.parseInt(coordinatesString[0]);
                int y = Integer.parseInt(coordinatesString[1]);
                int z = Integer.parseInt(coordinatesString[2]);

                try {
                    return new Location(x, y, z);
                } catch (IllegalArgumentException e) {
                    throw new MalformedMapFileException("Invalid location for wall: " + wallString);
                }

            } catch (NumberFormatException e) {
                throw new MalformedMapFileException("Malformed wall string, could not parse location ints: " + wallString);
            }
        }

        throw new MalformedMapFileException("Could not find location on line: " + wallString);
    }

}
