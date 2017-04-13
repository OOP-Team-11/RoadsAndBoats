package game;

import game.controller.ControllerManager;
import game.view.ViewHandler;
import javafx.scene.Scene;

public class GameInitializer {

    private ViewHandler viewHandler;
    private ControllerManager controllerManager;

    public GameInitializer(String gameFile, String player1Name, String player2Name, Scene scene){
        // TODO
        System.out.println("New Game has started");
        viewHandler = new ViewHandler(scene);
        controllerManager = new ControllerManager(viewHandler);
        // TODO initialize other stuff
        viewHandler.startGameLoop();

    }
}
