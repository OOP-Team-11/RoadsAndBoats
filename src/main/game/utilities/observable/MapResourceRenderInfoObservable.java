package game.utilities.observable;

import game.utilities.observer.MapResourceRenderInfoObserver;

public interface MapResourceRenderInfoObservable {

    void attachMapResourceRenderInfoObserver(MapResourceRenderInfoObserver observer);
    void detachMapResourceRenderInfoObserver(MapResourceRenderInfoObserver observer);
}
