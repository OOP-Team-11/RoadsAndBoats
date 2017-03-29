package controller;

import controller.keyControlsMapper.MapMakerKeyControlsMapper;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import model.Map;
import model.tile.InvalidLocationException;
import view.MapMakerView;
import view.TileSelectorView;
import javafx.stage.Stage;
import view.ViewInitializer;

public class MapMakerController {

    private ControlHandler controlHandler;
    private MapMakerView mapMakerView;
    private TileSelectorView tileSelectorView;
    private ViewInitializer viewInitializer;
    private MenuBar menuBar;
    private Scene scene;

    public MapMakerController(Stage primaryStage, Map gameMap) {
        initializeViews(primaryStage);
        getReferences();
        initializeControlHandler(gameMap);
        attachControlsToScene(this.controlHandler);
        attachScrollEventToScene();
        // after everything is setup, start the animation timer
        viewInitializer.startAnimationLoop();
    }

    private void initializeViews(Stage primaryStage){
        this.viewInitializer = new ViewInitializer(primaryStage);
    }

    private void getReferences(){
        this.mapMakerView = viewInitializer.getMapMakerViewReference();
        this.tileSelectorView = viewInitializer.getTileSelectorViewReference();
        this.menuBar = viewInitializer.getMenuBarReferense();
        this.scene = viewInitializer.getSceneReferense();
    }

    private void initializeControlHandler(Map gameMap) {
        // initialize controlHandler and pass in the 2 views that will be used as the observers for communication
        this.controlHandler = new ControlHandler(gameMap, mapMakerView, tileSelectorView);
    }

    private void attachControlsToScene(ControlHandler controlHandler) {
        MapMakerKeyControlsMapper mapMakerKeyControlsMapper = new MapMakerKeyControlsMapper(controlHandler);
        mapMakerKeyControlsMapper.attachToScene(this.scene);
    }

    private void attachScrollEventToScene() {
        this.scene.setOnScroll(event -> {
            this.mapMakerView.changeZoom((int) event.getDeltaY()));
        });
    }
}
