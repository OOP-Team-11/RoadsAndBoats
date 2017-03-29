package view;

import utilities.Observer.CursorObserver.CursorObserver;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import view.utilities.Assets;

public class MapMakerView implements CursorObserver{

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

    private void testPastureDraw(){
        Image t = assets.getInstance().PASTURE;
        Image t1 = assets.getInstance().PASTURE_R1_SPRING;
        Image t2 = assets.getInstance().PASTURE_R2_ADJACENT;
        Image t3 = assets.getInstance().PASTURE_R3_SKIP;
        Image t4 = assets.getInstance().PASTURE_R4_OPPOSITE;
        Image t5 = assets.getInstance().PASTURE_R5_EVERYOTHER;

        gc.drawImage(t, 0, 0);
        gc.drawImage(t1,0,114);
        gc.drawImage(t2, 192, 228);
        gc.drawImage(t3,192,-227);
        gc.drawImage(t4, 192, 681);
        gc.drawImage(t5,768,0);
    }

    private void drawDivider(){
        setGraphicsContentStroke(Color.BLACK);
        setGraphicsContentFill(Color.BLACK);
        setLineWidth(5.0);
        drawLine(0,0,canvas.getWidth(),0);
    }
}
