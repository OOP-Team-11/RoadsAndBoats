package game.view.render;

import game.model.direction.Location;

import java.util.ArrayList;
import java.util.HashMap;

public class WallRenderInfo {

    private HashMap<Location,ArrayList<WallInfo>> renderInformation;

    public WallRenderInfo(HashMap<Location,ArrayList<WallInfo>> renderInformation){
        this.renderInformation = renderInformation;
    }

    public HashMap<Location,ArrayList<WallInfo>> getRenderInformation(){
        return this.renderInformation;
    }
}
