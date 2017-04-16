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

    @Before
    public void setUp() {
        testFile = new File(filePath);
        map = new RBMap();
        mapImporter = new MapImporter();
        try{
            mapImporter.importMapFromFile(map, br);
        } catch (Exception e) {
            fail();
        }
        TransportAbilityManager tm = new TransportAbilityManager(new MainViewController(),new GooseManager(), map);
        Game game = new Game(map,
                new Player(tm, new PlayerId(1),"Player1"),
                new Player(tm, new PlayerId(2),"Player2"),
                new GooseManager(),
                new StructureManager(new StructureAbilityManager(new MainViewController())));
        GameExporter gameExporter = new GameExporter(game);
    }

    @Test
    public void printAllLocationsTest(){

    }





}
