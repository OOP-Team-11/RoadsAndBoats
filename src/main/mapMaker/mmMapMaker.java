package mapMaker;

import javafx.scene.Scene;
import mapMaker.controller.mmMapMakerController;
import mapMaker.model.mmMap;

import startApplication.WelcomeViewController;

public class mmMapMaker {
    // main entry point of the application
    private mmMapMakerController mmMapMakerController;
    private mmMap gameMmMap;

    public mmMapMaker(Scene scene, WelcomeViewController welcomeViewController){
        // initialize the mmMapMakerController and main mmMapMaker.mmMapMaker mmMap
        this.gameMmMap = new mmMap();
        this.mmMapMakerController = new mmMapMakerController(scene, gameMmMap, welcomeViewController);
    }
}