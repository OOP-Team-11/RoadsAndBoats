package game;

import game.controller.ControllerManager;
import game.view.ViewHandler;
import javafx.scene.Scene;

public class Game {

    private ViewHandler viewHandler;
    private ControllerManager controllerManager;

    public Game(String gameFile, String player1Name, String player2Name, Scene scene){
        // TODO
        System.out.println("New Game has started");
        viewHandler = new ViewHandler(scene);
        controllerManager = new ControllerManager(viewHandler);
        // TODO initialize other stuff
        viewHandler.startGameLoop();

    }
}
