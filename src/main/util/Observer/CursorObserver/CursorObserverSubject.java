package util.Observer.CursorObserver;

import util.Observer.ObserverSubject;

public interface CursorObserverSubject extends ObserverSubject {
    void registerCursorObserver(CursorObserver o);
    void removeCursorObserver(CursorObserver o);
}
