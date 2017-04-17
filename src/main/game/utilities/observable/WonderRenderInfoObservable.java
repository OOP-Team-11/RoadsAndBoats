package game.utilities.observable;

import game.utilities.observer.WonderRenderInfoObserver;

public interface WonderRenderInfoObservable {
    void attach(WonderRenderInfoObserver observer);
    void detach(WonderRenderInfoObserver observer);
}
