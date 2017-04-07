package mapMaker.controller;

import mapMaker.model.tile.Location;


// the purpose of this class is to map the x,y location of where the mouse was clicked to an x,y,z tile
// some really confusing geometry that will take a while to explain
public class MouseInterpreter {

    private int canvasWidth;
    private int canvasHeight;
    private double assetWidth;
    private double assetHeight;
    private double cameraOffsetX;
    private double cameraOffsetY;

    public MouseInterpreter(int canvasWidth, int canvasHeight, int assetHeight, int assetWidth){
        this.canvasWidth = canvasWidth;
        this.canvasHeight = canvasHeight;
        this.assetHeight = assetHeight;
        this.assetWidth = assetWidth;
        this.cameraOffsetX = 0;
        this.cameraOffsetY = 0;
    }

    public void updateCameraOffsetValues(int offsetX, int offSetY){
        this.cameraOffsetX = offsetX;
        this.cameraOffsetY = offSetY;
    }
    public void updateCanvasDimensions(int canvasX, int canvasY){
        this.canvasWidth = canvasX;
        this.canvasHeight = canvasY;
    }

    public Location interpretMouseClick(double x, double y){
        int xx = 0;
        int yy = 0;

        // get rid of recentering coefficient and any offset cause by moving the camera
        x = x -canvasWidth/2 - cameraOffsetX*5;
        y = y -canvasHeight/2 - cameraOffsetY*5;


        int xOffset = (int)(x/(assetWidth*1.5));
        int yOffset = (int)(y/assetHeight);

        if(x > 0){
            if(y > 0) {
                x = x - (Math.abs(xOffset)*assetWidth*1.5);
                y = y -(Math.abs(yOffset)*assetHeight);
                yy += Math.abs(yOffset)-xOffset;
            } else {
                x = x - (Math.abs(xOffset)*assetWidth*1.5);
                y = y +(Math.abs(yOffset)*assetHeight);
                y = assetHeight + y;
                yy -= Math.abs(yOffset)+xOffset+1;
            }
            xx += xOffset*2;
        } else {
            if(y > 0){
                x = x + (Math.abs(xOffset)*assetWidth*1.5);
                y = y -(Math.abs(yOffset)*assetHeight);
                x = assetWidth*1.5 +x;
                xx += xOffset *2 - 2;
                yy += yOffset + 1 + Math.abs(xOffset);
            } else {
                x = x + (Math.abs(xOffset)*assetWidth*1.5);
                y = y +(Math.abs(yOffset)*assetHeight);
                y = assetHeight + y;
                x = assetWidth*1.5 +x;
                xx -= Math.abs(xOffset)*2 + 2;
                yy -= Math.abs(yOffset) - Math.abs(xOffset) ;
            }
        }
        /*
                   ______
                #2/      \ #3
                 /   #5   \____
                 \        /
                #1\______/  #4
         */

        if(getSlope1(x, y) >= 1.5){
            xx--;  // Zone #1
            yy++;
        } else if(getSlope1(x,y) <= -1.5){
            xx--; // Zone #2
        } else if(getSlope2(x,y) >= 1.5 || (x > assetWidth && getSlope2(x,y) < 0)){
            xx++; // Zone #3
            yy--;
        } else if(getSlope2(x,y) <= -1.5 || (x > assetWidth) ){
            xx++; // Zone #4
        } else {
            // Zone #5
        }
        Location location = null;
        try{
            // convert from axial to cube
            location = new Location(xx,-xx-yy ,yy);
        } catch(Exception e){
            e.printStackTrace();
        }
        return location;
    }

    private double getSlope1( double x, double y){
        return ((assetHeight/2 - y)/(0 - x));
    }

    private double getSlope2( double x, double y){
        return ((assetHeight/2 - y)/(assetWidth - x));
    }

}
