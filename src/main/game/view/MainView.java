package game.view;

import game.model.direction.Location;
import game.model.tile.RiverConfiguration;
import game.model.tile.Terrain;
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
import javafx.scene.control.ListView;
import javafx.scene.control.Slider;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

import java.awt.image.ImageConsumer;
import java.util.Map;

public class MainView extends View
        implements TransportRenderInfoObserver,
        StructureRenderInfoObserver,
        ResourceRenderInfoObserver,
        MapRenderInfoObserver,
        CursorRenderInfoObserver,
        RoadRenderInfoObserver,
        WallRenderInfoObserver,
        CameraObserver,
        PlayerRenderInfoObserver,
        PhaseRenderInfoObserver {

    private AnchorPane anchorPane;
    private TransportRenderInfo transportRenderInfo;
    private StructureRenderInfo structureRenderInfo;
    private ResourceRenderInfo resourceRenderInfo;
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
    private int[] compartmentX = {30,68,10,90,30,68}; // for scaling resources/structures in compartments
    private int[] compartmentY = {10,10,45,45,80,80};
    private int cameraX;
    private int cameraY;
    private int imageX;
    private int imageY;
    private int verticalOffset;

    public MainView(AnchorPane anchorPane){
        setAnchorPane(anchorPane);
        initializeRenderConverter();
        initailizeCanvas();
        initializeOverlay();
        setZoomSlider();
        placeFinishButton();
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
        this.overlayMenu.setPrefWidth(120);
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

    private void setupSelectorCanvas(){
        // color temporary, just for testing to see if properly divided
        this.selectCanvas = new Canvas(350, 800);
        this.selectGC = this.selectCanvas.getGraphicsContext2D();
        this.selectGC.setFill(Color.TEAL);
        this.selectGC.fillRect(0,0,350,800);
        this.anchorPane.getChildren().add(selectCanvas);
        this.anchorPane.setLeftAnchor(selectCanvas,950.0);
    }

    private void initializeRenderConverter(){
        this.renderToImageConverter = new RenderToImageConverter(assets.getInstance());
    }

    private void updateSidePanel(){
        if(cursorRenderInfo != null){
            drawLargeSelectedTileOnSide(cursorRenderInfo.getCursorLocation());
        }
    }
    private boolean checkForNullTerrain(Terrain terrain){
        if(terrain == null){
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

    private void drawCompartmentImage(Image image, int x, int y, int z, int compartment){
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
    private void drawSideCompartmentImage( Image image, int x, int y, int z, int compartment){
        selectGC.drawImage(image, 30+compartmentX[compartment-1]*2.3, 100+compartmentY[compartment-1]*2.3, 280/5,250/5);
    }

    private void drawLargeSelectedTileOnSide(Location location){
        Terrain terrainType = mapRenderInfo.getTerrainMap().get(location);
        if(checkForNullTerrain(terrainType)){
            // white area selected
        } else {
            Image image = this.renderToImageConverter.getTerrainImage(terrainType);
            selectGC.drawImage(image, 30, 100, 280,250);
            RiverConfiguration riverConfiguration = mapRenderInfo.getRiverConfigurationMap().get(location);
            Image riverImage = this.renderToImageConverter.getRiverImage(riverConfiguration);
            if(!terrainType.equals(Terrain.SEA)){
                selectGC.drawImage(riverImage, 30, 100, 280,250);
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
            setOverLayOptions();
            displayOverlaySelect(cursorRenderInfo.getClickX(), cursorRenderInfo.getClickY());
        } else {
            closeOverlaySelect();
        }
    }

    private void setOverLayOptions(){
        // TODO, for the moment just random options, hook up later to actual options
        String[] data = {"Transport1", "Transport2", "Transport3", "Goose1"};
        ObservableList<String> items = FXCollections.observableArrayList(data);
        this.overlayMenu.setItems(items);
    }

    private void drawMap(){

        if(mapRenderInfo == null){
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
    private void drawCursor(){
        if(cursorRenderInfo != null){
            drawImage(assets.GREEN_CURSOR,cursorRenderInfo.getCursorLocation().getX(), cursorRenderInfo.getCursorLocation().getY(), cursorRenderInfo.getCursorLocation().getZ());
        }
    }
    public double getZoomSliderValue(){
        return this.zoomSlider.getValue();
    }

    public void addEventFilterToMainView(EventType eventType, EventHandler filter){
        this.anchorPane.setFocusTraversable(true);
        this.anchorPane.addEventFilter(eventType, filter);
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

    private void clearMapCanvas(){
        this.mapGC.clearRect(0,0,950, 800);
        this.mapGC.setFill(Color.LIGHTGREY);
        this.mapGC.fillRect(0,0,950,800);
    }
    private void clearNewDataFlag(){
        this.refresh = false;
    }

    private void TESTINGREMOVELATER(){

        // FOR TESTING Tile compartments
        drawCompartmentImage(assets.CLAY_GOODS,0,0,0,1);
        drawCompartmentImage(assets.CLAY_GOODS,0,0,0,2);
        drawCompartmentImage(assets.CLAY_GOODS,0,0,0,3);
        drawCompartmentImage(assets.CLAY_GOODS,0,0,0,4);
        drawCompartmentImage(assets.CLAY_GOODS,0,0,0,5);
        drawCompartmentImage(assets.CLAY_GOODS,0,0,0,6);

        drawSideCompartmentImage(assets.CLAY_GOODS,0,0,0,1);
        drawSideCompartmentImage(assets.CLAY_GOODS,0,0,0,2);
        drawSideCompartmentImage(assets.CLAY_GOODS,0,0,0,3);
        drawSideCompartmentImage(assets.CLAY_GOODS,0,0,0,4);
        drawSideCompartmentImage(assets.CLAY_GOODS,0,0,0,5);
        drawSideCompartmentImage(assets.CLAY_GOODS,0,0,0,6);
    }


    @Override
    public void render() {
        if(refresh){
            // new data coming in
            clearMapCanvas();
            drawMap();
            clearNewDataFlag();
            drawCursor();
            checkForOverlay();
            updateSidePanel();
            TESTINGREMOVELATER();
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
    public void updateStructureInfo(StructureRenderInfo structureRenderInfo) {
        this.structureRenderInfo = structureRenderInfo;
        this.refresh = true;
    }
    @Override
    public void updateResourceInfo(ResourceRenderInfo resourceRenderInfo) {
        this.resourceRenderInfo = resourceRenderInfo;
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
        this.drawPlayerName(playerRenderInfo.getName());
        this.refresh = true;
    }

    @Override
    public void updatePhaseInfo(PhaseRenderInfo phaseRenderInfo) {
        this.drawCurrentPhase(phaseRenderInfo.getName());
        this.refresh = true;
    }

}
