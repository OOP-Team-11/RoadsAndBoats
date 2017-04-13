package game.view.utilities;

import javafx.stage.DirectoryChooser;
import javafx.stage.Stage;

import java.io.File;

public class DirectoryPicker {

    private Stage primaryStage;
    private DirectoryChooser directoryChooser;

    public DirectoryPicker(Stage primaryStage){
        setPrimaryStage(primaryStage);
        setupDirectoryChooser();
    }
    private void setupDirectoryChooser(){
        directoryChooser = new DirectoryChooser();
    }
    private void setPrimaryStage(Stage primaryStage){
        this.primaryStage = primaryStage;
    }

    public void setDirectory(File file){
        directoryChooser.setInitialDirectory(file);
    }
    public void setTitle(String title){
        directoryChooser.setTitle(title);
    }
    public File launchDirectoryChooser(){
        return directoryChooser.showDialog(primaryStage);
    }
}
