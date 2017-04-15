package game;

import game.controller.ControllerManager;
import game.model.managers.*;
import game.model.tinyGame.Game;
import game.model.Player;
import game.model.PlayerId;
import game.model.gameImportExport.GameExporter;
import game.model.gameImportExport.MapImporter;
import game.model.managers.GooseManager;
import game.model.managers.StructureAbilityManager;
import game.model.managers.StructureManager;
import game.model.managers.TransportAbilityManager;
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

        MapImporter mapImporter = new MapImporter();
        GameExporter gameExporter;
        try {
            BufferedReader br = new BufferedReader(new FileReader("map/" + gameFile));
            RBMap map = new RBMap();
            map.attach(viewHandler.getMainViewReference());
            mapImporter.importMapFromFile(map, br);
            GooseManager gooseManager = new GooseManager(new GooseAbilityManager(controllerManager.getMainViewController(), map));
            TransportAbilityManager transportAbilityManager = new TransportAbilityManager(controllerManager.getMainViewController(), gooseManager, map);
            Player player1 = new Player(transportAbilityManager, new PlayerId(1), player1Name);
            player1.attach(viewHandler.getMainViewReference());
            Player player2 = new Player(transportAbilityManager, new PlayerId(2), player2Name);
            player2.attach(viewHandler.getMainViewReference());

            gooseManager.addTransportManager(player1.getTransportManager());
            gooseManager.addTransportManager(player2.getTransportManager());

            StructureAbilityManager structureAbilityManager = new StructureAbilityManager(controllerManager.getMainViewController());
            StructureManager structureManager = new StructureManager(structureAbilityManager);
            structureManager.attach(viewHandler.getMainViewReference());

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
}
