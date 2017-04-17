package game.model.wonder;

import game.model.PlayerId;
import game.utilities.observable.WonderRenderInfoObservable;
import game.utilities.observer.WonderPhaseEndedObserver;
import game.utilities.observer.WonderRenderInfoObserver;
import game.view.render.WonderRenderInfo;

import java.util.ArrayList;
import java.util.List;

public class WonderManager implements WonderPhaseEndedObserver, WonderRenderInfoObservable {

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

    private boolean haveNotIrrigated() {
        return !irrigationHasOcurred;
    }

    private void irrigate() {
        turnDesertToPasture(irrigatable);
        irrigationHasOcurred = true;
    }

    public void notifyWonderRenderInfoObservers() {
        WonderRenderInfo wonderRenderInfo = new WonderRenderInfo(this);
        for (WonderRenderInfoObserver observer : this.wonderRenderInfoObservers) {
            observer.updateWonderInfo(wonderRenderInfo);
        }
    }

    @Override
    public void attach(WonderRenderInfoObserver observer) {
        this.wonderRenderInfoObservers.add(observer);
    }

    @Override
    public void detach(WonderRenderInfoObserver observer) {
        this.wonderRenderInfoObservers.remove(observer);
    }

    @Override
    public void onWonderPhaseEnded() {
        for (TurnObserver observer : this.turnObservers) {
            observer.onTurnEnded();
        }

        if(wonder.isIrrigationPointActivated() && haveNotIrrigated()){
            irrigate();
        }

        notifyWonderRenderInfoObservers();
    }
}
