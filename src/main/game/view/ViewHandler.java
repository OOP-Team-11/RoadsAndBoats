package game.view;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;

public class ViewHandler {

    private Scene scene;
    private MainView mainView;
    private OptionsView optionsView;
    private ResearchView researchView;
    private SaveLoadView saveLoadView;
    private TransportView transportView;
    private WonderView wonderView;
    private View activeView;
    private NavigationBar navigationBar;
    private AnchorPane primaryAnchorPane;
    private AnchorPane navigationBarAnchorPane;
    private AnimationTimer animationTimer;
    private HBox hbox;
    private AnchorPane mainViewAnchor;
    private AnchorPane optionsViewAnchor;
    private AnchorPane researchViewAnchor;
    private AnchorPane saveLoadAnchor;
    private AnchorPane transportAnchor;
    private AnchorPane wonderAnchor;


    public ViewHandler(Scene scene){
            setScene(scene);
            setUpGameWindow();
            setUpNavigationBar();
            initializeGameLoop();
    }

    private void setScene(Scene scene){
        this.scene = scene;
    }

    private void setUpGameWindow(){
        this.hbox = new HBox();
        this.scene.setRoot(hbox);
        this.navigationBar = new NavigationBar(this);
        this.mainViewAnchor = new AnchorPane();
        this.optionsViewAnchor = new AnchorPane();
        this.researchViewAnchor  = new AnchorPane();
        this.saveLoadAnchor = new AnchorPane();
        this.transportAnchor = new AnchorPane();
        this.wonderAnchor = new AnchorPane();
        this.mainView = new MainView(mainViewAnchor);
        this.optionsView = new OptionsView(optionsViewAnchor);
        this.transportView = new TransportView(transportAnchor);
        this.researchView = new ResearchView(researchViewAnchor);
        this.wonderView = new WonderView(wonderAnchor);
        this.saveLoadView = new SaveLoadView(saveLoadAnchor);
    }

    public void jumpToMainView(){
        this.activeView = mainView;
        this.hbox.getChildren().remove(1);
        this.hbox.getChildren().add(mainViewAnchor);
    }
    public void jumpToResearchView(){
        this.activeView = researchView;
        this.hbox.getChildren().remove(1);
        this.hbox.getChildren().add(researchViewAnchor);
    }
    public void jumpToTransportView(){
        this.activeView = transportView;
        this.hbox.getChildren().remove(1);
        this.hbox.getChildren().add(transportAnchor);
    }
    public void jumptToWonderView(){
        this.activeView = wonderView;
        this.hbox.getChildren().remove(1);
        this.hbox.getChildren().add(wonderAnchor);
    }
    public void jumpToSaveLoadView(){
        this.activeView = saveLoadView;
        this.hbox.getChildren().remove(1);
        this.hbox.getChildren().add(saveLoadAnchor);

    }
    public void jumpToOptionsView(){
        this.activeView = optionsView;
        this.hbox.getChildren().remove(1);
        this.hbox.getChildren().add(optionsViewAnchor);
    }

    private void setUpNavigationBar(){
        this.navigationBarAnchorPane = navigationBar.getAnchorPaneReference();
        this.primaryAnchorPane = new AnchorPane();
        this.hbox.getChildren().add(navigationBarAnchorPane);
        this.activeView = mainView;
        this.navigationBarAnchorPane.setMaxHeight(800);
        this.navigationBarAnchorPane.setPrefHeight(800);
        this.navigationBarAnchorPane.setMaxWidth(80);
        this.navigationBarAnchorPane.setPrefWidth(80);
        this.navigationBarAnchorPane.setMinWidth(80);
        this.hbox.getChildren().add(mainViewAnchor);
        this.hbox.setSpacing(0.0);
        // at the start of the game mainView is active
    }

    private void initializeGameLoop(){
        animationTimer = new AnimationTimer()
        {
            public void handle(long currentNanoTime)
            {
                activeView.render();
            }
        };
    }
    public void startGameLoop(){
        this.animationTimer.start();
    }

    public void stopGameloop(){
        this.animationTimer.stop();
    }


}
