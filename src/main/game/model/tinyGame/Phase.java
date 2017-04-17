package game.model.tinyGame;

import game.utilities.observable.WonderPhaseEndedObservable;
import game.utilities.observer.WonderPhaseEndedObserver;

import java.util.ArrayList;
import java.util.List;

public class Phase implements WonderPhaseEndedObservable {

    private Integer currentPhase;
    private List<String> phases;
    private List<WonderPhaseEndedObserver> wonderPhaseEndedObservers;

    public Phase() {
        this.currentPhase = 0;
        this.phases = new ArrayList<>();
        this.wonderPhaseEndedObservers = new ArrayList<>();
        initializePhases();
    }

    private void initializePhases() {
        this.phases.add("Trading");
        this.phases.add("(Re)Production");
        this.phases.add("Movement");
        this.phases.add("Building");
        this.phases.add("Wonder");
    }

    private Integer getCurrentPhase() {
        return this.currentPhase;
    }

    public String getCurrentPhaseName() {
        return this.phases.get(getCurrentPhase());
    }

    public void goToNextPhase() {
        if (getCurrentPhaseName().equals("Wonder")) notifyWonderPhaseEndedObservers();
        currentPhase = (currentPhase + 1) % phases.size();
    }


    private void notifyWonderPhaseEndedObservers() {
        for (WonderPhaseEndedObserver observer : this.wonderPhaseEndedObservers) {
            observer.onWonderPhaseEnded();
        }
    }

    @Override
    public void attach(WonderPhaseEndedObserver observer) {
        this.wonderPhaseEndedObservers.add(observer);
    }

    @Override
    public void detach(WonderPhaseEndedObserver observer) {
        this.wonderPhaseEndedObservers.remove(observer);
    }
}
