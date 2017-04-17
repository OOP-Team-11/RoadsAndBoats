package game.utilities.observable;

import game.utilities.observer.TileResourceInfoObserver;

public interface TileResourceInfoObservable {

    void attach(TileResourceInfoObserver observer);
    void detach(TileResourceInfoObserver observer);
}
