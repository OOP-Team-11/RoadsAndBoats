package mapMaker;

import javafx.scene.Scene;
import mapMaker.controller.MapMakerController;
import mapMaker.model.Map;

import startApplication.WelcomeViewController;

public class MapMaker {
    // main entry point of the application
    private MapMakerController mapMakerController;
    private Map gameMap;

    public MapMaker(Scene scene, WelcomeViewController welcomeViewController){
        // initialize the mapMakerController and main MapMaker.MapMaker Map
        this.gameMap = new Map();
        this.mapMakerController = new MapMakerController(scene, gameMap, welcomeViewController);
    }
}