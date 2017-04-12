package game.view;

import game.model.RBMap;
import javafx.scene.Scene;

import startApplication.WelcomeViewController;

public class MapMaker {
    // main entry point of the application
//    private MapMakerController mmMapMakerController;
    private RBMap gameMmMap;

    public MapMaker(Scene scene, WelcomeViewController welcomeViewController){
        // initialize the mmMapMakerController and main mmMapMaker.mmMapMaker mmMap
        this.gameMmMap = new RBMap();
//        this.mmMapMakerController = new MapMakerController(scene, gameMmMap, welcomeViewController);
    }
}