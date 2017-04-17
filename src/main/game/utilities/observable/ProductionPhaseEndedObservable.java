package game.utilities.observable;

import game.utilities.observer.ProductionPhaseEndedObserver;

public interface ProductionPhaseEndedObservable {

    void attach(ProductionPhaseEndedObserver observer);
    void detach(ProductionPhaseEndedObserver observer);
}
