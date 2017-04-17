package game.model.wonder;

import game.model.PlayerId;

public class WonderManager {


    private Wonder wonder;
    private Irrigatable irrigatable;
    public int getBrickCost(PlayerId playerId){
        return wonder.getCurrentBrickCost(playerId);
    }
    public WonderManager(Irrigatable irrigatable, IrrigationPoint irrigationPoint){
        wonder = new Wonder();
        this.irrigatable = irrigatable;
        wonder.setIrrigationPoint(irrigationPoint);
    }
//
    public void turnDesertToPasture(Irrigatable irrigatable) {
        irrigatable.irrigate();
    }
    public int getWealthPoints(PlayerId playerId){
        return wonder.getWealthPoints(playerId);
    }
    public void addBrick(PlayerId playerId){
        wonder.addBrick(playerId);
        if(wonder.isIrrigationPointActivated()){
            turnDesertToPasture(irrigatable);
        }
    }
    public void onTurnEnded(){
        wonder.onTurnEnded();
        if(wonder.isIrrigationPointActivated()){
            turnDesertToPasture(irrigatable);
        }
    }

}
