package mapMaker.model;

import mapMaker.model.tile.InvalidLocationException;
import mapMaker.model.tile.Location;
import mapMaker.model.tile.Terrain;
import mapMaker.model.tile.Tile;
import mapMaker.model.tile.riverConfiguration.RiverConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//For Importing Map


public class FileImporter {
    public Map readFile(File file) throws IOException {
        Map map = new Map();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(isValidLine(line)) {
                    if (hasRiver(line)) {
                        map.placeTileWithoutCheck(parseTile(line), new Tile(parseTerrain(line),parseRiver(line)));
                    }
                    else {
                        map.placeTileWithoutCheck(parseTile(line), new Tile(parseTerrain(line), RiverConfiguration.getNoRivers()));
                    }
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
        Matcher matcher = findMatch(line, "[A-Z][A-Z||a-z]*[A-Z]");
        String intString = null;
        while (matcher.find()) {intString = matcher.group();}
        return getTerrain(intString);
    }

    private Terrain getTerrain(String terrain) {
        terrain = terrain.replace(" ","");
        if(terrain.equalsIgnoreCase("WOODS"))
            return Terrain.WOODS;
        else if(terrain.equalsIgnoreCase("PASTURE"))
            return Terrain.PASTURE;
        else if(terrain.equalsIgnoreCase("DESERT"))
            return Terrain.DESERT;
        else if(terrain.equalsIgnoreCase("MOUNTAIN"))
            return Terrain.MOUNTAIN;
        else if(terrain.equalsIgnoreCase("SEA"))
            return Terrain.SEA;
        else if(terrain.equalsIgnoreCase("ROCK"))
            return Terrain.ROCK;
        else
            return Terrain.WOODS; ///default
    }

    public RiverConfiguration parseRiver(String group) {
        Matcher matcher = findMatch(group,"[( ]?([0-9][ ])?([0-9][ ])?([0-9][ )])");
        String intString = null;
        while (matcher.find()){intString = matcher.group();}
        String [] locationString = intString.split(" ");
        if(locationString.length==4)
        {
            int x = Integer.parseInt(locationString[1]);
            int y = Integer.parseInt(locationString[2]);
            int z = Integer.parseInt(locationString[3]);
            return new RiverConfiguration(x,y,z);
        }
        else if(locationString.length==3)
        {
            int x = Integer.parseInt(locationString[1]);
            int y = Integer.parseInt(locationString[2]);
            return new RiverConfiguration(x,y);
        }
        else if(locationString.length==2)
        {
            int x = Integer.parseInt(locationString[1]);
            return new RiverConfiguration(x);
        }
        else
        {
            System.out.print("Called Null");
            return RiverConfiguration.getNoRivers();
        }

        }
    private Matcher findMatch(String line, String pattern){
        Pattern p = Pattern.compile(pattern);
        Matcher matcher = p.matcher(line);
        return matcher;
    }

    private boolean isValidLine(String line){
        StringTokenizer st = new StringTokenizer(line," ");
        return st.countTokens()==11 || st.countTokens()==6 || st.countTokens()==9 |st.countTokens()==10;
    }
    private boolean hasRiver(String line){
        StringTokenizer st = new StringTokenizer(line," ");
        return st.countTokens()==11 || st.countTokens()==9 |st.countTokens()==10;
    }
}
