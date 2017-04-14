package game.utilities.observable;

import game.utilities.observer.MapRenderInfoObserver;

public interface MapRenderInfoObservable {

    void attach(MapRenderInfoObserver observer);
    void detach(MapRenderInfoObserver observer);
}
