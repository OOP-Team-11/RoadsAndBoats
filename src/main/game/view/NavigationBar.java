package game.view;


import javafx.scene.canvas.Canvas;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import mapMaker.view.utilities.Assets;


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
        this.assets = assets.getInstance();
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
        this.canvas = new Canvas(100,800);
        this.canvas.getGraphicsContext2D().setFill(Color.TEAL);
        this.canvas.getGraphicsContext2D().fillRect(0,0,120,800);


        // TODO replace images, make them look like icons or something
        this.mainViewButton.setImage(assets.NAVIGATION_BAR_1);
        this.transportViewButton.setImage(assets.NAVIGATION_BAR_1);
        this.researchViewButton.setImage(assets.NAVIGATION_BAR_1);
        this.wonderViewButton.setImage(assets.NAVIGATION_BAR_1);
        this.saveLoadButton.setImage(assets.NAVIGATION_BAR_1);
        this.optionsViewButton.setImage(assets.NAVIGATION_BAR_1);
        this.mainViewButton.setFitHeight(75);
        this.mainViewButton.setFitWidth(75);
        this.transportViewButton.setFitWidth(75);
        this.transportViewButton.setFitHeight(75);
        this.researchViewButton.setFitHeight(75);
        this.researchViewButton.setFitWidth(75);
        this.wonderViewButton.setFitHeight(75);
        this.wonderViewButton.setFitWidth(75);
        this.saveLoadButton.setFitHeight(75);
        this.saveLoadButton.setFitWidth(75);
        this.optionsViewButton.setFitWidth(75);
        this.optionsViewButton.setFitHeight(75);
        this.anchorPane.setPrefWidth(120);
        this.anchorPane.setMaxWidth(120);
        this.anchorPane.setPrefHeight(800);
        this.anchorPane.setMaxHeight(800);
        this.anchorPane.getChildren().add(canvas);
        this.anchorPane.getChildren().add(mainViewButton);
        this.anchorPane.setTopAnchor(mainViewButton,50.0);
        this.anchorPane.setLeftAnchor(mainViewButton,15.0);
        this.anchorPane.getChildren().add(transportViewButton);
        this.anchorPane.setTopAnchor(transportViewButton,150.0);
        this.anchorPane.setLeftAnchor(transportViewButton,15.0);
        this.anchorPane.getChildren().add(researchViewButton);
        this.anchorPane.setTopAnchor(researchViewButton,250.0);
        this.anchorPane.setLeftAnchor(researchViewButton,15.0);
        this.anchorPane.getChildren().add(wonderViewButton);
        this.anchorPane.setTopAnchor(wonderViewButton,350.0);
        this.anchorPane.setLeftAnchor(wonderViewButton,15.0);
        this.anchorPane.getChildren().add(saveLoadButton);
        this.anchorPane.setTopAnchor(saveLoadButton,450.0);
        this.anchorPane.setLeftAnchor(saveLoadButton,15.0);
        this.anchorPane.getChildren().add(optionsViewButton);
        this.anchorPane.setTopAnchor(optionsViewButton,550.0);
        this.anchorPane.setLeftAnchor(optionsViewButton, 15.0);

    }
}
