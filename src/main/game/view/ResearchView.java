package game.view;

import game.utilities.observer.PhaseRenderInfoObserver;
import game.utilities.observer.PlayerRenderInfoObserver;
import game.utilities.observer.TechRenderInfoObserver;
import game.view.render.PhaseRenderInfo;
import game.view.render.PlayerRenderInfo;
import game.view.render.TechRenderInfo;
import javafx.animation.*;
import javafx.event.EventHandler;
import javafx.scene.input.MouseEvent;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.LineTo;
import javafx.scene.shape.MoveTo;
import javafx.scene.shape.Path;
import javafx.scene.text.Font;
import javafx.util.Duration;

public class ResearchView extends View implements TechRenderInfoObserver, PlayerRenderInfoObserver, PhaseRenderInfoObserver {

    private AnchorPane anchorPane;
    private TechRenderInfo techRenderInfo;
    private Boolean newData;
    private Canvas canvas;
    private GraphicsContext gc;
    private ListView transporters;
    private Button selectFirst;
    private Button selectSecond;
    private Button selectThird;
    private Button selectFourth;
    private Button selectFifth;
    private Button selectSixth;
    private Button selectSeventh;
    private Button selectEighth;
    private ImageView stoneOne;
    private ImageView stoneTwo;
    private ImageView stoneThree;
    private ImageView stoneFour;
    private ImageView stoneFife;
    private ImageView stoneSix;
    private ImageView stoneSeven;
    private ImageView stoneEight;
    private PathTransition pathTransition1;
    private PathTransition pathTransition2;
    private PathTransition pathTransition3;
    private PathTransition pathTransition4;
    private PathTransition pathTransition5;
    private PathTransition pathTransition6;
    private PathTransition pathTransition7;
    private PathTransition pathTransition8;
    private PhaseRenderInfo currentPhaseRenderInfo;
    private PlayerRenderInfo currentPlayerRenderInfo;


    public ResearchView(AnchorPane anchorPane){
        setAnchorPane(anchorPane);
        initializeCanvas();
        setCanvasBackground();
        drawBackgroundStyling();
        drawHeadingTitle();
        drawResearchTable();
        drawTransportList();
        initializeButtons();


        // just for testing atm, later depending on render info these will be added/removed dynamically
        addFirst();
        addSecond();
        addThird();
        addFourth();
        addFifth();
        addSixth();
        addSeventh();
        addEight();

        addStoneOne();
        addStoneTwo();
        addStoneThree();
        addStoneFour();
        addStoneFive();
        addStoneSix();
        addStoneSeven();
        addStoneEight();
    }

    private void initializeCanvas(){
        this.canvas = new Canvas(1300, 800);
        this.gc = this.canvas.getGraphicsContext2D();
        this.anchorPane.getChildren().add(canvas);
    }
    private void drawResearchTable(){

        this.gc.drawImage(assets.RESEARCH_TABLE, 100,200,1100,200 );
    }

    private void setAnchorPane(AnchorPane anchorPane){
        this.anchorPane = anchorPane;
    }

    private void drawHeadingTitle(){
        this.gc.setLineWidth(3.0);
        this.gc.setFont(new Font(80));
        this.gc.strokeText("Research", 500.0, 70.0);
    }
    private void setCanvasBackground(){
        this.gc.setFill(Color.LIGHTGREY);
        this.gc.fillRect(0,0,1300,800);
    }

    private void drawBackgroundStyling(){
        this.gc.setFill(Color.TEAL);
        this.gc.fillRoundRect(75,100,1140,400,20,20);
    }

    private void drawPhase(){
        this.gc.setFill(Color.TEAL);
        this.gc.fillRoundRect(780,520,400,250,20,20);
        this.gc.setLineWidth(1.0);
        this.gc.setFont(new Font(20));
        this.gc.strokeText("Current Phase: " + currentPhaseRenderInfo.getName(), 800, 550);
    }

    private void drawTransportList(){
        this.transporters = new ListView();
        this.transporters.setPrefHeight(190.0);
        this.transporters.setPrefWidth(335.0);
        this.anchorPane.getChildren().add(transporters);
        this.anchorPane.setLeftAnchor(transporters, 810.0);
        this.anchorPane.setTopAnchor(transporters, 560.0);
    }

    private void initializeButtons(){
        this.selectFirst = new Button();
        this.selectSecond = new Button();
        this.selectThird = new Button();
        this.selectFourth = new Button();
        this.selectFifth = new Button();
        this.selectSixth = new Button();
        this.selectSeventh = new Button();
        this.selectEighth = new Button();
        this.selectFirst.setText("Research");
        this.selectSecond.setText("Research");
        this.selectThird.setText("Research");
        this.selectFourth.setText("Research");
        this.selectFifth.setText("Research");
        this.selectSixth.setText("Research");
        this.selectSeventh.setText("Research");
        this.selectEighth.setText("Research");
    }

    private void addFirst(){
        this.anchorPane.getChildren().add(selectFirst);
        this.anchorPane.setLeftAnchor(selectFirst,150.0);
        this.anchorPane.setTopAnchor(selectFirst, 420.0);

    }
    private void addSecond(){
        this.anchorPane.getChildren().add(selectSecond);
        this.anchorPane.setLeftAnchor(selectSecond,280.0);
        this.anchorPane.setTopAnchor(selectSecond, 420.0);

    }
    private void addThird(){
        this.anchorPane.getChildren().add(selectThird);
        this.anchorPane.setLeftAnchor(selectThird,420.0);
        this.anchorPane.setTopAnchor(selectThird, 420.0);

    }
    private void addFourth(){
        this.anchorPane.getChildren().add(selectFourth);
        this.anchorPane.setLeftAnchor(selectFourth,550.0);
        this.anchorPane.setTopAnchor(selectFourth, 420.0);
    }
    private void addFifth(){
        this.anchorPane.getChildren().add(selectFifth);
        this.anchorPane.setLeftAnchor(selectFifth,690.0);
        this.anchorPane.setTopAnchor(selectFifth, 420.0);
    }
    private void addSixth(){
        this.anchorPane.getChildren().add(selectSixth);
        this.anchorPane.setLeftAnchor(selectSixth,820.0);
        this.anchorPane.setTopAnchor(selectSixth, 420.0);
    }
    private void addSeventh(){
        this.anchorPane.getChildren().add(selectSeventh);
        this.anchorPane.setLeftAnchor(selectSeventh,950.0);
        this.anchorPane.setTopAnchor(selectSeventh,420.0);
    }
    private void addEight(){
        this.anchorPane.getChildren().add(selectEighth);
        this.anchorPane.setLeftAnchor(selectEighth, 1080.0);
        this.anchorPane.setTopAnchor(selectEighth, 420.0);
    }
    private void addStoneOne(){
        this.stoneOne = new ImageView();
        this.stoneOne.setImage(assets.GLASS_STONE);
        this.anchorPane.getChildren().add(stoneOne);
        this.anchorPane.setLeftAnchor(stoneOne,135.0);
        this.anchorPane.setTopAnchor(stoneOne, 250.0);
        this.pathTransition1 = setPathTransition(setPath(50,50));
        this.pathTransition1.setNode(stoneOne);
        this.selectFirst.setOnMouseClicked(event ->{
            pathTransition1.play();
            anchorPane.getChildren().remove(event.getSource());
        });
    }

    private void addStoneTwo(){
        this.stoneTwo = new ImageView();
        this.stoneTwo.setImage(assets.GLASS_STONE);
        this.anchorPane.getChildren().add(stoneTwo);
        this.anchorPane.setLeftAnchor(stoneTwo,260.0);
        this.anchorPane.setTopAnchor(stoneTwo, 250.0);
        this.pathTransition2 = setPathTransition(setPath(50,50));
        this.pathTransition2.setNode(stoneTwo);
        this.selectSecond.setOnMouseClicked(event ->{
            pathTransition2.play();
            anchorPane.getChildren().remove(event.getSource());
        });
    }

    private void addStoneThree(){
        this.stoneThree = new ImageView();
        this.stoneThree.setImage(assets.GLASS_STONE);
        this.anchorPane.getChildren().add(stoneThree);
        this.anchorPane.setLeftAnchor(stoneThree,400.0);
        this.anchorPane.setTopAnchor(stoneThree, 250.0);
        this.pathTransition3 = setPathTransition(setPath(50,50));
        this.pathTransition3.setNode(stoneThree);
        this.selectThird.setOnMouseClicked(event ->{
            pathTransition3.play();
            anchorPane.getChildren().remove(event.getSource());
        });
    }

    private void addStoneFour(){
        this.stoneFour = new ImageView();
        this.stoneFour.setImage(assets.GLASS_STONE);
        this.anchorPane.getChildren().add(stoneFour);
        this.anchorPane.setLeftAnchor(stoneFour,530.0);
        this.anchorPane.setTopAnchor(stoneFour, 250.0);
        this.pathTransition4 = setPathTransition(setPath(50,50));
        this.pathTransition4.setNode(stoneFour);
        this.selectFourth.setOnMouseClicked(event ->{
            pathTransition4.play();
            anchorPane.getChildren().remove(event.getSource());
        });
    }

    private void addStoneFive(){
        this.stoneFife = new ImageView();
        this.stoneFife.setImage(assets.GLASS_STONE);
        this.anchorPane.getChildren().add(stoneFife);
        this.anchorPane.setLeftAnchor(stoneFife,670.0);
        this.anchorPane.setTopAnchor(stoneFife, 250.0);
        this.pathTransition5 = setPathTransition(setPath(50,50));
        this.pathTransition5.setNode(stoneFife);
        this.selectFifth.setOnMouseClicked(event ->{
            pathTransition5.play();
            anchorPane.getChildren().remove(event.getSource());
        });
    }

    private void addStoneSix(){
        this.stoneSix = new ImageView();
        this.stoneSix.setImage(assets.GLASS_STONE);
        this.anchorPane.getChildren().add(stoneSix);
        this.anchorPane.setLeftAnchor(stoneSix,800.0);
        this.anchorPane.setTopAnchor(stoneSix, 250.0);
        this.pathTransition6 = setPathTransition(setPath(50,50));
        this.pathTransition6.setNode(stoneSix);
        this.selectSixth.setOnMouseClicked(event ->{
            pathTransition6.play();
            anchorPane.getChildren().remove(event.getSource());
        });
    }

    private void addStoneSeven(){
        this.stoneSeven = new ImageView();
        this.stoneSeven.setImage(assets.GLASS_STONE);
        this.anchorPane.getChildren().add(stoneSeven);
        this.anchorPane.setLeftAnchor(stoneSeven,930.0);
        this.anchorPane.setTopAnchor(stoneSeven, 250.0);
        this.pathTransition7 = setPathTransition(setPath(50,50));
        this.pathTransition7.setNode(stoneSeven);
        this.selectSeventh.setOnMouseClicked(event ->{
            pathTransition7.play();
            anchorPane.getChildren().remove(event.getSource());
        });
    }

    private void addStoneEight(){
        this.stoneEight = new ImageView();
        this.stoneEight.setImage(assets.GLASS_STONE);
        this.anchorPane.getChildren().add(stoneEight);
        this.anchorPane.setLeftAnchor(stoneEight,1060.0);
        this.anchorPane.setTopAnchor(stoneEight, 250.0);
        this.pathTransition8 = setPathTransition(setPath(50,50));
        this.pathTransition8.setNode(stoneEight);
        this.selectEighth.setOnMouseClicked(event ->{
            pathTransition8.play();
            anchorPane.getChildren().remove(event.getSource());
        });
    }

    private Path setPath(int startX, int startY){
        Path path = new Path();
        path.getElements().add (new MoveTo (startX, startY));
        path.getElements().add (new LineTo(0, -100.0));
        return path;
    }

    private PathTransition setPathTransition(Path path){
        PathTransition pathTransition = new PathTransition();
        pathTransition.setDuration(Duration.millis(2000));
        pathTransition.setPath(path);
        pathTransition.setOrientation(PathTransition.OrientationType.ORTHOGONAL_TO_TANGENT);
        pathTransition.setCycleCount(1);
        pathTransition.setAutoReverse(false);
        return pathTransition;
    }

    private void removeAllButtons(){
        this.anchorPane.getChildren().remove(selectFirst);
        this.anchorPane.getChildren().remove(selectSecond);
        this.anchorPane.getChildren().remove(selectThird);
        this.anchorPane.getChildren().remove(selectFourth);
        this.anchorPane.getChildren().remove(selectFifth);
        this.anchorPane.getChildren().remove(selectSixth);
        this.anchorPane.getChildren().remove(selectSeventh);
        this.anchorPane.getChildren().remove(selectEighth);
    }

    public void addTech1EventFilter(EventHandler filter){
        this.selectFirst.addEventFilter(MouseEvent.MOUSE_CLICKED, filter);
    }
    public void addTech2EventFilter(EventHandler filter){
        this.selectSecond.addEventFilter(MouseEvent.MOUSE_CLICKED, filter);
    }
    public void addTech3EventFilter(EventHandler filter){
        this.selectThird.addEventFilter(MouseEvent.MOUSE_CLICKED, filter);
    }
    public void addTech4EventFilter(EventHandler filter){
        this.selectFourth.addEventFilter(MouseEvent.MOUSE_CLICKED, filter);
    }
    public void addTech5EventFilter(EventHandler filter){
        this.selectFifth.addEventFilter(MouseEvent.MOUSE_CLICKED, filter);
    }
    public void addTech6EventFilter(EventHandler filter){
        this.selectSixth.addEventFilter(MouseEvent.MOUSE_CLICKED, filter);
    }
    public void addTech7EventFilter(EventHandler filter){
        this.selectSeventh.addEventFilter(MouseEvent.MOUSE_CLICKED, filter);
    }
    public void addTech8EventFilter(EventHandler filter){
        this.selectEighth.addEventFilter(MouseEvent.MOUSE_CLICKED, filter);
    }


    @Override
    public void render() {

    }

    @Override
    public void updateTechInfo(TechRenderInfo techRenderInfo) {
        this.techRenderInfo = techRenderInfo;
        this.newData = true;
    }

    @Override
    public void updatePlayerInfo(PlayerRenderInfo playerRenderInfo) {
        this.currentPlayerRenderInfo = playerRenderInfo;
        this.newData = true;
    }

    @Override
    public void updatePhaseInfo(PhaseRenderInfo phaseRenderInfo) {
        this.currentPhaseRenderInfo = phaseRenderInfo;
        this.newData = true;
        drawPhase();
    }
}
