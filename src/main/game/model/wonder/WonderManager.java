package game.model.wonder;

import game.model.PlayerId;
import game.utilities.observable.WonderRenderInfoObservable;
import game.utilities.observer.WonderRenderInfoObserver;

import java.util.ArrayList;
import java.util.List;

public class WonderManager implements TurnObserver, WonderRenderInfoObservable {

    private Wonder wonder;
    private Irrigatable irrigatable;
    private boolean irrigationHasOcurred = false;
    private List<TurnObserver> turnObservers;
    private List<WonderRenderInfoObserver> wonderRenderInfoObservers;

    public int getBrickCost(PlayerId playerId){
        return wonder.getCurrentBrickCost(playerId);
    }

    public WonderManager(Irrigatable irrigatable){
        this.wonder = new Wonder();
        this.turnObservers = new ArrayList<>();
        this.turnObservers.add(wonder);
        this.irrigatable = irrigatable;
        this.wonder.setIrrigationPoint(new IrrigationPoint(10, 1));
        this.wonderRenderInfoObservers = new ArrayList<>();
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

    public List<WonderBrick> getOrderedWonderBricks() {
        return wonder.getOrderedWonderBricks();
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

    @Override
    public void attach(WonderRenderInfoObserver observer) {
        this.wonderRenderInfoObservers.add(observer);
    }

    @Override
    public void detach(WonderRenderInfoObserver observer) {
        this.wonderRenderInfoObservers.remove(observer);
    }
}
