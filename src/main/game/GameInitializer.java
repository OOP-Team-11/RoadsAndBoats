package game;

import game.controller.ControllerManager;
import game.model.direction.Location;
import game.model.direction.TileCompartmentDirection;
import game.model.direction.TileCompartmentLocation;
import game.model.gameImportExport.exporter.GameExporter;
import game.model.gameImportExport.importer.GameImporter;
import game.model.managers.*;
import game.model.tinyGame.Game;
import game.model.Player;
import game.model.PlayerId;
import game.model.gameImportExport.importer.MapImporter;
import game.model.map.RBMap;
import game.utilities.exceptions.MalformedMapFileException;
import game.view.ViewHandler;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class GameInitializer {

    private ViewHandler viewHandler;
    private ControllerManager controllerManager;

    public GameInitializer(String gameFile, String player1Name, String player2Name, Stage primaryStage){

        System.out.println("New Game has started");
        viewHandler = new ViewHandler(primaryStage);
        controllerManager = new ControllerManager(viewHandler);

        GameExporter gameExporter;
        try {
            RBMap map = new RBMap();
            map.attach(viewHandler.getMainViewReference());

            GooseManager gooseManager = new GooseManager(controllerManager.getMainViewController(), map);
            StructureManager structureManager = new StructureManager(controllerManager.getMainViewController(), map);
            structureManager.attach(viewHandler.getMainViewReference());

            PlayerId player1Id = new PlayerId(1);
            TransportManager player1TransportManager = new TransportManager(player1Id, controllerManager.getMainViewController(), gooseManager, map, structureManager);
            TileCompartmentLocation player1StartingLocation = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
            Player player1 = new Player(player1TransportManager, new PlayerId(1), player1Name, player1StartingLocation);
            player1.attach(viewHandler.getMainViewReference());

            PlayerId player2Id = new PlayerId(2);
            TransportManager player2TransportManager = new TransportManager(player2Id, controllerManager.getMainViewController(), gooseManager, map, structureManager);
            TileCompartmentLocation player2StartingLocation = new TileCompartmentLocation(new Location(0,1,-1), TileCompartmentDirection.getNorth());
            Player player2 = new Player(player2TransportManager, new PlayerId(2), player2Name, player2StartingLocation);
            player2.attach(viewHandler.getMainViewReference());

            gooseManager.addTransportManager(player1.getTransportManager());
            gooseManager.addTransportManager(player2.getTransportManager());


            importFile(gameFile, map, structureManager);

            Game game = new Game(map, player1, player2, gooseManager, structureManager);

            game.attachPlayerInfoObserver(viewHandler.getMainViewReference());
            game.attachPhaseInfoObserver(viewHandler.getMainViewReference());
            game.attachPlayerInfoObserver(viewHandler.getResearchViewReference());
            game.attachPhaseInfoObserver(viewHandler.getResearchViewReference());

            //TODO: Add a controller and view element to trigger this gameExporter's exportGameToPath()
            gameExporter = new GameExporter(game);
        } catch (MalformedMapFileException|IOException e) {
            System.out.println(e);
            System.exit(1);
        }

        viewHandler.startGameLoop();
    }

    private void importFile(String filename, RBMap map, StructureManager structureManager) throws IOException, MalformedMapFileException {
        if (filename.contains(".tinyrick")) {
            importGame(filename, map, structureManager);
        } else if (filename.contains(".map")) {
            importMap(filename, map);
        }
    }

    private void importMap(String filename, RBMap map) throws IOException, MalformedMapFileException {
        BufferedReader br = new BufferedReader(new FileReader("map/" + filename));
        MapImporter mapImporter = new MapImporter();
        mapImporter.importMapFromFile(map, br);
    }

    private void importGame(String filename, RBMap map, StructureManager structureManager) throws IOException, MalformedMapFileException {
        BufferedReader br = new BufferedReader(new FileReader("savedGames/" + filename));
        GameImporter gameImporter = new GameImporter();
        gameImporter.importGameFromFile(map, structureManager, br);
    }
}
