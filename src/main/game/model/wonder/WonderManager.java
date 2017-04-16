package game.model.wonder;

import game.model.Player;
import game.model.PlayerId;
import game.view.render.MapRenderInfo;

public class WonderManager {


    private Wonder wonder;
    public int getBrickCost(PlayerId playerId){
        return wonder.getCurrentBrickCost(playerId);
    }
//
    public void turnDesertToPasture(MapRenderInfo mapRenderInfo) {
        wonder.updateMapInfo(mapRenderInfo);
    }
    public int getWealthPoints(PlayerId playerId){
        return wonder.getWealthPoints(playerId);
    }

}
