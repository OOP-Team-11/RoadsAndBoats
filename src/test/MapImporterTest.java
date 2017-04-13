
import game.model.direction.Location;
import game.model.tile.RiverConfiguration;
import game.model.tile.Tile;
import game.utilities.exceptions.MalformedMapFileException;
import game.model.gameImporter.MapImporter;
import game.model.map.RBMap;
import game.model.tile.Terrain;
import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.*;

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

    private void assertTerrainAtLocation(Terrain terrain, Location location) {
        try {
            mapImporter.importMapFromFile(br);
            assertNotNull(map.getTiles().get(location));
            assertEquals(map.getTiles().get(location).getTerrain(), terrain);
        } catch (MalformedMapFileException |IOException e) {
            fail();
        }
    }

    @Test
    public void terrain_sea() {
        try {
            fw.write("( 0 0 0 ) SEA");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        assertTerrainAtLocation(Terrain.SEA, new Location(0,0,0));
    }

    @Test
    public void terrain_pasture() {
        try {
            fw.write("( 0 0 0 ) PASTURE");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        assertTerrainAtLocation(Terrain.PASTURE, new Location(0,0,0));
    }

    @Test
    public void terrain_woods() {
        try {
            fw.write("( 0 0 0 ) WOODS");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        assertTerrainAtLocation(Terrain.WOODS, new Location(0,0,0));
    }

    @Test
    public void terrain_rock() {
        try {
            fw.write("( 0 0 0 ) ROCK");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        assertTerrainAtLocation(Terrain.ROCK, new Location(0,0,0));
    }

    @Test
    public void terrain_desert() {
        try {
            fw.write("( 0 0 0 ) DESERT");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        assertTerrainAtLocation(Terrain.DESERT, new Location(0,0,0));
    }

    @Test
    public void terrain_mountain() {
        try {
            fw.write("( 0 0 0 ) MOUNTAIN");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        assertTerrainAtLocation(Terrain.MOUNTAIN, new Location(0,0,0));
    }

    @Test
    public void location_normal() {
        try {
            fw.write("( -1 0 1 ) PASTURE");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        assertTerrainAtLocation(Terrain.PASTURE, new Location(-1,0,1));
    }

    @Test
    public void location_outer_bound() {
        try {
            fw.write("( -10 0 10 ) PASTURE");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        assertTerrainAtLocation(Terrain.PASTURE, new Location(-10,0,10));
    }

    @Test
    public void location_outer_middle_bound() {
        try {
            fw.write("( 0 -10 10 ) PASTURE");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        assertTerrainAtLocation(Terrain.PASTURE, new Location(0,-10,10));
    }

    @Test
    public void location_invalid() {
        try {
            fw.write("( 0 -1 0 ) PASTURE");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        try {
            mapImporter.importMapFromFile(br);
        } catch (IOException e) {
            fail();
        } catch (MalformedMapFileException e) {
            return;
        }

        fail();
    }

    @Test
    public void river_none() {
        try {
            fw.write("( 0 0 0 ) PASTURE");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        try {
            mapImporter.importMapFromFile(br);
            Location tileLocation = new Location(0,0,0);
            Tile placedTile = map.getTiles().get(tileLocation);
            assertEquals(placedTile.getRiverConfiguration(), RiverConfiguration.getNoRivers());
        } catch (MalformedMapFileException |IOException e) {
            fail();
        }
    }

    @Test
    public void river_source() {
        try {
            fw.write("( 0 0 0 ) PASTURE ( 1 )");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        try {
            mapImporter.importMapFromFile(br);
            Location tileLocation = new Location(0,0,0);
            Tile placedTile = map.getTiles().get(tileLocation);
            assertEquals(placedTile.getRiverConfiguration(), RiverConfiguration.getSpringHead());
            assertTrue(placedTile.getRiverConfiguration().canConnectNorth());
        } catch (MalformedMapFileException |IOException e) {
            fail();
        }
    }

    @Test
    public void river_source_2() {
        try {
            fw.write("( 0 0 0 ) PASTURE ( 2 )");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        try {
            mapImporter.importMapFromFile(br);
            Location tileLocation = new Location(0,0,0);
            Tile placedTile = map.getTiles().get(tileLocation);
            assertTrue(placedTile.getRiverConfiguration().canConnectNortheast());
        } catch (MalformedMapFileException |IOException e) {
            fail();
        }
    }

    @Test
    public void river_source_3() {
        try {
            fw.write("( 0 0 0 ) PASTURE ( 3 )");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        try {
            mapImporter.importMapFromFile(br);
            Location tileLocation = new Location(0,0,0);
            Tile placedTile = map.getTiles().get(tileLocation);
            assertTrue(placedTile.getRiverConfiguration().canConnectSoutheast());
        } catch (MalformedMapFileException |IOException e) {
            fail();
        }
    }

    @Test
    public void river_source_4() {
        try {
            fw.write("( 0 0 0 ) PASTURE ( 4 )");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        try {
            mapImporter.importMapFromFile(br);
            Location tileLocation = new Location(0,0,0);
            Tile placedTile = map.getTiles().get(tileLocation);
            assertTrue(placedTile.getRiverConfiguration().canConnectSouth());
        } catch (MalformedMapFileException |IOException e) {
            fail();
        }
    }

    @Test
    public void river_source_5() {
        try {
            fw.write("( 0 0 0 ) PASTURE ( 5 )");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        try {
            mapImporter.importMapFromFile(br);
            Location tileLocation = new Location(0,0,0);
            Tile placedTile = map.getTiles().get(tileLocation);
            assertTrue(placedTile.getRiverConfiguration().canConnectSouthwest());
        } catch (MalformedMapFileException |IOException e) {
            fail();
        }
    }

    @Test
    public void river_source_6() {
        try {
            fw.write("( 0 0 0 ) PASTURE ( 6 )");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        try {
            mapImporter.importMapFromFile(br);
            Location tileLocation = new Location(0,0,0);
            Tile placedTile = map.getTiles().get(tileLocation);
            assertTrue(placedTile.getRiverConfiguration().canConnectNorthwest());
        } catch (MalformedMapFileException |IOException e) {
            fail();
        }
    }

    @Test
    public void river_adjacent() {
        try {
            fw.write("( 0 0 0 ) PASTURE ( 1 2 )");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        try {
            mapImporter.importMapFromFile(br);
            Location tileLocation = new Location(0,0,0);
            Tile placedTile = map.getTiles().get(tileLocation);
            assertTrue(placedTile.getRiverConfiguration().canConnectNorth());
            assertTrue(placedTile.getRiverConfiguration().canConnectNortheast());
            assertFalse(placedTile.getRiverConfiguration().canConnectSoutheast());
            assertFalse(placedTile.getRiverConfiguration().canConnectSouth());
            assertFalse(placedTile.getRiverConfiguration().canConnectSouthwest());
            assertFalse(placedTile.getRiverConfiguration().canConnectNorthwest());
        } catch (MalformedMapFileException |IOException e) {
            fail();
        }
    }

    @Test
    public void river_adjacent_2() {
        try {
            fw.write("( 0 0 0 ) PASTURE ( 2 3 )");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        try {
            mapImporter.importMapFromFile(br);
            Location tileLocation = new Location(0,0,0);
            Tile placedTile = map.getTiles().get(tileLocation);
            assertFalse(placedTile.getRiverConfiguration().canConnectNorth());
            assertTrue(placedTile.getRiverConfiguration().canConnectNortheast());
            assertTrue(placedTile.getRiverConfiguration().canConnectSoutheast());
            assertFalse(placedTile.getRiverConfiguration().canConnectSouth());
            assertFalse(placedTile.getRiverConfiguration().canConnectSouthwest());
            assertFalse(placedTile.getRiverConfiguration().canConnectNorthwest());
        } catch (MalformedMapFileException |IOException e) {
            fail();
        }
    }

    @Test
    public void river_adjacent_3() {
        try {
            fw.write("( 0 0 0 ) PASTURE ( 3 4 )");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        try {
            mapImporter.importMapFromFile(br);
            Location tileLocation = new Location(0,0,0);
            Tile placedTile = map.getTiles().get(tileLocation);
            assertFalse(placedTile.getRiverConfiguration().canConnectNorth());
            assertFalse(placedTile.getRiverConfiguration().canConnectNortheast());
            assertTrue(placedTile.getRiverConfiguration().canConnectSoutheast());
            assertTrue(placedTile.getRiverConfiguration().canConnectSouth());
            assertFalse(placedTile.getRiverConfiguration().canConnectSouthwest());
            assertFalse(placedTile.getRiverConfiguration().canConnectNorthwest());
        } catch (MalformedMapFileException |IOException e) {
            fail();
        }
    }

    @Test
    public void river_adjacent_4() {
        try {
            fw.write("( 0 0 0 ) PASTURE ( 4 5 )");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        try {
            mapImporter.importMapFromFile(br);
            Location tileLocation = new Location(0,0,0);
            Tile placedTile = map.getTiles().get(tileLocation);
            assertFalse(placedTile.getRiverConfiguration().canConnectNorth());
            assertFalse(placedTile.getRiverConfiguration().canConnectNortheast());
            assertFalse(placedTile.getRiverConfiguration().canConnectSoutheast());
            assertTrue(placedTile.getRiverConfiguration().canConnectSouth());
            assertTrue(placedTile.getRiverConfiguration().canConnectSouthwest());
            assertFalse(placedTile.getRiverConfiguration().canConnectNorthwest());
        } catch (MalformedMapFileException |IOException e) {
            fail();
        }
    }

    @Test
    public void river_adjacent_5() {
        try {
            fw.write("( 0 0 0 ) PASTURE ( 5 6 )");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        try {
            mapImporter.importMapFromFile(br);
            Location tileLocation = new Location(0,0,0);
            Tile placedTile = map.getTiles().get(tileLocation);
            assertFalse(placedTile.getRiverConfiguration().canConnectNorth());
            assertFalse(placedTile.getRiverConfiguration().canConnectNortheast());
            assertFalse(placedTile.getRiverConfiguration().canConnectSoutheast());
            assertFalse(placedTile.getRiverConfiguration().canConnectSouth());
            assertTrue(placedTile.getRiverConfiguration().canConnectSouthwest());
            assertTrue(placedTile.getRiverConfiguration().canConnectNorthwest());
        } catch (MalformedMapFileException |IOException e) {
            fail();
        }
    }

    @Test
    public void river_adjacent_6() {
        try {
            fw.write("( 0 0 0 ) PASTURE ( 1 6 )");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        try {
            mapImporter.importMapFromFile(br);
            Location tileLocation = new Location(0,0,0);
            Tile placedTile = map.getTiles().get(tileLocation);
            assertTrue(placedTile.getRiverConfiguration().canConnectNorth());
            assertFalse(placedTile.getRiverConfiguration().canConnectNortheast());
            assertFalse(placedTile.getRiverConfiguration().canConnectSoutheast());
            assertFalse(placedTile.getRiverConfiguration().canConnectSouth());
            assertFalse(placedTile.getRiverConfiguration().canConnectSouthwest());
            assertTrue(placedTile.getRiverConfiguration().canConnectNorthwest());
        } catch (MalformedMapFileException |IOException e) {
            fail();
        }
    }

    @Test
    public void river_skip() {
        try {
            fw.write("( 0 0 0 ) PASTURE ( 1 3 )");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        try {
            mapImporter.importMapFromFile(br);
            Location tileLocation = new Location(0,0,0);
            Tile placedTile = map.getTiles().get(tileLocation);
            assertTrue(placedTile.getRiverConfiguration().canConnectNorth());
            assertFalse(placedTile.getRiverConfiguration().canConnectNortheast());
            assertTrue(placedTile.getRiverConfiguration().canConnectSoutheast());
            assertFalse(placedTile.getRiverConfiguration().canConnectSouth());
            assertFalse(placedTile.getRiverConfiguration().canConnectSouthwest());
            assertFalse(placedTile.getRiverConfiguration().canConnectNorthwest());
        } catch (MalformedMapFileException |IOException e) {
            fail();
        }
    }

    @Test
    public void river_skip_1() {
        try {
            fw.write("( 0 0 0 ) PASTURE ( 2 4 )");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        try {
            mapImporter.importMapFromFile(br);
            Location tileLocation = new Location(0,0,0);
            Tile placedTile = map.getTiles().get(tileLocation);
            assertFalse(placedTile.getRiverConfiguration().canConnectNorth());
            assertTrue(placedTile.getRiverConfiguration().canConnectNortheast());
            assertFalse(placedTile.getRiverConfiguration().canConnectSoutheast());
            assertTrue(placedTile.getRiverConfiguration().canConnectSouth());
            assertFalse(placedTile.getRiverConfiguration().canConnectSouthwest());
            assertFalse(placedTile.getRiverConfiguration().canConnectNorthwest());
        } catch (MalformedMapFileException |IOException e) {
            fail();
        }
    }

    @Test
    public void river_skip_2() {
        try {
            fw.write("( 0 0 0 ) PASTURE ( 3 5 )");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        try {
            mapImporter.importMapFromFile(br);
            Location tileLocation = new Location(0,0,0);
            Tile placedTile = map.getTiles().get(tileLocation);
            assertFalse(placedTile.getRiverConfiguration().canConnectNorth());
            assertFalse(placedTile.getRiverConfiguration().canConnectNortheast());
            assertTrue(placedTile.getRiverConfiguration().canConnectSoutheast());
            assertFalse(placedTile.getRiverConfiguration().canConnectSouth());
            assertTrue(placedTile.getRiverConfiguration().canConnectSouthwest());
            assertFalse(placedTile.getRiverConfiguration().canConnectNorthwest());
        } catch (MalformedMapFileException |IOException e) {
            fail();
        }
    }

    @Test
    public void river_skip_3() {
        try {
            fw.write("( 0 0 0 ) PASTURE ( 4 6 )");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        try {
            mapImporter.importMapFromFile(br);
            Location tileLocation = new Location(0,0,0);
            Tile placedTile = map.getTiles().get(tileLocation);
            assertFalse(placedTile.getRiverConfiguration().canConnectNorth());
            assertFalse(placedTile.getRiverConfiguration().canConnectNortheast());
            assertFalse(placedTile.getRiverConfiguration().canConnectSoutheast());
            assertTrue(placedTile.getRiverConfiguration().canConnectSouth());
            assertFalse(placedTile.getRiverConfiguration().canConnectSouthwest());
            assertTrue(placedTile.getRiverConfiguration().canConnectNorthwest());
        } catch (MalformedMapFileException |IOException e) {
            fail();
        }
    }

    @Test
    public void river_skip_4() {
        try {
            fw.write("( 0 0 0 ) PASTURE ( 1 5 )");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        try {
            mapImporter.importMapFromFile(br);
            Location tileLocation = new Location(0,0,0);
            Tile placedTile = map.getTiles().get(tileLocation);
            assertTrue(placedTile.getRiverConfiguration().canConnectNorth());
            assertFalse(placedTile.getRiverConfiguration().canConnectNortheast());
            assertFalse(placedTile.getRiverConfiguration().canConnectSoutheast());
            assertFalse(placedTile.getRiverConfiguration().canConnectSouth());
            assertTrue(placedTile.getRiverConfiguration().canConnectSouthwest());
            assertFalse(placedTile.getRiverConfiguration().canConnectNorthwest());
        } catch (MalformedMapFileException |IOException e) {
            fail();
        }
    }

    @Test
    public void river_skip_5() {
        try {
            fw.write("( 0 0 0 ) PASTURE ( 2 6 )");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        try {
            mapImporter.importMapFromFile(br);
            Location tileLocation = new Location(0,0,0);
            Tile placedTile = map.getTiles().get(tileLocation);
            assertFalse(placedTile.getRiverConfiguration().canConnectNorth());
            assertTrue(placedTile.getRiverConfiguration().canConnectNortheast());
            assertFalse(placedTile.getRiverConfiguration().canConnectSoutheast());
            assertFalse(placedTile.getRiverConfiguration().canConnectSouth());
            assertFalse(placedTile.getRiverConfiguration().canConnectSouthwest());
            assertTrue(placedTile.getRiverConfiguration().canConnectNorthwest());
        } catch (MalformedMapFileException |IOException e) {
            fail();
        }
    }

    @Test
    public void river_across() {
        try {
            fw.write("( 0 0 0 ) PASTURE ( 1 4 )");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        try {
            mapImporter.importMapFromFile(br);
            Location tileLocation = new Location(0,0,0);
            Tile placedTile = map.getTiles().get(tileLocation);
            assertTrue(placedTile.getRiverConfiguration().canConnectNorth());
            assertFalse(placedTile.getRiverConfiguration().canConnectNortheast());
            assertFalse(placedTile.getRiverConfiguration().canConnectSoutheast());
            assertTrue(placedTile.getRiverConfiguration().canConnectSouth());
            assertFalse(placedTile.getRiverConfiguration().canConnectSouthwest());
            assertFalse(placedTile.getRiverConfiguration().canConnectNorthwest());
        } catch (MalformedMapFileException |IOException e) {
            fail();
        }
    }

    @Test
    public void river_across_2() {
        try {
            fw.write("( 0 0 0 ) PASTURE ( 2 5 )");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        try {
            mapImporter.importMapFromFile(br);
            Location tileLocation = new Location(0,0,0);
            Tile placedTile = map.getTiles().get(tileLocation);
            assertFalse(placedTile.getRiverConfiguration().canConnectNorth());
            assertTrue(placedTile.getRiverConfiguration().canConnectNortheast());
            assertFalse(placedTile.getRiverConfiguration().canConnectSoutheast());
            assertFalse(placedTile.getRiverConfiguration().canConnectSouth());
            assertTrue(placedTile.getRiverConfiguration().canConnectSouthwest());
            assertFalse(placedTile.getRiverConfiguration().canConnectNorthwest());
        } catch (MalformedMapFileException |IOException e) {
            fail();
        }
    }

    @Test
    public void river_across_3() {
        try {
            fw.write("( 0 0 0 ) PASTURE ( 3 6 )");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        try {
            mapImporter.importMapFromFile(br);
            Location tileLocation = new Location(0,0,0);
            Tile placedTile = map.getTiles().get(tileLocation);
            assertFalse(placedTile.getRiverConfiguration().canConnectNorth());
            assertFalse(placedTile.getRiverConfiguration().canConnectNortheast());
            assertTrue(placedTile.getRiverConfiguration().canConnectSoutheast());
            assertFalse(placedTile.getRiverConfiguration().canConnectSouth());
            assertFalse(placedTile.getRiverConfiguration().canConnectSouthwest());
            assertTrue(placedTile.getRiverConfiguration().canConnectNorthwest());
        } catch (MalformedMapFileException |IOException e) {
            fail();
        }
    }

    @Test
    public void river_everyother() {
        try {
            fw.write("( 0 0 0 ) PASTURE ( 1 3 5 )");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        try {
            mapImporter.importMapFromFile(br);
            Location tileLocation = new Location(0,0,0);
            Tile placedTile = map.getTiles().get(tileLocation);
            assertTrue(placedTile.getRiverConfiguration().canConnectNorth());
            assertFalse(placedTile.getRiverConfiguration().canConnectNortheast());
            assertTrue(placedTile.getRiverConfiguration().canConnectSoutheast());
            assertFalse(placedTile.getRiverConfiguration().canConnectSouth());
            assertTrue(placedTile.getRiverConfiguration().canConnectSouthwest());
            assertFalse(placedTile.getRiverConfiguration().canConnectNorthwest());
        } catch (MalformedMapFileException |IOException e) {
            fail();
        }
    }

    @Test
    public void river_everyother_2() {
        try {
            fw.write("( 0 0 0 ) PASTURE ( 2 4 6 )");
            fw.write(System.lineSeparator());
            fw.write("END MAP");
            fw.write(System.lineSeparator());
            fw.close();
        } catch (IOException e) {
            fail();
        }

        try {
            mapImporter.importMapFromFile(br);
            Location tileLocation = new Location(0,0,0);
            Tile placedTile = map.getTiles().get(tileLocation);
            assertFalse(placedTile.getRiverConfiguration().canConnectNorth());
            assertTrue(placedTile.getRiverConfiguration().canConnectNortheast());
            assertFalse(placedTile.getRiverConfiguration().canConnectSoutheast());
            assertTrue(placedTile.getRiverConfiguration().canConnectSouth());
            assertFalse(placedTile.getRiverConfiguration().canConnectSouthwest());
            assertTrue(placedTile.getRiverConfiguration().canConnectNorthwest());
        } catch (MalformedMapFileException |IOException e) {
            fail();
        }
    }
}
