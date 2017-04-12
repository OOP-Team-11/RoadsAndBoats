package game;

import game.view.ViewHandler;
import javafx.scene.Scene;

public class Game {

    private ViewHandler viewHandler;

    public Game(String gameFile, String player1Name, String player2Name, Scene scene){
        // TODO
        System.out.println("New Game has started");
        viewHandler = new ViewHandler(scene);
        // TODO initialize other stuff
        viewHandler.startGameLoop();
    }
}
