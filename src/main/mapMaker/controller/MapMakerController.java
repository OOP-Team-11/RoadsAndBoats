package mapMaker.controller;

import mapMaker.controller.keyControlsMapper.MapMakerKeyControlsMapper;
import javafx.event.EventHandler;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import mapMaker.model.Map;
import mapMaker.view.MapMakerView;
import mapMaker.view.TileSelectorView;
import mapMaker.view.ViewInitializer;
import startApplication.WelcomeViewController;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;

public class MapMakerController {

    private ControlHandler controlHandler;
    private MapMakerView mapMakerView;
    private TileSelectorView tileSelectorView;
    private ViewInitializer viewInitializer;
    private WelcomeViewController welcomeViewController;
    private Canvas mapMakerCanvas;
    private Canvas tileSelectorCanvas;
    private Scene scene;
    private Button overlayConfirm;
    private Button overlayClose;
    private TextField textField;
    private Slider zoomSlider;
    private boolean normalSizeCanvas = true;

    public MapMakerController(Scene scene, Map gameMap, WelcomeViewController welcomeViewController) {
        initializeViews(scene, welcomeViewController);
        getReferences();
        initializeControlHandler(gameMap);
        attachControlsToScene(this.controlHandler);
        MouseClickEvents();
        cursorHandler();
        buttonClickEvents();
        zoomHandler();
        // after everything is setup, start the animation timer
        viewInitializer.startAnimationLoop();
    }

    private void initializeViews(Scene scene, WelcomeViewController welcomeViewController){
        this.viewInitializer = new ViewInitializer(scene);
        this.welcomeViewController = welcomeViewController;
    }

    private void getReferences(){
        this.mapMakerView = viewInitializer.getMapMakerViewReference();
        this.tileSelectorView = viewInitializer.getTileSelectorViewReference();
        this.scene = viewInitializer.getSceneReferense();
        this.mapMakerCanvas = viewInitializer.getMapMakerCanvas();
        this.tileSelectorCanvas = viewInitializer.getTileSelectorCanvas();
        this.overlayConfirm = viewInitializer.getOverlayConfirmButtonReference();
        this.overlayClose = viewInitializer.getOverlayCloseButtonRefernce();
        this.textField = viewInitializer.getExportOverlayTextFieldRefernse();
        this.zoomSlider = viewInitializer.getZoomScrollBarReference();
    }

    private void initializeControlHandler(Map gameMap) {
        // initialize controlHandler and pass in the 2 views that will be used as the observers for communication
        this.controlHandler = new ControlHandler(gameMap, mapMakerView, tileSelectorView);
    }

    private void attachControlsToScene(ControlHandler controlHandler) {
        MapMakerKeyControlsMapper mapMakerKeyControlsMapper = new MapMakerKeyControlsMapper(controlHandler);
        mapMakerKeyControlsMapper.attachToScene(this.scene);
    }

    private void zoomHandler(){

        this.zoomSlider.setOnMouseReleased(event -> {
            if(zoomSlider.getValue() < 1){
                viewInitializer.enlargeCanvas();
                this.mapMakerView.changeZoom((int)zoomSlider.getValue());
                normalSizeCanvas = false;
                this.controlHandler.updateMouseClickInterpreter(2000,1600);
                this.controlHandler.resetCamera();
            } else {
                if(!normalSizeCanvas){
                    viewInitializer.setCanvasToNormalSize();
                    normalSizeCanvas = true;
                    this.controlHandler.updateMouseClickInterpreter(1000,800);
                    this.controlHandler.resetCamera();
                }
                this.mapMakerView.changeZoom((int)zoomSlider.getValue());
            }
            this.scene.getRoot().getChildrenUnmodifiable().get(0).requestFocus();
        });
        this.zoomSlider.setOnMouseClicked(event ->{
            if(zoomSlider.getValue() < 1){
                viewInitializer.enlargeCanvas();
                this.mapMakerView.changeZoom((int)zoomSlider.getValue());
                normalSizeCanvas = false;
                this.controlHandler.updateMouseClickInterpreter(2000,1600);
                this.controlHandler.resetCamera();
            } else {
                if(!normalSizeCanvas){
                    viewInitializer.setCanvasToNormalSize();
                    normalSizeCanvas = true;
                    this.controlHandler.updateMouseClickInterpreter(1000,800);
                    this.controlHandler.resetCamera();
                }
                this.mapMakerView.changeZoom((int)zoomSlider.getValue());
            }
            this.scene.getRoot().getChildrenUnmodifiable().get(0).requestFocus();
        });
    }

    private void buttonClickEvents(){

        // export button when overlay is clicked
        this.overlayConfirm.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                controlHandler.exportMap("map/" +textField.getText() + ".txt");
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Export");
                alert.setHeaderText("Export is successful");
                alert.setContentText("File saved to : " + textField.getText() + ".txt");
                alert.showAndWait();
                viewInitializer.closeExportOverlay();
            }
        });

        // close button to exit overlay
        this.overlayClose.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                viewInitializer.closeExportOverlay();
            }
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
                    } else if(event.getX() > 1180 && event.getX() < 1255 && event.getY() > 20 && event.getY() < 50){
                        scene.setCursor(Cursor.HAND); // Return ButtonN
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
                    viewInitializer.displayExportOverlay();
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
                } else if (event.getX() > 200 && event.getX() < 270 && event.getY() > 20 && event.getY() < 50){
                    viewInitializer.stopAnimationTimer(); // Return Button
                    welcomeViewController.returnToWelcomeView();
                }
            }
        });
    }

}
