package view;

import controller.GameLoop;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class ViewInitializer {


    private Stage primaryStage;
    private MapMakerView mapMakerView;
    private TileSelectorView tileSelectorView;
    private GameLoop gameLoop;
    private MenuBar menuBar;
    private Scene scene;

    public ViewInitializer(Stage primaryStage){
        this.primaryStage = primaryStage;
        setUpStage();
    }

    public void setUpStage(){
        HBox root = new HBox();
        VBox root2 = new VBox();
        createMenuBar();
        Canvas mapMakerCanvas = new Canvas(1000,800);
        Canvas tileSelectorCanvas = new Canvas(400,800);
        this.mapMakerView = new MapMakerView(mapMakerCanvas);
        this.tileSelectorView = new TileSelectorView(tileSelectorCanvas);
        this.primaryStage.setTitle("Roads and Boats ");
        root2.getChildren().add(menuBar);
        root.getChildren().add(mapMakerCanvas);
        root.getChildren().add(tileSelectorCanvas);
        root2.getChildren().add(root);
        this.scene = new Scene(root2,1400 , 800);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private void createMenuBar(){
        this.menuBar = new MenuBar();
        menuBar.setMinWidth(300);
        Menu menu = new Menu();
        menu.setText("Import/Export");
        menuBar.getMenus().add(menu);
        MenuItem item = new MenuItem();
        MenuItem item2 = new MenuItem();
        item.setText("Import");
        item2.setText("Export");
        menu.getItems().add(item);
        menu.getItems().add(item2);
    }

    public TileSelectorView getTileSelectorViewReference(){
        return this.tileSelectorView;
    }
    public MapMakerView getMapMakerViewReference(){
        return this.mapMakerView;
    }
    public MenuBar getMenuBarReferense(){
        return this.menuBar;
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
