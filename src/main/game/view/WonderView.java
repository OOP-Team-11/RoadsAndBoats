package game.view;

import game.utilities.observer.PhaseRenderInfoObserver;
import game.utilities.observer.PlayerRenderInfoObserver;
import game.utilities.observer.WonderRenderInfoObserver;
import game.view.render.PhaseRenderInfo;
import game.view.render.PlayerRenderInfo;
import game.view.render.WonderRenderInfo;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;
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
    private Button testButton;
    private GraphicsContext gc;
    private int brickX = 775; // starting location of first brick
    private int brickY = 640;
    private int brickCount = 0;



    public WonderView(AnchorPane anchorPane){
        setAnchorPane(anchorPane);
        setUpCanvas();
        setCanvasBackground();
        drawHeadingTitle();
        drawWonderImage();
        placeAddButton();
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
    private void placeAddButton(){
        this.testButton = new Button();
        this.anchorPane.getChildren().add(testButton);
        this.anchorPane.setLeftAnchor(testButton, 100.0);
        this.anchorPane.setTopAnchor(testButton,200.0);
        this.testButton.setOnMouseClicked(event ->{
            drawNextBrick();
            incrementBrickCoordinates();
        });
    }

    private void setCanvasBackground(){
        this.gc.setFill(Color.LIGHTGREY);
        this.gc.fillRect(0,0,1300,800);
    }

    private void drawNetualBrick(){ // TODO hook up later
        this.gc.drawImage(assets.WONDERBRICK_NEUTRAL, brickX, brickY, 50, 20);
        this.brickCount++;
    }

    private void drawRedBrick(){ // TOdo hook up later
        this.gc.drawImage(assets.WONDERBRICK_RED, brickX, brickY, 50, 20);
        this.brickCount++;

    }
    private void drawBlueBrick(){ // TODO hook up later
        this.gc.drawImage(assets.WONDERBRICK_BLUE, brickX, brickY, 50, 20);
        this.brickCount++;
    }

    private void drawNextBrick(){
        // we check if we are in the correct phase
        if(phaseRenderInfo.getName().equals("Wonder")){

        }
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

    public void addEventFilterToAddBrickButton(EventHandler filter){
        this.testButton.addEventFilter(MouseEvent.MOUSE_CLICKED, filter);
    }

    @Override
    public void render() {

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
