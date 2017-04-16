package game.model.gameImportExport;

import game.controller.MainViewController;
import game.model.Player;
import game.model.PlayerId;
import game.model.direction.Location;
import game.model.managers.GooseManager;
import game.model.managers.StructureAbilityManager;
import game.model.managers.StructureManager;
import game.model.managers.TransportAbilityManager;
import game.model.resources.ResourceType;
import game.model.tile.RiverConfiguration;
import game.model.tile.Tile;
import game.model.tinyGame.Game;
import game.utilities.exceptions.MalformedMapFileException;
import game.model.gameImportExport.MapImporter;
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


        TransportAbilityManager tm = new TransportAbilityManager(new MainViewController(),new GooseManager(), map);
        Game game = new Game(map,
                new Player(tm, new PlayerId(1),"Player1"),
                new Player(tm, new PlayerId(2),"Player2"),
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





}
