package startApplication;

import game.view.utilities.Assets;
import javafx.animation.FadeTransition;
import javafx.collections.ObservableList;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;

public class WelcomeView {

    private AnchorPane anchorPane;
    private AnchorPane overlayAnchorPane;
    private StackPane stackPane;
    private Canvas startGameCanvas;
    private Button newGameButton;
    private Button loadGameButton;
    private Button mapMakerButton;
    private Button optionsButton;
    private Button exitGameButton;
    private TextField player1NameInput;
    private TextField player2NameInput;
    private Button closeNewGameButton;
    private Button startNewGameButoon;
    private Button changeDirectoryButton;
    private ListView listview;
    private Stage primaryStage;
    private ImageView imageView;
    private Assets assets;


    public WelcomeView(AnchorPane anchorPane, StackPane stackPane, Stage primaryStage){
        this.anchorPane = anchorPane;
        this.stackPane = stackPane;
        this.primaryStage = primaryStage;
        this.assets = Assets.getInstance();

        initializeComponents();
    }

    public void refresh(){
        this.stackPane.getChildren().remove(0);
        this.stackPane.getChildren().add(this.anchorPane);
        this.primaryStage.getScene().setRoot(stackPane);
        initializeComponents();
    }

    private void initializeComponents(){

        this.newGameButton = new Button();
        this.newGameButton.setFont(new Font(20));
        this.newGameButton.setText("New Game");
        this.newGameButton.setPrefWidth(150.0);
        this.loadGameButton = new Button();
        this.loadGameButton.setText("Load Game");
        this.loadGameButton.setFont(new Font(20));
        this.loadGameButton.setPrefWidth(150.0);
        this.mapMakerButton = new Button();
        this.mapMakerButton.setText("Map Maker");
        this.mapMakerButton.setFont(new Font(20));
        this.mapMakerButton.setPrefWidth(150.0);
        this.optionsButton = new Button();
        this.optionsButton.setText("Options");
        this.optionsButton.setFont(new Font(20));
        this.optionsButton.setPrefWidth(150.0);
        this.exitGameButton = new Button();
        this.exitGameButton.setText("Exit");
        this.exitGameButton.setFont(new Font(20));
        this.exitGameButton.setPrefWidth(150.0);
        this.imageView = new ImageView();
        this.anchorPane.getChildren().add(imageView);
        this.imageView.setImage(assets.BACKGROUND);
        this.anchorPane.getChildren().add(newGameButton);
        this.anchorPane.setTopAnchor(newGameButton, 200.0);
        this.anchorPane.setLeftAnchor(newGameButton, 1100.0);
        this.anchorPane.getChildren().add(loadGameButton);
        this.anchorPane.setTopAnchor(loadGameButton, 300.0);
        this.anchorPane.setLeftAnchor(loadGameButton, 1100.0);
        this.anchorPane.getChildren().add(mapMakerButton);
        this.anchorPane.setTopAnchor(mapMakerButton, 400.0);
        this.anchorPane.setLeftAnchor(mapMakerButton, 1100.0);
        this.anchorPane.getChildren().add(optionsButton);
        this.anchorPane.setTopAnchor(optionsButton,500.0);
        this.anchorPane.setLeftAnchor(optionsButton, 1100.0);
        this.anchorPane.getChildren().add(exitGameButton);
        this.anchorPane.setTopAnchor(exitGameButton, 600.0);
        this.anchorPane.setLeftAnchor(exitGameButton, 1100.0);
        this.startGameCanvas = new Canvas(600.0, 600.0);
        this.overlayAnchorPane = new AnchorPane();
        this.closeNewGameButton = new Button();
        this.startNewGameButoon = new Button();
        this.changeDirectoryButton = new Button();
        this.listview = new ListView();
        this.player1NameInput = new TextField();
        this.player2NameInput = new TextField();
    }

    private void overlayTransitionAnimation(){
        FadeTransition ft = new FadeTransition(Duration.millis(500), this.anchorPane);
        ft.setFromValue(1.0);
        ft.setToValue(0.2);
        ft.play();
        this.overlayAnchorPane.setOpacity(0.0);
        this.stackPane.getChildren().add(overlayAnchorPane);
        FadeTransition ft2 = new FadeTransition(Duration.millis(500), this.overlayAnchorPane);
        ft2.setFromValue(0.0);
        ft2.setToValue(1.0);
        ft2.play();
    }


    public void displayLoadGameOverlay(){
        clearOverlayAnchorPane();
        setUpOverlay();
        setUpLoadGameOverlay();
        overlayTransitionAnimation();
    }

    public void displayStartGameOverlay(){
        clearOverlayAnchorPane();
        setUpOverlay();
        setUpStartGameOverlay();
        overlayTransitionAnimation();
    }

    private void setUpOverlay(){
        this.startGameCanvas.getGraphicsContext2D().setFill(Color.TEAL);
        this.startGameCanvas.getGraphicsContext2D().setLineWidth(3.0);
        this.startGameCanvas.getGraphicsContext2D().fillRoundRect(0,0,600,600,20,20);
        this.startGameCanvas.getGraphicsContext2D().setFont(new Font(20));
        this.startGameCanvas.getGraphicsContext2D().setLineWidth(1.5);
        this.startGameCanvas.getGraphicsContext2D().strokeText("Player 1 Name: ",20.0,80.0);
        this.startGameCanvas.getGraphicsContext2D().strokeText("Player 2 Name: ",20.0,125.0);
        this.startGameCanvas.getGraphicsContext2D().strokeLine(20.0,150.0,580.0,150.0);
        this.startGameCanvas.getGraphicsContext2D().strokeLine(20.0,525.0,580.0,525.0);
        this.player1NameInput.setFont(new Font(15));
        this.player1NameInput.setText("Player1");
        this.player2NameInput.setFont((new Font(15)));
        this.player2NameInput.setText("Player2");
        this.closeNewGameButton.setText("Cancel");
        this.closeNewGameButton.setFont(new Font(18));
        this.closeNewGameButton.setPrefWidth(80.0);
        this.listview.setPrefWidth(530.0);
        this.listview.setPrefHeight(350.0);
        this.changeDirectoryButton.setText("Change Directory");
        this.changeDirectoryButton.setFont(new Font(15));
        this.changeDirectoryButton.setPrefWidth(150.0);
        this.overlayAnchorPane.getChildren().add(startGameCanvas);
        this.overlayAnchorPane.setTopAnchor(startGameCanvas,100.0);
        this.overlayAnchorPane.setLeftAnchor(startGameCanvas,200.0);
        this.overlayAnchorPane.getChildren().add(player1NameInput);
        this.overlayAnchorPane.setTopAnchor(player1NameInput, 155.0);
        this.overlayAnchorPane.setLeftAnchor(player1NameInput,370.0);
        this.overlayAnchorPane.getChildren().add(player2NameInput);
        this.overlayAnchorPane.setTopAnchor(player2NameInput,205.0);
        this.overlayAnchorPane.setLeftAnchor(player2NameInput,370.0);
        this.overlayAnchorPane.getChildren().add(startNewGameButoon);
        this.overlayAnchorPane.setTopAnchor(startNewGameButoon,640.0);
        this.overlayAnchorPane.setLeftAnchor(startNewGameButoon,580.0);
        this.overlayAnchorPane.getChildren().add(closeNewGameButton);
        this.overlayAnchorPane.setTopAnchor(closeNewGameButton,640.0);
        this.overlayAnchorPane.setLeftAnchor(closeNewGameButton,690.0);
        this.overlayAnchorPane.getChildren().add(changeDirectoryButton);
        this.overlayAnchorPane.setTopAnchor(changeDirectoryButton, 640.0);
        this.overlayAnchorPane.setLeftAnchor(changeDirectoryButton, 250.0);
        this.overlayAnchorPane.getChildren().add(listview);
        this.overlayAnchorPane.setTopAnchor(listview, 265.0);
        this.overlayAnchorPane.setLeftAnchor(listview,230.0);
    }

    private void setUpStartGameOverlay(){
        this.startGameCanvas.getGraphicsContext2D().setFont(new Font(40));
        this.startGameCanvas.getGraphicsContext2D().strokeText("New Game", 220.0,40.0);
        this.startNewGameButoon.setText("Start");
        this.startNewGameButoon.setFont(new Font(18));
        this.startNewGameButoon.setPrefWidth(80.0);
    }

    private void setUpLoadGameOverlay(){
        this.startGameCanvas.getGraphicsContext2D().setFont(new Font(40));
        this.startGameCanvas.getGraphicsContext2D().strokeText("Load Game", 220.0,40.0);
        this.startNewGameButoon.setText("Resume");
        this.startNewGameButoon.setFont(new Font(18));
        this.startNewGameButoon.setPrefWidth(100.0);
    }

    public void showSaveMessage(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Saving Game");
        alert.setHeaderText("Save Status:");
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearOverlayAnchorPane(){
        int size = this.overlayAnchorPane.getChildren().size();
        for(int i=size-1; i>=0; i--){
            this.overlayAnchorPane.getChildren().remove(i);
        }
    }
    public String getPlayer1Name(){return this.player1NameInput.getText();}
    public String getPlayer2Name(){
        return  this.player2NameInput.getText();
    }

    public String getListViewSelectedItem(){
        return (String)this.listview.getSelectionModel().getSelectedItem();
    }

    public void updateListView( ObservableList<String> items){
        this.listview.setItems(items);
    }

    public void closeOverlay(){
        FadeTransition ft2 = new FadeTransition(Duration.millis(500), this.overlayAnchorPane);
        ft2.setFromValue(1.0);
        ft2.setToValue(0.0);
        ft2.play();
        ft2.setOnFinished(event -> {
            this.stackPane.getChildren().remove(1);
        });
        FadeTransition ft = new FadeTransition(Duration.millis(500), this.anchorPane);
        ft.setFromValue(0.2);
        ft.setToValue(1);
        ft.play();
    }

    public void showAlert(String title, String headerText, String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(headerText);
        alert.setContentText(message);
        alert.showAndWait();
    }



    public Button getStartGameButtonReference() { return startNewGameButoon; }
    public Button getExitButtonRefernce(){
        return exitGameButton;
    }
    public Button getNewGameButtonReference(){
        return newGameButton;
    }
    public Button getLoadGameButtonReference(){
        return loadGameButton;
    }
    public Button getMapMakerButtonReference(){
        return mapMakerButton;
    }
    public Button getOptionsButtonReference(){
        return optionsButton;
    }
    public Button getCancelStartGameReference() { return closeNewGameButton; }
    public Button getChangeDirectoryButtonReference() { return changeDirectoryButton; }



}
