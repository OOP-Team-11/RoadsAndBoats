package mapMaker.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.io.File;

public class ExportView {
    private Canvas canvas;
    private TextField textField;
    private AnchorPane anchorPane;
    private Button confirm;
    private Button close;
    private GraphicsContext gc;

    public ExportView(AnchorPane anchorPane, Button confirm, Button close, TextField textField){
        this.anchorPane = anchorPane;
        this.confirm = confirm;
        this.close = close;
        this.textField = textField;
    }

    public void initialize(){
        this.canvas = new Canvas(600,600);
        this.gc = canvas.getGraphicsContext2D();
        this.textField.setFont(new Font(20));
        this.confirm.setFont(new Font(20));
        this.close.setFont(new Font(20));
        this.confirm.setText("Export");
        this.close.setText("Cancel");
        this.anchorPane.getChildren().add(canvas);
        this.anchorPane.getChildren().add(confirm);
        this.anchorPane.getChildren().add(close);
        this.anchorPane.getChildren().add(textField);
        this.anchorPane.setTopAnchor(canvas, 100.0);
        this.anchorPane.setLeftAnchor(canvas, 200.0);
        this.anchorPane.setTopAnchor(confirm, 640.0);
        this.anchorPane.setLeftAnchor(confirm, 550.0);
        this.anchorPane.setTopAnchor(close,640.0);
        this.anchorPane.setLeftAnchor(close,680.0);
        this.anchorPane.setTopAnchor(textField,640.0);
        this.anchorPane.setLeftAnchor(textField,250.0);
        this.gc.setFill(Color.TEAL);
        this.gc.setLineWidth(10);
        this.gc.fillRoundRect(0,0,600,600, 30, 30);
    }
    public void displayCurrentFiles(){
        // clear previous files displayed
        this.gc.fillRoundRect(0,0,600,520,30,30);
        // get files from the folder
        File mapData = new File("map/");
        File[] listOfFiles = mapData.listFiles();
        this.gc.setFont(new Font(20));
        this.gc.setStroke(Color.BLACK);
        this.gc.setLineWidth(2);
        for (int i = 0; i < listOfFiles.length && i < 20; i++) {
            if (listOfFiles[i].isFile()) {
                this.canvas.getGraphicsContext2D().strokeText(listOfFiles[i].getName(),50, (i+1)*30);
            }
        }
    }
}
