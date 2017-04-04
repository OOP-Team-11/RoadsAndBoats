package view;

import controller.GameLoop;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewInitializer {


    private Stage primaryStage;
    private MapMakerView mapMakerView;
    private TileSelectorView tileSelectorView;
    private ExportView exportView;
    private GameLoop gameLoop;
    private Scene scene;
    private StackPane stackPane;
    private AnchorPane anchorPane;
    private AnchorPane anchorPane2;
    private Canvas mapMakerCanvas;
    private Canvas tileSelectorCanvas;
    private Button overLayConfirm;
    private Button exportClose;
    private TextField textField;

    public ViewInitializer(Stage primaryStage){
        this.primaryStage = primaryStage;
        setUpStage();
    }

    public void setUpStage(){
        this.stackPane = new StackPane();
        this.anchorPane = new AnchorPane();
        this.anchorPane2 = new AnchorPane();
        this.mapMakerCanvas = new Canvas(1000,800);
        this.tileSelectorCanvas = new Canvas(400,800);
        this.overLayConfirm = new Button();
        this.exportClose =  new Button();
        this.textField = new TextField();
        this.exportView = new ExportView(anchorPane2, overLayConfirm, exportClose, textField);
        this.mapMakerView = new MapMakerView(mapMakerCanvas);
        this.tileSelectorView = new TileSelectorView(tileSelectorCanvas);
        this.primaryStage.setTitle("Roads and Boats ");
        this.anchorPane.getChildren().add(mapMakerCanvas);
        this.anchorPane.setTopAnchor(mapMakerCanvas,0.0);
        this.anchorPane.setLeftAnchor(mapMakerCanvas,0.0);
        this.anchorPane.getChildren().add(tileSelectorCanvas);
        this.anchorPane.setTopAnchor(tileSelectorCanvas,0.0);
        this.anchorPane.setLeftAnchor(tileSelectorCanvas,1000.0);
        this.stackPane.getChildren().add(anchorPane);
        this.scene = new Scene(stackPane,1400 , 800);
        this.exportView.initialize();
        this.primaryStage.setScene(scene);
        this.primaryStage.show();
    }
    public void displayExportOverlay(){
        this.stackPane.getChildren().add(anchorPane2);
        this.exportView.displayCurrentFiles();
    }
    public void closeExportOverlay(){
        if(this.stackPane.getChildren().size() > 1){
            this.stackPane.getChildren().remove(1);
        }
    }
    public Canvas getMapMakerCanvas() { return  this.mapMakerCanvas; }
    public Canvas getTileSelectorCanvas() { return this.tileSelectorCanvas; }
    public TileSelectorView getTileSelectorViewReference(){
        return this.tileSelectorView;
    }
    public MapMakerView getMapMakerViewReference(){
        return this.mapMakerView;
    }
    public TextField getExportOverlayTextFieldRefernse() { return this.textField; }
    public Button getOverlayCloseButtonRefernce() { return this.exportClose; }
    public Button getOverlayConfirmButtonReference() { return this.overLayConfirm; }
    public Scene getSceneReferense(){
        return this.scene;
    }
    public void startAnimationLoop(){
        // initialize GameLoop that will control FPS
        this.gameLoop = new GameLoop(this.mapMakerView, this.tileSelectorView);
        // start animation timer
        this.gameLoop.startAnimationTimer();
    }



}
