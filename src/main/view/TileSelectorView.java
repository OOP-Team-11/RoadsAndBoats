package view;

import direction.TileEdgeDirection;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import model.tile.Terrain;
import model.tile.Tile;
import model.tile.riverConfiguration.RiverConfiguration;

import view.render.TileSelectorRenderInfo;
import view.utilities.Assets;

import utilities.Observer.TileSelectObserver.TileSelectObserver;

public class TileSelectorView implements TileSelectObserver{
    private Canvas canvas;
    private GraphicsContext gc;
    private TileSelectorRenderInfo currentRenderInfo;
    private boolean newDataFlag = false;
    private int terrainSelected = 1;

    private Image top;
    private Image middle;
    private Image bottom;

    private Assets assets;
    private Image terrain1 = assets.getInstance().SEA_TERRAIN;
    private Image terrain2 = assets.getInstance().PASTURE_TERRAIN;
    private Image terrain3 = assets.getInstance().WOODS_TERRAIN;
    private Image terrain4 = assets.getInstance().ROCK_TERRAIN;
    private Image terrain5 = assets.getInstance().DESERT_TERRAIN;
    private Image terrain6 = assets.getInstance().MOUNTAIN_TERRAIN;
    private Image test = assets.getInstance().DESERT_R1_SPRING;

    public TileSelectorView(Canvas canvas){
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        this.setFontSize();
        assets = Assets.getInstance();
        assets.loadAssets();
    }

    // public method called by GameLoop when refresh is necessary
    public void render(){
        drawViewDivider();
        drawCanvasBackGround();
        drawTileSelectBox();
        drawTerrainOptions();
        drawArrowKeys();
        drawMiddleRectangle();
        drawTerrainSelectRectangle();
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
    private void drawViewDivider(){
        setGraphicsContentStroke(Color.BLACK);
        setGraphicsContentFill(Color.BLACK);
        setLineWidth(5.0);
        drawLine(0,0,0,canvas.getHeight()); // vertical stroke line
        drawLine(0,0,canvas.getWidth(),0); // horizontal stroke line
    }
    private void setImagesForTiles(){

    }
    private void drawMiddleImage(){

    }

    private void drawCanvasBackGround(){
        this.gc.setFill(Color.TEAL);
        this.gc.fillRect(2,2,canvas.getWidth(), canvas.getHeight());
    }
    private void setFontSize(){
        this.gc.setFont(Font.font(20));
    }

    private void drawTileSelectBox(){
        this.gc.setLineWidth(3);
        this.gc.strokeRoundRect(35,20,300,600,20,20);
    }
    private void drawArrowKeys(){
        Image arrowKeys = assets.getInstance().ARROW_KEYS;
        gc.drawImage(arrowKeys, 135, 610, 100, 100);
    }
    private void drawMiddleRectangle(){
        this.gc.strokeRoundRect(35,210,300,200,20,20);
    }

    private void drawTerrainSelectRectangle(){
        this.gc.setLineWidth(7);
        if(terrainSelected == 1){
            this.gc.strokeRoundRect(40,700,35,35,5,5);
        } else if(terrainSelected == 2){
            this.gc.strokeRoundRect(90,700,35,35,5,5);
        } else if(terrainSelected == 3){
            this.gc.strokeRoundRect(140,700,35,35,5,5);
        } else if(terrainSelected == 4){
            this.gc.strokeRoundRect(190,700,35,35,5,5);
        } else if(terrainSelected == 5){
            this.gc.strokeRoundRect(240,700,35,35,5,5);
        } else {
            this.gc.strokeRoundRect(290,700,35,35,5,5);
        }
    }

    private void drawTerrainOptions(){
        this.gc.strokeRoundRect(40,700,35,35,5,5);
        this.gc.strokeRoundRect(90,700,35,35,5,5);
        this.gc.strokeRoundRect(140,700,35,35,5,5);
        this.gc.strokeRoundRect(190,700,35,35,5,5);
        this.gc.strokeRoundRect(240,700,35,35,5,5);
        this.gc.strokeRoundRect(290,700,35,35,5,5);
        this.gc.strokeText("1",53,760);
        this.gc.strokeText("2",103,760);
        this.gc.strokeText("3",153,760);
        this.gc.strokeText("4",203,760);
        this.gc.strokeText("5",253,760);
        this.gc.strokeText("6",303,760);
        gc.drawImage(terrain1, 40, 700, 35, 35);
        gc.drawImage(terrain2, 90, 700, 35, 35);
        gc.drawImage(terrain3, 140, 700, 35, 35);
        gc.drawImage(terrain4, 190, 700, 35, 35);
        gc.drawImage(terrain5, 240, 700, 35, 35);
        gc.drawImage(terrain6, 290, 700, 35, 35);
    }

    @Override
    public void updateTileSelect(TileSelectorRenderInfo tileSelectorRenderInfo) {

        System.out.println(tileSelectorRenderInfo.getTerrainTypeSelection());
        this.currentRenderInfo = tileSelectorRenderInfo;
        if(tileSelectorRenderInfo.getMiddleTile().getTerrain().equals(Terrain.DESERT)){
            terrainSelected = 1;
        } else if (tileSelectorRenderInfo.getMiddleTile().getTerrain().equals(Terrain.MOUNTAIN)){
            terrainSelected = 2;
        } else if (tileSelectorRenderInfo.getMiddleTile().getTerrain().equals(Terrain.PASTURE)){
            terrainSelected = 3;
        } else if (tileSelectorRenderInfo.getMiddleTile().getTerrain().equals(Terrain.ROCK)){
            terrainSelected = 4;
        } else if (tileSelectorRenderInfo.getMiddleTile().getTerrain().equals(Terrain.SEA)){
            terrainSelected = 5;
        } else {
            terrainSelected = 6;
        }

        top = getTileImage(tileSelectorRenderInfo.getTopTile());
        middle = getTileImage(tileSelectorRenderInfo.getMiddleTile());
        bottom = getTileImage(tileSelectorRenderInfo.getLowerTile());
        this.newDataFlag = true;
    }

    private Image getTileImage(Tile tile) {
        RiverConfiguration riverConfig = tile.getRiverConfiguration();
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
        return null;
    }
}
