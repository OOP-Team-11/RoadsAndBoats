package game.utilities.observable;

import game.utilities.observer.MapRenderInfoObserver;
import game.view.render.MapRenderInfo;

public interface MapRenderInfoObservable {

    void attach(MapRenderInfoObserver observer);
    void detach(MapRenderInfoObserver observer);
}
