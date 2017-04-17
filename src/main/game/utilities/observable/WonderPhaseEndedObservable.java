package game.utilities.observable;

import game.utilities.observer.WonderPhaseEndedObserver;

public interface WonderPhaseEndedObservable {
    void attach(WonderPhaseEndedObserver observer);
    void detach(WonderPhaseEndedObserver observer);
}
