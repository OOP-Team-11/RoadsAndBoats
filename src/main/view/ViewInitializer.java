package view;

import controller.GameLoop;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewInitializer {


    private Stage primaryStage;
    private MapMakerView mapMakerView;
    private TileSelectorView tileSelectorView;
    private GameLoop gameLoop;
    private MenuBar menuBar;
    private Scene scene;
    private StackPane stackPane;
    private AnchorPane anchorPane;
    private Canvas mapMakerCanvas;
    private Canvas tileSelectorCanvas;

    public ViewInitializer(Stage primaryStage){
        this.primaryStage = primaryStage;
        setUpStage();
    }

    public void setUpStage(){
        stackPane = new StackPane();
        anchorPane = new AnchorPane();
        HBox root = new HBox();
        VBox root2 = new VBox();
        mapMakerCanvas = new Canvas(1000,800);
        tileSelectorCanvas = new Canvas(400,800);
        this.mapMakerView = new MapMakerView(mapMakerCanvas);
        this.tileSelectorView = new TileSelectorView(tileSelectorCanvas);
        this.primaryStage.setTitle("Roads and Boats ");
        anchorPane.getChildren().add(mapMakerCanvas);
        anchorPane.setTopAnchor(mapMakerCanvas,0.0);
        anchorPane.setLeftAnchor(mapMakerCanvas,0.0);
        anchorPane.getChildren().add(tileSelectorCanvas);
        anchorPane.setTopAnchor(tileSelectorCanvas,0.0);
        anchorPane.setLeftAnchor(tileSelectorCanvas,1000.0);
        stackPane.getChildren().add(anchorPane);
        this.scene = new Scene(stackPane,1400 , 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }


    public Canvas getMapMakerCanvas() { return  mapMakerCanvas; }
    public Canvas getTileSelectorCanvas() { return tileSelectorCanvas; }
    public TileSelectorView getTileSelectorViewReference(){
        return this.tileSelectorView;
    }
    public MapMakerView getMapMakerViewReference(){
        return this.mapMakerView;
    }
    public Scene getSceneReferense(){
        return this.scene;
    }
    public void startAnimationLoop(){
        // initialize GameLoop that will control FPS
        this.gameLoop = new GameLoop(mapMakerView, tileSelectorView);
        // start animation timer
        this.gameLoop.startAnimationTimer();
    }



}
