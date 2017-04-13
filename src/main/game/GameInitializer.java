package game;

import game.controller.ControllerManager;
import game.model.Game;
import game.model.Player;
import game.model.gameImporter.MapImporter;
import game.model.managers.AbilityManager;
import game.model.map.RBMap;
import game.utilities.exceptions.MalformedMapFileException;
import game.view.ViewHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class GameInitializer {

    private ViewHandler viewHandler;
    private ControllerManager controllerManager;
    private AbilityManager abilityManager;

    public GameInitializer(String gameFile, String player1Name, String player2Name, Stage primaryStage){

        // TODO
        System.out.println("New Game has started");
        viewHandler = new ViewHandler(primaryStage);
        controllerManager = new ControllerManager(viewHandler);
        abilityManager = new AbilityManager(controllerManager.getMainViewController());
        MapImporter mapImporter = new MapImporter();
        try {
            BufferedReader br = new BufferedReader(new FileReader("map/" + gameFile));
            RBMap map = new RBMap();
            map.attach(viewHandler.getMainViewReference());
            mapImporter.importMapFromFile(map, br);
            Player player1 = new Player(abilityManager);
            Player player2 = new Player(abilityManager);
            Game game = new Game(map, player1, player2, abilityManager);
        } catch (MalformedMapFileException e) {
            //TODO handle malformed part
            System.out.println(e);
        } catch (IOException e) {
            // TODO handle io exception
            System.out.println(e);
        }

        // TODO initialize other stuff
        viewHandler.startGameLoop();

    }
}
