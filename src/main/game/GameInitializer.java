package game;

import game.controller.ControllerManager;
import game.view.ViewHandler;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class GameInitializer {

    private ViewHandler viewHandler;
    private ControllerManager controllerManager;

    public GameInitializer(String gameFile, String player1Name, String player2Name, Stage primaryStage){

        // TODO
        System.out.println("New Game has started");
        viewHandler = new ViewHandler(primaryStage);
        controllerManager = new ControllerManager(viewHandler);
        // TODO initialize other stuff
        viewHandler.startGameLoop();

    }
}
