package game.view;

import game.utilities.observer.*;
import game.view.render.*;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;

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
    private int cameraX;
    private int cameraY;

    public MainView(AnchorPane anchorPane){
        setAnchorPane(anchorPane);
        initailizeCanvas();
    }
    private void setAnchorPane(AnchorPane anchorPane){
        this.anchorPane = anchorPane;
    }

    private void initailizeCanvas(){
        setupMapCanvas();
        setupSelectorCanvas();
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

    private void drawImage(Image image, int x, int y, int z){
        // first thing we want to do is get the axial coordinates
        int xx = x;
        int yy = z;
        if(xx%2 == 0){ // even
            double offsetHorizontal = image.getWidth()*xx*0.25;
            double offsetVertical = (image.getHeight())*(xx/2);
            mapGC.drawImage(image,image.getWidth()*xx-offsetHorizontal+cameraX,image.getHeight()*yy+offsetVertical+cameraY); // x, y
        } else {

            double offset = image.getHeight()*0.50;
            double offsetVertical = (image.getHeight())*((xx-1)/2);
            mapGC.drawImage(image,image.getWidth()*xx*0.75+cameraX,(image.getHeight()*((yy)) + offset +offsetVertical + cameraY)); // x, y
        }
    }

    @Override
    public void render() {

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
        this.mapRenderInfo = mapRenderInfo;
        this.newData = true;
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
