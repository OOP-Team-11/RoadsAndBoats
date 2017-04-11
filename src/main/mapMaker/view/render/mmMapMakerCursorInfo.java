package mapMaker.view.render;

import mapMaker.model.tile.mmLocation;

public class mmMapMakerCursorInfo {
    private mmLocation cursorMmLocation;
    private boolean isCursorValid;
    private int cameraX;
    private int cameraY;

    public mmMapMakerCursorInfo(mmLocation cursorMmLocation, boolean isValid ) {
        this.cursorMmLocation = cursorMmLocation;
        this.isCursorValid = isValid;
        this.cameraX = 0;
        this.cameraY = 0;
    }

    public mmLocation getCursorMmLocation() {
        return cursorMmLocation;
    }
    public void setCursorMmLocation(mmLocation cursorMmLocation) {
        this.cursorMmLocation = cursorMmLocation;
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
