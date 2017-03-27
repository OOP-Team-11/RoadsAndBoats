package controller;

import model.Map;
import view.MapMakerView;
import view.TileSelectorView;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;


/**
 * Created by Konrad on 3/26/2017.
 */
public class MapMakerController {

    private Stage primaryStage;
    private ControlHandler controlHandler;
    private MapMakerView mapMakerView;
    private TileSelectorView tileSelectorView;
    private GameLoop gameLoop;

    public MapMakerController(Stage primaryStage, Map gameMap){
        // main javaFX window that keylisteners and mouselisteners will be added to
        this.primaryStage = primaryStage;

        primaryStage.setTitle("Roads and Boats ");
        // initialize canvas object that each of the views will draw to
        StackPane root = new StackPane();
        Canvas mapMakerCanvas = new Canvas(1400,800);
        Canvas tileSelectorCanvas = new Canvas(300,800);
        root.getChildren().add(mapMakerCanvas);
        root.getChildren().add(tileSelectorCanvas);
        Scene scene = new Scene(root,1700 , 800);
        primaryStage.setScene(scene);
        primaryStage.show();

        // initialize both views, give each a canvas that they will use to render and draw things to
        this.mapMakerView = new MapMakerView(mapMakerCanvas);
        this.tileSelectorView = new TileSelectorView(tileSelectorCanvas);

        // initialize controlHandler and pass in the 2 views that will be used as the observers for communication
        this.controlHandler = new ControlHandler(gameMap, mapMakerView, tileSelectorView);


        // initialize GameLoop that will control FPS
        this.gameLoop = new GameLoop(mapMakerView, tileSelectorView);

        // start animation timer
        this.gameLoop.startAnimationTimer();
    }


}
