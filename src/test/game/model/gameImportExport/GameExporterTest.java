package game.model.gameImportExport;

import game.controller.MainViewController;
import game.model.Player;
import game.model.PlayerId;
import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileCompartmentLocation;
import game.model.gameImportExport.exporter.GameExporter;
import game.model.managers.*;
import game.model.resources.ResourceType;
import game.model.structures.resourceProducer.primaryProducer.*;
import game.model.structures.resourceProducer.secondaryProducer.*;
import game.model.tile.RiverConfiguration;
import game.model.tile.Tile;
import game.model.tinyGame.Game;
import game.model.map.RBMap;
import game.model.tile.Terrain;
import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class GameExporterTest {

    private static final String testingFilePath = "testFiles/GameExportTest.tinyrick";
    RBMap map;

    ArrayList<Location> locations;// = new ArrayList<>();
    ArrayList<Tile> tiles;// = new ArrayList<>();

    GameExporter gameExporter;// = new GameExporter(game);


    @Before
    public void setUp() {
        map = new RBMap();
        locations = new ArrayList<>();
        tiles = new ArrayList<>();

        locations.add(new Location(0,0,0));
        locations.add(new Location(0,-1,1));
        locations.add(new Location(0,1,-1));
        locations.add(new Location(-2,1,1));
        locations.add(new Location(1,0,-1));

        tiles.add(new Tile(Terrain.PASTURE, RiverConfiguration.getNoRivers()));
        tiles.add(new Tile(Terrain.DESERT, RiverConfiguration.getAdjacentFaces()));
        tiles.add(new Tile(Terrain.SEA, RiverConfiguration.getOppositeFaces()));
        tiles.add(new Tile(Terrain.WOODS, RiverConfiguration.getSkipAFace()));
        tiles.add(new Tile(Terrain.MOUNTAIN, RiverConfiguration.getNoRivers()));

        tiles.get(0).getTileCompartment(TileCompartmentDirection.getNorth()).storeResource(ResourceType.BOARDS,1);
        tiles.get(0).getTileCompartment(TileCompartmentDirection.getNorth()).storeResource(ResourceType.PAPER,10);
        tiles.get(1).getTileCompartment(TileCompartmentDirection.getSouth()).storeResource(ResourceType.GOLD,2);
        tiles.get(2).getTileCompartment(TileCompartmentDirection.getSouthEast()).storeResource(ResourceType.COINS,3);
        tiles.get(3).getTileCompartment(TileCompartmentDirection.getEast()).storeResource(ResourceType.FUEL,4);
        tiles.get(4).getTileCompartment(TileCompartmentDirection.getEast()).storeResource(ResourceType.TRUNKS,5);
        tiles.get(4).getTileCompartment(TileCompartmentDirection.getEast()).storeResource(ResourceType.CLAY,6);

        map.placeTile(locations.get(0),tiles.get(0));
        map.placeTile(locations.get(1),tiles.get(1));
        map.placeTile(locations.get(2),tiles.get(2));
        map.placeTile(locations.get(3),tiles.get(3));
        map.placeTile(locations.get(4),tiles.get(4));

        PlayerId pid1 = new PlayerId(1);
        PlayerId pid2 = new PlayerId(2);

        MainViewController mvc = new MainViewController();

        TransportManager tm1 = new TransportManager(pid1, mvc, new GooseManager(), map, new StructureManager(mvc,map), null);
        TransportManager tm2 = new TransportManager(pid2, mvc, new GooseManager(), map, new StructureManager(mvc,map), null);

        Player p1 = new Player(tm1, pid1, "Karl", new TileCompartmentLocation(new Location(0,0,0),TileCompartmentDirection.getNorth()));
        Player p2 = new Player(tm2, pid1, "Friedrich", new TileCompartmentLocation(new Location(-1,0,1),TileCompartmentDirection.getEast()));

        StructureManager sm = new StructureManager(mvc,map);

        /* Put some mines in there */
        sm.addStructure(new TileCompartmentLocation(locations.get(1),TileCompartmentDirection.getSouth()), new Mine(4,8));
        sm.addStructure(new TileCompartmentLocation(locations.get(4),TileCompartmentDirection.getSouthSouthWest()), new Mine(10,10));

        /* Sprinkle in some Oil Rigs too */
        OilRig oilRig1 = new OilRig();
        oilRig1.storeResource(ResourceType.BOARDS,5);
        oilRig1.storeResource(ResourceType.COINS,2);
        sm.addStructure(new TileCompartmentLocation(locations.get(1),TileCompartmentDirection.getNorth()), oilRig1);
        OilRig oilRig2 = new OilRig();
        oilRig2.storeResource(ResourceType.TRUNKS,1);
        oilRig2.storeResource(ResourceType.GOLD,29);
        sm.addStructure(new TileCompartmentLocation(locations.get(4),TileCompartmentDirection.getNorthWest()), oilRig2);

        /* Put a ClayPit in there */
        sm.addStructure(new TileCompartmentLocation(locations.get(1),TileCompartmentDirection.getNorthNorthEast()), new ClayPit());

        /* Put a couple of PaperMills in there */
        sm.addStructure(new TileCompartmentLocation(locations.get(2),TileCompartmentDirection.getNorthWest()), new Papermill());
        sm.addStructure(new TileCompartmentLocation(locations.get(3),TileCompartmentDirection.getSouthSouthWest()), new Papermill());

        /* Put a StoneQuarry in there */
        sm.addStructure(new TileCompartmentLocation(locations.get(0),TileCompartmentDirection.getEast()), new StoneQuarry());

        /* Put a WoodCutter in there */
        sm.addStructure(new TileCompartmentLocation(locations.get(2),TileCompartmentDirection.getSouth()), new Woodcutter());

        /* Put a Mint in there */
        sm.addStructure(new TileCompartmentLocation(locations.get(1),TileCompartmentDirection.getNorthWest()), new Mint());

        /* Put a CoalBurner in there */
        sm.addStructure(new TileCompartmentLocation(locations.get(3),TileCompartmentDirection.getNorthWest()), new CoalBurner());

        /* Put a Sawmill in there */
        sm.addStructure(new TileCompartmentLocation(locations.get(1),TileCompartmentDirection.getNorth()), new Sawmill());

        /* Put a StoneFactory in there */
        sm.addStructure(new TileCompartmentLocation(locations.get(2),TileCompartmentDirection.getEast()), new StoneFactory());

         /* Put a StockMarket in there */
        sm.addStructure(new TileCompartmentLocation(locations.get(3),TileCompartmentDirection.getNorthEast()), new StockMarket());


        Game game = new Game(map, p1, p2, new GooseManager(), sm);

        gameExporter = new GameExporter(game);
    }

    @Test
    public void fileWasActuallyWrittenTest(){
        gameExporter.exportGameToPath(testingFilePath);
        File f = new File(testingFilePath);
        assertTrue(f.exists());
    }

    @Test
    public void fileActuallyHasStuffInItTest(){
        gameExporter.exportGameToPath(testingFilePath);
        File f = new File(testingFilePath);
        assertTrue(f.exists());
        assertTrue(f.isFile());
    }

    @Test
    public void mapSectionTest(){
        gameExporter.exportGameToPath(testingFilePath);
        String contents = readFile(testingFilePath);

        assertEquals("-----BEGIN MAP-----\n" +
                        "( 0 0 0 ) PASTURE \n" +
                        "( 0 -1 1 ) DESERT ( 1 2 )\n" +
                        "( 0 1 -1 ) SEA \n" +
                        "( -2 1 1 ) WOODS ( 1 3 )\n" +
                        "( 1 0 -1 ) MOUNTAIN \n",
                contents.substring(0,contents.indexOf("-----END MAP-----"))
        );
    }

    @Test
    public void resourceSectionTest(){
        gameExporter.exportGameToPath(testingFilePath);
        String contents = readFile(testingFilePath);
        assertEquals(
                contents.substring(contents.indexOf("-----BEGIN RESOURCES-----"), contents.indexOf("-----END RESOURCES-----")).trim(),
                "-----BEGIN RESOURCES-----\n" +
                        "( 0 0 0 )  N BOARDS 1\n" +
                        "( 0 0 0 )  N PAPER 10\n" +
                        "( 0 -1 1 )  S GOLD 2\n" +
                        "( 0 1 -1 )  N COINS 3\n" +
                        "( -2 1 1 )  E FUEL 4\n" +
                        "( 1 0 -1 )  N TRUNKS 5\n" +
                        "( 1 0 -1 )  N CLAY 6".trim()
        );
    }

    @Test
    public void mineSectionTest(){
        gameExporter.exportGameToPath(testingFilePath);
        String contents = readFile(testingFilePath);

        String expected = "-----BEGIN MINES-----\n" +
                "( 0 -1 1 ) S MAX=[GOLD=4 IRON=8] CURRENT=[GOLD=4 IRON=8]\n" +
                "( 1 0 -1 ) SSW MAX=[GOLD=10 IRON=10] CURRENT=[GOLD=10 IRON=10]\n";

        assertEquals(
                contents.substring(contents.indexOf("-----BEGIN MINES-----"),contents.indexOf("-----END MINES-----")),
                expected
        );
    }

    @Test
    public void oilRigSectionTest(){
        gameExporter.exportGameToPath(testingFilePath);
        String contents = readFile(testingFilePath);

        String expected = "-----BEGIN OIL_RIG-----\n" +
                "( 1 0 -1 ) NW [GOLD=29 TRUNKS=1 ]";

        assertEquals(
                contents.substring(contents.indexOf("-----BEGIN OIL_RIG-----"),contents.indexOf("-----END OIL_RIG-----")).trim(),
                expected.trim()
        );
    }


    private String readFile(String filepath){
        File f = new File(filepath);
        String contents = "";
        try{
            FileReader fr = new FileReader(f);
            int nextChar = -2;  //lol
            while(nextChar != -1){
                nextChar = fr.read();
                contents += (nextChar == -1) ? "" : (char)nextChar;
            }
        } catch (Exception e){
            e.printStackTrace();
        }
        return contents;
    }

}
