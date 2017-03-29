package controller;

import controller.keyControlsMapper.MapMakerKeyControlsMapper;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.MenuBar;
import javafx.scene.control.TextInputDialog;
import model.Map;
import model.tile.InvalidLocationException;
import view.MapMakerView;
import view.TileSelectorView;
import javafx.stage.Stage;
import view.ViewInitializer;

import java.util.Optional;

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
        importExportEvent();
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
            this.mapMakerView.changeZoom((int) event.getDeltaY());
        });
    }
    private void importExportEvent(){
        this.menuBar.getMenus().get(0).getItems().get(0).setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                TextInputDialog dialog = new TextInputDialog("walter");
                dialog.setTitle("Import");
                dialog.setHeaderText("Please specify the name of the file");
                dialog.setContentText("Enter the file name in the directory");

                // TODO get this hooked up
                Optional<String> result = dialog.showAndWait();
                if (result.isPresent()){
                    System.out.println(": " + result.get());
                }

            }
        });
        this.menuBar.getMenus().get(0).getItems().get(1).setOnAction(new EventHandler<ActionEvent>() {
            public void handle(ActionEvent e) {
                // TODO hook up with export

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Export");
                alert.setHeaderText("Export is succsefull");
                alert.setContentText("File saved to : TODO");

                alert.showAndWait();
            }
        });

    }
}
