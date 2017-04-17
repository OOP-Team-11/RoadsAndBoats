package game.view;

import game.model.PlayerId;
import game.utilities.observer.PhaseRenderInfoObserver;
import game.utilities.observer.PlayerRenderInfoObserver;
import game.utilities.observer.WonderRenderInfoObserver;
import game.view.render.PhaseRenderInfo;
import game.view.render.PlayerRenderInfo;
import game.view.render.WonderBrickRenderInfo;
import game.view.render.WonderRenderInfo;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.ListView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class WonderView extends View implements WonderRenderInfoObserver, PlayerRenderInfoObserver, PhaseRenderInfoObserver{

    private AnchorPane anchorPane;
    private WonderRenderInfo wonderRenderInfo;
    private PlayerRenderInfo playerRenderInfo;
    private PhaseRenderInfo phaseRenderInfo;
    private Boolean newData;
    private Canvas canvas;
    private GraphicsContext gc;
    private int brickX = 775; // starting location of first brick
    private int brickY = 640;
    private int brickCount = 0;
    private boolean neutralBrickPlaced;
    private ListView listView;



    public WonderView(AnchorPane anchorPane){
        setAnchorPane(anchorPane);
        setUpCanvas();
        setCanvasBackground();
        drawHeadingTitle();
        drawWonderImage();
        markNeutralBrickAsPlaced();
        initializeCurrentPhaseSection();
        initializeCurrentWealthSection();
        displayWealthPlayerText();
    }
    private void setAnchorPane(AnchorPane anchorPane){
        this.anchorPane = anchorPane;
    }
    private void setUpCanvas(){
        this.canvas = new Canvas(1300, 800);
        this.gc = this.canvas.getGraphicsContext2D();
        this.anchorPane.getChildren().add(this.canvas);
    }
    private void drawHeadingTitle(){
        this.gc.setStroke(Color.BLACK);
        this.gc.setLineWidth(3.0);
        this.gc.setFont(new Font(80));
        this.gc.strokeText("Wonder", 550.0, 70.0);
    }
    private void drawWonderImage(){
        this.gc.drawImage(assets.WONDER,600, 80, 600,700);
    }

    private void setCanvasBackground(){
        this.gc.setFill(Color.LIGHTGREY);
        this.gc.fillRect(0,0,1300,800);
    }

    private void drawNeutralBrick(){
        this.gc.drawImage(assets.WONDERBRICK_NEUTRAL, brickX, brickY, 50, 20);
        this.brickCount++;
    }

    private void drawRedBrick(){
        this.gc.drawImage(assets.WONDERBRICK_RED, brickX, brickY, 50, 20);
        this.brickCount++;

    }
    private void drawBlueBrick(){
        this.gc.drawImage(assets.WONDERBRICK_BLUE, brickX, brickY, 50, 20);
        this.brickCount++;
    }

    private void incrementBrickCoordinates(){

        if(brickCount < 62){
            this.brickX += 66;
        }
        if(brickCount % 4 == 0 && brickCount < 9){
            this.brickY -= 34;
            this.brickX -= (4*66);
        }
        else if(brickCount == 12){
            this.brickY -= 34;
            this.brickX -= (4.5*66);
        } else if(brickCount == 17 || brickCount == 22 || brickCount == 27){
            this.brickY -= 34;
            this.brickX -= (5*66);
        } else if (brickCount == 32){
            this.brickY -= 36;
            this.brickX -= (5.5*66);
        } else if (brickCount == 38 || brickCount == 44 || brickCount == 50 || brickCount == 56){
            this.brickY -= 34;
            this.brickX -= (6*66);
        } else if (brickCount == 62) {
            // end tinyGame
        }
    }


    private void markNeutralBrickAsPlaced(){
        this.neutralBrickPlaced = true;
    }
    private void markNeutralBrickAsAvailable(){
        this.neutralBrickPlaced = false;
    }

    private void clearPhaseInformation(){
        this.gc.clearRect(105,105,380,180);
    }

    private void initializeCurrentPhaseSection(){
        this.gc.setFill(Color.TEAL);
        this.gc.fillRoundRect(100,100,400,200,20,20);
    }

    private void drawCurrentPhaseInformation(){
        this.gc.setFont(new Font(25));
        this.gc.setLineWidth(2.0);
        this.gc.strokeText("Current Phase: " +phaseRenderInfo.getName(), 140,140);
    }
    private void drawCurrentPlayerInformation(){
        this.gc.setFont(new Font(20));
        this.gc.setLineWidth(1.5);
        this.gc.strokeText("Player: " +playerRenderInfo.getName(), 140,180);
    }
    private void initializeCurrentWealthSection(){
        this.gc.setFill(Color.TEAL);
        this.gc.fillRoundRect(100,350,400,100,20,20);
        this.gc.drawImage(assets.STOCK_GOODS,130,373);
    }

    private void displayWealthPlayerText(){
        this.gc.setFont(new Font(20));
        this.gc.setLineWidth(1.5);
        this.gc.strokeText("Player 1 Wealth Points :", 200,380);
        this.gc.strokeText("Player 2 Wealth Points :", 200,420);
    }
    private void displayWealthAmount(int player1Amount, int player2Amount){
        this.gc.setFont(new Font(20));
        this.gc.setLineWidth(1.5);
        this.gc.strokeText(" " +player1Amount,420,380);
        this.gc.strokeText(" " +player2Amount,420,420);
    }

    private void drawBricks(){
        if(wonderRenderInfo != null){
            brickX = 775; // starting location of first brick
            brickY = 640;
            brickCount = 0;

            for(WonderBrickRenderInfo brick : wonderRenderInfo.getWonderBrickRenderInfo()){
                if (brick.getPlayerId() == null) {
                    drawNeutralBrick();
                    incrementBrickCoordinates();
                } else if(brick.getPlayerId().getPlayerIdNumber() == 1){
                    drawBlueBrick();
                    incrementBrickCoordinates();
                } else if(brick.getPlayerId().getPlayerIdNumber() == 2){
                    drawRedBrick();
                    incrementBrickCoordinates();
                }
            }
        }

    }

    private void displayWealth(){

        if(wonderRenderInfo != null){
            displayWealthAmount(wonderRenderInfo.getWealthPoints(new PlayerId(1)),wonderRenderInfo.getWealthPoints(new PlayerId(2)));
        }

    }

    @Override
    public void render() {
        if(newData = true){
            clearPhaseInformation();
            initializeCurrentPhaseSection();
            drawCurrentPhaseInformation();
            drawCurrentPlayerInformation();
            initializeCurrentWealthSection();
            displayWealthPlayerText();
            displayWealth();
            drawBricks();
            newData = false;
        } else {

        }
    }

    @Override
    public void updateWonderInfo(WonderRenderInfo wonderRenderInfo) {
        this.wonderRenderInfo = wonderRenderInfo;
        this.newData = true;
    }

    @Override
    public void updatePlayerInfo(PlayerRenderInfo playerRenderInfo) {
        this.playerRenderInfo = playerRenderInfo;
        this.newData = true;
    }

    @Override
    public void updatePhaseInfo(PhaseRenderInfo phaseRenderInfo) {
        this.phaseRenderInfo = phaseRenderInfo;
        this.newData = true;
    }
}
