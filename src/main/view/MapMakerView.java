package view;

import direction.TileCompartmentDirection;
import direction.TileEdgeDirection;
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
import view.render.TileRenderInformation;
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
    private boolean firstTime = true;

    private Image cursorGreen = assets.getInstance().GREEN_CURSOR;
    private Image cursorRed = assets.getInstance().RED_CURSOR;


    private void recenterCanvas(){
        this.cameraX = (int)(canvas.getWidth()/2);
        this.cameraY = (int)(canvas.getHeight()/2);
    }

    public MapMakerView(Canvas canvas){

        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        assets = Assets.getInstance();
        assets.loadAssets();
    }

    // public method called by GameLoop when refresh is necessary
    public void render(){
        if(firstTime){
            recenterCanvas();
            firstTime = false;
        }
        if(newDataFlag){
            checkClearCanvas();
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
            for (Map.Entry<Location, TileRenderInformation> entry : renderInformation.getTileInformation().entrySet())
            {
                Image image = getTerrainImage(entry.getValue());
                drawImage(image, entry.getKey().getX(), entry.getKey().getY(), entry.getKey().getZ());
            }
            // second time around we draw the rivers
            for (Map.Entry<Location, TileRenderInformation> entry : renderInformation.getTileInformation().entrySet())
            {
                Image image = getRiverImage(entry.getValue());
                if(image != null){
                    drawImage(image, entry.getKey().getX(), entry.getKey().getY(), entry.getKey().getZ());
                }
            }
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
        this.cameraX = mapMakerCursorInfo.getCameraX()*5 +(int)(canvas.getWidth()/2);
        this.cameraY = mapMakerCursorInfo.getCameraY()*5 + (int)(canvas.getHeight()/2);
    }

    @Override
    public void updateMapMaker(MapMakerRenderInfo mapMakerRenderInfo) {
        recenterCanvas();
        this.newDataFlag = true;
        this.renderInformation = mapMakerRenderInfo;
    }

    private Image getTerrainImage(TileRenderInformation tile){
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

    private Image getRiverImage(TileRenderInformation tileRenderInformation) {
        int waterCount = 0;

        if(tileRenderInformation.getNorth()){
            waterCount++;
        }
        if(tileRenderInformation.getNorthEast()){
            waterCount++;
        }
        if(tileRenderInformation.getSouthEast()){
            waterCount++;
        }
        if(tileRenderInformation.getSouth()){
            waterCount++;
        }
        if(tileRenderInformation.getSouthWest()){
            waterCount++;
        }
        if(tileRenderInformation.getNorthWest()){
            waterCount++;
        }
        if(waterCount == 0){
            return null;
        }

        if(waterCount == 1){
            if(tileRenderInformation.getNorth()){
                return assets.getInstance().RIVER_SPRING_R0;
            } else if (tileRenderInformation.getNorthEast()){
                return assets.getInstance().RIVER_SPRING_R1;
            } else if(tileRenderInformation.getSouthEast()){
                return assets.getInstance().RIVER_SPRING_R2;
            } else if(tileRenderInformation.getSouth()){
                return assets.getInstance().RIVER_SPRING_R3;
            } else if(tileRenderInformation.getSouthWest()){
                return assets.getInstance().RIVER_SPRING_R4;
            } else if(tileRenderInformation.getNorthWest()){
                return assets.getInstance().RIVER_SPRING_R5;
            } else {
                return null;
            }

        } else if(waterCount == 2){
            if(tileRenderInformation.getNorth() && tileRenderInformation.getSouth()){
                return assets.getInstance().RIVER_OPPOSITE_R0;
            } else if(tileRenderInformation.getNorthEast() && tileRenderInformation.getSouthWest()){
                return assets.getInstance().RIVER_OPPOSITE_R1;
            } else if (tileRenderInformation.getSouthEast() && tileRenderInformation.getNorthWest()){
                return assets.getInstance().RIVER_OPPOSITE_R2;
            } else if (tileRenderInformation.getNorth() && tileRenderInformation.getNorthEast()){
                return assets.getInstance().RIVER_ADJACENT_R0;
            } else if (tileRenderInformation.getNorthEast() && tileRenderInformation.getSouthEast()){
                return assets.getInstance().RIVER_ADJACENT_R1;
            } else if (tileRenderInformation.getSouthEast()&& tileRenderInformation.getSouth()){
                return assets.getInstance().RIVER_ADJACENT_R2;
            } else if (tileRenderInformation.getSouth() && tileRenderInformation.getSouthWest()){
                return assets.getInstance().RIVER_ADJACENT_R3;
            } else if (tileRenderInformation.getSouthWest() && tileRenderInformation.getNorthWest()){
                return assets.getInstance().RIVER_ADJACENT_R4;
            } else if (tileRenderInformation.getNorthWest()&& tileRenderInformation.getNorth()){
                return assets.getInstance().RIVER_ADJACENT_R5;
            } else if (tileRenderInformation.getNorth() && tileRenderInformation.getSouthEast()){
                return assets.getInstance().RIVER_SKIP_R0;
            } else if (tileRenderInformation.getNorthEast() && tileRenderInformation.getSouth()){
                return assets.getInstance().RIVER_SKIP_R1;
            } else if (tileRenderInformation.getSouthEast()&& tileRenderInformation.getSouthWest()){
                return assets.getInstance().RIVER_SKIP_R2;
            } else if (tileRenderInformation.getSouth() && tileRenderInformation.getNorthWest()){
                return assets.getInstance().RIVER_SKIP_R3;
            } else if (tileRenderInformation.getSouthWest() && tileRenderInformation.getNorth()){
                return assets.getInstance().RIVER_SKIP_R4;
            } else {
                return assets.getInstance().RIVER_SKIP_R5;
            }
        } else {
            // 3 sides
            if(tileRenderInformation.getNorth()){
                return assets.getInstance().RIVER_EVERYOTHER_R0;
            } else {
                return assets.getInstance().RIVER_EVERYOTHER_R1;
            }
        }
    }
}
