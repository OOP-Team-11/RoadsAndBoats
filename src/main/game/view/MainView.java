package game.view;

import game.model.ability.Ability;
import game.model.direction.Location;
import game.model.direction.TileCompartmentLocation;
import game.model.resources.ResourceType;
import game.model.structures.StructureType;
import game.model.tile.RiverConfiguration;
import game.model.tile.Terrain;
import game.model.transport.TransportId;
import game.utilities.observer.*;
import game.view.render.*;
import game.view.utilities.RenderToImageConverter;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.geometry.Orientation;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.util.Callback;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainView extends View
        implements TransportRenderInfoObserver,
        MapStructureRenderInfoObserver,
        MapResourceRenderInfoObserver,
        MapRenderInfoObserver,
        CursorRenderInfoObserver,
        RoadRenderInfoObserver,
        WallRenderInfoObserver,
        CameraObserver,
        PlayerRenderInfoObserver,
        PhaseRenderInfoObserver,
        MapTransportRenderInfoObserver {

    private AnchorPane anchorPane;
    private TransportRenderInfo transportRenderInfo;
    private MapStructureRenderInfo mapStructureRenderInfo;
    private MapTransportRenderInfo mapTransportRenderInfoP1;
    private MapTransportRenderInfo mapTransportRenderInfoP2;
    private MapResourceRenderInfo mapResourceRenderInfo;
    private PlayerRenderInfo playerRenderInfo;
    private PhaseRenderInfo phaseRenderInfo;
    private MapRenderInfo mapRenderInfo;
    private RoadRenderInfo roadRenderInfo;
    private WallRenderInfo wallRenderInfo;
    private CursorRenderInfo cursorRenderInfo;
    private Canvas mapCanvas;
    private Canvas selectCanvas;
    private GraphicsContext mapGC;
    private GraphicsContext selectGC;
    private Boolean refresh;
    private RenderToImageConverter renderToImageConverter;
    private ListView overlayMenu;
    private Slider zoomSlider;
    private Button finishTurn;
    private Button resourceButton;
    private Button buildingButton;
    private Button abilitiesButton;
    private int[] compartmentX = {30,68,10,90,30,68}; // for scaling resources/structures in compartments
    private int[] compartmentY = {10,10,45,45,80,80};
    private int cameraX;
    private int cameraY;
    private int imageX;
    private int imageY;
    private int verticalOffset;
    private int currentDisplayState;
    private Location rightClickedLocation;
    private ArrayList<TransportId> transportIds;
    private Map<KeyCode, Ability> abilities;
    private ListView abilityList;

    public MainView(AnchorPane anchorPane){
        setAnchorPane(anchorPane);
        initializeRenderConverter();
        initailizeCanvas();
        initializeOverlay();
        setZoomSlider();
        placeFinishButton();
        initializeSelectButtons();
        placeStartingCursor();
    }
    private void setAnchorPane(AnchorPane anchorPane){
        this.anchorPane = anchorPane;
    }

    private void initailizeCanvas(){
        setupMapCanvas();
        setupSelectorCanvas();
        setupImageScale();
    }
    private void setupImageScale(){
        this.imageX = 128;
        this.imageY = 114;
        this.verticalOffset = 0;
    }

    private void setupMapCanvas(){
        // color temporary, just for testing to see if properly divided
        this.mapCanvas = new Canvas(950,800);
        this.mapGC = this.mapCanvas.getGraphicsContext2D();
        this.mapGC.fillRect(0,0,950,800);
        this.anchorPane.getChildren().add(mapCanvas);
        this.anchorPane.setLeftAnchor(mapCanvas,0.0);
    }
    private void initializeOverlay(){
        this.overlayMenu = new ListView();
        this.overlayMenu.setPrefWidth(125);
        this.overlayMenu.setPrefHeight(150);
        this.overlayMenu.setStyle(" -fx-font-size: 12pt");
    }

    private void setZoomSlider(){
        this.zoomSlider = new Slider(1,7,1);
        this.zoomSlider.setStyle("-fx-pref-height:200; -fx-control-inner-background: teal;");
        this.zoomSlider.setMajorTickUnit(1);
        this.zoomSlider.setShowTickLabels(true);
        this.zoomSlider.setShowTickMarks(true);
        this.zoomSlider.setOrientation(Orientation.VERTICAL);
        this.anchorPane.getChildren().add(zoomSlider);
        this.anchorPane.setTopAnchor(zoomSlider,550.0);
        this.anchorPane.setLeftAnchor(zoomSlider,50.0);
    }

    private void placeStartingCursor(){
        this.cursorRenderInfo = new CursorRenderInfo(0,0,new Location(0,0,0),false);

    }

    private void setupSelectorCanvas(){
        // color temporary, just for testing to see if properly divided
        this.selectCanvas = new Canvas(350, 800);
        this.selectGC = this.selectCanvas.getGraphicsContext2D();
        this.selectGC.setFill(Color.TEAL);
        this.selectGC.fillRect(0,0,350,800);
        this.anchorPane.getChildren().add(selectCanvas);
        this.anchorPane.setLeftAnchor(selectCanvas,950.0);
        this.currentDisplayState = 2;
    }

    private void initializeRenderConverter(){
        this.renderToImageConverter = new RenderToImageConverter(assets);
    }

    private void updateSidePanel(){
        if(cursorRenderInfo != null){
            drawLargeSelectedTileOnSide(cursorRenderInfo.getCursorLocation());
            drawTransportsOnLargeSideTile(cursorRenderInfo.getCursorLocation(), mapTransportRenderInfoP1);
            drawTransportsOnLargeSideTile(cursorRenderInfo.getCursorLocation(), mapTransportRenderInfoP2);
            drawGoodsOnLargeSideTile(cursorRenderInfo.getCursorLocation());
            drawBuildingsOnLargeSideTile(cursorRenderInfo.getCursorLocation());
        }
    }
    private boolean isNull(Object object){
        if(object == null){
            return true;
        }
        return false;
    }

    private void drawCurrentPhase(String phase){
        this.selectGC.setFont(new Font(20));
        this.selectGC.setLineWidth(2.0);
        this.selectGC.strokeText(phase, 25, 80);
    }

    private void drawPlayerName(String name){
        this.selectGC.setFont(new Font(30));
        this.selectGC.setLineWidth(2.0);
        this.selectGC.strokeText(name, 25, 40);
    }

    private void placeFinishButton(){
        this.finishTurn = new Button();
        this.finishTurn.setText("Finish");
        this.finishTurn.setFont(new Font(15));
        this.anchorPane.getChildren().add(finishTurn);
        this.anchorPane.setLeftAnchor(finishTurn, 1200.0);
        this.anchorPane.setTopAnchor(finishTurn, 40.0);
    }

    public void setZoom(int dimensionX, int dimensionY, int verticalOffset){
        this.zoomSlider.setValue((int)this.zoomSlider.getValue());
        this.imageX = dimensionX;
        this.imageY = dimensionY;
        this.verticalOffset = verticalOffset;
        this.refresh = true;
    }


    private void drawImage(Image image, int x, int y, int z){
        // first thing we want to do is get the axial coordinates
        int xx = x;
        int yy = z;
        if(xx%2 == 0){ // even
            double offsetHorizontal = imageX*xx*0.25;
            double offsetVertical = imageY*(xx/2);
            mapGC.drawImage(image,imageX*xx-offsetHorizontal+cameraX,imageY*yy+offsetVertical+cameraY, imageX, imageY); // x, y
        } else {

            double offset = imageX*0.50;
            double offsetVertical = imageY*((xx-1)/2)-7;
            mapGC.drawImage(image,imageX*xx*0.75+cameraX,(imageY*((yy)) + offset +offsetVertical + cameraY + verticalOffset ), imageX,imageY); // x, y
        }
    }


    private void drawCompartmentSmallImage(Image image, int x, int y, int z, int compartment){
        // first thing we want to do is get the axial coordinates
        int xx = x;
        int yy = z;
        int sliderZ = (int)this.zoomSlider.getValue();
        if(xx%2 == 0){ // even
            double offsetHorizontal = imageX*xx*0.25;
            double offsetVertical = imageY*(xx/2);
            mapGC.drawImage(image,imageX*xx-offsetHorizontal+cameraX + compartmentX[compartment-1]*sliderZ,imageY*yy+offsetVertical+cameraY + compartmentY[compartment-1]*sliderZ, imageX/5,imageY/5); // x, y
        } else {
            double offset = imageX*0.50;
            double offsetVertical = imageY*((xx-1)/2)-7;
            mapGC.drawImage(image,imageX*xx*0.75+cameraX + compartmentX[compartment-1]*sliderZ,(imageY*((yy)) + offset +offsetVertical + cameraY + verticalOffset + compartmentY[compartment-1]*sliderZ), imageX/5,imageY/5); // x, y
        }
    }

    private void drawCompartmentLargeImage(Image image, int x, int y, int z, int compartment){
        // first thing we want to do is get the axial coordinates

        int xx = x;
        int yy = z;
        // zoom factor
        int sliderZ = (int)this.zoomSlider.getValue();
        if(xx%2 == 0){ // even column
            double offsetHorizontal = imageX*xx*0.25;
            double offsetVertical = imageY*(xx/2);
            mapGC.drawImage(image,imageX*xx-offsetHorizontal+cameraX + compartmentX[compartment-1]*sliderZ,imageY*yy+offsetVertical+cameraY + compartmentY[compartment-1]*sliderZ, imageX/3.8,imageY/3.8); // x, y
        } else { // odd column
            double offset = imageX*0.50;
            double offsetVertical = imageY*((xx-1)/2)-7;
            mapGC.drawImage(image,imageX*xx*0.75+cameraX + compartmentX[compartment-1]*sliderZ,(imageY*((yy)) + offset +offsetVertical + cameraY + verticalOffset + compartmentY[compartment-1]*sliderZ), imageX/3.8,imageY/3.8); // x, y
        }

    }

    private void drawSideCompartmentGoodImage( Image image, int compartment){
        selectGC.drawImage(image, 15+compartmentX[compartment-1]*2.3, 92+compartmentY[compartment-1]*2.3, 280/5,250/5);
    }

    private void drawSideCompartmentLargeImage(Image image, int compartment){
        selectGC.drawImage(image,15+compartmentX[compartment-1]*2.3, 92+compartmentY[compartment-1]*2.3, 280/3.8,250/3.8 );
    }

    private void drawLargeSelectedTileOnSide(Location location){
        if (isNull(mapRenderInfo)) return;
        Terrain terrainType = mapRenderInfo.getTerrainMap().get(location);
        if(isNull(terrainType)){
            // white area selected
        } else {
            Image image = this.renderToImageConverter.getTerrainImage(terrainType);
            selectGC.drawImage(image, 20, 100, 300,250);
            RiverConfiguration riverConfiguration = mapRenderInfo.getRiverConfigurationMap().get(location);
            Image riverImage = this.renderToImageConverter.getRiverImage(riverConfiguration);
            if(!terrainType.equals(Terrain.SEA)){
                selectGC.drawImage(riverImage, 20, 100, 300,250);
            }
        }
    }

    private void drawTransportsOnLargeSideTile(Location location, MapTransportRenderInfo mapTransportRenderInfo){
        if(isNull(mapTransportRenderInfo)){
            // nothing to display
        } else {
            // itterate through map and find if any match location
            for (Map.Entry<TileCompartmentLocation, List<TransportRenderInfo>> entry : mapTransportRenderInfo.getTransports().entrySet()) {
                for(TransportRenderInfo renderInfo : entry.getValue()){
                    if(entry.getKey().getLocation().equals(location)){
                        int compartment = (entry.getKey().getTileCompartmentDirection().getAngle().getDegrees())/60;
                        Image image = null;
                        if(renderInfo.getOwner().getPlayerIdNumber() == 1){
                            image = renderToImageConverter.getBlueTransportImage(renderInfo.getTransportType());
                        } else {
                            image = renderToImageConverter.getRedTransportImage(renderInfo.getTransportType());
                        }
                        drawSideCompartmentLargeImage(image,compartment+1);
                    } else {
                        // location doesn't match up
                    }
                }
            }
        }
    }

    private void drawGoodsOnLargeSideTile(Location location){
        if(isNull(mapResourceRenderInfo)){
            // nothing to render
        } else {
            for ( Map.Entry<TileCompartmentLocation, Map<ResourceType, Integer>> entry : mapResourceRenderInfo.getResourceMap().entrySet())
            {
                if(entry.getKey().getLocation().equals(location)){
                    int compartment = (entry.getKey().getTileCompartmentDirection().getAngle().getDegrees())/60;
                    for ( HashMap.Entry<ResourceType, Integer> entry2 : entry.getValue().entrySet()){
                        Image image = renderToImageConverter.getResourceImage(entry2.getKey());
                        drawSideCompartmentGoodImage(image,compartment+1);
                    }
                } else {
                    // not same locaiton
                }
            }
        }
    }

    private void drawBuildingsOnLargeSideTile(Location location){
        if(isNull(mapStructureRenderInfo)){
            // nothing to render
        } else {
            for ( HashMap.Entry<TileCompartmentLocation,StructureRenderInfo> entry : mapStructureRenderInfo.getStructures().entrySet())
            {
                if(entry.getKey().getLocation().equals(location)){
                    int compartment = (entry.getKey().getTileCompartmentDirection().getAngle().getDegrees())/60;
                    Image image = renderToImageConverter.getStructureImage(entry.getValue().getStructureType());
                    drawSideCompartmentLargeImage(image,compartment+1);
                } else {
                    // not same locaiton
                }
            }
        }
    }

    private void displayOverlaySelect(double xLocation, double yLocation){
        this.anchorPane.getChildren().add(overlayMenu);
        this.anchorPane.setLeftAnchor(overlayMenu, xLocation);
        this.anchorPane.setTopAnchor(overlayMenu, yLocation);
        this.overlayMenu.setFocusTraversable(true);
    }

    private void closeOverlaySelect(){
        this.anchorPane.getChildren().remove(overlayMenu);
    }

    private void checkForOverlay(){
        if(cursorRenderInfo != null && cursorRenderInfo.isMenuClicked()){
            closeOverlaySelect();
            if(playerRenderInfo.getPlayerID().getPlayerIdNumber() == 1){
                setOverLayOptions(mapTransportRenderInfoP1);
            } else {
                setOverLayOptions(mapTransportRenderInfoP2);
            }
            displayOverlaySelect(cursorRenderInfo.getClickX(), cursorRenderInfo.getClickY());
        } else {
            closeOverlaySelect();
        }
    }

    private void setOverLayOptions(MapTransportRenderInfo mapTransportRenderInfo){
        // first we get the location of cursor
        Location location = cursorRenderInfo.getCursorLocation();
        ArrayList<String> transporteres = new ArrayList<String>();
        this.transportIds = new ArrayList<>(); // clear
        if(isNull(location) || isNull(mapTransportRenderInfo)){
            // not valid location
        } else {
            this.rightClickedLocation = location; // saved in case it's changed after right clicking
            // itterate over transports and see if location
                for ( Map.Entry<TileCompartmentLocation, List<TransportRenderInfo>>  entry : mapTransportRenderInfo.getTransports().entrySet())
                {
                    for (TransportRenderInfo renderInfo : entry.getValue()) {
                        if(entry.getKey().getLocation().equals(location)){
                            // same location
                            transporteres.add(renderInfo.getTransportType().getName()); // append to options that will be displayed
                            transportIds.add(renderInfo.getTransportID()); // append to list that keeps track of IDs to return to model
                        } else {
                            // nope
                        }
                    }
                }
        }
        if(transporteres.size() == 0){
            transporteres.add(new String("No Transports"));
        }
        ObservableList<String> items = FXCollections.observableArrayList(transporteres);
        this.overlayMenu.setItems(items);

    }

    public TransportId getCurrentlySelectedTransportID(){
        int index = this.overlayMenu.getSelectionModel().getSelectedIndex();
        if(index == 0 && this.overlayMenu.getSelectionModel().getSelectedItems().get(0).equals("No Transports")){
            return null; // no transporter available on tile
        } else {
            return transportIds.get(index); // valid transport has been selected
        }
    }

    private void drawMap(){

        if(isNull(mapRenderInfo)){
            // no information yet
        } else {
            for (Map.Entry<Location, Terrain> entry : mapRenderInfo.getTerrainMap().entrySet())
            {
                // first time around we just render the terrain
                Image image = this.renderToImageConverter.getTerrainImage(entry.getValue());
                if(image != null){
                    drawImage(image, entry.getKey().getX(), entry.getKey().getY(), entry.getKey().getZ());
                }
            }
            for (Map.Entry<Location, RiverConfiguration> entry : mapRenderInfo.getRiverConfigurationMap().entrySet())
            {
                // second time around we draw the rivers
                Image riverImage = this.renderToImageConverter.getRiverImage(entry.getValue());
                if(!mapRenderInfo.getTerrainMap().get(entry.getKey()).equals(Terrain.SEA)){
                    drawImage(riverImage, entry.getKey().getX(), entry.getKey().getY(), entry.getKey().getZ());
                }
            }
        }
    }


    private void displayGoodsOnSidePanel(){
        this.selectGC.setLineWidth(5.0);
        this.selectGC.strokeLine(25,370,320,370);

        // first column of goods
        this.selectGC.drawImage(assets.BOARDS_GOODS,25,430);
        this.selectGC.drawImage(assets.CLAY_GOODS,25,490);
        this.selectGC.drawImage(assets.GOLD_GOODS,25,550);
        this.selectGC.drawImage(assets.COINS_GOODS,25,610);
        this.selectGC.drawImage(assets.FUEL_GOODS,25,670);
        this.selectGC.drawImage(assets.GOOSE_GOODS,25,730);

        // second column of goods
        this.selectGC.drawImage(assets.IRON_GOODS,175,430);
        this.selectGC.drawImage(assets.PAPER_GOODS,175,490);
        this.selectGC.drawImage(assets.STOCK_GOODS,175,550);
        this.selectGC.drawImage(assets.STONE_GOODS,175,610);
        this.selectGC.drawImage(assets.TRUNKS_GOODS,175,670);
    }

    private void displayGoodsCount(){
        this.selectGC.setFont(new Font(17));
        this.selectGC.setLineWidth(1);

        int boardCount = 0;
        int clayCount = 0;
        int goldCount = 0;
        int coinCount = 0;
        int fuelCount = 0;
        int geeseCount = 0;
        int ironCount = 0;
        int paperCount = 0;
        int stockCount = 0;
        int trunkCount = 0;

        if(isNull(mapResourceRenderInfo)){
            // nothing to render
        } else {
            for ( Map.Entry<TileCompartmentLocation, Map<ResourceType, Integer>> entry : mapResourceRenderInfo.getResourceMap().entrySet())
            {
                if(cursorRenderInfo.getCursorLocation().equals(entry.getKey().getLocation())){ // same location
                    for ( HashMap.Entry<ResourceType, Integer> entry2 : entry.getValue().entrySet()){
                        if(entry2.getKey().equals(ResourceType.BOARDS)){
                            boardCount = entry2.getValue();
                        } else if(entry2.getKey().equals(ResourceType.CLAY)){
                            clayCount = entry2.getValue();
                        } else if(entry2.getKey().equals(ResourceType.GOLD)){
                            goldCount = entry2.getValue();
                        } else if(entry2.getKey().equals(ResourceType.COINS)){
                            coinCount = entry2.getValue();
                        } else if(entry2.getKey().equals(ResourceType.FUEL)){
                            fuelCount = entry2.getValue();
                        } else if(entry2.getKey().equals(ResourceType.GOOSE)){
                            geeseCount = entry2.getValue();
                        } else if(entry2.getKey().equals(ResourceType.IRON)){
                            ironCount = entry2.getValue();
                        } else if(entry2.getKey().equals(ResourceType.PAPER)){
                            paperCount = entry2.getValue();
                        } else if(entry2.getKey().equals(ResourceType.STOCKBOND)){
                            stockCount = entry2.getValue();
                        } else if(entry2.getKey().equals(ResourceType.TRUNKS)){
                            trunkCount = entry2.getValue();
                        } else {

                        }
                    }
                } else {

                }
            }
        }

        this.selectGC.strokeText("Boards: " +boardCount,80,460 );
        this.selectGC.strokeText("Clay: " +clayCount,80,520 );
        this.selectGC.strokeText("Gold: " +goldCount,80,580 );
        this.selectGC.strokeText("Coin: " +coinCount,80,640 );
        this.selectGC.strokeText("Fuel: " +fuelCount,80,700 );
        this.selectGC.strokeText("Geese: " +geeseCount,80,760 );

        // second column of count
        this.selectGC.strokeText("Iron: " +ironCount,230,460 );
        this.selectGC.strokeText("Paper " +paperCount,230,520 );
        this.selectGC.strokeText("Stock: " +stockCount,230,580 );
        this.selectGC.strokeText("Stone: " +stockCount,230,640 );
        this.selectGC.strokeText("Trunks: " +trunkCount,230,700 );

    }

    private void displayBuildingsOnSidePanel(){
        this.selectGC.setLineWidth(5.0);
        this.selectGC.strokeLine(25,370,320,370);

        // column 1
        this.selectGC.drawImage(assets.CLAY_PIT_BUILDING,25,430, 52, 52);
        this.selectGC.drawImage(assets.STONE_FACTORY_BUILDING,25,490,52,52);
        this.selectGC.drawImage(assets.QUARRY_BUILDING,25,550,52,52);
        this.selectGC.drawImage(assets.WOODCUTTER_BUILDING,25,610,52,52);
        this.selectGC.drawImage(assets.SAWMILL_BUILDING,25,670,52,52);
        this.selectGC.drawImage(assets.COAL_BURNER_BUILDING,25,730,52,52);

        // column 2
        this.selectGC.drawImage(assets.PAPERMILL_BUILDING,125,430, 52, 52);
        this.selectGC.drawImage(assets.MINT_BUILDING,125,490,52,52);
        this.selectGC.drawImage(assets.STOCK_EXCHANGE_BUILDING,125,550,52,52);
        this.selectGC.drawImage(assets.OIL_RIG_BUILDING,125,610,52,52);
        this.selectGC.drawImage(assets.MINE_BUILDING,125,670,52,104);
        this.selectGC.drawImage(assets.MINE_AMOUNT_UNKOWN,125,670,52,18);

        // column 3
        this.selectGC.drawImage(assets.WAGON_FACTORY,225,430, 52, 52);
        this.selectGC.drawImage(assets.TRUCK_FACTORY,225,490,52,52);
        this.selectGC.drawImage(assets.RAFT_FACTORY,225,550,52,52);
        this.selectGC.drawImage(assets.ROWBOAT_FACTORY,225,610,52,52);
        this.selectGC.drawImage(assets.STEAMER_FACTORY,225,670,52,52);
    }

    private void displayBuildingCount(){
        this.selectGC.setFont(new Font(30));
        this.selectGC.setLineWidth(2.0);
        if(isNull(mapStructureRenderInfo)){
            // nothing to render
            this.selectGC.strokeText("No structure ", 50, 480);
        } else {
            Boolean drawnStructure = false;
            for ( HashMap.Entry<TileCompartmentLocation,StructureRenderInfo> entry : mapStructureRenderInfo.getStructures().entrySet()) {
                if (cursorRenderInfo.getCursorLocation().equals(entry.getKey().getLocation())) { // same location
                    Image image = renderToImageConverter.getStructureImage(entry.getValue().getStructureType());
                    this.selectGC.drawImage(image,125,500);
                    this.selectGC.strokeText(entry.getValue().getStructureType() + " ", 50, 480);
                    drawnStructure = true;

                } else {

                }
            }
            if(!drawnStructure){
                this.selectGC.strokeText("No structure ", 50, 480);
            }
        }
    }

    private void initializeSelectButtons(){
        this.buildingButton = new Button();
        this.resourceButton = new Button();
        this.abilitiesButton = new Button();
        this.buildingButton.setText("Buildings");
        this.buildingButton.setPrefWidth(80.0);
        this.buildingButton.setFont(new Font(14));
        this.resourceButton.setText("Goods");
        this.resourceButton.setPrefWidth(80.0);
        this.resourceButton.setFont(new Font(14));
        this.abilitiesButton.setText("Abilities");
        this.abilitiesButton.setFont(new Font(14));
        this.abilitiesButton.setPrefWidth(80.0);
        this.anchorPane.getChildren().add(buildingButton);
        this.anchorPane.getChildren().add(resourceButton);
        this.anchorPane.getChildren().add(abilitiesButton);
        this.anchorPane.setLeftAnchor(buildingButton, 980.0);
        this.anchorPane.setLeftAnchor(abilitiesButton, 1070.0);
        this.anchorPane.setLeftAnchor(resourceButton,1160.0);
        this.anchorPane.setTopAnchor(resourceButton,380.0);
        this.anchorPane.setTopAnchor(buildingButton,380.0);
        this.anchorPane.setTopAnchor(abilitiesButton,380.0);
        this.resourceButton.setOnMouseClicked(event ->{
            this.currentDisplayState = 1;
            this.anchorPane.getChildren().remove(abilityList);
            this.refresh = true;
        });

        this.buildingButton.setOnMouseClicked(event ->{
            this.currentDisplayState = 2;
            this.anchorPane.getChildren().remove(abilityList);
            this.refresh = true;
        });

        this.abilitiesButton.setOnMouseClicked(event ->{
            this.currentDisplayState = 3;
            this.refresh = true;
        });
    }
    private void removeAbilitesTable(){
        this.anchorPane.getChildren().remove(abilityList);
    }

    private void displaySidePanelInformation(){
        if(currentDisplayState == 1){ // structures
            displayGoodsOnSidePanel();
            displayGoodsCount();
            removeAbilitesTable();
        } else if(currentDisplayState == 2){ // goods
            displayBuildingCount();
            removeAbilitesTable();
        } else if(currentDisplayState == 3){ // abilities
            displayAbilitiesOnSidePanel();
        }
    }
    private void displayAbilitiesOnSidePanel(){
        if(isNull(abilities)){

        } else {
            this.anchorPane.getChildren().remove(abilityList);
            this.abilityList = new ListView();
            this.abilityList.setMaxHeight(350.0);
            this.anchorPane.getChildren().add(abilityList);
            this.anchorPane.setLeftAnchor(abilityList,1000.0);
            this.anchorPane.setTopAnchor(abilityList, 420.0);

            ArrayList<String> displayInfo = new ArrayList<>();

            for (Map.Entry<KeyCode, Ability> entry : abilities.entrySet()) {

                displayInfo.add("" +entry.getKey() + "    " +entry.getValue().getDisplayString() );
            }
            this.abilityList.setItems(FXCollections.observableList(displayInfo));

            abilityList.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
                @Override // way too complicated but this is what is needed to make the font bigger for each item in listView
                public ListCell<String> call(ListView<String> p) {
                    return new ListCell<String>() {
                        @Override
                        protected void updateItem(String item, boolean empty) {
                            super.updateItem(item, empty);
                            if (item != null) {
                                setText(item);
                                setFont(Font.font(22));
                            }
                        }
                    };
                }
            });
        }
    }


    private void drawCursor(){
        if(cursorRenderInfo != null){
            drawImage(assets.GREEN_CURSOR,cursorRenderInfo.getCursorLocation().getX(), cursorRenderInfo.getCursorLocation().getY(), cursorRenderInfo.getCursorLocation().getZ());
        }
    }
    public double getZoomSliderValue(){
        return this.zoomSlider.getValue();
    }

    public void addEventFilterToFinishButton(EventType eventType, EventHandler filter){
        this.finishTurn.addEventFilter(eventType, filter);
    }

    public void addEventFilterToMainView(EventType eventType, EventHandler filter){
        this.anchorPane.setFocusTraversable(true);
        this.anchorPane.addEventFilter(eventType, filter);
    }
    public void addEventFilterToRightClickMenu(EventType eventType, EventHandler filter){
        this.overlayMenu.addEventFilter(eventType,filter);
    }

    public void addEventFilterToZoomSlider(EventType eventType, EventHandler filter){
        this.zoomSlider.addEventFilter(eventType, filter);
    }

    public void updateCameraInfo(CameraInfo cameraInfo){
        this.cameraX = cameraInfo.getCameraX();
        this.cameraY = cameraInfo.getCameraY();
        this.refresh = true;
        this.anchorPane.getChildren().remove(overlayMenu); // close in case it might be open and we move camera
    }
    private void clearRightPanel(){
        this.selectGC.clearRect(0,0,350,800);
        this.selectGC.setFill(Color.TEAL);
        this.selectGC.fillRect(0,0,350,800);
    }
    private void drawPlayerName(){
        this.drawPlayerName(playerRenderInfo.getName());
    }
    private void drawCurrentPhase(){
        this.drawCurrentPhase(phaseRenderInfo.getName());
    }

    private void clearMapCanvas(){
        this.mapGC.clearRect(0,0,950, 800);
        this.mapGC.setFill(Color.LIGHTGREY);
        this.mapGC.fillRect(0,0,950,800);
    }
    private void clearNewDataFlag(){
        this.refresh = false;
    }

    private void drawBlueNorthWall(int x, int y, int z){
        drawImage(assets.WALL_BLUE_SOUTH,x,y,z-1);
        drawImage(assets.WALL_BLUE_NORTH,x,y,z);
    }

    private void drawBlueSouthWall(int x, int y, int z){
        drawImage(assets.WALL_BLUE_SOUTH,x,y,z);
        drawImage(assets.WALL_BLUE_NORTH,x,y,z+1);
    }
    private void drawBlueSEWall(int x, int y, int z){
        drawImage(assets.WALL_BLUE_SOUTHEAST,x,y,z);
        drawImage(assets.WALL_BLUE_NORTHWEST,x+1,y,z);
    }
    private void drawBlueNEWall(int x, int y, int z){
        drawImage(assets.WALL_BLUE_NORTHEAST,x,y,z);
        drawImage(assets.WALL_BLUE_SOUTHWEST,x+1,y,z-1);
    }
    private void drawBlueNWWall(int x, int y, int z){
        drawImage(assets.WALL_BLUE_NORTHWEST,x,y,z);
        drawImage(assets.WALL_BLUE_SOUTHEAST,x-1,y,z);
    }
    private void drawBlueSWWall(int x, int y, int z){
        drawImage(assets.WALL_BLUE_SOUTHWEST,x,y,z);
        drawImage(assets.WALL_BLUE_NORTHEAST,x-1,y,z+1);
    }

    private void drawNeutralNorthWall(int x, int y, int z){
        drawImage(assets.WALL_NEUTRAL_SOUTH,x,y,z-1);
        drawImage(assets.WALL_NEUTRAL_NORTH,x,y,z);
    }

    private void drawNeutralSouthWall(int x, int y, int z){
        drawImage(assets.WALL_NEUTRAL_SOUTH,x,y,z);
        drawImage(assets.WALL_NEUTRAL_NORTH,x,y,z+1);
    }
    private void drawNeutralNEWall(int x, int y, int z){
        drawImage(assets.WALL_NEUTRAL_NORTHEAST,x,y,z);
        drawImage(assets.WALL_NEUTRAL_SOUTHWEST,x+1,y,z-1);
    }
    private void drawNeutralNWWall(int x, int y, int z){
        drawImage(assets.WALL_NEUTRAL_NORTHWEST,x,y,z);
        drawImage(assets.WALL_NEUTRAL_SOUTHEAST,x-1,y,z);
    }
    private void drawNeutralSWWall(int x, int y, int z){
        drawImage(assets.WALL_NEUTRAL_SOUTHWEST,x,y,z);
        drawImage(assets.WALL_NEUTRAL_NORTHEAST,x-1,y,z+1);
    }
    private void drawNeutralSEWall(int x, int y, int z){
        drawImage(assets.WALL_NEUTRAL_SOUTHEAST,x,y,z);
        drawImage(assets.WALL_NEUTRAL_NORTHWEST,x+1,y,z);
    }

    private void drawREDNorthWall(int x, int y, int z){
        drawImage(assets.WALL_RED_SOUTH,x,y,z-1);
        drawImage(assets.WALL_RED_NORTH,x,y,z);
    }

    private void drawRedSouthWall(int x, int y, int z){
        drawImage(assets.WALL_RED_SOUTH,x,y,z);
        drawImage(assets.WALL_RED_NORTH,x,y,z+1);
    }

    private void drawRedSEWall(int x, int y, int z){
        drawImage(assets.WALL_RED_SOUTHEAST,x,y,z);
        drawImage(assets.WALL_RED_NORTHWEST,x+1,y,z);
    }
    private void drawRedNEWall(int x, int y, int z){
        drawImage(assets.WALL_RED_NORTHEAST,x,y,z);
        drawImage(assets.WALL_RED_SOUTHWEST,x+1,y,z-1);
    }
    private void drawRedNWWall(int x, int y, int z){
        drawImage(assets.WALL_RED_NORTHWEST,x,y,z);
        drawImage(assets.WALL_RED_SOUTHEAST,x-1,y,z);
    }
    private void drawRedSWWall(int x, int y, int z){
        drawImage(assets.WALL_RED_SOUTHWEST,x,y,z);
        drawImage(assets.WALL_RED_NORTHEAST,x-1,y,z+1);
    }


    private void TESTING_REMOVE_LATER(){


        // Walls not working 100%
        drawBlueSouthWall(1,0,0);
        drawBlueNorthWall(1,0,0);
        drawBlueNEWall(1,0,0);
        drawBlueSEWall(1,0,0);
        drawBlueSWWall(1,0,0);
        drawBlueNWWall(1,0,0);
       // drawNeutralNorthWall(1,0,0);



        drawRedSouthWall(-1,0,0);
        drawREDNorthWall(-1,0,0);
        drawRedSEWall(-1,0,0);
        drawRedNEWall(-1,0,0);
        drawRedNWWall(-1,0,0);
        drawRedSWWall(-1,0,0);

        drawNeutralNorthWall(-2,0,-2);
        drawNeutralNEWall(-2,0,-2);
        drawNeutralSEWall(-2,0,-2);
        drawNeutralSouthWall(-2,0,-2);
        drawNeutralSWWall(-2,0,-2);
        drawNeutralNWWall(-2,0,-2);


        //drawImage(assets.WALL_BLUE_NORTH,0,0,-1);
        //drawImage(assets.WALL_BLUE_SOUTH,0,0,-1);

    }

    private void displayMapTransportRenderInfo(MapTransportRenderInfo mapTransportRenderInfo){
        if(isNull(mapTransportRenderInfo)){
            // nothing to render
        } else {
            for ( Map.Entry<TileCompartmentLocation, List<TransportRenderInfo>>  entry : mapTransportRenderInfo.getTransports().entrySet())
            {
                for (TransportRenderInfo renderInfo : entry.getValue()) {

                    Image image;
                    if(renderInfo.getOwner().getPlayerIdNumber() == 1){
                        image = renderToImageConverter.getBlueTransportImage(renderInfo.getTransportType());
                    } else {
                        image = renderToImageConverter.getRedTransportImage(renderInfo.getTransportType());
                    }
                    int x = entry.getKey().getLocation().getX();
                    int y = entry.getKey().getLocation().getY();
                    int z = entry.getKey().getLocation().getZ();
                    int compartment = (entry.getKey().getTileCompartmentDirection().getAngle().getDegrees())/60;
                    drawCompartmentLargeImage(image,x,y,z,compartment+1);
                }
            }
        }
    }

    private void displayResourceRenderInfo(){
        if(isNull(mapResourceRenderInfo)){
            // nothing to render
        } else {
            for ( Map.Entry<TileCompartmentLocation, Map<ResourceType, Integer>> entry : mapResourceRenderInfo.getResourceMap().entrySet())
            {
                int x = entry.getKey().getLocation().getX();
                int y = entry.getKey().getLocation().getY();
                int z = entry.getKey().getLocation().getZ();
                int compartment = (entry.getKey().getTileCompartmentDirection().getAngle().getDegrees())/60;
                for ( HashMap.Entry<ResourceType, Integer> entry2 : entry.getValue().entrySet()){
                    Image image = renderToImageConverter.getResourceImage(entry2.getKey());
                    drawCompartmentSmallImage(image,x,y,z,compartment+1);
                }
            }
        }
    }
    private void displayStructureRenderInfo(){
        if(isNull(mapStructureRenderInfo)){
            // nothing to render
        } else {
            for ( Map.Entry<TileCompartmentLocation, StructureRenderInfo> entry : mapStructureRenderInfo.getStructures().entrySet())
            {
                Image image = renderToImageConverter.getStructureImage(entry.getValue().getStructureType());
                int x = entry.getKey().getLocation().getX();
                int y = entry.getKey().getLocation().getY();
                int z = entry.getKey().getLocation().getZ();
                int compartment = (entry.getKey().getTileCompartmentDirection().getAngle().getDegrees())/60;
                drawCompartmentLargeImage(image,x,y,z,compartment+1);
            }
        }
    }

    @Override
    public void render() {
        if(refresh){
            // new data coming in
            clearMapCanvas();
            clearRightPanel();
            drawMap();
            checkForOverlay();
            updateSidePanel();
            displaySidePanelInformation();
            drawPlayerName();
            drawCurrentPhase();

            displayMapTransportRenderInfo(mapTransportRenderInfoP1);
            displayMapTransportRenderInfo(mapTransportRenderInfoP2);
            displayResourceRenderInfo();
            displayStructureRenderInfo();

            clearNewDataFlag();
            TESTING_REMOVE_LATER();
            drawCursor(); // cursor is always last
        } else {
            // nothing to update
        }
    }

    @Override
    public void updateTransportInfo(TransportRenderInfo transportRenderInfo) {
        this.transportRenderInfo = transportRenderInfo;
        this.refresh = true;
    }
    @Override
    public void updateMapStructureInfo(MapStructureRenderInfo mapStructureRenderInfo) {
        this.mapStructureRenderInfo = mapStructureRenderInfo;
        this.refresh = true;
    }
    @Override
    public void updateMapResourceInfo(MapResourceRenderInfo mapResourceRenderInfo) {
        this.mapResourceRenderInfo = mapResourceRenderInfo;
        this.refresh = true;
    }
    @Override
    public void updateMapInfo(MapRenderInfo mapRenderInfo) {
        this.mapRenderInfo = mapRenderInfo;
        this.refresh = true;
    }
    @Override
    public void updateRoadInfo(RoadRenderInfo roadRenderInfo) {
        this.roadRenderInfo = roadRenderInfo;
        this.refresh = true;
    }
    @Override
    public void updateWallInfo(WallRenderInfo wallRenderInfo) {
        this.wallRenderInfo = wallRenderInfo;
        this.refresh = true;
    }
    @Override
    public void updateCursorInfo(CursorRenderInfo cursorRenderInfo) {
        this.cursorRenderInfo = cursorRenderInfo;
        this.refresh = true;
    }
    @Override
    public void updateCamera(CameraInfo cameraInfo) {
        this.cameraX = cameraInfo.getCameraX();
        this.cameraY = cameraInfo.getCameraY();
        this.refresh = true;
    }

    @Override
    public void updatePlayerInfo(PlayerRenderInfo playerRenderInfo) {
        this.playerRenderInfo = playerRenderInfo;
        this.refresh = true;
    }

    @Override
    public void updatePhaseInfo(PhaseRenderInfo phaseRenderInfo) {
        this.phaseRenderInfo = phaseRenderInfo;
        this.refresh = true;
    }

    @Override
    public void updateMapTransportInfo(MapTransportRenderInfo mapTransportRenderInfo) {

        if(mapTransportRenderInfo.getPlayerId().getPlayerIdNumber() == 1){
            this.mapTransportRenderInfoP1 = mapTransportRenderInfo;
        } else {
            this.mapTransportRenderInfoP2 = mapTransportRenderInfo;
        }
        this.refresh = true;
    }
    public void setAbilities(Map<KeyCode, Ability> abilities )
    {
        this.abilities = abilities;
    this.refresh = true;
    }

    public Location getRightClickedLocation() {
        return rightClickedLocation;
    }
}
