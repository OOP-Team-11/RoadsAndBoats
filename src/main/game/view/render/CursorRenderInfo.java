package game.view.render;

public class CursorRenderInfo {

    // camera offset information
    private int cameraX;
    private int cameraY;

    public CursorRenderInfo(int cameraX, int cameraY){
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
