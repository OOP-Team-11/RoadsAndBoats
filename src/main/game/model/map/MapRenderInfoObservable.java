package game.model.map;

import game.utilities.observer.MapRenderInfoObserver;

public interface MapRenderInfoObservable {

    void attach(MapRenderInfoObserver observer);
    void detach(MapRenderInfoObserver observer);
}
