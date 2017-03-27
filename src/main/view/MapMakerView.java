package view;

import util.Observer.CursorObserver.CursorObserver;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
public class MapMakerView implements CursorObserver{
    private Canvas canvas;
    private GraphicsContext gc;
    private int cameraX;
    private int cameraY;
    private int cameraZoom;

    public MapMakerView(Canvas canvas){

        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
    }

    // public method called by GameLoop when refresh is necessary
    public void render(){
        drawDivider();
    }

    // camera offset function to pan/scroll placement area
    public void moveCamera(int moveX, int moveY){
        this.cameraX += moveX;
        this.cameraY += moveY;
    }

    // camera zoom function to move in/out of placement area
    public void changeZoom(int moveZ){
        this.cameraZoom += moveZ;
    }
    private void setGraphicsContentStroke(Paint p){
        this.gc.setStroke(p);
    }
    private void setGraphicsContentFill(Paint p){
        this.gc.setFill(p);
    }
    private void setLineWidth(double width){
        this.gc.setLineWidth(width);
    }
    private void drawLine(double x1, double y1, double x2, double y2){
        this.gc.strokeLine(x1,y1,x2,y2);
    }

    private void drawDivider(){
        setGraphicsContentStroke(Color.BLACK);
        setGraphicsContentFill(Color.BLACK);
        setLineWidth(5.0);
        drawLine(0,0,canvas.getWidth(),0);
    }
}
