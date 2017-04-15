package game.utilities.observable;

import game.utilities.observer.PhaseRenderInfoObserver;

public interface PhaseRenderInfoObservable {

    void attachPhaseInfoObserver(PhaseRenderInfoObserver observer);
    void detachPhaseInfoObserver(PhaseRenderInfoObserver observer);
}
