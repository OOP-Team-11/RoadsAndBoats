package startApplication;

import game.GameInitializer;
import game.view.MapMaker;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.Button;
import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class WelcomeViewController {

    private Stage primaryStage;
    private WelcomeView welcomeView;
    private Button exitButton;
    private Button newGameButton;
    private Button loadGameButton;
    private Button mapMakerButton;
    private Button optionsButton;
    private Button cancelStartGameButton;
    private Button changeDirectoryButton;
    private Button startGameButton;
    private File startGameDirectory;
    private File loadGameDirectory;
    private Boolean startOrLoad;

    public WelcomeViewController(WelcomeView welcomeView, Stage primaryStage){
        this.welcomeView = welcomeView;
        this.primaryStage = primaryStage;
        initializeFilePaths();
        getReferences();
        initializeEventHandlers();
    }

    private void initializeFilePaths(){
        this.startGameDirectory = new File("map/");
        this.loadGameDirectory = new File("SavedGames/");
    }

    private void getReferences(){
        this.exitButton = welcomeView.getExitButtonRefernce();
        this.newGameButton = welcomeView.getNewGameButtonReference();
        this.loadGameButton = welcomeView.getLoadGameButtonReference();
        this.mapMakerButton = welcomeView.getMapMakerButtonReference();
        this.optionsButton = welcomeView.getOptionsButtonReference();
        this.cancelStartGameButton = welcomeView.getCancelStartGameReference();
        this.changeDirectoryButton = welcomeView.getChangeDirectoryButtonReference();
        this.startGameButton = welcomeView.getStartGameButtonReference();
    }

    public void returnToWelcomeView(){
        this.welcomeView.refresh();
        this.getReferences();
        this.initializeEventHandlers();
    }

    private void updateNewGameDirectoryContents(){
        File[] listOfFiles = startGameDirectory.listFiles();
        String[] fileNames = new String[listOfFiles.length];
        for(int i=0; i<listOfFiles.length; i++){
            fileNames[i] = listOfFiles[i].getName();
        }
        if(listOfFiles.length == 0){
            fileNames = new String[1];
            fileNames[0] = "No files in Directory";
        }
        ObservableList<String> items = FXCollections.observableArrayList(fileNames);
        this.welcomeView.updateListView(items);
    }

    private void updateLoadGameDirectoryContents(){
        File[] listOfFiles = loadGameDirectory.listFiles();
        String[] fileNames = new String[listOfFiles.length];
        for(int i=0; i<listOfFiles.length; i++){
            fileNames[i] = listOfFiles[i].getName();
        }
        if(listOfFiles.length == 0){
            fileNames = new String[1];
            fileNames[0] = "No files in Directory";
        }

        ObservableList<String> items = FXCollections.observableArrayList(fileNames);
        this.welcomeView.updateListView(items);
    }
    private void setLoad(){
        this.startOrLoad = false;
    }
    private void setNewGame(){
        this.startOrLoad = true;
    }


    private void initializeEventHandlers(){

        // start game
        this.newGameButton.setOnMouseClicked(event -> {
             setNewGame();
             updateNewGameDirectoryContents();
             welcomeView.displayStartGameOverlay();
        });

        // load existing game
        this.loadGameButton.setOnMouseClicked(event -> {
            setLoad();
            updateLoadGameDirectoryContents();
            welcomeView.displayLoadGameOverlay();
        });

        // jump to mmMapMaker
        this.mapMakerButton.setOnMouseClicked(event ->{
            new MapMaker(primaryStage.getScene(), this);
        });

        // options page
        this.optionsButton.setOnMouseClicked(event ->{
            // TODO maybe if there is time
        });

        // exit game
        this.exitButton.setOnMouseClicked(event -> {
            System.exit(0);
        });

        // start game once a file has been chosen
        this.startGameButton.setOnMouseClicked(event -> {
            String gameFile = welcomeView.getListViewSelectedItem();
            String player1Name = welcomeView.getPlayer1Name();
            String player2Name = welcomeView.getPlayer2Name();
            if(gameFile != null){
                new GameInitializer(gameFile,player1Name ,player2Name ,primaryStage.getScene());
            } else {
                // no file has been selected
            }
        });

        // Cancel button for StartGame
        this.cancelStartGameButton.setOnMouseClicked(event -> {
            welcomeView.closeOverlay();
        });

        // change directory
        this.changeDirectoryButton.setOnMouseClicked(event -> {
            DirectoryChooser directoryChooser = new DirectoryChooser();
            if(startOrLoad){
                directoryChooser.setTitle("Choose Map");
                directoryChooser.setInitialDirectory(startGameDirectory);
                startGameDirectory = directoryChooser.showDialog(primaryStage);
                updateNewGameDirectoryContents();
            } else {
                directoryChooser.setTitle("Choose Saved GameInitializer");
                directoryChooser.setInitialDirectory(loadGameDirectory);
                loadGameDirectory = directoryChooser.showDialog(primaryStage);
                updateLoadGameDirectoryContents();
            }
        });


    }
}
