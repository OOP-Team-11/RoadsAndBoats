package game.model.wonder;

import game.model.Player;
import game.model.PlayerId;

public class WonderManager {


    private Wonder wonder;
    public int getBrickCost(PlayerId playerId){
        return wonder.getBrickCost(playerId);
    }
//
    public void turnDesertToPasture() {

    }
    public int getWealthPoints(PlayerId playerId){
        return wonder.getWealthPoints(playerId);
    }

}
