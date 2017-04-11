package mapMaker.model;

import mapMaker.model.tile.mmInvalidLocationException;
import mapMaker.model.tile.mmLocation;
import mapMaker.model.tile.mmTerrain;
import mapMaker.model.tile.mmTile;
import mapMaker.model.tile.riverConfiguration.mmRiverConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//For Importing mmMap


public class mmFileImporter {
    public mmMap readFile(File file) throws IOException {
        mmMap mmMap = new mmMap();
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if(isValidLine(line)) {
                    if (hasRiver(line)) {
                        mmMap.placeTileWithoutCheck(parseTile(line), new mmTile(parseTerrain(line),parseRiver(line)));
                    }
                    else {
                        mmMap.placeTileWithoutCheck(parseTile(line), new mmTile(parseTerrain(line), mmRiverConfiguration.getNoRivers()));
                    }
                }
                else {
                    System.err.println("Not the correct mmTile Format");
                    break;
                }
            }
            scanner.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (mmInvalidLocationException e) {
            e.printStackTrace();
            System.out.print("File Not Found");
        }
        return mmMap;
    }

    public mmLocation parseTile(String line) throws mmInvalidLocationException {
        Matcher matcher = findMatch(line,"[( ]-?[0-9][ ]-?[0-9][ ]-?[0-9][ )]");
        String intString = null;
        while (matcher.find()){
            intString = matcher.group(); break;}
        String [] locationString = intString.split(" ");
            int x = Integer.parseInt(locationString[1]);
            int y = Integer.parseInt(locationString[2]);
            int z = Integer.parseInt(locationString[3]);
        mmLocation mmLocation = new mmLocation(x,y,z);
        return mmLocation;
    }

    public mmTerrain parseTerrain(String line) {
        Matcher matcher = findMatch(line, "[A-Z][A-Z||a-z]*[A-Z]");
        String intString = null;
        while (matcher.find()) {intString = matcher.group();}
        return getTerrain(intString);
    }

    private mmTerrain getTerrain(String terrain) {
        terrain = terrain.replace(" ","");
        if(terrain.equalsIgnoreCase("WOODS"))
            return mmTerrain.WOODS;
        else if(terrain.equalsIgnoreCase("PASTURE"))
            return mmTerrain.PASTURE;
        else if(terrain.equalsIgnoreCase("DESERT"))
            return mmTerrain.DESERT;
        else if(terrain.equalsIgnoreCase("MOUNTAIN"))
            return mmTerrain.MOUNTAIN;
        else if(terrain.equalsIgnoreCase("SEA"))
            return mmTerrain.SEA;
        else if(terrain.equalsIgnoreCase("ROCK"))
            return mmTerrain.ROCK;
        else
            return mmTerrain.WOODS; ///default
    }

    public mmRiverConfiguration parseRiver(String group) {
        Matcher matcher = findMatch(group,"[( ]?([0-9][ ])?([0-9][ ])?([0-9][ )])");
        String intString = null;
        while (matcher.find()){intString = matcher.group();}
        String [] locationString = intString.split(" ");
        if(locationString.length==4)
        {
            int x = Integer.parseInt(locationString[1]);
            int y = Integer.parseInt(locationString[2]);
            int z = Integer.parseInt(locationString[3]);
            return new mmRiverConfiguration(x,y,z);
        }
        else if(locationString.length==3)
        {
            int x = Integer.parseInt(locationString[1]);
            int y = Integer.parseInt(locationString[2]);
            return new mmRiverConfiguration(x,y);
        }
        else if(locationString.length==2)
        {
            int x = Integer.parseInt(locationString[1]);
            return new mmRiverConfiguration(x);
        }
        else
        {
            System.out.print("Called Null");
            return mmRiverConfiguration.getNoRivers();
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
