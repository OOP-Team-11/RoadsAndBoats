package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import view.utilities.Assets;

public class MapMakerView {

    private Canvas canvas;
    private GraphicsContext gc;
    private int cameraX;
    private int cameraY;
    private int cameraZoom;

    private Assets assets;

    public MapMakerView(Canvas canvas){

        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        assets = Assets.getInstance();
        assets.loadAssets();
    }

    // public method called by GameLoop when refresh is necessary
    public void render(){
        drawDivider();
        testPastureDraw();
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

    private void testDraw(){
        Image sea = assets.getInstance().SEA;

        gc.drawImage(sea, 250, 250);
    }

    private void testPastureDraw(){
        Image pasture = assets.getInstance().PASTURE;
        Image pastureR1 = assets.getInstance().PASTURE_RIVER1;
        Image pastureR2 = assets.getInstance().PASTURE_RIVER2;
        Image pastureR3 = assets.getInstance().PASTURE_RIVER3;
        Image pastureR4 = assets.getInstance().PASTURE_RIVER4;
        Image pastureR5 = assets.getInstance().PASTURE_RIVER5;

        gc.drawImage(pasture, 0, 0);
        gc.drawImage(pastureR1,0,454);
        gc.drawImage(pastureR2, 384, 227);
        gc.drawImage(pastureR3,384,-227);
        gc.drawImage(pastureR4, 384, 681);
        gc.drawImage(pastureR5,768,0);
    }

    private void drawDivider(){
        setGraphicsContentStroke(Color.BLACK);
        setGraphicsContentFill(Color.BLACK);
        setLineWidth(5.0);
        drawLine(0,0,canvas.getWidth(),0);
    }
}
