package view;

import utilities.Observer.CursorObserver.CursorObserver;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import utilities.Observer.MapMakerObserver.MapMakerObserver;
import view.render.MapMakerCursorInfo;
import view.render.MapMakerRenderInfo;
import view.utilities.Assets;

public class MapMakerView implements CursorObserver, MapMakerObserver{

    private Canvas canvas;
    private GraphicsContext gc;
    private int cameraX;
    private int cameraY;
    private int cameraZoom;
    private Assets assets;
    private MapMakerCursorInfo cursorInformation;
    private MapMakerRenderInfo renderInformation;
    private boolean newDataFlag = false;


    private Image pasture = assets.getInstance().PASTURE;
    private Image pastureR1 = assets.getInstance().PASTURE_R1_SPRING;
    private Image pastureR2 = assets.getInstance().PASTURE_R2_ADJACENT;
    private Image pastureR3 = assets.getInstance().PASTURE_R3_SKIP;
    private Image pastureR4 = assets.getInstance().PASTURE_R4_OPPOSITE;
    private Image pastureR5 = assets.getInstance().PASTURE_R5_EVERYOTHER;



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
    private void drawImage(Image image, int x, int y, int z){
        // first thing we want to do is get the axial coordinates
        int xx = x;
        int yy = z;
        if(xx%2 == 0){ // even
            double offsetHorizontal = image.getWidth()*xx*0.25;
            double offsetVertical = (image.getHeight())*(xx/2);
            gc.drawImage(image,image.getWidth()*xx-offsetHorizontal,image.getHeight()*yy+offsetVertical); // x, y
        } else {

            double offset = image.getHeight()*0.50;
            double offsetVertical = (image.getHeight())*((xx-1)/2);
            gc.drawImage(image,image.getWidth()*xx*0.75,(image.getHeight()*((yy)) + offset +offsetVertical)); // x, y
        }

    }

    private void testPastureDraw(){

        drawImage(pastureR4,-1,0,1);
        drawImage(pastureR4,-1,-1,2);
        drawImage(pastureR2,-1,1,0);

        drawImage(pasture,0,0,0);
        drawImage(pastureR1,0,-1,1);
        //drawImage(pastureR1,0,-2,2);

        drawImage(pastureR1,1,-1,0);
        drawImage(pastureR1,1,-2,1);
        // drawImage(pastureR1,1,0,-1);

        drawImage(pasture,2,-2,0);
        drawImage(pastureR1,2,-3,1);
        // drawImage(pastureR1,2,-1,-1);

        drawImage(pastureR1,3,-2,-1);
        drawImage(pastureR1,3,-3,0);
    }

    private void drawDivider(){
        setGraphicsContentStroke(Color.BLACK);
        setGraphicsContentFill(Color.BLACK);
        setLineWidth(5.0);
        drawLine(0,0,canvas.getWidth(),0);
    }


    @Override
    public void updateCursorInfo(MapMakerCursorInfo mapMakerCursorInfo) {
        this.newDataFlag = true;
        this.cursorInformation = mapMakerCursorInfo;
    }

    @Override
    public void updateMapMaker(MapMakerRenderInfo mapMakerRenderInfo) {
        this.newDataFlag = true;
        this.renderInformation = mapMakerRenderInfo;
    }
}
