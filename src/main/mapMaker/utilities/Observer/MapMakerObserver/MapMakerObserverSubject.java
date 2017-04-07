package mapMaker.utilities.Observer.MapMakerObserver;

import mapMaker.view.render.MapMakerRenderInfo;

public interface MapMakerObserverSubject {

    void registeMapMakerObserver(MapMakerObserver o);
    void removeMapMakerObserver(MapMakerObserver o);
    void notifyMapMakerObservers(MapMakerRenderInfo mapMakerRenderInfo);
}
