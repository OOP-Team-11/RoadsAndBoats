package mapMaker.utilities.Observer.MapMakerObserver;

import mapMaker.view.render.mmMapMakerRenderInfo;

public interface mmMapMakerObserverSubject {

    void registeMapMakerObserver(mmMapMakerObserver o);
    void removeMapMakerObserver(mmMapMakerObserver o);
    void notifyMapMakerObservers(mmMapMakerRenderInfo mmMapMakerRenderInfo);
}
