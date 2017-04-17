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
import game.model.tile.RiverConfiguration;
import game.model.tile.Tile;
import game.model.tinyGame.Game;
import game.utilities.exceptions.MalformedMapFileException;
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

        TransportManager tm1 = new TransportManager(pid1, mvc, new GooseManager(), map, new StructureManager(mvc,map));
        TransportManager tm2 = new TransportManager(pid2, mvc, new GooseManager(), map, new StructureManager(mvc,map));

        Player p1 = new Player(tm1, pid1, "Karl", new TileCompartmentLocation(new Location(0,0,0),TileCompartmentDirection.getNorth()));
        Player p2 = new Player(tm2, pid1, "Friedrich", new TileCompartmentLocation(new Location(-1,0,1),TileCompartmentDirection.getEast()));

        Game game = new Game(map, p1, p2, new GooseManager(), new StructureManager(mvc,map));
        gameExporter = new GameExporter(game);
    }

    @Test
    public void fileWasActuallyWrittenTest(){
        gameExporter.exportGameToPath(testingFilePath);
        File f = new File(testingFilePath);
        assertTrue(f.exists());
    }

    @Test
    public void fileActuallyHasShitInItTest(){
        gameExporter.exportGameToPath(testingFilePath);
        File f = new File(testingFilePath);
        assertTrue(f.exists());
        assertTrue(f.isFile());
    }

    @Test
    public void mapSectionGucciTest(){
        gameExporter.exportGameToPath(testingFilePath);
        String contents = readFile(testingFilePath);

        assertEquals(contents.substring(0,contents.indexOf("-----END MAP-----")),
                "-----BEGIN MAP-----\n" +
                "( 0 0 0 ) PASTURE \n" +
                "( 0 -1 1 ) DESERT ( 1 2 )\n" +
                "( 0 1 -1 ) SEA ( 1 4 )\n" +
                "( -2 1 1 ) WOODS ( 1 3 )\n" +
                "( 1 0 -1 ) MOUNTAIN \n"
        );
    }

    @Test
    public void resourceSectionGucciTest(){
        gameExporter.exportGameToPath(testingFilePath);
        String contents = readFile(testingFilePath);
        System.out.println(contents);
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
