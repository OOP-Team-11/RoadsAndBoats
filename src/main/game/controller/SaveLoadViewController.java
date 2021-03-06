package game.controller;

import game.GameInitializer;
import game.utilities.observer.PlayerRenderInfoObserver;
import game.view.SaveLoadView;
import game.view.render.PlayerRenderInfo;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

import java.io.File;


public class SaveLoadViewController implements PlayerRenderInfoObserver {

    private SaveLoadView saveLoadView;
    private File saveDirectory;
    private File loadDirectory;
    private String player1Name;
    private String player2Name;
    private Stage primaryStage;

    public SaveLoadViewController(SaveLoadView saveLoadView){
        setupDirectories();
        setupSaveLoadView(saveLoadView);
        setupLoadEventHandler();
        setupSaveEventHandler();
        setupLoadDirectoryHandler();
        setupSaveDirectoryHandler();
        setLoadList();
        setSaveList();
    }
    private void setupDirectories(){
        this.saveDirectory = new File("SavedGames");
        this.loadDirectory = new File("SavedGames");
    }

    private void setupSaveLoadView(SaveLoadView saveLoadView){
        this.saveLoadView = saveLoadView;
    }

    private void setLoadList(){
        File[] listOfFiles = loadDirectory.listFiles();
        String[] fileNames = new String[listOfFiles.length];
        for(int i=0; i<listOfFiles.length; i++){
            fileNames[i] = listOfFiles[i].getName();
        }
        if(listOfFiles.length == 0){
            fileNames = new String[1];
            fileNames[0] = "No files in Directory";
        }
        ObservableList<String> items = FXCollections.observableArrayList(fileNames);
        this.saveLoadView.updateLoadList(items);
    }
    private void setSaveList(){
        File[] listOfFiles = saveDirectory.listFiles();
        String[] fileNames = new String[listOfFiles.length];
        for(int i=0; i<listOfFiles.length; i++){
            fileNames[i] = listOfFiles[i].getName();
        }
        if(listOfFiles.length == 0){
            fileNames = new String[1];
            fileNames[0] = "No files in Directory";
        }
        ObservableList<String> items = FXCollections.observableArrayList(fileNames);
        this.saveLoadView.updateSaveList(items);
    }

    private void setupLoadEventHandler(){
            EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
                public void handle(MouseEvent e) {
                    String selectedFile = saveLoadView.getSelectedLoadFile();
                    saveLoadView.showLoadMessage(selectedFile + " has been selected");
                    try{
                        new GameInitializer(selectedFile,player1Name,player2Name,primaryStage);
                    } catch(Exception err){

                    }

                }
            };
            this.saveLoadView.addEventFilterToLoadButton(eventHandler);
    }

    private void setupSaveEventHandler(){
            EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
                public void handle(MouseEvent e) {
                    String saveName = saveLoadView.getSaveFileName();
                    saveLoadView.showSaveMessage(saveName + " is being saved TODO stuff here");
                    // TODO
                }
            };
            this.saveLoadView.addEventFilterToSaveButton(eventHandler);
    }

    private void setupSaveDirectoryHandler(){
            EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
                public void handle(MouseEvent e) {
                    File newDirectory = saveLoadView.showDirectoryChoose("Save Directory", saveDirectory);
                    if(newDirectory != null){
                        saveDirectory = newDirectory;
                    }
                }
            };
            this.saveLoadView.addEventFilterToLoadDirectory(eventHandler);
    }

    private void setupLoadDirectoryHandler(){
            EventHandler<MouseEvent> eventHandler = new EventHandler<MouseEvent>() {
                public void handle(MouseEvent e) {
                    File newDirectory = saveLoadView.showDirectoryChoose("Load Directory", loadDirectory);
                    if(newDirectory != null){
                        loadDirectory = newDirectory;
                    }
                }
            };
            this.saveLoadView.addEventFilterToSaveDirectory(eventHandler);
    }

    public void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }



    @Override
    public void updatePlayerInfo(PlayerRenderInfo playerRenderInfo) {
        if(playerRenderInfo.getPlayerID().getPlayerIdNumber() == 1){
            player1Name = playerRenderInfo.getName();
        } else {
            player2Name = playerRenderInfo.getName();
        }
    }
}
