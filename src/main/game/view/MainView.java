package game.view;

import game.model.direction.Location;
import game.model.tile.Terrain;
import game.utilities.observer.*;
import game.view.render.*;
import game.view.utilities.RenderToImageConverter;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

import java.awt.image.ImageConsumer;
import java.util.Map;

public class MainView extends View implements TransportRenderInfoObserver, StructureRenderInfoObserver, ResourceRenderInfoObserver, MapRenderInfoObserver, CursorRenderInfoObserver, RoadRenderInfoObserver, WallRenderInfoObserver, CameraObserver{

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
    private Boolean newData;
    private RenderToImageConverter renderToImageConverter;
    private int cameraX;
    private int cameraY;
    private int imageX;
    private int imageY;

    public MainView(AnchorPane anchorPane){
        setAnchorPane(anchorPane);
        initializeRenderConverter();
        initailizeCanvas();
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
    }

    private void setupMapCanvas(){
        // color temporary, just for testing to see if properly divided
        this.mapCanvas = new Canvas(950,800);
        this.mapGC = this.mapCanvas.getGraphicsContext2D();
        this.mapGC.setFill(Color.AQUA);
        this.mapGC.fillRect(0,0,950,800);
        this.anchorPane.getChildren().add(mapCanvas);
        this.anchorPane.setLeftAnchor(mapCanvas,0.0);
    }

    private void setupSelectorCanvas(){
        // color temporary, just for testing to see if properly divided
        this.selectCanvas = new Canvas(350, 800);
        this.selectGC = this.selectCanvas.getGraphicsContext2D();
        this.selectGC.setFill(Color.BEIGE);
        this.selectGC.fillRect(0,0,350,800);
        this.anchorPane.getChildren().add(selectCanvas);
        this.anchorPane.setLeftAnchor(selectCanvas,950.0);
    }

    private void initializeRenderConverter(){
        this.renderToImageConverter = new RenderToImageConverter(assets.getInstance());
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
            mapGC.drawImage(image,imageX*xx*0.75+cameraX,(imageY*((yy)) + offset +offsetVertical + cameraY), imageX,imageY); // x, y
        }
    }

    private void drawMap(){

        if(mapRenderInfo == null){
            // no information yet
        } else {

            for (Map.Entry<Location, Terrain> entry : mapRenderInfo.getTerrainMap().entrySet())
            {
                // first time around we just render the terrain
                Image image = this.renderToImageConverter.getTerrainImage(entry.getValue());
                drawImage(image, entry.getKey().getX(), entry.getKey().getY(), entry.getKey().getZ());

                /*
                // second time around we draw the rivers
                Image riverImage = mmRenderToImageConverter.getRiverImage(entry.getValue(), mmAssets);
                if(riverImage != null && image != mmAssets.SEA){
                    drawImage(riverImage, entry.getKey().getX(), entry.getKey().getY(), entry.getKey().getZ());
                }
                */
            }
        }
    }

    public void addEventFilterToMainCanvas(EventType eventType, EventHandler filter){
        this.mapCanvas.setFocusTraversable(true);
        this.mapCanvas.addEventFilter(eventType, filter);
    }

    public void updateCameraInfo(CameraInfo cameraInfo){
        this.cameraX = cameraInfo.getCameraX();
        this.cameraY = cameraInfo.getCameraY();
        this.newData = true;
    }


    @Override
    public void render() {
        if(newData){
            // new data coming in
            this.mapGC.clearRect(0,0,950, 800);
            this.drawMap();
            this.newData = false;
        } else {
            // nothing to update
        }
    }
    @Override
    public void updateTransportInfo(TransportRenderInfo transportRenderInfo) {
        this.transportRenderInfo = transportRenderInfo;
        this.newData = true;
    }
    @Override
    public void updateStructureInfo(StructureRenderInfo structureRenderInfo) {
        this.structureRenderInfo = structureRenderInfo;
        this.newData = true;
    }
    @Override
    public void updateResourceInfo(ResourceRenderInfo resourceRenderInfo) {
        this.resourceRenderInfo = resourceRenderInfo;
        this.newData = true;
    }
    @Override
    public void updateMapInfo(MapRenderInfo mapRenderInfo) {
        System.out.println("map info coming in");
        this.mapRenderInfo = mapRenderInfo;
        this.newData = true;
        drawMap();
    }
    @Override
    public void updateRoadInfo(RoadRenderInfo roadRenderInfo) {
        this.roadRenderInfo = roadRenderInfo;
        this.newData = true;
    }
    @Override
    public void updateWallInfo(WallRenderInfo wallRenderInfo) {
        this.wallRenderInfo = wallRenderInfo;
        this.newData = true;
    }
    @Override
    public void updateCursorInfo(CursorRenderInfo cursorRenderInfo) {
        this.cursorRenderInfo = cursorRenderInfo;
        this.newData = true;
    }
    @Override
    public void updateCamera(CameraInfo cameraInfo) {
        this.cameraX = cameraInfo.getCameraX();
        this.cameraY = cameraInfo.getCameraY();
        this.newData = true;
    }
}
