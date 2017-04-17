package game.utilities.observable;

import game.utilities.observer.BridgeRenderInfoObserver;

public interface BridgeRenderInfoObservable
{
    void attach(BridgeRenderInfoObserver observer);
    void detach(BridgeRenderInfoObserver observer);
}
