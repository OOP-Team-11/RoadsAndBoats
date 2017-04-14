package game.view.render;

import game.model.direction.Location;

public class CursorRenderInfo {

    // location offset information
    private double clickX;
    private double clickY;
    private boolean menuClicked;
    // currently clicked cursor location
    private Location cursorLocation;

    public CursorRenderInfo(double clickX, double clickY, Location cursorLocation, boolean menuClicked){
        this.clickX = clickX;
        this.clickY = clickY;
        this.cursorLocation = cursorLocation;
        this.menuClicked = menuClicked;
    }

    public boolean isMenuClicked() { return this.menuClicked; }
    public Location getCursorLocation() { return this.cursorLocation; }
    public double getClickX(){
        return this.clickX;
    }
    public double getClickY(){
        return this.clickY;
    }
}
