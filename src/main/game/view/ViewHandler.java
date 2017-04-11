package game.view;

import javafx.animation.AnimationTimer;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.StackPane;

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
    private StackPane stackPane;
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
        this.stackPane = new StackPane();
        this.scene.setRoot(stackPane);
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
        this.primaryAnchorPane.getChildren().remove(1);
        this.primaryAnchorPane.getChildren().add(mainViewAnchor);
        this.primaryAnchorPane.setLeftAnchor(mainViewAnchor,100.0);
    }
    public void jumpToResearchView(){
        this.activeView = researchView;
        this.primaryAnchorPane.getChildren().remove(1);
        this.primaryAnchorPane.getChildren().add(researchViewAnchor);
        this.primaryAnchorPane.setLeftAnchor(researchViewAnchor,100.0);
    }
    public void jumpToTransportView(){
        this.activeView = transportView;
        this.primaryAnchorPane.getChildren().remove(1);
        this.primaryAnchorPane.getChildren().add(transportAnchor);
        this.primaryAnchorPane.setLeftAnchor(transportAnchor,100.0);
    }
    public void jumptToWonderView(){
        this.activeView = wonderView;
        this.primaryAnchorPane.getChildren().remove(1);
        this.primaryAnchorPane.getChildren().add(wonderAnchor);
        this.primaryAnchorPane.setLeftAnchor(wonderAnchor,100.0);
    }
    public void jumpToSaveLoadView(){
        this.activeView = saveLoadView;
        this.primaryAnchorPane.getChildren().remove(1);
        this.primaryAnchorPane.getChildren().add(saveLoadAnchor);
        this.primaryAnchorPane.setLeftAnchor(saveLoadAnchor,100.0);
    }
    public void jumpToOptionsView(){
        this.activeView = optionsView;
        this.primaryAnchorPane.getChildren().remove(1);
        this.primaryAnchorPane.getChildren().add(optionsViewAnchor);
        this.primaryAnchorPane.setLeftAnchor(optionsViewAnchor,100.0);
    }

    private void setUpNavigationBar(){
        this.navigationBarAnchorPane = navigationBar.getAnchorPaneReference();
        this.primaryAnchorPane = new AnchorPane();
        this.stackPane.getChildren().add(primaryAnchorPane);

        this.activeView = mainView;
        this.primaryAnchorPane.getChildren().add(navigationBarAnchorPane);
        this.navigationBarAnchorPane.setMaxHeight(800);
        this.navigationBarAnchorPane.setPrefHeight(800);
        this.navigationBarAnchorPane.setMaxWidth(150);
        this.navigationBarAnchorPane.setPrefWidth(150);
        this.stackPane.getChildren().add(mainViewAnchor);
        // at the start of the game mainView is active
        this.primaryAnchorPane.getChildren().add(mainViewAnchor);
        this.primaryAnchorPane.setLeftAnchor(mainViewAnchor,100.0);
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
