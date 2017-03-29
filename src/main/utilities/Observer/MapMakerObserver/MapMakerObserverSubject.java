package utilities.Observer.MapMakerObserver;

import view.render.MapMakerRenderInfo;

public interface MapMakerObserverSubject {

    void registeMapMakerObserver(MapMakerObserver o);
    void removeMapMakerObserver(MapMakerObserver o);
    void notifyMapMakerObservers(MapMakerRenderInfo mapMakerRenderInfo);
}
