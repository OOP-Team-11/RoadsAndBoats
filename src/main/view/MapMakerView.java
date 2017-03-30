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
            System.out.println("DRAWMING MAP");
            for (Map.Entry<Location, Tile> entry : renderInformation.getTileInformation().entrySet())
            {
                System.out.println(entry.getValue().getTileEdge(TileEdgeDirection.getNorth()).canConnectRiver());
                System.out.println(entry.getValue().getTileEdge(TileEdgeDirection.getNorthEast()).canConnectRiver());
                System.out.println(entry.getValue().getTileEdge(TileEdgeDirection.getSouthEast()).canConnectRiver());
                System.out.println(entry.getValue().getTileEdge(TileEdgeDirection.getSouth()).canConnectRiver());
                System.out.println(entry.getValue().getTileEdge(TileEdgeDirection.getSouthWest()).canConnectRiver());
                System.out.println(entry.getValue().getTileEdge(TileEdgeDirection.getNorthWest()).canConnectRiver());
                System.out.println("-");
                System.out.println(entry.getValue().getRiverConfiguration().canConnectNorth());
                System.out.println(entry.getValue().getRiverConfiguration().canConnectNortheast());
                System.out.println(entry.getValue().getRiverConfiguration().canConnectSoutheast());
                System.out.println(entry.getValue().getRiverConfiguration().canConnectSouth());
                System.out.println(entry.getValue().getRiverConfiguration().canConnectSouthwest());
                System.out.println(entry.getValue().getRiverConfiguration().canConnectNorthwest());
                System.out.println("--");
                System.out.println(entry.getValue().getTileCompartment(TileCompartmentDirection.getNorth()).hasWater());
                System.out.println(entry.getValue().getTileCompartment(TileCompartmentDirection.getNorthEast()).hasWater());
                System.out.println(entry.getValue().getTileCompartment(TileCompartmentDirection.getSouthEast()).hasWater());
                System.out.println(entry.getValue().getTileCompartment(TileCompartmentDirection.getSouth()).hasWater());
                System.out.println(entry.getValue().getTileCompartment(TileCompartmentDirection.getSouthWest()).hasWater());
                System.out.println(entry.getValue().getTileCompartment(TileCompartmentDirection.getNorthWest()).hasWater());
                System.out.println("------------------------------");
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
        this.cameraX = mapMakerCursorInfo.getCameraX()*10;
        this.cameraY = mapMakerCursorInfo.getCameraY()*10;

    }

    @Override
    public void updateMapMaker(MapMakerRenderInfo mapMakerRenderInfo) {
        this.newDataFlag = true;
        this.renderInformation = mapMakerRenderInfo;
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

    private Image getRiverImage(Tile tile) {


        int riverCount = 0;
        if(tile.getTileCompartment(TileCompartmentDirection.getNorth()).hasWater()){
            riverCount++; }
        if (tile.getTileCompartment(TileCompartmentDirection.getNorthWest()).hasWater()){
            riverCount++; }
        if(tile.getTileCompartment(TileCompartmentDirection.getSouthWest()).hasWater()){
            riverCount++; }
        if(tile.getTileCompartment(TileCompartmentDirection.getSouth()).hasWater()){
            riverCount++; }
        if(tile.getTileCompartment(TileCompartmentDirection.getNorthEast()).hasWater()){
            riverCount++; }
        if(tile.getTileCompartment(TileCompartmentDirection.getSouthEast()).hasWater()){
            riverCount++;
        }

        if(riverCount == 1){
            if(tile.getTileCompartment(TileCompartmentDirection.getNorth()).hasWater()){
                return assets.getInstance().RIVER_SPRING_R0;
            } else if (tile.getTileCompartment(TileCompartmentDirection.getNorthEast()).hasWater()){
                return assets.getInstance().RIVER_SPRING_R1;
            } else if(tile.getTileCompartment(TileCompartmentDirection.getSouthEast()).hasWater()){
                return assets.getInstance().RIVER_SPRING_R2;
            } else if(tile.getTileCompartment(TileCompartmentDirection.getSouth()).hasWater()){
                return assets.getInstance().RIVER_SPRING_R3;
            } else if(tile.getTileCompartment(TileCompartmentDirection.getSouthWest()).hasWater()){
                return assets.getInstance().RIVER_SPRING_R4;
            } else if(tile.getTileCompartment(TileCompartmentDirection.getNorthWest()).hasWater()){
                return assets.getInstance().RIVER_SPRING_R5;
            } else {
                return null;
            }
        } else if (riverCount == 2){
            if(tile.getTileCompartment(TileCompartmentDirection.getNorth()).hasWater() && tile.getTileCompartment(TileCompartmentDirection.getSouth()).hasWater()){
                return assets.getInstance().RIVER_OPPOSITE_R0;
            } else if(tile.getTileCompartment(TileCompartmentDirection.getNorthEast()).hasWater() && tile.getTileCompartment(TileCompartmentDirection.getSouthWest()).hasWater()){
                return assets.getInstance().RIVER_OPPOSITE_R1;
            } else if (tile.getTileCompartment(TileCompartmentDirection.getSouthEast()).hasWater() && tile.getTileCompartment(TileCompartmentDirection.getNorthWest()).hasWater()){
                return assets.getInstance().RIVER_OPPOSITE_R2;
            } else if (tile.getTileCompartment(TileCompartmentDirection.getNorth()).hasWater() && tile.getTileCompartment(TileCompartmentDirection.getNorthEast()).hasWater()){
                return assets.getInstance().RIVER_ADJACENT_R0;
            } else if (tile.getTileCompartment(TileCompartmentDirection.getNorthEast()).hasWater() && tile.getTileCompartment(TileCompartmentDirection.getSouthEast()).hasWater()){
                return assets.getInstance().RIVER_ADJACENT_R1;
            } else if (tile.getTileCompartment(TileCompartmentDirection.getSouthEast()).hasWater() && tile.getTileCompartment(TileCompartmentDirection.getSouth()).hasWater()){
                return assets.getInstance().RIVER_ADJACENT_R2;
            } else if (tile.getTileCompartment(TileCompartmentDirection.getSouth()).hasWater() && tile.getTileCompartment(TileCompartmentDirection.getSouthWest()).hasWater()){
                return assets.getInstance().RIVER_ADJACENT_R3;
            } else if (tile.getTileCompartment(TileCompartmentDirection.getSouthWest()).hasWater() && tile.getTileCompartment(TileCompartmentDirection.getNorthWest()).hasWater()){
                return assets.getInstance().RIVER_ADJACENT_R4;
            } else if (tile.getTileEdge(TileEdgeDirection.getNorthWest()).canConnectRiver() && tile.getTileCompartment(TileCompartmentDirection.getNorth()).hasWater()){
                return assets.getInstance().RIVER_ADJACENT_R5;
            } else if (tile.getTileCompartment(TileCompartmentDirection.getNorth()).hasWater() && tile.getTileCompartment(TileCompartmentDirection.getSouthEast()).hasWater()){
                return assets.getInstance().RIVER_SKIP_R0;
            } else if (tile.getTileCompartment(TileCompartmentDirection.getNorthEast()).hasWater() && tile.getTileCompartment(TileCompartmentDirection.getSouth()).hasWater()){
                return assets.getInstance().RIVER_SKIP_R1;
            } else if (tile.getTileCompartment(TileCompartmentDirection.getSouthEast()).hasWater() && tile.getTileCompartment(TileCompartmentDirection.getSouthWest()).hasWater()){
                return assets.getInstance().RIVER_SKIP_R2;
            } else if (tile.getTileCompartment(TileCompartmentDirection.getSouth()).hasWater() && tile.getTileCompartment(TileCompartmentDirection.getNorthWest()).hasWater()){
                return assets.getInstance().RIVER_SKIP_R3;
            } else if (tile.getTileCompartment(TileCompartmentDirection.getSouthWest()).hasWater() && tile.getTileCompartment(TileCompartmentDirection.getNorth()).hasWater()){
                return assets.getInstance().RIVER_SKIP_R4;
            } else {
                return assets.getInstance().RIVER_SKIP_R5;
            }
        } else if(riverCount == 3){
            // 3 sides
            if(tile.getTileCompartment(TileCompartmentDirection.getNorth()).hasWater()){
                return assets.getInstance().RIVER_EVERYOTHER_R0;
            } else {
                return assets.getInstance().RIVER_EVERYOTHER_R1;
            }
        } else {
            return null;
        }

    }
}
