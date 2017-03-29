package view;

import model.tile.Location;
import model.tile.Terrain;
import model.tile.Tile;
import model.tile.riverConfiguration.RiverConfiguration;
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

import java.util.Map;

public class MapMakerView implements CursorObserver, MapMakerObserver{

    private Canvas canvas;
    private GraphicsContext gc;
    private int cameraX;
    private int cameraY;
    private int cameraZoom;
    private Assets assets;
    private MapMakerCursorInfo cursorInformation;
    private MapMakerRenderInfo renderInformation;
    private boolean newDataFlag = true;


    private Image pasture = assets.getInstance().PASTURE;
    private Image pastureR1 = assets.getInstance().PASTURE_R1_SPRING;
    private Image pastureR2 = assets.getInstance().PASTURE_R2_ADJACENT;
    private Image pastureR3 = assets.getInstance().PASTURE_R3_SKIP;
    private Image pastureR4 = assets.getInstance().PASTURE_R4_OPPOSITE;
    private Image pastureR5 = assets.getInstance().PASTURE_R5_EVERYOTHER;
    private Image cursorGreen = assets.getInstance().GREEN_CURSOR;
    private Image cursorRed = assets.getInstance().RED_CURSOR;



    public MapMakerView(Canvas canvas){

        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        assets = Assets.getInstance();
        assets.loadAssets();
    }

    // public method called by GameLoop when refresh is necessary
    public void render(){
        if(newDataFlag){
            checkClearCanvas();
           // drawDivider();
            drawMap();
            drawCursor();
            resetFlag();
        } else {
            // nothing to redraw
        }
    }

    private void resetFlag(){
        this.newDataFlag = false;
    }


    // camera zoom function to move in/out of placement area
    public void changeZoom(int moveZ){
        System.out.println(this.cameraZoom);
            if(moveZ > 0) {
                    zoomIN();
            } else {
                    zoomOUT();
            }
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
    private void drawCursor(){

        if(cursorInformation.getIsCursorValid()){
           drawImage(cursorGreen,cursorInformation.getCursorLocation().getX(), cursorInformation.getCursorLocation().getY(), cursorInformation.getCursorLocation().getZ());
        } else {
           drawImage(cursorRed,cursorInformation.getCursorLocation().getX(), cursorInformation.getCursorLocation().getY(), cursorInformation.getCursorLocation().getZ());
        }
    }

    private void drawImage(Image image, int x, int y, int z){
        // first thing we want to do is get the axial coordinates
        int xx = x;
        int yy = z;
        if(xx%2 == 0){ // even
            double offsetHorizontal = image.getWidth()*xx*0.25;
            double offsetVertical = (image.getHeight())*(xx/2);
            gc.drawImage(image,image.getWidth()*xx-offsetHorizontal+cameraX,image.getHeight()*yy+offsetVertical+cameraY); // x, y
        } else {

            double offset = image.getHeight()*0.50;
            double offsetVertical = (image.getHeight())*((xx-1)/2);
            gc.drawImage(image,image.getWidth()*xx*0.75+cameraX,(image.getHeight()*((yy)) + offset +offsetVertical + cameraY)); // x, y
        }
    }

    private void checkClearCanvas(){
        if(newDataFlag){
            // clear all old information
            gc.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        } else {
            // nothing to clear
        }
    }


    private void drawMap(){

        if(renderInformation == null){
            // no information yet
        } else {
            // first time around we just render the terrain
            for (Map.Entry<Location, Tile> entry : renderInformation.getTileInformation().entrySet())
            {
                Image image = getTerrainImage(entry.getValue());
                drawImage(image, entry.getKey().getX(), entry.getKey().getY(), entry.getKey().getZ());
            }
            // second time around we draw the rivers
            for (Map.Entry<Location, Tile> entry : renderInformation.getTileInformation().entrySet())
            {
                Image image = getRiverImage(entry.getValue());
                if(image != null){
                    drawImage(image, entry.getKey().getX(), entry.getKey().getY(), entry.getKey().getZ());
                }
            }
        }
    }

    private Image getTerrainImage(Tile tile){
        if(tile.getTerrain().equals(Terrain.SEA)){
            return assets.SEA;
        } else if (tile.getTerrain().equals(Terrain.DESERT)){
            return assets.DESERT;
        } else if(tile.getTerrain().equals(Terrain.MOUNTAIN)){
            return assets.MOUNTAIN;
        } else if(tile.getTerrain().equals(Terrain.PASTURE)){
            return assets.PASTURE;
        } else if(tile.getTerrain().equals(Terrain.ROCK)){
            return assets.ROCK;
        } else {
            return assets.WOODS;
        }
    }

    private void drawDivider(){
        setGraphicsContentStroke(Color.BLACK);
        setGraphicsContentFill(Color.BLACK);
        setLineWidth(5.0);
        drawLine(0,0,canvas.getWidth(),0);
    }

    private void zoomIN(){
        canvas.setScaleX(canvas.getScaleX()+0.01);
        canvas.setScaleY(canvas.getScaleY()+0.01);
    }
    private void zoomOUT(){
        canvas.setScaleX(canvas.getScaleX()-0.01);
        canvas.setScaleY(canvas.getScaleY()-0.01);
    }

    @Override
    public void updateCursorInfo(MapMakerCursorInfo mapMakerCursorInfo) {
        this.newDataFlag = true;
        this.cursorInformation = mapMakerCursorInfo;
        this.cameraX = mapMakerCursorInfo.getCameraX()*10;
        this.cameraY = mapMakerCursorInfo.getCameraY()*10;

    }

    @Override
    public void updateMapMaker(MapMakerRenderInfo mapMakerRenderInfo) {
        this.newDataFlag = true;
        this.renderInformation = mapMakerRenderInfo;
    }

    private Image getRiverImage(Tile tile) {

        RiverConfiguration riverConfig = tile.getRiverConfiguration();
        /*
        System.out.println("NORTH: "+riverConfig.canConnectNorth());
        System.out.println("NE: "+riverConfig.canConnectNortheast());
        System.out.println("SE: "+riverConfig.canConnectSoutheast());
        System.out.println("S: "+riverConfig.canConnectSouth());
        System.out.println("SW: "+riverConfig.canConnectSouthwest());
        System.out.println("MW: "+riverConfig.canConnectNorthwest());
    */

        if(riverConfig.canConnectNorth()){
            return assets.getInstance().RIVER1_R0;
        } else if (riverConfig.canConnectNortheast()){
            return assets.getInstance().RIVER1_R1;
        } else if(riverConfig.canConnectSoutheast()){
            return assets.getInstance().RIVER1_R2;
        } else if(riverConfig.canConnectSouth()){
            return  assets.getInstance().RIVER1_R3;
        } else if(riverConfig.canConnectSouthwest()){
            return assets.getInstance().RIVER1_R4;
        } else if(riverConfig.canConnectNorthwest()){
            return assets.getInstance().RIVER1_R5;
        } else {
            return null;
        }
        /*

        if(riverConfig.canConnectNorth()) {
            if(riverConfig.canConnectNortheast()) {
                switch(tile.getTerrain()) {
                    case DESERT:
                        return assets.DESERT_R2_ADJACENT;
                    case ROCK:
                        return assets.ROCK_R2_ADJACENT;
                    case MOUNTAIN:
                        return assets.MOUNTAIN_R2_ADJACENT;
                    case PASTURE:
                        return assets.PASTURE_R2_ADJACENT;
                    case WOODS:
                        return assets.WOODS_R2_ADJACENT;
                }
            }
            else if(riverConfig.canConnectSoutheast()) {
                if(riverConfig.canConnectSouthwest()) {
                    switch(tile.getTerrain()) {
                        case DESERT:
                            return assets.DESERT_R5_EVERYOTHER;
                        case ROCK:
                            return assets.ROCK_R5_EVERYOTHER;
                        case MOUNTAIN:
                            return assets.MOUNTAIN_R5_EVERYOTHER;
                        case PASTURE:
                            return assets.PASTURE_R5_EVERYOTHER;
                        case WOODS:
                            return assets.WOODS_R5_EVERYOTHER;
                    }
                }
                else {
                    switch(tile.getTerrain()) {
                        case DESERT:
                            return assets.DESERT_R3_SKIP;
                        case ROCK:
                            return assets.ROCK_R3_SKIP;
                        case MOUNTAIN:
                            return assets.MOUNTAIN_R3_SKIP;
                        case PASTURE:
                            return assets.PASTURE_R3_SKIP;
                        case WOODS:
                            return assets.WOODS_R3_SKIP;
                    }
                }
            }
            else if(riverConfig.canConnectSouth()) {
                switch(tile.getTerrain()) {
                    case DESERT:
                        return assets.DESERT_R4_OPPOSITE;
                    case ROCK:
                        return assets.ROCK_R4_OPPOSITE;
                    case MOUNTAIN:
                        return assets.MOUNTAIN_R4_OPPOSITE;
                    case PASTURE:
                        return assets.PASTURE_R4_OPPOSITE;
                    case WOODS:
                        return assets.WOODS_R4_OPPOSITE;
                }
            }
            else {
                switch(tile.getTerrain()) {
                    case DESERT:
                        return assets.DESERT_R1_SPRING;
                    case ROCK:
                        return assets.ROCK_R1_SPRING;
                    case MOUNTAIN:
                        return assets.MOUNTAIN_R1_SPRING;
                    case PASTURE:
                        return assets.PASTURE_R1_SPRING;
                    case WOODS:
                        return assets.WOODS_R1_SPRING;
                }
            }
        }
        else {
            switch(tile.getTerrain()) {
                case DESERT:
                    return assets.DESERT;
                case ROCK:
                    return assets.ROCK;
                case MOUNTAIN:
                    return assets.MOUNTAIN;
                case PASTURE:
                    return assets.PASTURE;
                case WOODS:
                    return assets.WOODS;
            }
        }

        */

    }
}
