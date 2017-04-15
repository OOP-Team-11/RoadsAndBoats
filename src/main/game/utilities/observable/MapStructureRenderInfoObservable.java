package game.utilities.observable;

import game.utilities.observer.MapStructureRenderInfoObserver;

public interface MapStructureRenderInfoObservable {

    void attach(MapStructureRenderInfoObserver observer);
    void detach(MapStructureRenderInfoObserver observer);
}
