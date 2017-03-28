package model;

import model.tile.InvalidLocationException;
import model.tile.Location;
import model.tile.Terrain;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//For Importing Map


public class FileImporter {
    public Map readFile(String fileName) throws IOException {
        Map map = new Map();
        //Get file from resources folder\
        File directory = new File("./");
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(directory.getAbsolutePath().replace(".","")+fileName);
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                parseRiver(line);
                map.setTile(parseTile(line), parseTerrain(line));
            }
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidLocationException e) {
            e.printStackTrace();
            System.out.print("File Not Found");
        }
        return map;
    }

    public Location parseTile(String line) throws InvalidLocationException {
        Matcher matcher = findMatch(line,"[\"][(][\"].*[\"][)][\"]");
        String intString = null;
        while (matcher.find()){intString = matcher.group().replace("\"","").replace("( ","").replace(" )","");}
        String [] locationString = intString.split(" ");
        int x = Integer.parseInt(locationString[0]);
        int y = Integer.parseInt(locationString[1]);
        int z= Integer.parseInt(locationString[2]);
        Location location = new Location(x,y,z);
        return location;
    }
    public Terrain parseTerrain(String line) {
        Matcher matcher = findMatch(line, "[\"][a-z || A-Z][\"]");
        String intString = null;
        while (matcher.find()) {intString = line.substring(matcher.start()).replace("\"","");}
        return getTerrain(intString);
    }

    private Terrain getTerrain(String terrain) {
        terrain = terrain.replace(" ","");
        if(terrain.equalsIgnoreCase("wood"))
            return Terrain.WOODS;
        else if(terrain.equalsIgnoreCase("pasture"))
            return Terrain.PASTURE;
        else if(terrain.equalsIgnoreCase("desert"))
            return Terrain.DESERT;
        else if(terrain.equalsIgnoreCase("mountain"))
            return Terrain.MOUNTAIN;
        else if(terrain.equalsIgnoreCase("sea"))
            return Terrain.SEA;
        else if(terrain.equalsIgnoreCase("rock"))
            return Terrain.ROCK;
        else
            return Terrain.WOODS; ///default
    }

    public void parseRiver(String group) {
    }
    private Matcher findMatch(String line, String pattern){
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(line);
        return matcher;
    }
    private boolean isValidLine(Matcher matcher){
        int count = 0;
       while (matcher.find()){
           count++;
       }
        return count==3;
    }
}
