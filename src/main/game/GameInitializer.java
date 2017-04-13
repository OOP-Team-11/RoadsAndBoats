package game;

import game.controller.ControllerManager;
import game.model.Game;
import game.model.Player;
import game.model.gameImporter.MapImporter;
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
    private TransportAbilityManager transportAbilityManager;

    public GameInitializer(String gameFile, String player1Name, String player2Name, Stage primaryStage){

        // TODO
        System.out.println("New Game has started");
        viewHandler = new ViewHandler(primaryStage);
        controllerManager = new ControllerManager(viewHandler);
        transportAbilityManager = new TransportAbilityManager(controllerManager.getMainViewController());
        MapImporter mapImporter = new MapImporter();
        try {
            BufferedReader br = new BufferedReader(new FileReader("map/" + gameFile));
            RBMap map = new RBMap();
            map.attach(viewHandler.getMainViewReference());
            mapImporter.importMapFromFile(map, br);
            Player player1 = new Player(transportAbilityManager);
            Player player2 = new Player(transportAbilityManager);
            Game game = new Game(map, player1, player2, transportAbilityManager);
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
