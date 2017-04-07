package mapMaker.view;

import mapMaker.controller.GameLoop;
import javafx.geometry.Orientation;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

public class ViewInitializer {


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
    private Slider zoomSlider;

    public ViewInitializer(Scene scene){
        this.scene = scene;
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
        this.zoomSlider = new Slider(0,7,1);
        this.zoomSlider.setStyle("-fx-pref-height:200; -fx-control-inner-background: teal;");
        this.zoomSlider.setMajorTickUnit(100);
        this.zoomSlider.setShowTickLabels(false);
        this.zoomSlider.setShowTickMarks(false);
        this.zoomSlider.setOrientation(Orientation.VERTICAL);
        this.anchorPane.getChildren().add(mapMakerCanvas);
        this.anchorPane.setTopAnchor(mapMakerCanvas,0.0);
        this.anchorPane.setLeftAnchor(mapMakerCanvas,0.0);
        this.anchorPane.getChildren().add(zoomSlider);
        this.anchorPane.setTopAnchor(zoomSlider,550.0);
        this.anchorPane.setLeftAnchor(zoomSlider,50.0);
        this.anchorPane.getChildren().add(tileSelectorCanvas);
        this.anchorPane.setTopAnchor(tileSelectorCanvas,0.0);
        this.anchorPane.setLeftAnchor(tileSelectorCanvas,1000.0);
        this.stackPane.getChildren().add(anchorPane);
        this.scene.setRoot(stackPane);
        this.scene.getRoot().getChildrenUnmodifiable().get(0).requestFocus();
        this.exportView.initialize();
    }
    public void enlargeCanvas(){
        this.mapMakerCanvas.setHeight(1600.0);
        this.mapMakerCanvas.setWidth(2000.0);
        this.anchorPane.getChildren().removeAll(mapMakerCanvas,zoomSlider,tileSelectorCanvas);
        this.anchorPane.getChildren().add(mapMakerCanvas);
        this.anchorPane.setTopAnchor(mapMakerCanvas, 0.0);
        this.anchorPane.setLeftAnchor(mapMakerCanvas, -360.0);
        this.anchorPane.getChildren().add(zoomSlider);
        this.anchorPane.setTopAnchor(zoomSlider,950.0);
        this.anchorPane.setLeftAnchor(zoomSlider,170.0);
        this.anchorPane.getChildren().add(tileSelectorCanvas);
        this.anchorPane.setLeftAnchor(tileSelectorCanvas,1120.0);
        this.anchorPane.setTopAnchor(tileSelectorCanvas, 400.0);
    }
    public void setCanvasToNormalSize(){
        this.mapMakerCanvas.setHeight(800.0);
        this.mapMakerCanvas.setWidth(1000.0);
        this.anchorPane.getChildren().removeAll(mapMakerCanvas,zoomSlider,tileSelectorCanvas);
        this.anchorPane.getChildren().add(mapMakerCanvas);
        this.anchorPane.setTopAnchor(mapMakerCanvas,0.0);
        this.anchorPane.setLeftAnchor(mapMakerCanvas,0.0);
        this.anchorPane.getChildren().add(zoomSlider);
        this.anchorPane.setTopAnchor(zoomSlider,550.0);
        this.anchorPane.setLeftAnchor(zoomSlider,50.0);
        this.anchorPane.getChildren().add(tileSelectorCanvas);
        this.anchorPane.setTopAnchor(tileSelectorCanvas,0.0);
        this.anchorPane.setLeftAnchor(tileSelectorCanvas,1000.0);
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
    public Slider getZoomScrollBarReference() { return this.zoomSlider; }
    public Scene getSceneReferense(){
        return this.scene;
    }
    public void startAnimationLoop(){
        // initialize GameLoop that will control FPS
        this.gameLoop = new GameLoop(this.mapMakerView, this.tileSelectorView);
        // start animation timer
        this.gameLoop.startAnimationTimer();
    }

    public void stopAnimationTimer(){
        this.gameLoop.stopAnimationTimer();
    }



}
