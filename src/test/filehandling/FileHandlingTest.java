package filehandling;

import model.FileExporter;
import model.FileImporter;
import model.Map;
import model.tile.InvalidLocationException;
import model.tile.Location;
import model.tile.Terrain;
import model.tile.Tile;
import model.tile.riverConfiguration.RiverConfiguration;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

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
}
