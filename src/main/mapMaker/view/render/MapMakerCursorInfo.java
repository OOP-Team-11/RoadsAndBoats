package mapMaker.view.render;

import mapMaker.model.tile.Location;

public class MapMakerCursorInfo {
    private Location cursorLocation;
    private boolean isCursorValid;
    private int cameraX;
    private int cameraY;

    public MapMakerCursorInfo(Location cursorLocation, boolean isValid ) {
        this.cursorLocation = cursorLocation;
        this.isCursorValid = isValid;
        this.cameraX = 0;
        this.cameraY = 0;
    }

    public Location getCursorLocation() {
        return cursorLocation;
    }
    public void setCursorLocation(Location cursorLocation) {
        this.cursorLocation = cursorLocation;
    }
    public boolean getIsCursorValid() {
        return isCursorValid;
    }
    public void setIsCursorValid(boolean isValid) {
        this.isCursorValid = isValid;
    }
    public void setCameraX(int camX) {
        this.cameraX = camX;
    }
    public int getCameraX() {
        return this.cameraX;
    }
    public void setCameraY(int camY) {
        this.cameraY = camY;
    }
    public int getCameraY() {
        return this.cameraY;
    }
}
