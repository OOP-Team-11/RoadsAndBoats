package game.view;


import game.view.utilities.Assets;
import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;


public class NavigationBar {

    private AnchorPane anchorPane;
    private ViewHandler viewHandler;
    private ImageView mainViewButton;
    private ImageView transportViewButton;
    private ImageView researchViewButton;
    private ImageView wonderViewButton;
    private ImageView saveLoadButton;
    private ImageView optionsViewButton;
    private Assets assets;
    private Canvas canvas;


    public NavigationBar(ViewHandler viewHandler){
        setViewHandler(viewHandler);
        initializeAnchorPane();
        loadAssets();
        setupNavigationBar();
        setUpEventHandlers();
    }
    private void setViewHandler(ViewHandler viewHandler){
        this.viewHandler = viewHandler;
    }
    private void initializeAnchorPane(){
        this.anchorPane = new AnchorPane();
    }
    private void loadAssets(){
        this.assets = Assets.getInstance();
    }

    public AnchorPane getAnchorPaneReference(){
        return this.anchorPane;
    }

    private void setUpEventHandlers(){

        // jump to main View
        this.mainViewButton.setOnMouseClicked(event ->{
            viewHandler.jumpToMainView();
        });

        // jump to transportView
        this.transportViewButton.setOnMouseClicked(event ->{
            viewHandler.jumpToTransportView();
        });

        // jump to research view
        this.researchViewButton.setOnMouseClicked(event ->{
            viewHandler.jumpToResearchView();
        });

        // jump to wonder view
        this.wonderViewButton.setOnMouseClicked( event ->{
            viewHandler.jumptToWonderView();
        });

        // jump to saveLoad view
        this.saveLoadButton.setOnMouseClicked(event ->{
            viewHandler.jumpToSaveLoadView();
        });

        // jump to transport view
        this.transportViewButton.setOnMouseClicked(event ->{
            viewHandler.jumpToTransportView();
        });

        // jump to options view
        this.optionsViewButton.setOnMouseClicked(event ->{
            viewHandler.jumpToOptionsView();
        });

    }

    private void setupNavigationBar(){
        this.mainViewButton = new ImageView();
        this.transportViewButton = new ImageView();
        this.researchViewButton = new ImageView();
        this.wonderViewButton = new ImageView();
        this.saveLoadButton = new ImageView();
        this.optionsViewButton = new ImageView();
        this.canvas = new Canvas(80,800);
        this.canvas.getGraphicsContext2D().setFill(Color.TEAL);
        this.canvas.getGraphicsContext2D().fillRect(0,0,80,800);


        // TODO replace images, make them look like icons or something
        this.mainViewButton.setImage(assets.NAVIGATION_BAR_1);
        this.transportViewButton.setImage(assets.NAVIGATION_BAR_2);
        this.researchViewButton.setImage(assets.NAVIGATION_BAR_3);
        this.wonderViewButton.setImage(assets.NAVIGATION_BAR_4);
        this.saveLoadButton.setImage(assets.NAVIGATION_BAR_5);
        this.optionsViewButton.setImage(assets.NAVIGATION_BAR_6);
        this.mainViewButton.setFitHeight(55);
        this.mainViewButton.setFitWidth(55);
        this.transportViewButton.setFitWidth(55);
        this.transportViewButton.setFitHeight(55);
        this.researchViewButton.setFitHeight(55);
        this.researchViewButton.setFitWidth(55);
        this.wonderViewButton.setFitHeight(55);
        this.wonderViewButton.setFitWidth(55);
        this.saveLoadButton.setFitHeight(55);
        this.saveLoadButton.setFitWidth(55);
        this.optionsViewButton.setFitWidth(55);
        this.optionsViewButton.setFitHeight(55);
        this.anchorPane.setPrefWidth(80);
        this.anchorPane.setMaxWidth(80);
        this.anchorPane.setMinWidth(80);
        this.anchorPane.setPrefHeight(800);
        this.anchorPane.setMaxHeight(800);
        this.anchorPane.getChildren().add(canvas);
        this.anchorPane.getChildren().add(mainViewButton);
        this.anchorPane.setTopAnchor(mainViewButton,50.0);
        this.anchorPane.setLeftAnchor(mainViewButton,12.5);
        this.anchorPane.getChildren().add(transportViewButton);
        this.anchorPane.setTopAnchor(transportViewButton,125.0);
        this.anchorPane.setLeftAnchor(transportViewButton,15.0);
        this.anchorPane.getChildren().add(researchViewButton);
        this.anchorPane.setTopAnchor(researchViewButton,200.0);
        this.anchorPane.setLeftAnchor(researchViewButton,12.5);
        this.anchorPane.getChildren().add(wonderViewButton);
        this.anchorPane.setTopAnchor(wonderViewButton,275.0);
        this.anchorPane.setLeftAnchor(wonderViewButton,12.5);
        this.anchorPane.getChildren().add(saveLoadButton);
        this.anchorPane.setTopAnchor(saveLoadButton,350.0);
        this.anchorPane.setLeftAnchor(saveLoadButton,12.5);
        this.anchorPane.getChildren().add(optionsViewButton);
        this.anchorPane.setTopAnchor(optionsViewButton,425.0);
        this.anchorPane.setLeftAnchor(optionsViewButton, 12.5);

    }
}
