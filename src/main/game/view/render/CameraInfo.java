package game.view.render;

public class CameraInfo {
    private int cameraX;
    private int cameraY;
    public CameraInfo(int cameraX, int cameraY){
        this.cameraX = cameraX;
        this.cameraY = cameraY;
    }
    public int getCameraX(){
        return this.cameraX;
    }
    public int getCameraY(){
        return this.cameraY;
    }
}
