package model;

import model.tile.InvalidLocationException;
import model.tile.Location;
import model.tile.Terrain;
import model.tile.Tile;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Scanner;
import java.util.StringTokenizer;
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
                if(isValidLine(line)){
                    if(hasRiver(line)) {
                        parseRiver(line);
                    }
                    map.placeTile(parseTile(line), new Tile(parseTerrain(line)));
                }
                else {
                    System.err.println("Not the correct Tile Format");
                    break;
                }
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
        Matcher matcher = findMatch(line,"[( ]-?[0-9][ ]-?[0-9][ ]-?[0-9][ )]");
        String intString = null;
        while (matcher.find()){
            intString = matcher.group(); break;}
        String [] locationString = intString.split(" ");
            int x = Integer.parseInt(locationString[1]);
            int y = Integer.parseInt(locationString[2]);
            int z = Integer.parseInt(locationString[3]);
        Location location = new Location(x,y,z);
        return location;
    }
    public Terrain parseTerrain(String line) {
        Matcher matcher = findMatch(line, "[A-Z][A-Z||a-z]*[a-z]");
        String intString = null;
        while (matcher.find()) {intString = matcher.group();}
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
        Matcher matcher = findMatch(group,"[( ]-?[0-9][ ]-?[0-9][ ]-?[0-9][ )]");
        String intString = null;
        while (matcher.find()){intString = matcher.group();}
        String [] locationString = intString.split(" ");
        int x = Integer.parseInt(locationString[1]);
        int y = Integer.parseInt(locationString[2]);
        int z = Integer.parseInt(locationString[3]);
    }
    private Matcher findMatch(String line, String pattern){
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(line);
        return matcher;
    }
    private boolean isValidLine(String line){
        StringTokenizer st = new StringTokenizer(line," ");
        return st.countTokens()==11 || st.countTokens()==6;
    }
    private boolean hasRiver(String line){
        StringTokenizer st = new StringTokenizer(line," ");
        return st.countTokens()==11;
    }
}
