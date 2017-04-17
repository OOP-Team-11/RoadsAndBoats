package game.model.wonder;

import game.model.Player;
import game.model.PlayerId;
import game.view.render.MapRenderInfo;

public class WonderManager {


    private Wonder wonder;
    private MapRenderInfo mapRenderInfo;
    public int getBrickCost(PlayerId playerId){
        return wonder.getCurrentBrickCost(playerId);
    }
    public WonderManager(MapRenderInfo mapRenderInfo, IrrigationPoint irrigationPoint){
        wonder = new Wonder();
        this.mapRenderInfo = mapRenderInfo;
        wonder.setIrrigationPoint(irrigationPoint);
    }
//
    public void turnDesertToPasture(MapRenderInfo mapRenderInfo) {
        wonder.updateMapInfo(mapRenderInfo);
    }
    public int getWealthPoints(PlayerId playerId){
        return wonder.getWealthPoints(playerId);
    }
    public void addBrick(PlayerId playerId){
        wonder.addBrick(playerId);
        if(wonder.isIrrigationPointActivated()){
            turnDesertToPasture(mapRenderInfo);
        }
    }
    public void onTurnEnded(){
        wonder.onTurnEnded();
        if(wonder.isIrrigationPointActivated()){
            turnDesertToPasture(mapRenderInfo);
        }
    }

}
