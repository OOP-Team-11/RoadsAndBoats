package mapMaker.utilities.Observer.CursorObserver;

import mapMaker.view.render.MapMakerCursorInfo;

public interface CursorObserverSubject {
    void registerCursorObserver(CursorObserver o);
    void removeCursorObserver(CursorObserver o);
    void notifyCursorObservers(MapMakerCursorInfo mapMakerCursorInfo);
}
