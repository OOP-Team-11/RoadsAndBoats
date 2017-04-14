package game.utilities.observable;

import game.utilities.observer.PlayerRenderInfoObserver;

public interface PlayerRenderInfoObservable {

    void attachPlayerInfoObserver(PlayerRenderInfoObserver observer);
    void detachPlayerInfoObserver(PlayerRenderInfoObserver observer);
}
