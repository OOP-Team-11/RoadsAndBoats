package mapMaker.filehandling;

import mapMaker.model.mmFileExporter;
import mapMaker.model.mmFileImporter;
import mapMaker.model.mmMap;
import mapMaker.model.tile.mmInvalidLocationException;
import mapMaker.model.tile.mmLocation;
import mapMaker.model.tile.mmTerrain;
import mapMaker.model.tile.mmTile;
import mapMaker.model.tile.riverConfiguration.mmRiverConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class FileHandlingTest {

    private mmRiverConfiguration mmRiverConfiguration;
    @Before
    public void setUp() {
        this.mmRiverConfiguration = mmRiverConfiguration.getSpringHead();
    }

    @Test
    public void readFile() throws IOException {
        File directory = new File("./");
        File file = new File(directory.getAbsolutePath().replace(".","") + "/map/map2.txt");

        mmFileImporter mmFileImporter = new mmFileImporter();

        mmMap mmMap = mmFileImporter.readFile(file);
        if(mmMap !=null)
            assertTrue(mmMap.hasTiles());

    }
    @Test
    public void writeFile() throws IOException {
        mmMap mmMap = new mmMap();
        try {
            mmMap.placeTile(new mmLocation(0, 0, 0), new mmTile(mmTerrain.ROCK, mmRiverConfiguration));
            mmMap.placeTile(new mmLocation(0, -1, 1), new mmTile(mmTerrain.DESERT, mmRiverConfiguration));
            mmMap.placeTile(new mmLocation(0, 1, -1), new mmTile(mmTerrain.WOODS, mmRiverConfiguration));
            mmMap.placeTile(new mmLocation(1, 0, -1), new mmTile(mmTerrain.ROCK, mmRiverConfiguration));
            mmMap.placeTile(new mmLocation(-1, 0, 1), new mmTile(mmTerrain.ROCK, mmRiverConfiguration));
            mmMap.placeTile(new mmLocation(1, -1, 0), new mmTile(mmTerrain.ROCK, mmRiverConfiguration));
        }
        catch(mmInvalidLocationException e)
        {
            fail("Invalid mmLocation used in mmMap creation");
        }
        mmFileExporter mmFileExporter = new mmFileExporter();
        //Give File Name
        mmFileExporter.writeToFile(mmMap,"map/map2.txt");
    }
    @Test
    public void isCorrectFileExported() throws IOException {
        mmMap mmMap = new mmMap();
        try {
            mmMap.placeTile(new mmLocation(0, 0, 0), new mmTile(mmTerrain.ROCK, mmRiverConfiguration));
            mmMap.placeTile(new mmLocation(0, -1, 1), new mmTile(mmTerrain.DESERT, mmRiverConfiguration));
            mmMap.placeTile(new mmLocation(0, 1, -1), new mmTile(mmTerrain.WOODS, mmRiverConfiguration));
            mmMap.placeTile(new mmLocation(1, 0, -1), new mmTile(mmTerrain.ROCK, mmRiverConfiguration));
            mmMap.placeTile(new mmLocation(-1, 0, 1), new mmTile(mmTerrain.ROCK, mmRiverConfiguration));
            mmMap.placeTile(new mmLocation(1, -1, 0), new mmTile(mmTerrain.ROCK, mmRiverConfiguration));
        }
        catch(mmInvalidLocationException e)
        {
            fail("Invalid mmLocation used in mmMap creation");
        }
        mmFileExporter mmFileExporter = new mmFileExporter();
        //Give File Name
        mmFileExporter.writeToFile(mmMap,"map/map3.txt");
        assertTrue(isFileEqual("map/map3.txt", "map/test1.txt"));
    }
    @Test
    public void isCorrectFileImported() throws IOException{
        mmMap mmMap = new mmMap();
        try {
            mmMap.placeTile(new mmLocation(0, 0, 0), new mmTile(mmTerrain.ROCK, mmRiverConfiguration));
            mmMap.placeTile(new mmLocation(0, -1, 1), new mmTile(mmTerrain.DESERT, mmRiverConfiguration));
            mmMap.placeTile(new mmLocation(0, 1, -1), new mmTile(mmTerrain.WOODS, mmRiverConfiguration));
            mmMap.placeTile(new mmLocation(1, 0, -1), new mmTile(mmTerrain.ROCK, mmRiverConfiguration));
            mmMap.placeTile(new mmLocation(-1, 0, 1), new mmTile(mmTerrain.ROCK, mmRiverConfiguration));
            mmMap.placeTile(new mmLocation(1, -1, 0), new mmTile(mmTerrain.ROCK, mmRiverConfiguration));
        } catch (mmInvalidLocationException e) {
            e.printStackTrace();
        }
        mmFileImporter mmFileImporter = new mmFileImporter();
        File directory = new File("./");
        File file = new File(directory.getAbsolutePath().replace(".","")+"map/test1.txt");
        mmMap mmMap1 = mmFileImporter.readFile(file);
        assertTrue(isMapEqual(mmMap, mmMap1));
    }
    private boolean isMapEqual(mmMap mmMap1, mmMap mmMap2){
        boolean isEqual = true;
        if(mmMap1.getAllLocations().size() == mmMap2.getAllLocations().size()){
            for(mmLocation mmLocation : mmMap1.getAllLocations()){
                if(mmMap1.getTile(mmLocation).getMmRiverConfiguration().getRotationAmount() != mmMap2.getTile(mmLocation).getMmRiverConfiguration().getRotationAmount()){
                    isEqual = false;
                }
            }
        }
        else {
            isEqual = false;
        }
        System.out.print(isEqual);
        return isEqual;
    }
    public boolean isFileEqual(String filename1, String filename2) throws IOException {
        File directory = new File("./");
        FileReader file1 = new FileReader(directory.getAbsolutePath().replace(".","")+filename1);
        FileReader file2 = new FileReader(directory.getAbsolutePath().replace(".","")+filename2);
        directory.delete();
        BufferedReader reader1 = new BufferedReader(file1);
        BufferedReader reader2 = new BufferedReader(file2);
        String line1 = reader1.readLine();
        String line2 = reader2.readLine();
        boolean areEqual = true;
        int lineNum = 1;
        while (line1 != null || line2 != null) {
            if (line1 == null || line2 == null) {
                areEqual = false;

                break;
            } else if (!line1.equalsIgnoreCase(line2)) {
                areEqual = false;

                break;
            }

            line1 = reader1.readLine();

            line2 = reader2.readLine();

            lineNum++;
        }
        reader1.close();
        reader2.close();
        return areEqual;
    }
}
