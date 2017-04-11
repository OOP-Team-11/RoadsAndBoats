package game;

import game.view.ViewHandler;
import javafx.scene.Scene;

public class mmGame {

    private ViewHandler viewHandler;

    public mmGame(String gameFile, String player1Name, String player2Name, Scene scene){
        // TODO
        System.out.println("New mmGame has started");
        viewHandler = new ViewHandler(scene);
        // TODO initialize other stuff
        viewHandler.startGameLoop();
    }
}
