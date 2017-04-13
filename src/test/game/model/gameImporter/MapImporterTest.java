package model.gameImporter;

import game.model.direction.Location;
import game.utilities.exceptions.MalformedMapFileException;
import game.model.gameImporter.MapImporter;
import game.model.map.RBMap;
import game.model.tile.Terrain;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

public class MapImporterTest {

    private static final String filePath = "testFiles/example.tinyrick";
    File testFile;
    RBMap map;
    MapImporter mapImporter;
    BufferedReader br;
    FileWriter fw;

    @Before
    public void setUp() {
        testFile = new File(filePath);
        map = new RBMap();
        mapImporter = new MapImporter(map);
        try {
            br = new BufferedReader(new FileReader(testFile));
            fw = new FileWriter(testFile);
            fw.write("BEGIN MAP");
            fw.write(System.lineSeparator());
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    public void importTest() {
        try {
            fw.write("( 0 -1 1 ) PASTURE");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        try {
            mapImporter.importMapFromFile(br);
            Location tileLocation = new Location(0,-1,1);
            assertNotNull(map.getTiles().get(tileLocation));
            assertEquals(map.getTiles().get(tileLocation).getTerrain(), Terrain.PASTURE);
        } catch (MalformedMapFileException |IOException e) {
            fail();
        }
    }
}
