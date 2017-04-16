package game.model.gameImportExport;

import game.controller.MainViewController;
import game.model.Player;
import game.model.PlayerId;
import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileCompartmentLocation;
import game.model.gameImportExport.exporter.GameExporter;
import game.model.managers.GooseManager;
import game.model.managers.StructureAbilityManager;
import game.model.managers.StructureManager;
import game.model.managers.TransportAbilityManager;
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

        tiles.add(new Tile(Terrain.PASTURE, RiverConfiguration.getEveryOtherFace()));
        tiles.add(new Tile(Terrain.DESERT, RiverConfiguration.getAdjacentFaces()));
        tiles.add(new Tile(Terrain.SEA, RiverConfiguration.getOppositeFaces()));
        tiles.add(new Tile(Terrain.WOODS, RiverConfiguration.getSkipAFace()));
        tiles.add(new Tile(Terrain.MOUNTAIN, RiverConfiguration.getNoRivers()));


        map.placeTile(locations.get(0),tiles.get(0));
        map.placeTile(locations.get(1),tiles.get(1));
        map.placeTile(locations.get(2),tiles.get(2));
        map.placeTile(locations.get(3),tiles.get(3));
        map.placeTile(locations.get(4),tiles.get(4));

        map.getTile(locations.get(0)).getTileCompartment(TileCompartmentDirection.getNorth()).storeResource(ResourceType.BOARDS,3);
        map.getTile(locations.get(0)).getTileCompartment(TileCompartmentDirection.getSouth()).storeResource(ResourceType.GOLD,6);
        map.getTile(locations.get(2)).getTileCompartment(TileCompartmentDirection.getSouthEast()).storeResource(ResourceType.COINS,12);
        map.getTile(locations.get(3)).getTileCompartment(TileCompartmentDirection.getEast()).storeResource(ResourceType.FUEL,2);


        TransportAbilityManager tm = new TransportAbilityManager(new MainViewController(),new GooseManager(), map);
        Game game = new Game(map,
                new Player(tm, new PlayerId(1),"Player1", new TileCompartmentLocation(locations.get(0), TileCompartmentDirection.getEast())),
                new Player(tm, new PlayerId(2),"Player2", new TileCompartmentLocation(locations.get(2), TileCompartmentDirection.getNorth())),
                new GooseManager(),
                new StructureManager(new StructureAbilityManager(new MainViewController())));
        gameExporter = new GameExporter(game);
    }

    @Test
    public void fileWasActuallyEvenWrittenTest(){
        gameExporter.exportGameToPath(testingFilePath);
        File f = new File(testingFilePath);
        assertTrue(f.exists());
        assertTrue(f.isFile());

    }

    @Test
    public void fileActuallyHasShitInItTest(){
        gameExporter.exportGameToPath(testingFilePath);
        File f = new File(testingFilePath);
        assertTrue(f.exists());
        assertTrue(f.isFile());
    }

    @Test
    public void fileIsOfTheRightLengthTest(){
        gameExporter.exportGameToPath(testingFilePath);
        File f = new File(testingFilePath);
        assertTrue(f.exists());
        assertTrue(f.isFile());
        assertEquals(f.length(), 160);
    }

    @Test
    public void fileContentsGucciTest(){
        gameExporter.exportGameToPath(testingFilePath);
        File f = new File(testingFilePath);
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

        assertEquals(contents.substring(0,160),"-----BEGIN MAP-----\n" +
                "( 0 0 0 ) PASTURE ( 1 3 5 )\n" +
                "( 0 -1 1 ) DESERT ( 1 2 )\n" +
                "( 0 1 -1 ) SEA ( 1 4 )\n" +
                "( -2 1 1 ) WOODS ( 1 3 )\n" +
                "( 1 0 -1 ) MOUNTAIN \n" +
                "-----END MAP-----");
    }








}
