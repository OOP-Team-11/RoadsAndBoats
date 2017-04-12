package game.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;


public class SaveLoadView extends View {

    private AnchorPane anchorPane;
    private Canvas canvas;
    private GraphicsContext gc;
    private TextField loadField;
    private TextField saveField;
    private Button loadButton;
    private Button saveButton;
    private Button saveDirectory;
    private Button loadDirectory;
    private ListView loadList;
    private ListView saveList;

    public SaveLoadView(AnchorPane anchorPane){
        setAnchorPane(anchorPane);
        setupPage();
        drawLoadOptions();
        drawSaveOption();
    }

    private void setAnchorPane(AnchorPane anchorPane){
        this.anchorPane = anchorPane;
    }
    private void setupPage(){
        this.canvas = new Canvas(1300, 800);
        this.gc = this.canvas.getGraphicsContext2D();
        this.gc.setFill(Color.TEAL);
        this.gc.fillRect(0,0,1300,800);
        this.gc.setStroke(Color.BLACK);
        this.gc.setLineWidth(3.0);
        this.gc.strokeLine(650, 100, 650, 750);
        this.anchorPane.getChildren().add(canvas);
    }
    private void drawLoadOptions(){
        this.gc.setLineWidth(2.0);
        this.gc.setFont(new Font(35));
        this.gc.strokeText("Load", 100, 150);
        this.loadField = new TextField();
        this.loadField.setFont(new Font(20));
        this.loadField.setPrefWidth(350.0);
        this.anchorPane.getChildren().add(loadField);
        this.anchorPane.setLeftAnchor(loadField, 100.0);
        this.anchorPane.setTopAnchor(loadField,700.0);
        this.loadButton = new Button();
        this.loadButton.setText("Load Game");
        this.loadButton.setFont(new Font(20));
        this.anchorPane.getChildren().add(loadButton);
        this.anchorPane.setLeftAnchor(loadButton, 500.0);
        this.anchorPane.setTopAnchor(loadButton, 700.0);
        this.loadList = new ListView();
        this.loadList.setPrefWidth(500.0);
        this.anchorPane.getChildren().add(loadList);
        this.anchorPane.setTopAnchor(loadList, 200.0);
        this.anchorPane.setLeftAnchor(loadList, 100.0);
        this.loadDirectory = new Button();
        this.loadDirectory.setText("Change Directory");
        this.loadDirectory.setFont(new Font(20));
        this.anchorPane.getChildren().add(loadDirectory);
        this.anchorPane.setTopAnchor(loadDirectory, 125.0);
        this.anchorPane.setLeftAnchor(loadDirectory, 450.0);

    }
    private void drawSaveOption(){
        this.gc.setFont(new Font(35));
        this.gc.strokeText("Save", 700, 150);
        this.saveField = new TextField();
        this.saveField.setFont(new Font(20));
        this.saveField.setPrefWidth(350.0);
        this.anchorPane.getChildren().add(saveField);
        this.anchorPane.setLeftAnchor(saveField, 700.0);
        this.anchorPane.setTopAnchor(saveField,700.0);
        this.saveButton = new Button();
        this.saveButton.setText("Save Game");
        this.saveButton.setFont(new Font(20));
        this.anchorPane.getChildren().add(saveButton);
        this.anchorPane.setLeftAnchor(saveButton, 1100.0);
        this.anchorPane.setTopAnchor(saveButton, 700.0);
        this.saveList = new ListView();
        this.saveList.setPrefWidth(500.0);
        this.anchorPane.getChildren().add(saveList);
        this.anchorPane.setTopAnchor(saveList, 200.0);
        this.anchorPane.setLeftAnchor(saveList, 700.0);
        this.saveDirectory = new Button();
        this.saveDirectory.setText("Change Directory");
        this.saveDirectory.setFont(new Font(20));
        this.anchorPane.getChildren().add(saveDirectory);
        this.anchorPane.setTopAnchor(saveDirectory, 125.0);
        this.anchorPane.setLeftAnchor(saveDirectory, 1050.0);
    }

    public void showLoadMessage(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Loading Game");
        alert.setHeaderText("Load Status:");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public void showSaveMessage(String message){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Saving Game");
        alert.setHeaderText("Save Status:");
        alert.setContentText(message);
        alert.showAndWait();
    }

    public Button getLoadButtonReference(){return this.loadButton; }
    public Button getSaveButtonReference(){return this.saveButton; }
    public Button getSaveDirectoryReference() { return this.saveDirectory;}
    public Button getLoadDirectoryReference() { return this.loadDirectory;}
    public TextField getLoadFieldReference() { return this.loadField; }
    public TextField getSaveFieldReference() { return this.saveField; }


    @Override
    public void render() {


    }
}
