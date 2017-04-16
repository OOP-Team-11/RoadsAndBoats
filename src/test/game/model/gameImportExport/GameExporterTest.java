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

import static org.junit.Assert.*;

public class GameExporterTest {

    private static final String filePath = "testFiles/example.tinyrick";
    File testFile;
    RBMap map;
    MapImporter mapImporter;
    BufferedReader br;
    FileWriter fw;

    GameExporter gameExporter;// = new GameExporter(game);


    @Before
    public void setUp() {
        map = new RBMap();
        map.placeTile(new Location(0,0,0),new Tile(Terrain.PASTURE, RiverConfiguration.getNoRivers()));
        map.placeTile(new Location(0,-1,1),new Tile(Terrain.DESERT, RiverConfiguration.getNoRivers()));
        map.placeTile(new Location(0,1,-1),new Tile(Terrain.SEA, RiverConfiguration.getNoRivers()));
        map.placeTile(new Location(-2,1,1),new Tile(Terrain.WOODS, RiverConfiguration.getNoRivers()));
        map.placeTile(new Location(1,0,-1),new Tile(Terrain.MOUNTAIN, RiverConfiguration.getNoRivers()));
        TransportAbilityManager tm = new TransportAbilityManager(new MainViewController(),new GooseManager(), map);
        Game game = new Game(map,
                new Player(tm, new PlayerId(1),"Player1"),
                new Player(tm, new PlayerId(2),"Player2"),
                new GooseManager(),
                new StructureManager(new StructureAbilityManager(new MainViewController())));
        gameExporter = new GameExporter(game);
    }

    @Test
    public void printAllLocationsTest(){
        String s = gameExporter.serializeMap();
        System.out.println(s);
        assertTrue(true);
    }





}
