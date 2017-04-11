package mapMaker.view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Font;
import mapMaker.model.tile.mmTerrain;
import mapMaker.view.render.mmTileRenderInformation;
import mapMaker.view.render.mmTileSelectorRenderInfo;
import mapMaker.view.utilities.mmAssets;

import mapMaker.utilities.Observer.TileSelectObserver.mmTileSelectObserver;

public class mmTileSelectorView implements mmTileSelectObserver {
    private Canvas canvas;
    private GraphicsContext gc;
    private mmTileSelectorRenderInfo currentRenderInfo;
    private boolean newDataFlag = false;
    private int terrainSelected = 1;
    private boolean mapStatus = false;

    private Image top;
    private Image middle;
    private Image bottom;

    private Image topTerrain;
    private Image middleTerrain;
    private Image bottomTerain;

    private mmAssets mmAssets;
    private Image terrain1 = mmAssets.getInstance().SEA_TERRAIN;
    private Image terrain2 = mmAssets.getInstance().PASTURE_TERRAIN;
    private Image terrain3 = mmAssets.getInstance().WOODS_TERRAIN;
    private Image terrain4 = mmAssets.getInstance().ROCK_TERRAIN;
    private Image terrain5 = mmAssets.getInstance().DESERT_TERRAIN;
    private Image terrain6 = mmAssets.getInstance().MOUNTAIN_TERRAIN;

    public mmTileSelectorView(Canvas canvas){
        this.canvas = canvas;
        this.gc = canvas.getGraphicsContext2D();
        this.setFontSize();
        mmAssets = mmAssets.getInstance();
        mmAssets.loadAssets();
    }

    // public method called by mmGameLoop when refresh is necessary
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
            drawUpper(this.top, currentRenderInfo.getTopTile().getMmTerrain());
            drawMiddle(this.middle, currentRenderInfo.getMiddleTile().getMmTerrain());
            drawLower(this.bottom, currentRenderInfo.getLowerTile().getMmTerrain());
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
        Image arrowKeys = mmAssets.getInstance().ARROW_KEYS;
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
        this.gc.drawImage(mmAssets.FADED,130,90);
    }
    private void drawLower(Image image){
        this.gc.drawImage(image, 130, 450);
        this.gc.drawImage(mmAssets.FADED,130,450);
    }
    private void drawMiddle(Image image, mmTerrain mmTerrain){
        if (!mmTerrain.equals(mmTerrain.SEA)) {
            this.gc.drawImage(image,130,275);
        }
    }
    private void drawUpper(Image image, mmTerrain mmTerrain) {
        if (!mmTerrain.equals(mmTerrain.SEA)) {
            this.gc.drawImage(image, 130, 90);
            this.gc.drawImage(mmAssets.FADED,130,90);
        }
    }
    private void drawMiddle(Image image){
        this.gc.drawImage(image,130,275);
    }
    private void drawLower(Image image, mmTerrain mmTerrain) {
        if (!mmTerrain.equals(mmTerrain.SEA)) {
            this.gc.drawImage(image, 130, 450);
            this.gc.drawImage(mmAssets.FADED,130,450);
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
    public void updateTileSelect(mmTileSelectorRenderInfo mmTileSelectorRenderInfo) {
        mapStatus = mmTileSelectorRenderInfo.getMapValidation();
        newDataFlag = true;
        
        this.currentRenderInfo = mmTileSelectorRenderInfo;
        if(mmTileSelectorRenderInfo.getMiddleTile().getMmTerrain().equals(mmTerrain.SEA)){
            terrainSelected = 1;
        } else if (mmTileSelectorRenderInfo.getMiddleTile().getMmTerrain().equals(mmTerrain.PASTURE)){
            terrainSelected = 2;
        } else if (mmTileSelectorRenderInfo.getMiddleTile().getMmTerrain().equals(mmTerrain.WOODS)){
            terrainSelected = 3;
        } else if (mmTileSelectorRenderInfo.getMiddleTile().getMmTerrain().equals(mmTerrain.ROCK)){
            terrainSelected = 4;
        } else if (mmTileSelectorRenderInfo.getMiddleTile().getMmTerrain().equals(mmTerrain.DESERT)){
            terrainSelected = 5;
        } else {
            terrainSelected = 6;
        }

        this.topTerrain = getTerrainImage(mmTileSelectorRenderInfo.getTopTile());
        this.middleTerrain = getTerrainImage(mmTileSelectorRenderInfo.getMiddleTile());
        this.bottomTerain = getTerrainImage(mmTileSelectorRenderInfo.getLowerTile());

        this.top = getRiverImage(mmTileSelectorRenderInfo.getTopTile());
        this.middle = getRiverImage(mmTileSelectorRenderInfo.getMiddleTile());
        this.bottom = getRiverImage(mmTileSelectorRenderInfo.getLowerTile());
        this.newDataFlag = true;
    }
    private Image getTerrainImage(mmTileRenderInformation mmTileRenderInformation){
        if(mmTileRenderInformation.getMmTerrain().equals(mmTerrain.SEA)){
            return mmAssets.SEA;
        } else if (mmTileRenderInformation.getMmTerrain().equals(mmTerrain.DESERT)){
            return mmAssets.DESERT;
        } else if(mmTileRenderInformation.getMmTerrain().equals(mmTerrain.MOUNTAIN)){
            return mmAssets.MOUNTAIN;
        } else if(mmTileRenderInformation.getMmTerrain().equals(mmTerrain.PASTURE)){
            return mmAssets.PASTURE;
        } else if(mmTileRenderInformation.getMmTerrain().equals(mmTerrain.ROCK)){
            return mmAssets.ROCK;
        } else {
            return mmAssets.WOODS;
        }
    }

    private Image getRiverImage(mmTileRenderInformation mmTileRenderInformation) {
        int waterCount = 0;

        if(mmTileRenderInformation.getNorth()){
            waterCount++;
        }
        if(mmTileRenderInformation.getNorthEast()){
            waterCount++;
        }
        if(mmTileRenderInformation.getSouthEast()){
            waterCount++;
        }
        if(mmTileRenderInformation.getSouth()){
            waterCount++;
        }
        if(mmTileRenderInformation.getSouthWest()){
            waterCount++;
        }
        if(mmTileRenderInformation.getNorthWest()){
            waterCount++;
        }
        if(waterCount == 0){
            return null;
        }

        if(waterCount == 1){
            if(mmTileRenderInformation.getNorth()){
                return mmAssets.getInstance().RIVER_SPRING_R0;
            } else if (mmTileRenderInformation.getNorthEast()){
                return mmAssets.getInstance().RIVER_SPRING_R1;
            } else if(mmTileRenderInformation.getSouthEast()){
                return mmAssets.getInstance().RIVER_SPRING_R2;
            } else if(mmTileRenderInformation.getSouth()){
                return mmAssets.getInstance().RIVER_SPRING_R3;
            } else if(mmTileRenderInformation.getSouthWest()){
                return mmAssets.getInstance().RIVER_SPRING_R4;
            } else if(mmTileRenderInformation.getNorthWest()){
                return mmAssets.getInstance().RIVER_SPRING_R5;
            } else {
                return null;
            }

        } else if(waterCount == 2){
            if(mmTileRenderInformation.getNorth() && mmTileRenderInformation.getSouth()){
                return mmAssets.getInstance().RIVER_OPPOSITE_R0;
            } else if(mmTileRenderInformation.getNorthEast() && mmTileRenderInformation.getSouthWest()){
                return mmAssets.getInstance().RIVER_OPPOSITE_R1;
            } else if (mmTileRenderInformation.getSouthEast() && mmTileRenderInformation.getNorthWest()){
                return mmAssets.getInstance().RIVER_OPPOSITE_R2;
            } else if (mmTileRenderInformation.getNorth() && mmTileRenderInformation.getNorthEast()){
                return mmAssets.getInstance().RIVER_ADJACENT_R0;
            } else if (mmTileRenderInformation.getNorthEast() && mmTileRenderInformation.getSouthEast()){
                return mmAssets.getInstance().RIVER_ADJACENT_R1;
            } else if (mmTileRenderInformation.getSouthEast()&& mmTileRenderInformation.getSouth()){
                return mmAssets.getInstance().RIVER_ADJACENT_R2;
            } else if (mmTileRenderInformation.getSouth() && mmTileRenderInformation.getSouthWest()){
                return mmAssets.getInstance().RIVER_ADJACENT_R3;
            } else if (mmTileRenderInformation.getSouthWest() && mmTileRenderInformation.getNorthWest()){
                return mmAssets.getInstance().RIVER_ADJACENT_R4;
            } else if (mmTileRenderInformation.getNorthWest()&& mmTileRenderInformation.getNorth()){
                return mmAssets.getInstance().RIVER_ADJACENT_R5;
            } else if (mmTileRenderInformation.getNorth() && mmTileRenderInformation.getSouthEast()){
                return mmAssets.getInstance().RIVER_SKIP_R0;
            } else if (mmTileRenderInformation.getNorthEast() && mmTileRenderInformation.getSouth()){
                return mmAssets.getInstance().RIVER_SKIP_R1;
            } else if (mmTileRenderInformation.getSouthEast()&& mmTileRenderInformation.getSouthWest()){
                return mmAssets.getInstance().RIVER_SKIP_R2;
            } else if (mmTileRenderInformation.getSouth() && mmTileRenderInformation.getNorthWest()){
                return mmAssets.getInstance().RIVER_SKIP_R3;
            } else if (mmTileRenderInformation.getSouthWest() && mmTileRenderInformation.getNorth()){
                return mmAssets.getInstance().RIVER_SKIP_R4;
            } else {
                return mmAssets.getInstance().RIVER_SKIP_R5;
            }
        } else {
            // 3 sides
            if(mmTileRenderInformation.getNorth()){
                return mmAssets.getInstance().RIVER_EVERYOTHER_R0;
            } else {
                return mmAssets.getInstance().RIVER_EVERYOTHER_R1;
            }
        }
    }
}
