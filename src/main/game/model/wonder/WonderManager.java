package game.model.wonder;

import game.model.PlayerId;

import java.util.ArrayList;
import java.util.List;

public class WonderManager implements TurnObserver {

    private Wonder wonder;
    private Irrigatable irrigatable;
    private boolean irrigationHasOcurred = false;
    private List<TurnObserver> turnObservers;

    public int getBrickCost(PlayerId playerId){
        return wonder.getCurrentBrickCost(playerId);
    }

    public WonderManager(Irrigatable irrigatable){
        this.wonder = new Wonder();
        this.turnObservers = new ArrayList<>();
        this.turnObservers.add(wonder);
        this.irrigatable = irrigatable;
        this.wonder.setIrrigationPoint(new IrrigationPoint(10, 1));
    }

    public void turnDesertToPasture(Irrigatable irrigatable) {
        irrigatable.irrigate();
    }

    public int getWealthPoints(PlayerId playerId){
        return wonder.getWealthPoints(playerId);
    }

    public void addBrick(PlayerId playerId){
        wonder.addBrick(playerId);
        if(wonder.isIrrigationPointActivated() && haveNotIrrigated()){
            irrigate();
        }
    }

    public void addNeutralBrick() {
        wonder.addNeutralBrick();
        if(wonder.isIrrigationPointActivated() && haveNotIrrigated()){
            irrigate();
        }
    }

    @Override
    public void onTurnEnded(){
        for (TurnObserver observer : this.turnObservers) {
            observer.onTurnEnded();
        }

        if(wonder.isIrrigationPointActivated() && haveNotIrrigated()){
            irrigate();
        }
    }

    private boolean haveNotIrrigated() {
        return !irrigationHasOcurred;
    }

    private void irrigate() {
        turnDesertToPasture(irrigatable);
        irrigationHasOcurred = true;
    }

}
