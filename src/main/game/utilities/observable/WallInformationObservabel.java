package game.utilities.observable;

import game.utilities.observer.WallRenderInfoObserver;

public interface WallInformationObservabel {
    void attach(WallRenderInfoObserver observer);
    void detach(WallRenderInfoObserver observer);
}
