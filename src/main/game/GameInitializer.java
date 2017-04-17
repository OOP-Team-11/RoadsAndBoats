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

    public GameInitializer(String gameFile, String player1Name, String player2Name, Stage primaryStage) throws MalformedMapFileException, IOException {

        System.out.println("New Game has started");
        viewHandler = new ViewHandler(primaryStage);
        controllerManager = new ControllerManager(viewHandler);

        GameExporter gameExporter;
        RBMap map = new RBMap();
        map.attach(viewHandler.getMainViewReference());
        map.attachMapResourceRenderInfoObserver(viewHandler.getMainViewReference());

        GooseManager gooseManager = new GooseManager(controllerManager.getMainViewController(), map);
        StructureManager structureManager = new StructureManager(controllerManager.getMainViewController(), map);
        structureManager.attach(viewHandler.getMainViewReference());

        PlayerId player1Id = new PlayerId(1);
        TileCompartmentLocation player1StartingLocation = new TileCompartmentLocation(new Location(0,0,0), TileCompartmentDirection.getNorth());
        ResearchManager p1ResearchManager = new ResearchManager(player1StartingLocation.getLocation(), player1Id);
        TransportManager player1TransportManager = new TransportManager(player1Id, controllerManager.getMainViewController(), gooseManager, map, structureManager, p1ResearchManager);
        Player player1 = new Player(player1TransportManager, new PlayerId(1), player1Name, player1StartingLocation);
        player1.attach(viewHandler.getMainViewReference());
        player1.attach(viewHandler.getTransportViewReference());

        PlayerId player2Id = new PlayerId(2);
        TileCompartmentLocation player2StartingLocation = new TileCompartmentLocation(new Location(0,1,-1), TileCompartmentDirection.getNorth());
        ResearchManager p2ResearchManager = new ResearchManager(player2StartingLocation.getLocation(), player2Id);
        TransportManager player2TransportManager = new TransportManager(player2Id, controllerManager.getMainViewController(), gooseManager, map, structureManager, p2ResearchManager);
        Player player2 = new Player(player2TransportManager, new PlayerId(2), player2Name, player2StartingLocation);
        player2.attach(viewHandler.getMainViewReference());
        player2.attach(viewHandler.getTransportViewReference());

        gooseManager.addTransportManager(player1.getTransportManager());
        gooseManager.addTransportManager(player2.getTransportManager());


        importFile(gameFile, map, structureManager);

        Game game = new Game(map, player1, player2, gooseManager, structureManager);

        game.attachPlayerInfoObserver(viewHandler.getMainViewReference());
        game.attachPlayerInfoObserver(viewHandler.getTransportViewReference());

        game.attachPhaseInfoObserver(viewHandler.getMainViewReference());

        game.attachPlayerInfoObserver(viewHandler.getResearchViewReference());
        game.attachPhaseInfoObserver(viewHandler.getResearchViewReference());
        game.attachPhaseInfoObserver(viewHandler.getMainViewReference());
        game.attachPlayerInfoObserver(viewHandler.getWonderViewReference());
        game.attachPhaseInfoObserver(viewHandler.getWonderViewReference());

        controllerManager.getMainViewController().setGame(game);

        //TODO: Add a controller and view element to trigger this gameExporter's exportGameToPath()
        gameExporter = new GameExporter(game);

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
        MapImporter.importMapFromFile(map, br);
        map.finalizeMap();
    }

    private void importGame(String filename, RBMap map, StructureManager structureManager) throws IOException, MalformedMapFileException {
        BufferedReader br = new BufferedReader(new FileReader("savedGames/" + filename));
        GameImporter.importGameFromFile(map, structureManager, br);
    }
}
