package game.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class OptionsView extends View{

    private AnchorPane anchorPane;
    private Canvas canvas;
    private GraphicsContext gc;

    public OptionsView(AnchorPane anchorPane){
        setAnchorPane(anchorPane);
        setUpCanvas();
        setCanvasBackground();
        drawHeadingTitle();
        drawProcessChart();
    }
    private void setAnchorPane(AnchorPane anchorPane){
        this.anchorPane = anchorPane;
    }

    private void setUpCanvas(){
        this.canvas = new Canvas(1300, 800);
        this.gc = this.canvas.getGraphicsContext2D();
        this.anchorPane.getChildren().add(this.canvas);
    }

    private void setCanvasBackground(){
        this.gc.setFill(Color.LIGHTGREY);
        this.gc.fillRect(0,0,1300,800);
    }

    private void drawHeadingTitle(){
        this.gc.setStroke(Color.BLACK);
        this.gc.setLineWidth(3.0);
        this.gc.setFont(new Font(80));
        this.gc.strokeText("Process Chart", 400.0, 70.0);
    }

    private void drawProcessChart() {
        this.gc.drawImage(assets.PROCESS_CHART, 50, 100, 1200, 680);
    }

    @Override
    public void render() {

    }
}
