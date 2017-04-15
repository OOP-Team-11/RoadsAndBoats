package game;

import game.controller.ControllerManager;
import game.model.game.Game;
import game.model.Player;
import game.model.PlayerId;
import game.model.factory.AbilityFactory;
import game.model.gameImporter.MapImporter;
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
    private GooseManager gooseManager;
    private AbilityFactory abilityFactory;

    public GameInitializer(String gameFile, String player1Name, String player2Name, Stage primaryStage){

        // TODO
        System.out.println("New Game has started");
        viewHandler = new ViewHandler(primaryStage);
        controllerManager = new ControllerManager(viewHandler);
        GooseManager gooseManager = new GooseManager();
        TransportAbilityManager transportAbilityManager = new TransportAbilityManager(controllerManager.getMainViewController(), gooseManager);
        MapImporter mapImporter = new MapImporter();
        try {
            BufferedReader br = new BufferedReader(new FileReader("map/" + gameFile));
            RBMap map = new RBMap();
            transportAbilityManager.setMap(map);
            map.attach(viewHandler.getMainViewReference());
            mapImporter.importMapFromFile(map, br);

            Player player1 = new Player(transportAbilityManager, new PlayerId(1), player1Name);
            Player player2 = new Player(transportAbilityManager, new PlayerId(2), player2Name);

            StructureAbilityManager structureAbilityManager = new StructureAbilityManager(controllerManager.getMainViewController());
            StructureManager structureManager = new StructureManager(structureAbilityManager);
            structureManager.attach(viewHandler.getMainViewReference());

            Game game = new Game(map, player1, player2, gooseManager, structureManager);
            game.attachPlayerInfoObserver(viewHandler.getMainViewReference());
            game.attachPhaseInfoObserver(viewHandler.getMainViewReference());
            game.attachPlayerInfoObserver(viewHandler.getResearchViewReference());
            game.attachPhaseInfoObserver(viewHandler.getResearchViewReference());
        } catch (MalformedMapFileException|IOException e) {
            System.out.println(e);
            System.exit(1);
        }

        viewHandler.startGameLoop();
    }
}
