package game.view.render;

import game.model.direction.Location;

import java.util.HashMap;

public class WallRenderInfo {

    private HashMap<Location,WallInfo> renderInformation;

    public WallRenderInfo(HashMap<Location,WallInfo> renderInformation){
        this.renderInformation = renderInformation;
    }

    public HashMap<Location,WallInfo> getRenderInformation(){
        return this.renderInformation;
    }
}
