package game.utilities.observable;

import game.utilities.observer.MapTransportRenderInfoObserver;

public interface MapTransportRenderInfoObservable {

    void attach(MapTransportRenderInfoObserver observer);
    void detach(MapTransportRenderInfoObserver observer);
}
