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

/**
 * Created by anip on 26/03/17.
 */
public class FileImporter {
    private Map map;
    public String readFile(Map map, String fileName) throws IOException {
        StringBuilder result = new StringBuilder("");
        this.map = map;
        //Get file from resources folder
        ClassLoader classLoader = getClass().getClassLoader();
        File file = new File(classLoader.getResource(fileName).getFile());

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String [] locationString = parseTile(line);

                parseRiver(line);
                int x = Integer.parseInt(locationString[0]);
                int y = Integer.parseInt(locationString[1]);
                int z= Integer.parseInt(locationString[2]);
                Location location = new Location(x,y,z);
                map.setTile(location, parseTerrain(line));
            }

            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (InvalidLocationException e) {
            e.printStackTrace();
        }

        return result.toString();

    }

    public String[] parseTile(String line) throws InvalidLocationException {
        Matcher matcher = findMatch(line,"[\"][(][\"].*[\"][)][\"]");
        String intString = null;
        while (matcher.find()){

            intString = matcher.group().replace("\"","").replace("( ","").replace(" )","");
        }
        String [] locationString = intString.split(" ");

        return locationString;
    }
    public Terrain parseTerrain(String line) {
        Matcher matcher = findMatch(line, "[\"][A-Z || a-z][\"]");
        String intString = null;
        while (matcher.find()) {
            intString = matcher.group();
        }
        System.out.println(intString);
        Terrain terrain =null;
        switch (intString) {
            case "Wood":
                terrain = Terrain.WOODS;
                break;
            case "Pasture":
                terrain =  Terrain.PASTURE;
                break;
            default:
                terrain =  Terrain.MOUNTAIN;
        }
        return terrain;

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
