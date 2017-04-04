package controller;

import controller.keyControlsMapper.MapMakerKeyControlsMapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextInputDialog;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import model.FileImporter;
import model.Map;
import model.tile.InvalidLocationException;
import model.tile.Location;
import view.MapMakerView;
import view.TileSelectorView;
import javafx.stage.Stage;
import view.ViewInitializer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

public class MapMakerController {

    private ControlHandler controlHandler;
    private MapMakerView mapMakerView;
    private TileSelectorView tileSelectorView;
    private ViewInitializer viewInitializer;
    private Canvas mapMakerCanvas;
    private Canvas tileSelectorCanvas;
    private Scene scene;

    public MapMakerController(Stage primaryStage, Map gameMap) {
        initializeViews(primaryStage);
        getReferences();
        initializeControlHandler(gameMap);
        attachControlsToScene(this.controlHandler);
        attachScrollEventToScene();
        MouseClickEvents();
        cursorHandler();
        // after everything is setup, start the animation timer
        viewInitializer.startAnimationLoop();
    }

    private void initializeViews(Stage primaryStage){
        this.viewInitializer = new ViewInitializer(primaryStage);
    }

    private void getReferences(){
        this.mapMakerView = viewInitializer.getMapMakerViewReference();
        this.tileSelectorView = viewInitializer.getTileSelectorViewReference();
        this.scene = viewInitializer.getSceneReferense();
        this.mapMakerCanvas = viewInitializer.getMapMakerCanvas();
        this.tileSelectorCanvas = viewInitializer.getTileSelectorCanvas();
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
            this.mapMakerView.changeZoom((int) event.getDeltaY());
        });
    }

    private void cursorHandler(){

        this.scene.setOnMouseMoved(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                if(event.getX() < 980){
                    scene.setCursor(Cursor.HAND);
                } else  {
                    if(event.getX() > 1020 && event.getX() < 1084 && event.getY() > 20 && event.getY() < 50){
                        scene.setCursor(Cursor.HAND); // Import
                    } else if (event.getX() > 1100 && event.getX() < 1175 && event.getY() > 20 && event.getY() < 50){
                        scene.setCursor(Cursor.HAND); // Export
                    } else if (event.getX() > 1020 && event.getX() < 1300 && event.getY() > 250 && event.getY() < 400 ){
                        scene.setCursor(Cursor.HAND); // Middle Tile
                    } else if (event.getX() > 1020 && event.getX() < 1300 && event.getY() > 100 && event.getY() < 200 ){
                        scene.setCursor(Cursor.HAND); // Upper Tile
                    } else if (event.getX() > 1020 && event.getX() < 1300 && event.getY() > 450 && event.getY() < 570 ){
                        scene.setCursor(Cursor.HAND); // Lower Tile
                    } else if (event.getX() > 1018 && event.getX() < 1055 && event.getY() > 700 && event.getY() < 735 ){
                        scene.setCursor(Cursor.HAND); // Terrain 1
                    } else if (event.getX() > 1070 && event.getX() < 1107 && event.getY() > 700 && event.getY() < 735 ){
                        scene.setCursor(Cursor.HAND); // Terrain 2
                    } else if (event.getX() > 1120 && event.getX() < 1158 && event.getY() > 700 && event.getY() < 735 ){
                        scene.setCursor(Cursor.HAND); // Terrain 3
                    } else if (event.getX() > 1168 && event.getX() < 1208 && event.getY() > 700 && event.getY() < 735 ){
                        scene.setCursor(Cursor.HAND); // Terrain 4
                    } else if (event.getX() > 1218 && event.getX() < 1256 && event.getY() > 700 && event.getY() < 735 ){
                        scene.setCursor(Cursor.HAND); // Terrain 5
                    } else if (event.getX() > 1270 && event.getX() < 1307 && event.getY() > 700 && event.getY() < 735 ){
                        scene.setCursor(Cursor.HAND); // Terrain 6
                    } else if (event.getX() > 1110 && event.getX() < 1215 && event.getY() > 650 && event.getY() < 692 ){
                        scene.setCursor(Cursor.HAND); // bottom 3 arrows
                    } else if (event.getX() > 1145 && event.getX() < 1180 && event.getY() > 620 && event.getY() < 650 ){
                        scene.setCursor(Cursor.HAND); // top arrow
                    }
                    else {
                        scene.setCursor(Cursor.DEFAULT);
                    }
                }
            }
        });
    }
    private void MouseClickEvents(){

        this.mapMakerCanvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            public void handle(MouseEvent event) {
                controlHandler.cursorClicked(event.getX(), event.getY());
            }
        });

        this.tileSelectorCanvas.setOnMouseClicked(new EventHandler<MouseEvent>() {

            public void handle(MouseEvent event) {
                if(event.getX() > 35 && event.getX() < 105 && event.getY() > 20 && event.getY() < 50){
                    final FileChooser fileChooser = new FileChooser();
                    fileChooser.setTitle("Import map data");
                    Path currentRelativePath = Paths.get("");
                    String directory = currentRelativePath.toAbsolutePath().toString();
                    fileChooser.setInitialDirectory(new File(directory+ "/map"));
                    File file = fileChooser.showOpenDialog(scene.getWindow());
                    try {
                        controlHandler.importMap(file);
                    } catch( Exception e2){
                    }
                }
                else if(event.getX() > 120 && event.getX() < 190 && event.getY() > 20 && event.getY() < 50){
                    controlHandler.exportMap("map/exportedMap.txt");
                    Alert alert = new Alert(Alert.AlertType.INFORMATION);
                    alert.setTitle("Export");
                    alert.setHeaderText("Export is successful");
                    alert.setContentText("File saved to : exportedMap.txt");
                    alert.showAndWait();
                }
                else if(event.getX() > 35 && event.getX() < 300 && event.getY() > 250 && event.getY() < 400 ){
                    controlHandler.tryPlaceTile();
                }
                else if(event.getX() > 35 && event.getX() < 300 && event.getY() > 100 && event.getY() < 200 ){
                    controlHandler.previousRiverConfiguration();
                }
                else if(event.getX() > 35 && event.getX() < 300 && event.getY() > 450 && event.getY() < 570 ){
                    controlHandler.nextRiverConfiguration();
                } else if (event.getX() > 35 && event.getX() < 72 && event.getY() > 700 && event.getY() < 735   ){
                    controlHandler.setSeaTerrain(); // Terrain 1
                } else if (event.getX() > 90 && event.getX()  < 125 && event.getY() > 700 && event.getY() < 735   ){
                    controlHandler.setPastureTerrain(); // Terrain 2
                } else if (event.getX() > 145 && event.getX() < 177 && event.getY() > 700 && event.getY() < 735   ){
                    controlHandler.setWoodsTerrain(); // Terrain 3
                } else if (event.getX() > 190 && event.getX() < 227 && event.getY() > 700 && event.getY() < 735   ){
                    controlHandler.setRockyTerrain();  // Terrain 4
                } else if (event.getX() > 240 && event.getX() < 277 && event.getY() > 700 && event.getY() < 735   ){
                    controlHandler.setDesertTerrain(); // Terrain 5
                } else if (event.getX() > 290 && event.getX() < 327 && event.getY() > 700 && event.getY() < 735   ){
                    controlHandler.setMountainTerrain(); // Terrain 6
                } else if (event.getX() > 130 && event.getX() < 168 && event.getY() > 650 && event.getY() < 690   ){
                    controlHandler.rotateTileCounterClockwise();
                } else if (event.getX() > 170 && event.getX() < 207 && event.getY() > 650 && event.getY() < 690   ){
                    controlHandler.previousRiverConfiguration();
                } else if (event.getX() > 170 && event.getX() < 207 && event.getY() > 625 && event.getY() < 650   ){
                    controlHandler.nextRiverConfiguration();
                } else if (event.getX() > 210 && event.getX() < 240 && event.getY() > 650 && event.getY() < 690   ){
                    controlHandler.rotateTileClockwise();
                }
            }
        });
    }

}
