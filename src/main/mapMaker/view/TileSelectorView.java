package mapMaker.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import mapMaker.model.tile.Terrain;
import mapMaker.view.render.TileRenderInformation;
import mapMaker.view.render.TileSelectorRenderInfo;
import mapMaker.view.utilities.Assets;

import mapMaker.utilities.Observer.TileSelectObserver.TileSelectObserver;

public class TileSelectorView implements TileSelectObserver{
    private Canvas canvas;
    private GraphicsContext gc;
    private TileSelectorRenderInfo currentRenderInfo;
    private boolean newDataFlag = false;
    private int terrainSelected = 1;
    private boolean mapStatus = false;

    private Image top;
    private Image middle;
    private Image bottom;

    private Image topTerrain;
    private Image middleTerrain;
    private Image bottomTerain;

    private Assets assets;
    private Image terrain1 = assets.getInstance().SEA_TERRAIN;
    private Image terrain2 = assets.getInstance().PASTURE_TERRAIN;
    private Image terrain3 = assets.getInstance().WOODS_TERRAIN;
    private Image terrain4 = assets.getInstance().ROCK_TERRAIN;
    private Image terrain5 = assets.getInstance().DESERT_TERRAIN;
    private Image terrain6 = assets.getInstance().MOUNTAIN_TERRAIN;

    public TileSelectorView(Canvas canvas){
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        this.setFontSize();
        assets = Assets.getInstance();
        assets.loadAssets();
    }

    // public method called by GameLoop when refresh is necessary
    public void render(){

        if(newDataFlag){
            drawViewDivider();
            drawCanvasBackGround();
            drawTerrainOptions();
            drawArrowKeys();
            drawMiddleRectangle();
            drawTileSelectBox();
            drawTerrainSelectRectangle();
            drawImportExportButtons();
            drawReturnButton();
            drawUpper(this.topTerrain);
            drawMiddle(this.middleTerrain);
            drawLower(this.bottomTerain);
            drawMapStatusIndicator();
            // on top of that we draw the river configurations
            drawUpper(this.top, currentRenderInfo.getTopTile().getTerrain());
            drawMiddle(this.middle, currentRenderInfo.getMiddleTile().getTerrain());
            drawLower(this.bottom, currentRenderInfo.getLowerTile().getTerrain());
            resetDataFlag();
        }
    }
    private void resetDataFlag(){
        newDataFlag = false;
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

    private void drawCanvasBackGround(){
        this.gc.setFill(Color.TEAL);
        this.gc.fillRect(2,2,canvas.getWidth(), canvas.getHeight());
    }
    private void setFontSize(){
        this.gc.setFont(Font.font(20));
    }

    private void drawTileSelectBox(){
        this.gc.setLineWidth(3);
        this.gc.strokeRoundRect(35,65,300,550,20,20);
    }
    private void drawImportExportButtons(){
        this.gc.setLineWidth(3);
        this.gc.setFill(Color.AQUAMARINE);
        this.gc.fillRoundRect(35,20,70,30,20,20);
        this.gc.strokeRoundRect(35,20,70,30,20,20);
        this.gc.strokeText("Import", 40,40);
        this.gc.fillRoundRect(120,20,70,30,20,20);
        this.gc.strokeRoundRect(120,20,70,30,20,20);
        this.gc.strokeText("Export", 125,40);
    }

    private void drawReturnButton(){
        this.gc.setLineWidth(3);
        this.gc.setFill(Color.AQUAMARINE);
        this.gc.fillRoundRect(200,20,70,30,20,20);
        this.gc.strokeRoundRect(200,20,70,30,20,20);
        this.gc.strokeText("Return",205,40);
    }

    private void drawMapStatusIndicator(){
        this.gc.strokeRoundRect(280,20,75,30,20,20);

        if(mapStatus){
            this.gc.setFill(Color.GREEN);
            this.gc.fillRoundRect(280,20,75,30,20,20);
            this.gc.strokeText("Valid", 285,40);
        }
        else {
            this.gc.setFill(Color.RED);
            this.gc.fillRoundRect(280,20,75,30,20,20);
            this.gc.strokeText("Invalid", 285,40);
        }
    }

    private void drawArrowKeys(){
        Image arrowKeys = assets.getInstance().ARROW_KEYS;
        gc.drawImage(arrowKeys, 135, 610, 100, 100);
    }
    private void drawMiddleRectangle(){
        //this.gc.strokeRoundRect(35,210,300,200,20,20);
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

    private void drawUpper(Image image){
        this.gc.drawImage(image, 130, 90);
        this.gc.drawImage(assets.FADED,130,90);
    }
    private void drawLower(Image image){
        this.gc.drawImage(image, 130, 450);
        this.gc.drawImage(assets.FADED,130,450);
    }
    private void drawMiddle(Image image, Terrain terrain){
        if (!terrain.equals(Terrain.SEA)) {
            this.gc.drawImage(image,130,275);
        }
    }
    private void drawUpper(Image image, Terrain terrain) {
        if (!terrain.equals(Terrain.SEA)) {
            this.gc.drawImage(image, 130, 90);
            this.gc.drawImage(assets.FADED,130,90);
        }
    }
    private void drawMiddle(Image image){
        this.gc.drawImage(image,130,275);
    }
    private void drawLower(Image image, Terrain terrain) {
        if (!terrain.equals(Terrain.SEA)) {
            this.gc.drawImage(image, 130, 450);
            this.gc.drawImage(assets.FADED,130,450);
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
        mapStatus = tileSelectorRenderInfo.getMapValidation();
        newDataFlag = true;
        
        this.currentRenderInfo = tileSelectorRenderInfo;
        if(tileSelectorRenderInfo.getMiddleTile().getTerrain().equals(Terrain.SEA)){
            terrainSelected = 1;
        } else if (tileSelectorRenderInfo.getMiddleTile().getTerrain().equals(Terrain.PASTURE)){
            terrainSelected = 2;
        } else if (tileSelectorRenderInfo.getMiddleTile().getTerrain().equals(Terrain.WOODS)){
            terrainSelected = 3;
        } else if (tileSelectorRenderInfo.getMiddleTile().getTerrain().equals(Terrain.ROCK)){
            terrainSelected = 4;
        } else if (tileSelectorRenderInfo.getMiddleTile().getTerrain().equals(Terrain.DESERT)){
            terrainSelected = 5;
        } else {
            terrainSelected = 6;
        }

        this.topTerrain = getTerrainImage(tileSelectorRenderInfo.getTopTile());
        this.middleTerrain = getTerrainImage(tileSelectorRenderInfo.getMiddleTile());
        this.bottomTerain = getTerrainImage(tileSelectorRenderInfo.getLowerTile());

        this.top = getRiverImage(tileSelectorRenderInfo.getTopTile());
        this.middle = getRiverImage(tileSelectorRenderInfo.getMiddleTile());
        this.bottom = getRiverImage(tileSelectorRenderInfo.getLowerTile());
        this.newDataFlag = true;
    }
    private Image getTerrainImage(TileRenderInformation tileRenderInformation){
        if(tileRenderInformation.getTerrain().equals(Terrain.SEA)){
            return assets.SEA;
        } else if (tileRenderInformation.getTerrain().equals(Terrain.DESERT)){
            return assets.DESERT;
        } else if(tileRenderInformation.getTerrain().equals(Terrain.MOUNTAIN)){
            return assets.MOUNTAIN;
        } else if(tileRenderInformation.getTerrain().equals(Terrain.PASTURE)){
            return assets.PASTURE;
        } else if(tileRenderInformation.getTerrain().equals(Terrain.ROCK)){
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
