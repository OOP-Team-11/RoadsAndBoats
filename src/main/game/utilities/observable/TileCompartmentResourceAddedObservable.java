package game.utilities.observable;

import game.utilities.observer.TileCompartmentResourceAddedObserver;

public interface TileCompartmentResourceAddedObservable {
    void attach(TileCompartmentResourceAddedObserver observer);
    void detach(TileCompartmentResourceAddedObserver observer);
}
