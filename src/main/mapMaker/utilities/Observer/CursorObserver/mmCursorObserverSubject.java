package mapMaker.utilities.Observer.CursorObserver;

import mapMaker.view.render.mmMapMakerCursorInfo;

public interface mmCursorObserverSubject {
    void registerCursorObserver(mmCursorObserver o);
    void removeCursorObserver(mmCursorObserver o);
    void notifyCursorObservers(mmMapMakerCursorInfo mmMapMakerCursorInfo);
}
