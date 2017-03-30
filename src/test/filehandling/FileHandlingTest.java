package filehandling;

import model.FileExporter;
import model.FileImporter;
import model.Map;
import model.tile.InvalidLocationException;
import model.tile.Location;
import model.tile.Terrain;
import model.tile.Tile;
import model.tile.riverConfiguration.RiverConfiguration;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class FileHandlingTest {

    private RiverConfiguration riverConfiguration;
    @Before
    public void setUp() {
        this.riverConfiguration = RiverConfiguration.getSpringHead();
    }

    @Test
    public void readFile() throws IOException {
        FileImporter fileImporter = new FileImporter();
        Map map = fileImporter.readFile("map/map2.txt");
        if(map!=null)
        assertTrue(map.hasTiles());

    }
    @Test
    public void writeFile() throws IOException {
        Map map = new Map();
        try {
            map.placeTile(new Location(0, 0, 0), new Tile(Terrain.ROCK, riverConfiguration));
            map.placeTile(new Location(0, -1, 1), new Tile(Terrain.DESERT, riverConfiguration));
            map.placeTile(new Location(0, 1, -1), new Tile(Terrain.WOODS, riverConfiguration));
            map.placeTile(new Location(1, 0, -1), new Tile(Terrain.ROCK, riverConfiguration));
            map.placeTile(new Location(-1, 0, 1), new Tile(Terrain.ROCK, riverConfiguration));
            map.placeTile(new Location(1, -1, 0), new Tile(Terrain.ROCK, riverConfiguration));
        }
        catch(InvalidLocationException e)
        {
            fail("Invalid Location used in map creation");
        }
        FileExporter fileExporter = new FileExporter();
        //Give File Name
        fileExporter.writeToFile(map,"map/map2.txt");
    }
    @Test
    public void isCorrectFileExported(){
        FileExporter fileExporter = new FileExporter();
        Map map = new Map();
        try {
            map.placeTile(new Location(0, 0, 0), new Tile(Terrain.ROCK, riverConfiguration));
            map.placeTile(new Location(0, -1, 1), new Tile(Terrain.DESERT, riverConfiguration));
            map.placeTile(new Location(0, 1, -1), new Tile(Terrain.WOODS, riverConfiguration));
            map.placeTile(new Location(1, 0, -1), new Tile(Terrain.ROCK, riverConfiguration));
        } catch (InvalidLocationException e) {
            e.printStackTrace();
        }
        fileExporter.writeToFile(map,"map/map2.txt");
        try {
            assertTrue(isFileEqual("map/map2.txt", "map/test1.txt"));
        } catch (IOException e) {
            Assert.fail("File Not Same");
            e.printStackTrace();
        }
    }
    public boolean isFileEqual(String file1, String file2) throws IOException {
        BufferedReader reader1 = new BufferedReader(new FileReader(file1));
        BufferedReader reader2 = new BufferedReader(new FileReader(file2));
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
